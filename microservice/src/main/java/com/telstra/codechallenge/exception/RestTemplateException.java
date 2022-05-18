package com.telstra.codechallenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestTemplateException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String error;

    public RestTemplateException(HttpStatus statusCode, String error) {
        super(error);
        this.statusCode = statusCode;
        this.error = error;
    }

    @Override
    public String toString() {
        return "RestTemplateException{" +
                "statusCode=" + statusCode +
                ", error='" + error + '\'' +
                '}';
    }
}
