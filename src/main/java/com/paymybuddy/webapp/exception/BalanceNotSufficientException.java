package com.paymybuddy.webapp.exception;

/**
 * The Class BalanceNotSufficientException.
 */
public class BalanceNotSufficientException extends RuntimeException {

    /**
     * Instantiates a new balance not sufficient exception.
     *
     * @param message the message
     */
    public BalanceNotSufficientException(String message) {
        super(message);
    }
    
    
	// **************************** TODOs LIST ***********************************
	
	// Method: c
	// --> UserAlreadyExistException (is thrown when a new user tries to signup with existing email) 
	// --> UserNotFoundException (Is thrown when user tries to login with email not registered
	// --> 
	// --> Implemnatation of exception handler as in previous project
	// --> GlobalExceptionHandler @ControllerAdvice
	// --> 
	// --> https://springframework.guru/exception-handling-in-spring-boot-rest-api/
	// --> https://www.baeldung.com/exception-handling-for-rest-with-spring
	// --> https://stackoverflow.com/questions/28902374/spring-boot-rest-service-exception-handling
}