package ua.com.cyberdone.accountmicroservice.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.cyberdone.accountmicroservice.common.exception.AccessDeniedException;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.AuthenticationException;
import ua.com.cyberdone.accountmicroservice.common.exception.InternalException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.common.exception.ValidationException;
import ua.com.cyberdone.accountmicroservice.dto.RestError;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequestMapping(value = "/error", method = RequestMethod.GET)
public class ExceptionHandlerController {
    public static final String BAD_REQUEST_MSG = "The request does not follow the correct syntax";
    public static final String INTERNAL_SERVER_ERROR_MSG = "There was an error processing the request.";
    public static final String NOT_FOUND_MSG = "Resource not found";
    public static final String ACCESS_DENIED_MSG = "Access denied";
    public static final String NO_CONTENT_MSG = "The resource is null or empty";
    public static final String METHOD_NOT_ALLOWED_MSG = "Operation with resource not allowed";
    public static final String CONFLICT_MSG = "Resource have a conflict with existing one in the system";
    public static final String UNAUTHORIZED_MSG = "You are unauthorized.";


    @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + NO_CONTENT_MSG + "\",\n" +
                    "   \"error\": \"204\",\n" +
                    "   \"exception\": \"NullPointerException\",\n" +
                    "   \"detail\": \"The resource is null or empty\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NullPointerException exception) {
        var error = RestError.builder()
                .error(HttpStatus.NO_CONTENT.getReasonPhrase())
                .title(NO_CONTENT_MSG)
                .detail(exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"ValidationException\",\n" +
                    "   \"detail\": \"Parameter 'token' must be not null.\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RestError> validationException(ValidationException exception) {
        var error = RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail("Validation failed. " + exception)
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"HttpClientErrorException\",\n" +
                    "   \"detail\": \"Clients request is of the wrong format. \",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<RestError> httpClientErrorException(HttpClientErrorException exception) {
        var error = RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail("Clients request is of the wrong format. " + exception.getMessage())
                .build();
        log.error("{}", error);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"MethodArgumentNotValidException\",\n" +
                    "   \"detail\": \"Method argument is invalid.\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentNotValidException exception) {
        var error = RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(String.format("Invalid parameters '%s'. %s",
                        exception.getBindingResult().getFieldErrors().stream()
                                .map(e -> "'" + e.getField() + "'->'" + e.getRejectedValue() + "'")
                                .collect(Collectors.toSet()), exception.getMessage()))
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"MethodArgumentTypeMismatchException\",\n" +
                    "   \"detail\": \"Method argument`s type is invalid.\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestError> httpClientErrorException(MethodArgumentTypeMismatchException exception) {
        var error = RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(String.format("Invalid url parameter '%s' has been sent. %s", exception.getName(),
                        exception.getMessage()))
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"MissingServletRequestParameterException\",\n" +
                    "   \"detail\": \"Request parameter is missing\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RestError> httpClientErrorException(MissingServletRequestParameterException exception) {
        var error = RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail("Request parameter is missing" + exception)
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"HttpMessageNotReadableException\",\n" +
                    "   \"detail\": \"Request parameter is missing\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestError> httpClientErrorException(HttpMessageNotReadableException exception) {
        var error = RestError.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .title(BAD_REQUEST_MSG)
                .detail(exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"AuthenticationException\",\n" +
                    "   \"detail\": \"Authentication failed: ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AuthenticationException exception) {
        var error = RestError.builder()
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .title(UNAUTHORIZED_MSG)
                .detail("Authentication failed: " + exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"ExpiredJwtException\",\n" +
                    "   \"detail\": \"JWT token is expired: ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(ExpiredJwtException exception) {
        var error = RestError.builder()
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .title(UNAUTHORIZED_MSG)
                .detail("JWT token is expired: " + exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"SignatureException\",\n" +
                    "   \"detail\": \"Bad JWT Signature:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<RestError> noHandlerFoundException(SignatureException exception) {
        var error = RestError.builder()
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .title(UNAUTHORIZED_MSG)
                .detail("Bad JWT Signature: " + exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"MalformedJwtException\",\n" +
                    "   \"detail\": \"Malformed Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(MalformedJwtException exception) {
        var error = RestError.builder()
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .title(UNAUTHORIZED_MSG)
                .detail("Malformed Jwt: " + exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"UnsupportedJwtException\",\n" +
                    "   \"detail\": \"Unsupported Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<RestError> noHandlerFoundException(UnsupportedJwtException exception) {
        var error = RestError.builder()
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .title(UNAUTHORIZED_MSG)
                .detail("Unsupported Jwt: " + exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + ACCESS_DENIED_MSG + "\",\n" +
                    "   \"error\": \"403\",\n" +
                    "   \"exception\": \"AccessDeniedException\",\n" +
                    "   \"detail\": \"You have no permission to access the resource\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AccessDeniedException exception) {
        var error = RestError.builder()
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .title(ACCESS_DENIED_MSG)
                .detail("You have no permission to access the resource ..." + exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + NOT_FOUND_MSG + "\",\n" +
                    "   \"error\": \"404\",\n" +
                    "   \"exception\": \"NoHandlerFoundException\",\n" +
                    "   \"detail\": \"Unsupported Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NoHandlerFoundException exception) {
        var error = RestError.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .title(NOT_FOUND_MSG)
                .detail("Resource not found for " + exception.getRequestURL())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + NOT_FOUND_MSG + "\",\n" +
                    "   \"error\": \"404\",\n" +
                    "   \"exception\": \"NotFoundException\",\n" +
                    "   \"detail\": \"Unsupported Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestError> noHandlerFoundException(NotFoundException exception) {
        var error = RestError.builder()
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .title(NOT_FOUND_MSG)
                .detail(exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + CONFLICT_MSG + "\",\n" +
                    "   \"error\": \"409\",\n" +
                    "   \"exception\": \"AlreadyExistException\",\n" +
                    "   \"detail\": \"Account already exists  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<RestError> noHandlerFoundException(AlreadyExistException exception) {
        var error = RestError.builder()
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .title(CONFLICT_MSG)
                .detail(exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + INTERNAL_SERVER_ERROR_MSG + "\",\n" +
                    "   \"error\": \"500\",\n" +
                    "   \"exception\": \"InternalException\",\n" +
                    "   \"detail\": \"Server have problems to process your request\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalException.class)
    public ResponseEntity<RestError> httpClientErrorException(InternalException exception) {
        var error = RestError.builder()
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .title(INTERNAL_SERVER_ERROR_MSG)
                .detail(exception.getMessage())
                .build();
        log.error("{}", error);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(
//            example = "{\n" +
//                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
//                    "   \"title\": \"INTERNAL_SERVER_ERROR\",\n" +
//                    "   \"error\": \"500\",\n" +
//                    "   \"exception\": \"Exception\",\n" +
//                    "   \"detail\": \"Server has problems to process your request\",\n" +
//                    "}")))
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<RestError> httpClientErrorException(Exception exception) {
//        return new ResponseEntity<>(RestError.builder()
//                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
//                .title(INTERNAL_SERVER_ERROR_MSG)
//                .detail(exception.getMessage())
//                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
