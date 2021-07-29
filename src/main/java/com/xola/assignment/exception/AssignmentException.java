package com.xola.assignment.exception;

public class AssignmentException extends RuntimeException {

    public AssignmentException() {
        super();
    }

    public AssignmentException(String message) {
        super(message);
    }

    public AssignmentException(Throwable cause) {
        super(cause);
    }
}
