package ua.com.cyberdone.accountmicroservice.common.exception;

public class AccessDeniedException extends Exception {

    public AccessDeniedException(String message) {
        super(message);
    }
}
