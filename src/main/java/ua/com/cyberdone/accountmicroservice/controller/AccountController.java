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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
import ua.com.cyberdone.accountmicroservice.service.AccountService;
import ua.com.cyberdone.accountmicroservice.service.AuthenticationService;

import javax.validation.Valid;

import static java.util.Objects.nonNull;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts','r_self')")
    public ResponseEntity<Object> readAccounts(@RequestParam(value = "username", required = false) String username)
            throws NotFoundException {
        if (nonNull(username)) {
            return ResponseEntity.ok(accountService.getAccount(username));
        }
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_accounts','d_self')")
    public ResponseEntity<String> deleteAccounts(@RequestParam(value = "username", required = false) String username) {
        if (nonNull(username)) {
            accountService.deleteAccount(username);
            return ResponseEntity.ok(ControllerConstantUtils.OK);
        }
        accountService.deleteAllAccounts();
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<AccountDto> createAccount(@RequestBody RegistrationDto registrationDto)
            throws AccessDeniedException, NotFoundException, AlreadyExistException {
        return ResponseEntity.ok(accountService.createAccount(registrationDto));
    }

    @GetMapping("/page/{page}/size/{size}/sort-by/{sort-by}/direction/{direction}")
    @PreAuthorize("hasAnyAuthority('r_all','r_accounts','r_self')")
    public ResponseEntity<AccountsDto> readAccounts(@PathVariable("page") Integer page,
                                                    @PathVariable("size") Integer size,
                                                    @PathVariable("sort-by") String sortBy,
                                                    @PathVariable("direction") String direction)
            throws NotFoundException {
        return ResponseEntity.ok(accountService.getAllAccounts(page, size, direction, sortBy));
    }

    @PutMapping("/change/password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDto)
            throws NotFoundException {
        accountService.changeAccountPassword(changePasswordDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/change/fullname")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    public ResponseEntity<String> changeFullName(@RequestBody @Valid ChangeFullNameDto changeFullNameDto)
            throws NotFoundException {
        accountService.changeAccountFullName(changeFullNameDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/change/username")
    @PreAuthorize("hasAnyAuthority('u_all','u_accounts','u_self')")
    public ResponseEntity<String> changeUsername(@RequestBody @Valid ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException {
        accountService.changeAccountUsername(changeEmailDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto) throws AuthenticationException {
        TokenDto tokenDto = authenticationService.login(loginDto);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/authentication/logout")
    public ResponseEntity<TokenDto> logout(@RequestBody @Valid LogoutDto logoutDto)
            throws NotFoundException, AlreadyExistException {
        TokenDto tokenDto = authenticationService.logout(logoutDto);
        return ResponseEntity.ok(tokenDto);
    }
}
