package com.company.fouthtask;

public class MyClassLoaderException extends RuntimeException {

    public MyClassLoaderException(String message) {
        super(message);
    }

    public MyClassLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
