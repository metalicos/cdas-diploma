package ua.com.cyberdone.accountmicroservice.common.exception;

public class AuthenticationException extends Exception {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
