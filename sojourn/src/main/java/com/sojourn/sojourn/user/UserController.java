package com.sojourn.sojourn.user;

import com.sojourn.sojourn.auth.jwt.JWTUtil;
import com.sojourn.sojourn.exceptions.UserAlreadyExistsException;
import com.sojourn.sojourn.object.DataObject;
import com.sojourn.sojourn.auth.AuthenticationRequest;
import com.sojourn.sojourn.auth.AuthenticationResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signup")
    public @NotNull String signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest) throws UserAlreadyExistsException {
        logger.info("tried to create account with username {}", userSignUpRequest.getUsername());
        User userCreated = userService.createUserWith(userSignUpRequest);
        logger.info("successfully created account for user {}", userCreated.getId());
        return userCreated.getUsername();
    }

    @GetMapping("/data")
    public List<DataObject> getAllUserData(Principal principal){
        logger.info("tried to access all the data of user {}", principal.getName());
        return userService.getAllUserData(principal.getName());
    }
    @PostMapping("/data")
    public String insertUserData(Principal principal, @Valid @RequestBody Map<String, Object> dataObject){
        logger.info("tried to insert user data for user {}", principal.getName());
        return userService.insertDataObject(dataObject, principal.getName());
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationrequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationrequest.getUsername(), authenticationrequest.getPassword()));
        UserDetails userDetails = userService.loadUserByUsername(authenticationrequest.getUsername());
        return new AuthenticationResponse(jwtUtil.generateToken(userDetails));
    }
}
