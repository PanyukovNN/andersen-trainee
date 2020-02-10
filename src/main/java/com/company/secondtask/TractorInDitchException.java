package com.company.secondtask;

public class TractorInDitchException extends RuntimeException {

    public TractorInDitchException(String message) {
        super(message);
    }

    public TractorInDitchException(String message, Throwable cause) {
        super(message, cause);
    }
}
