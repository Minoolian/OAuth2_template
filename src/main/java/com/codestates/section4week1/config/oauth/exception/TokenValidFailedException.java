package com.codestates.section4week1.config.oauth.exception;

public class TokenValidFailedException extends RuntimeException{

    public TokenValidFailedException() {
        super("Failed to generate Token.");
    }

    public TokenValidFailedException(String message) {
        super(message);
    }
}
