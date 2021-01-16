package com.example.apipoc.exception;

/**
 * An Exception that can be thrown when widget is not found
 * @author Parasuram
 *
 */
public class ResourceNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String exception) {
	        super(exception);
	    }
}
