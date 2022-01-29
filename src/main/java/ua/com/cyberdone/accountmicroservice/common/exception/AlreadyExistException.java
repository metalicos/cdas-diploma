package ua.com.cyberdone.accountmicroservice.common.exception;

public class AlreadyExistException extends Exception {

    public AlreadyExistException(String message) {
        super(message);
    }
}
