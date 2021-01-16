package com.example.apipoc.exception;

/**
 * @author Parasuram
 */

/**
 * An Exception that can be thrown when duplicate widget is encountered
 */
public class DuplicateWidgetException extends RuntimeException {

    public DuplicateWidgetException(String message) {
        super(message);
    }
}
