package com.sojourn.sojourn.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessRestrictedException extends Exception{

    Logger logger = LoggerFactory.getLogger(AccessRestrictedException.class);

    public AccessRestrictedException(String resource, String userName) {
        super();
        logger.error("access restricted for user {} for the resource {}", userName, resource);
    }
}
