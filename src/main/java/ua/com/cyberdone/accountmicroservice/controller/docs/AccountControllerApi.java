package ua.com.cyberdone.accountmicroservice.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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

import javax.validation.Valid;

@Tag(name = "Accounts", description = "Endpoints for managing accounts")
public interface AccountControllerApi {

    @Operation(summary = "Read Accounts", description = "Return all accounts with pagination")
    @ApiResponse(responseCode = "200", description = "Return accounts with pagination (page, size) / order 'direction' (ASC / DESC) / filter by word 'sortBy'",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountsDto.class)))
    ResponseEntity<AccountsDto> readAccounts(int page, int size, String direction, String sortBy) throws NotFoundException;

    @Operation(summary = "Read Accounts", description = "Return account by username")
    @ApiResponse(responseCode = "200", description = "Return account by username",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountDto.class)))
    ResponseEntity<AccountDto> readAccount(String username) throws NotFoundException;

    @Operation(summary = "Delete Accounts", description = "Delete all accounts")
    @ApiResponse(responseCode = "200", description = "Delete all accounts",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteAccounts();

    @Operation(summary = "Delete Account", description = "Delete account by username")
    @ApiResponse(responseCode = "200", description = "Delete account by username",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> deleteAccount(String username);

    @Operation(summary = "Register new account", description = "Registration of a new account")
    @ApiResponse(responseCode = "200", description = "Registration of a new account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountDto.class)))
    ResponseEntity<AccountDto> createAccount(RegistrationDto registrationDto)
            throws AlreadyExistException, NotFoundException;

    @Operation(summary = "Create Account", description = "Create the new account from another account")
    @ApiResponse(responseCode = "200", description = "Create the new account from another account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AccountDto.class)))
    ResponseEntity<AccountDto> createAccount(RegistrationDto registrationDto, String token)
            throws AccessDeniedException, NotFoundException, AlreadyExistException;

    @Operation(summary = "Change password", description = "Change account's password")
    @ApiResponse(responseCode = "200", description = "Change account's password",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto)
            throws NotFoundException;

    @Operation(summary = "Change full name", description = "Change account's full name")
    @ApiResponse(responseCode = "200", description = "Change account's full name",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> changeFullName(@RequestBody @Valid ChangeFullNameDto changeFullNameDto)
            throws NotFoundException;

    @Operation(summary = "Change email", description = "Change account's email")
    @ApiResponse(responseCode = "200", description = "Change account's email",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                    schema = @Schema(implementation = String.class, example = ControllerConstantUtils.OK)))
    ResponseEntity<String> changeUsername(@RequestBody @Valid ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException;

    @Operation(summary = "Login to account", description = "Perform login operation to account")
    @ApiResponse(responseCode = "200", description = "Perform login operation to account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                    implementation = TokenDto.class,
                    example = "{\n" +
                            "    \"authToken\": \"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJvc3RhcC5qYUBnbWFpbC5jb20iLCJqdGkiOiIxIiwicm9sZXMiOiJbe1wiaWRcIjoxLFwiY3JlYXRlZFRpbWVzdGFtcFwiOlwiMjAyMi0wMS0yOVQxNTozNDozNy42NDI3MjNcIixcInJvbGVcIjpcIk9XTkVSXCIsXCJwZXJtaXNzaW9uc1wiOlt7XCJpZFwiOjEsXCJjcmVhdGVkVGltZXN0YW1wXCI6XCIyMDIyLTAxLTI5VDE1OjM0OjM3LjYyODY2NFwiLFwibmFtZVwiOlwiUmVhZCBBbGxcIixcInZhbHVlXCI6XCJyX2FsbFwifSx7XCJpZFwiOjIsXCJjcmVhdGVkVGltZXN0YW1wXCI6XCIyMDIyLTAxLTI5VDE1OjM0OjM3LjYyODY2NFwiLFwibmFtZVwiOlwiV3JpdGUgQWxsXCIsXCJ2YWx1ZVwiOlwid19hbGxcIn0se1wiaWRcIjozLFwiY3JlYXRlZFRpbWVzdGFtcFwiOlwiMjAyMi0wMS0yOVQxNTozNDozNy42Mjg2NjRcIixcIm5hbWVcIjpcIlVwZGF0ZSBBbGxcIixcInZhbHVlXCI6XCJ1X2FsbFwifSx7XCJpZFwiOjQsXCJjcmVhdGVkVGltZXN0YW1wXCI6XCIyMDIyLTAxLTI5VDE1OjM0OjM3LjYyODY2NFwiLFwibmFtZVwiOlwiRGVsZXRlIEFsbFwiLFwidmFsdWVcIjpcImRfYWxsXCJ9XX1dIiwiaWF0IjoxNjQzNDkwMzgyLCJleHAiOjE2NDM1NzY3ODJ9.Hs_bbclHC2v85YVgLsei5Daumz-JlXJ70zZOqslpLtVcFIk86iuJ2Z_3IUdAd-pm-KAFYIxbvtDVXC7ZK_2AXw\",\n" +
                            "    \"tokenLiveTimeInSeconds\": 86400\n" +
                            "}")))
    ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) throws AuthenticationException;

    @Operation(summary = "Logout from account", description = "Perform logout operation from account")
    @ApiResponse(responseCode = "200", description = "Perform logout operation from account",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(
                    implementation = TokenDto.class,
                    example = "{\n" +
                            "    \"authToken\": \"\",\n" +
                            "}")))
    ResponseEntity<TokenDto> logout(@RequestBody @Valid LogoutDto logoutDto)
            throws NotFoundException, AlreadyExistException;
}
