package com.pirata.rest.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pirata.rest.exceptions.UserException;
import com.pirata.rest.model.User;
import com.pirata.rest.repository.UserRepository;
import com.pirata.rest.request.SessionRequest;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;


	public Optional<User> findById(long id){
		return userRepository.findById(id);
	}
  
	public Optional<User> findByUsername(String username){
		return userRepository.findByUsername(username);
	}

	public String addUser(User user){
		Optional<User> tmp = findByUsername(user.getUsername());
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		if(tmp.isPresent())
			throw new UserException("El username esta en uso");

		String encryptedPassword = bcrypt.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		User newuser = userRepository.save(user);

		System.out.println(newuser.getUsername());
		return newuser.getUsername();
	}
	
	private long checkPassword(User user){
		Optional<User> tmp = findByUsername(user.getUsername());
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		if(!tmp.isPresent())
			return -1;

		User dbUser = tmp.get();

		if(bcrypt.matches(user.getPassword(),dbUser.getPassword()))
			return dbUser.getId();
		else
			return -1;
	}

	public String authenticateUser(User user){
		if(checkPassword(user) == -1)
			throw new UserException("Not Authenticated");

		return "Authenticated";
	}

	public Optional<SessionRequest> sessionCreate(User user){
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		long userId = checkPassword(user);

		if(userId == -1)
			return Optional.ofNullable(null);

		String sessionId = UUID.randomUUID().toString();

		String sessionIdEncrypted = bcrypt.encode(sessionId);

		userRepository.updateSessionIdById(userId, sessionIdEncrypted);

		return Optional.of(new SessionRequest(userId,sessionId));
	}

	public Optional<User> sessionVerify(SessionRequest session){
		System.out.println(session.getSessionId() + " : " + session.getId());
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Optional<String> sessionIdDb = userRepository.selectSessionId(session.getId());

		if(!sessionIdDb.isPresent()) return Optional.ofNullable(null);

		String sessionID = sessionIdDb.get();

		if(sessionID.isEmpty()) return Optional.ofNullable(null);



		if(!bcrypt.matches(session.getSessionId(), sessionID))
			return Optional.ofNullable(null);

		User userDB = userRepository.findById(session.getId()).get();

		
		return Optional.of(userDB);
	}
}
