package com.company.exception;

/**
 * @author Sergey Bondar
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}