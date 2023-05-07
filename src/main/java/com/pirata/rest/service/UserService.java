package com.pirata.rest.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pirata.rest.exceptions.UserExistException;
import com.pirata.rest.model.User;
import com.pirata.rest.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

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

		if(!tmp.isPresent()){
			String encryptedPassword = bcrypt.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			User newuser = userRepository.save(user);

			System.out.println(newuser.getUsername());
			return newuser.getUsername();
		}

		throw new UserExistException("El username esta en uso");
	}
	
	private Boolean checkPassword(User user){
		Optional<User> tmp = findByUsername(user.getUsername());
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		if(tmp.isPresent()){
			User dbUser = tmp.get();

			if(bcrypt.matches(user.getPassword(),dbUser.getPassword())){
				return true;
			}else
				return false;
		}

		return false;
	}

	public String authenticateUser(User user){
		if(checkPassword(user)){
			return "Authenticated";
		}

		throw new UserExistException("Not Authenticated");
	}

	public Optional<String> sessionCreate(User user){
		if(checkPassword(user)){
			String sessionId = UUID.randomUUID().toString();

			userRepository.updateSessionIdById(user.getId(), sessionId);

			return Optional.of(sessionId);
		}

		return Optional.ofNullable(null);
	}

	public Optional<String> sessionVerify(User user){
		Optional<String> sessionIdDb = userRepository.selectSessionId(user.getId());

		if(!sessionIdDb.isPresent()) return Optional.ofNullable(null);

		String sessionId = sessionIdDb.get();

		if(sessionId == user.getSessionId())
			return Optional.of("verify");
		
		return Optional.of("expired");
	}
}
