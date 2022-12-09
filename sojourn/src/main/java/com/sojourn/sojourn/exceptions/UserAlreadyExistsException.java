package com.sojourn.sojourn.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UserAlreadyExistsException extends Exception{
    Logger logger = LoggerFactory.getLogger(UserAlreadyExistsException.class);

    public UserAlreadyExistsException(String username) {
        super();
        logger.error("user with username {} already exists", username);
    }
}
