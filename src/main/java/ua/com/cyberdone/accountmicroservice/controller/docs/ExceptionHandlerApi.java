package ua.com.cyberdone.accountmicroservice.controller.docs;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.cyberdone.accountmicroservice.common.exception.AccessDeniedException;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.AuthenticationException;
import ua.com.cyberdone.accountmicroservice.common.exception.InternalException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.RestError;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public interface ExceptionHandlerApi {
    String BAD_REQUEST_MSG = "The request does not follow the correct syntax";
    String INTERNAL_SERVER_ERROR_MSG = "There was an error processing the request.";
    String NOT_FOUND_MSG = "Resource not found";
    String ACCESS_DENIED_MSG = "Access denied";
    String NO_CONTENT_MSG = "The resource is null or empty";
    String CONFLICT_MSG = "Resource have a conflict with existing one in the system";
    String UNAUTHORIZED_MSG = "You are unauthorized.";


    @ApiResponse(responseCode = "204", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + NO_CONTENT_MSG + "\",\n" +
                    "   \"error\": \"204\",\n" +
                    "   \"exception\": \"NullPointerException\",\n" +
                    "   \"detail\": \"The resource is null or empty\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<RestError> noHandlerFoundException(NullPointerException exception);

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"ConstraintViolationException\",\n" +
                    "   \"detail\": \"Parameter 'token' must be not null.\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<RestError> validationException(ConstraintViolationException exception);

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"HttpClientErrorException\",\n" +
                    "   \"detail\": \"Clients request is of the wrong format. \",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<RestError> httpClientErrorException(HttpClientErrorException exception);

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"MethodArgumentNotValidException\",\n" +
                    "   \"detail\": \"Method argument is invalid.\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<RestError> httpClientErrorException(MethodArgumentNotValidException exception);

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"MethodArgumentTypeMismatchException\",\n" +
                    "   \"detail\": \"Method argument`s type is invalid.\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<RestError> httpClientErrorException(MethodArgumentTypeMismatchException exception);

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"MissingServletRequestParameterException\",\n" +
                    "   \"detail\": \"Request parameter is missing\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<RestError> httpClientErrorException(MissingServletRequestParameterException exception);

    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + BAD_REQUEST_MSG + "\",\n" +
                    "   \"error\": \"400\",\n" +
                    "   \"exception\": \"HttpMessageNotReadableException\",\n" +
                    "   \"detail\": \"Request parameter is missing\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<RestError> httpClientErrorException(HttpMessageNotReadableException exception);

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"AuthenticationException\",\n" +
                    "   \"detail\": \"Authentication failed: ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<RestError> noHandlerFoundException(AuthenticationException exception);

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"ExpiredJwtException\",\n" +
                    "   \"detail\": \"JWT token is expired: ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<RestError> noHandlerFoundException(ExpiredJwtException exception);

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"SignatureException\",\n" +
                    "   \"detail\": \"Bad JWT Signature:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<RestError> noHandlerFoundException(SignatureException exception);

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"MalformedJwtException\",\n" +
                    "   \"detail\": \"Malformed Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<RestError> noHandlerFoundException(MalformedJwtException exception);

    @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + UNAUTHORIZED_MSG + "\",\n" +
                    "   \"error\": \"401\",\n" +
                    "   \"exception\": \"UnsupportedJwtException\",\n" +
                    "   \"detail\": \"Unsupported Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<RestError> noHandlerFoundException(UnsupportedJwtException exception);

    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + ACCESS_DENIED_MSG + "\",\n" +
                    "   \"error\": \"403\",\n" +
                    "   \"exception\": \"AccessDeniedException\",\n" +
                    "   \"detail\": \"You have no permission to access the resource\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ResponseEntity<RestError> noHandlerFoundException(AccessDeniedException exception);

    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + NOT_FOUND_MSG + "\",\n" +
                    "   \"error\": \"404\",\n" +
                    "   \"exception\": \"NoHandlerFoundException\",\n" +
                    "   \"detail\": \"Unsupported Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<RestError> noHandlerFoundException(NoHandlerFoundException exception);

    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + NOT_FOUND_MSG + "\",\n" +
                    "   \"error\": \"404\",\n" +
                    "   \"exception\": \"NotFoundException\",\n" +
                    "   \"detail\": \"Unsupported Jwt:  ...\",\n" +
                    "}")))
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<RestError> noHandlerFoundException(NotFoundException exception);

    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + CONFLICT_MSG + "\",\n" +
                    "   \"error\": \"409\",\n" +
                    "   \"exception\": \"AlreadyExistException\",\n" +
                    "   \"detail\": \"Account already exists  ...\",\n" +
                    "}")))
    @ResponseStatus(CONFLICT)
    ResponseEntity<RestError> noHandlerFoundException(AlreadyExistException exception);

    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(
            example = "{\n" +
                    "   \"timestamp\": \"2022-01-29T10:10:10.324Z\",\n" +
                    "   \"title\": \"" + INTERNAL_SERVER_ERROR_MSG + "\",\n" +
                    "   \"error\": \"500\",\n" +
                    "   \"exception\": \"InternalException\",\n" +
                    "   \"detail\": \"Server have problems to process your request\",\n" +
                    "}")))
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    ResponseEntity<RestError> httpClientErrorException(InternalException exception);

    default ResponseEntity<RestError> buildResponse(HttpStatus httpStatus, String msg, String details) {
        return new ResponseEntity<>(
                RestError.builder().error(httpStatus.getReasonPhrase()).title(msg).detail(details).build(),
                httpStatus
        );
    }
}
