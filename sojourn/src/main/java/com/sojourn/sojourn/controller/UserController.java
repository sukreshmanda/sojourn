package com.sojourn.sojourn.controller;

import com.sojourn.sojourn.exceptions.UserAlreadyExistsException;
import com.sojourn.sojourn.models.UserSignUpRequest;
import com.sojourn.sojourn.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public void signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) throws UserAlreadyExistsException {
        System.out.println("user request came"+ userSignUpRequest.toString());
        boolean userExists = userService.checkIfUserExists(userSignUpRequest.getUsername());
        if(!userExists){
            userService.createUserWith(userSignUpRequest);
        }else{
            throw new UserAlreadyExistsException(userSignUpRequest.getUsername());
        }
    }

}
