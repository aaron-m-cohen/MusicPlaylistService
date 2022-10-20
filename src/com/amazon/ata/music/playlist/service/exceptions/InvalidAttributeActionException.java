package com.amazon.ata.music.playlist.service.exceptions;

public class InvalidAttributeActionException extends RuntimeException {

    private static final long serialVersionUID = 1012601786698202787L;
    public InvalidAttributeActionException() {
    }

    public InvalidAttributeActionException(String message) {
        super(message);
    }

    public InvalidAttributeActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAttributeActionException(Throwable cause) {
        super(cause);
    }
}
