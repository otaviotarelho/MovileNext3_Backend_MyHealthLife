package edu.otaviotarelho.users.controller.exception;

public class OperationNotSupportedByThisUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OperationNotSupportedByThisUserException(String msg) {
        super(msg);
    }

    public OperationNotSupportedByThisUserException(String msg, Throwable couse) {
        super(msg, couse);
    }

}
