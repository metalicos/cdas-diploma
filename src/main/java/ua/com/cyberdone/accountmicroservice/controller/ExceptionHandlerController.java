package ua.com.cyberdone.accountmicroservice.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.cyberdone.accountmicroservice.common.exception.AccessDeniedException;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.AuthenticationException;
import ua.com.cyberdone.accountmicroservice.common.exception.InternalException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.controller.docs.ExceptionHandlerApi;
import ua.com.cyberdone.accountmicroservice.dto.RestError;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@ControllerAdvice
@RequestMapping(value = "/error", method = RequestMethod.GET)
public class ExceptionHandlerController implements ExceptionHandlerApi {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NullPointerException exception) {
        return buildResponse(NO_CONTENT, NO_CONTENT_MSG, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RestError> validationException(ConstraintViolationException exception) {
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, exception.getConstraintViolations()
                .stream()
                .map(violation -> new StringBuilder()
                        .append("Value '")
                        .append(nonNull(violation.getInvalidValue()) ? violation.getInvalidValue().toString() : StringUtils.EMPTY)
                        .append("' is invalid.")
                        .append(nonNull(violation.getMessage()) ? (" Reason: " + violation.getMessage()) : StringUtils.EMPTY)
                        .toString())
                .collect(Collectors.toSet()).toString());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<RestError> httpClientErrorException(HttpClientErrorException exception) {
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, "Clients request is of the wrong format. " + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentNotValidException exception) {
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, String.format("Invalid parameters '%s'. %s",
                exception.getBindingResult().getFieldErrors().stream()
                        .map(e -> "'" + e.getField() + "'->'" + e.getRejectedValue() + "'")
                        .collect(Collectors.toSet()), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentTypeMismatchException exception) {
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, String.format("Invalid url parameter '%s' has been sent. %s",
                exception.getName(), exception.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestError> httpClientErrorException(MissingServletRequestParameterException exception) {
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, "Request parameter is missing" + exception);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestError> httpClientErrorException(HttpMessageNotReadableException exception) {
        return buildResponse(BAD_REQUEST, BAD_REQUEST_MSG, exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AuthenticationException exception) {
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Authentication failed: " + exception.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(ExpiredJwtException exception) {
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "JWT token is expired: " + exception.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<RestError> noHandlerFoundException(SignatureException exception) {
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Bad JWT Signature: " + exception.getMessage());
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(MalformedJwtException exception) {
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Malformed Jwt: " + exception.getMessage());
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(UnsupportedJwtException exception) {
        return buildResponse(UNAUTHORIZED, UNAUTHORIZED_MSG, "Unsupported Jwt: " + exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AccessDeniedException exception) {
        return buildResponse(FORBIDDEN, ACCESS_DENIED_MSG, "You have no permission to access the resource ..." + exception.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NoHandlerFoundException exception) {
        return buildResponse(NOT_FOUND, NOT_FOUND_MSG, "Resource not found for " + exception.getRequestURL());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NotFoundException exception) {
        return buildResponse(NOT_FOUND, NOT_FOUND_MSG, exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AlreadyExistException exception) {
        return buildResponse(CONFLICT, CONFLICT_MSG, exception.getMessage());
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<RestError> httpClientErrorException(InternalException exception) {
        return buildResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG, exception.getMessage());
    }
}
