package com.art.jeanyvesart.exceptionHandler;

import lombok.Getter;

@Getter
public class UserAlreadyExistException extends Exception {
    private final String message;
    public UserAlreadyExistException(String message) {
        super(message);
        this.message=message;
    }

}
