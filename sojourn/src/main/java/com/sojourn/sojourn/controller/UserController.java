package com.sojourn.sojourn.controller;

import com.sojourn.sojourn.config.jwt.JWTUtil;
import com.sojourn.sojourn.exceptions.UserAlreadyExistsException;
import com.sojourn.sojourn.models.DataObject;
import com.sojourn.sojourn.models.User;
import com.sojourn.sojourn.models.UserSignUpRequest;
import com.sojourn.sojourn.models.dto.auth.AuthenticationRequest;
import com.sojourn.sojourn.models.dto.auth.AuthenticationResponse;
import com.sojourn.sojourn.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) throws UserAlreadyExistsException {
        logger.info("tried to create account with username {}", userSignUpRequest.getUsername());
        User userCreated = userService.createUserWith(userSignUpRequest);
        logger.info("successfully created account for user {}", userCreated.getId());
    }

    @GetMapping("/data")
    public List<DataObject> getAllUserData(Principal principal){
        logger.info("tried to access all the data of user {}", principal.getName());
        return userService.getAllUserData(principal.getName());
    }
    @PostMapping("/data")
    public void insertUserData(Principal principal, @Valid @RequestBody Map<String, Object> dataObject){
        logger.info("tried to insert user data for user {}", principal.getName());
        userService.insertDataObject(dataObject, principal.getName());
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationrequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authenticationrequest.getUsername());
        return new AuthenticationResponse(jwtUtil.generateToken(userDetails));
    }
}
