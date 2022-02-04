package ua.com.cyberdone.accountmicroservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.accountmicroservice.common.constant.ControllerConstantUtils;
import ua.com.cyberdone.accountmicroservice.common.constant.Regex;
import ua.com.cyberdone.accountmicroservice.common.exception.AccessDeniedException;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.AuthenticationException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.controller.docs.AccountApi;
import ua.com.cyberdone.accountmicroservice.dto.account.AccountDto;
import ua.com.cyberdone.accountmicroservice.dto.account.AccountsDto;
import ua.com.cyberdone.accountmicroservice.dto.account.ChangeEmailDto;
import ua.com.cyberdone.accountmicroservice.dto.account.ChangeFullNameDto;
import ua.com.cyberdone.accountmicroservice.dto.account.ChangePasswordDto;
import ua.com.cyberdone.accountmicroservice.dto.account.LoginDto;
import ua.com.cyberdone.accountmicroservice.dto.account.LogoutDto;
import ua.com.cyberdone.accountmicroservice.dto.account.RegistrationDto;
import ua.com.cyberdone.accountmicroservice.dto.token.TokenDto;
import ua.com.cyberdone.accountmicroservice.service.AccountService;
import ua.com.cyberdone.accountmicroservice.service.AuthenticationService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ua.com.cyberdone.accountmicroservice.common.constant.Regex.EMAIL_RGX;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController implements AccountApi {
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts')")
    public ResponseEntity<AccountsDto> readAccounts(@RequestHeader(AUTHORIZATION) String token,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int size,
                                                    @RequestParam(defaultValue = "NONE") String direction,
                                                    @RequestParam(defaultValue = "NONE") String sortBy)
            throws NotFoundException {
        if ("NONE".equals(sortBy) && sortBy.equals(direction)) {
            return ResponseEntity.ok(accountService.getAllAccounts(page, size));
        }
        return ResponseEntity.ok(accountService.getAllAccounts(page, size, direction, sortBy));
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('r_all','r_account','r_self')")
    public ResponseEntity<AccountDto> readAccount(@RequestHeader(AUTHORIZATION) String token,
                                                  @PathVariable(value = "username") String username)
            throws NotFoundException {
        return ResponseEntity.ok(accountService.getAccount(username));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_accounts')")
    public ResponseEntity<String> deleteAccounts(@RequestHeader(AUTHORIZATION) String token) {
        accountService.deleteAllAccounts();
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{username}/permanent")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    public ResponseEntity<String> permanentDeleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                                         @PathVariable String username) {
        accountService.permanentDeleteAccount(username);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('d_all','d_account','d_self')")
    public ResponseEntity<String> deleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                                @PathVariable String username) throws NotFoundException {
        accountService.deleteAccount(username, token);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/self")
    @PreAuthorize("hasAnyAuthority('d_self')")
    public ResponseEntity<String> deleteSelf(@RequestHeader(AUTHORIZATION) String token) throws NotFoundException {
        accountService.deleteSelfAccount(token);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid RegistrationDto registrationDto)
            throws AlreadyExistException, NotFoundException {
        return ResponseEntity.ok(accountService.createAccount(registrationDto));
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestHeader(AUTHORIZATION) String token,
                                                    @RequestBody @Valid RegistrationDto registrationDto)
            throws AccessDeniedException, NotFoundException, AlreadyExistException {
        return ResponseEntity.ok(accountService.createAccountFromAnotherAccount(registrationDto, token));
    }

    @PutMapping("/change/password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto)
            throws NotFoundException {
        accountService.changeAccountPassword(changePasswordDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/change/fullname")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    public ResponseEntity<String> changeFullName(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestBody @Valid ChangeFullNameDto changeFullNameDto)
            throws NotFoundException {
        accountService.changeAccountFullName(changeFullNameDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/change/username")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    public ResponseEntity<String> changeUsername(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestBody @Valid ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException {
        accountService.changeAccountUsername(changeEmailDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/{username}/change/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_images','u_self')")
    public ResponseEntity<String> changeImage(@RequestHeader(AUTHORIZATION) String token,
                                              @Pattern(regexp = EMAIL_RGX, message = Regex.EMAIL_FAIL_MESSAGE)
                                              @PathVariable String username, @RequestParam MultipartFile file)
            throws NotFoundException, IOException, AlreadyExistException {
        accountService.changeAccountImage(username, file);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) throws AuthenticationException {
        TokenDto tokenDto = authenticationService.login(loginDto);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/authentication/logout")
    public ResponseEntity<TokenDto> logout(@RequestHeader(AUTHORIZATION) String token,
                                           @RequestBody @Valid LogoutDto logoutDto) {
        TokenDto tokenDto = authenticationService.logout(logoutDto);
        return ResponseEntity.ok(tokenDto);
    }
}
