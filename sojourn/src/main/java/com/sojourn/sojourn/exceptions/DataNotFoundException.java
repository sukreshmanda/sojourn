package com.sojourn.sojourn.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class DataNotFoundException extends Exception{
    Logger logger = LoggerFactory.getLogger(DataNotFoundException.class);

    public DataNotFoundException(String id) {
        super();
        logger.error("Data with Id {} not found", id);
    }
}
