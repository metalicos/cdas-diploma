package ua.com.cyberdone.accountmicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.accountmicroservice.common.constant.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.common.exception.AccessDeniedException;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.AuthenticationException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.account.AccountDto;
import ua.com.cyberdone.accountmicroservice.dto.account.AccountsDto;
import ua.com.cyberdone.accountmicroservice.dto.account.ChangeEmailDto;
import ua.com.cyberdone.accountmicroservice.dto.account.ChangeFullNameDto;
import ua.com.cyberdone.accountmicroservice.dto.account.ChangePasswordDto;
import ua.com.cyberdone.accountmicroservice.dto.account.LoginDto;
import ua.com.cyberdone.accountmicroservice.dto.account.LogoutDto;
import ua.com.cyberdone.accountmicroservice.dto.account.RegistrationDto;
import ua.com.cyberdone.accountmicroservice.dto.token.TokenDto;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Tag(name = "Accounts", description = "Endpoints for managing accounts")
public interface AccountApi {

    @Operation(summary = "Read accounts", description = "Return all accounts with pagination")
    @ApiResponse(responseCode = "200", description = "Return accounts with pagination (page, size) / order " +
            "'direction' (ASC / DESC) / filter by word 'sortBy'",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountsDto.class)))
    ResponseEntity<AccountsDto> readAccounts(String token, int page, int size, String direction, String sortBy)
            throws NotFoundException;

    @Operation(summary = "Read account", description = "Return account by username")
    @ApiResponse(responseCode = "200", description = "Return account by username",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountDto.class)))
    ResponseEntity<AccountDto> readAccount(String token, String username) throws NotFoundException;

    @Operation(summary = "Read self account", description = "Return account by user token")
    @ApiResponse(responseCode = "200", description = "Return account by user token",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountDto.class)))
    ResponseEntity<AccountDto> readSelfAccount(@RequestHeader(AUTHORIZATION) String token) throws NotFoundException;

    @Operation(summary = "Delete accounts", description = "Delete all accounts")
    @ApiResponse(responseCode = "200", description = "Delete all accounts",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteAccounts(String token);

    @Operation(summary = "Delete account (permanent)", description = "Delete account by username (permanent)")
    @ApiResponse(responseCode = "200", description = "Delete account by username (permanent)",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> permanentDeleteAccount(String token, String username);

    @Operation(summary = "Delete account", description = "Delete account by username")
    @ApiResponse(responseCode = "200", description = "Delete account by username",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteAccount(String token, String username)
            throws NotFoundException;

    @Operation(summary = "Delete self account", description = "Delete self account")
    @ApiResponse(responseCode = "200", description = "Delete self account",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteSelf(String token) throws NotFoundException;

    @Operation(summary = "Register new account", description = "Registration of a new account")
    @ApiResponse(responseCode = "200", description = "Registration of a new account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountDto.class)))
    ResponseEntity<AccountDto> createAccount(RegistrationDto registrationDto)
            throws AlreadyExistException, NotFoundException;

    @Operation(summary = "Create account", description = "Create the new account from another account")
    @ApiResponse(responseCode = "200", description = "Create the new account from another account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountDto.class)))
    ResponseEntity<AccountDto> createAccount(String token, RegistrationDto registrationDto)
            throws AccessDeniedException, NotFoundException, AlreadyExistException;

    @Operation(summary = "Change password", description = "Change account's password")
    @ApiResponse(responseCode = "200", description = "Change account's password",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> changePassword(ChangePasswordDto changePasswordDto) throws NotFoundException;

    @Operation(summary = "Change full name", description = "Change account's full name")
    @ApiResponse(responseCode = "200", description = "Change account's full name",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> changeFullName(String token, ChangeFullNameDto changeFullNameDto) throws NotFoundException;

    @Operation(summary = "Change email", description = "Change account's email")
    @ApiResponse(responseCode = "200", description = "Change account's email",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> changeUsername(String token, ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException;

    @Operation(summary = "Change image", description = "Change account's image")
    @ApiResponse(responseCode = "200", description = "Change account's image",
            content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> changeImage(String token, String username, MultipartFile file)
            throws NotFoundException, IOException, AlreadyExistException;

    @Operation(summary = "Login to account", description = "Perform login operation to account")
    @ApiResponse(responseCode = "200", description = "Perform login operation to account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                    implementation = TokenDto.class,
                    example = "{\n" +
                            "    \"authToken\": \"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.e30." +
                            "Et9HFtf9R3GEMA0IICOfFMVXY7kkTX1wr4qCyhIf58U\",\n" +
                            "    \"tokenLiveTimeInSeconds\": 86400\n" +
                            "}")))
    ResponseEntity<TokenDto> login(LoginDto loginDto) throws AuthenticationException;

    @Operation(summary = "Logout from account", description = "Perform logout operation from account")
    @ApiResponse(responseCode = "200", description = "Perform logout operation from account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                    implementation = TokenDto.class,
                    example = "{\n" +
                            "    \"authToken\": \"\",\n" +
                            "}")))
    ResponseEntity<TokenDto> logout(String token, LogoutDto logoutDto)
            throws NotFoundException, AlreadyExistException;
}
