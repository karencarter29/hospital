package com.gatewayapi.web.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomHttpError {
    HttpStatus httpStatus;
    String message;

    public CustomHttpError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
