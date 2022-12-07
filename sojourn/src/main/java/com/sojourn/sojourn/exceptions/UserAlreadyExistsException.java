package com.sojourn.sojourn.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(String username) {
        super("user with username "+username+" already exists");
    }
}
