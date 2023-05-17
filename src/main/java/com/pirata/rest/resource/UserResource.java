package com.pirata.rest.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RenderingResponse;

import com.pirata.rest.exceptions.UserException;
import com.pirata.rest.model.User;
import com.pirata.rest.request.SessionRequest;
import com.pirata.rest.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    private ResponseEntity<String> add(@RequestBody User user){
        return new ResponseEntity<String>(userService.addUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    private ResponseEntity<String> authenticate(@RequestBody User user){
        return new ResponseEntity<String>(userService.authenticateUser(user),HttpStatus.OK);
    }

    @PostMapping("/session/new")
    private ResponseEntity<Optional<SessionRequest>> sessionCreate(@RequestBody User user){
        return new ResponseEntity<Optional<SessionRequest>>(userService.sessionCreate(user),HttpStatus.OK);
    }

    @PostMapping("/session/verify")
    private ResponseEntity<Optional<User>> sessionVerify(@RequestBody SessionRequest session){
        return new ResponseEntity<Optional<User>>(userService.sessionVerify(session),HttpStatus.OK);
    }
}
