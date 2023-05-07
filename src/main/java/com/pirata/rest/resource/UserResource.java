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

import com.pirata.rest.exceptions.UserExistException;
import com.pirata.rest.model.User;
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

    /* 
    @RequestMapping(
        value = "/save",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    private ResponseEntity<String> add(User user){
        return new ResponseEntity<String>(userService.addUser(user),HttpStatus.CREATED);
    }
    */


    @PostMapping("/login")
    private ResponseEntity<String> authenticate(@RequestBody User user){
        return new ResponseEntity<String>(userService.authenticateUser(user),HttpStatus.OK);
    }

    @PostMapping("/session/new")
    private ResponseEntity<Optional<String>> sessionCreate(@RequestBody User user){
        return new ResponseEntity<Optional<String>>(userService.sessionCreate(user),HttpStatus.OK);
    }

    @PostMapping("/session/verify")
    private ResponseEntity<Optional<String>> sessionVerify(@RequestBody User user){
        return new ResponseEntity<Optional<String>>(userService.sessionVerify(user),HttpStatus.OK);
    }
}
