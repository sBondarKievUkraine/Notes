package com.company.exception;

/**
 * @author Sergey Bondar
 */
public class NoteNotFoundException extends EntityNotFoundException {
    public NoteNotFoundException(String message) {
        super(message);
    }
}