package com.example.demo.exception;

import java.io.Serial;

public class SkuNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public SkuNotFoundException(String message) {
        super(message);
    }

    public SkuNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
