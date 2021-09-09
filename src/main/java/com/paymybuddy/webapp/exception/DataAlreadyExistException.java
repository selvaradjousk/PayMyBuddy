package com.paymybuddy.webapp.exception;

/**
 * The Class DataAlreadyExistException.
 * @author Senthil
 */
public class DataAlreadyExistException extends RuntimeException {

    /**
     * Instantiates a new data already exist exception.
     *
     * @param message the message
     */
    public DataAlreadyExistException(final String message) {
        super(message);
    }
}
