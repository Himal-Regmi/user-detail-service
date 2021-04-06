package com.regmi.userdetail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDetailNotFoundException extends RuntimeException{
    public UserDetailNotFoundException(String message) {
        super(message);
    }

    public UserDetailNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDetailNotFoundException(Throwable cause) {
        super(cause);
    }
}
