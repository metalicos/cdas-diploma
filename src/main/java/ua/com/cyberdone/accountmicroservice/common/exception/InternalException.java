package ua.com.cyberdone.accountmicroservice.common.exception;

public class InternalException extends RuntimeException {

    public InternalException(Exception e) {
        super(e);
    }
}
