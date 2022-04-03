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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.com.cyberdone.accountmicroservice.common.constant.ControllerConstantUtils;
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
import ua.com.cyberdone.accountmicroservice.dto.account.OauthLoginDto;
import ua.com.cyberdone.accountmicroservice.dto.account.RegistrationDto;
import ua.com.cyberdone.accountmicroservice.dto.token.TokenDto;
import ua.com.cyberdone.accountmicroservice.service.AccountService;
import ua.com.cyberdone.accountmicroservice.service.AuthenticationService;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController implements AccountApi {
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('r_all','r_all_accounts')")
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
    @PreAuthorize("hasAnyAuthority('r_all','r_account')")
    public ResponseEntity<AccountDto> readAccount(@RequestHeader(AUTHORIZATION) String token,
                                                  @PathVariable String username)
            throws NotFoundException {
        return ResponseEntity.ok(accountService.getAccount(username));
    }

    @GetMapping("/{username}/profileImage")
    @PreAuthorize("hasAnyAuthority('r_all','r_account')")
    public ResponseEntity<String> readAccountProfileImage(@RequestHeader(AUTHORIZATION) String token,
                                                          @PathVariable String username)
            throws IOException {
        return ResponseEntity.ok(accountService.getAccountProfileImage(username));
    }

    @GetMapping("/self/profileImage")
    @PreAuthorize("hasAnyAuthority('r_all','r_self')")
    public ResponseEntity<String> readSelfAccountProfileImage(@RequestHeader(AUTHORIZATION) String token)
            throws IOException {
        return ResponseEntity.ok(accountService.getSelfAccountProfileImage(token));
    }

    @GetMapping("/self")
    @PreAuthorize("hasAnyAuthority('r_all','r_self')")
    public ResponseEntity<AccountDto> readSelfAccount(@RequestHeader(AUTHORIZATION) String token)
            throws NotFoundException {
        return ResponseEntity.ok(accountService.getSelfAccount(token));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('d_all','d_all_accounts')")
    public ResponseEntity<String> deleteAccounts(@RequestHeader(AUTHORIZATION) String token) {
        accountService.deleteAllAccounts();
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{username}/permanent")
    @PreAuthorize("hasAnyAuthority('d_all','d_account_permanent')")
    public ResponseEntity<String> permanentDeleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                                         @PathVariable String username) {
        accountService.permanentDeleteAccount(username);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasAnyAuthority('d_all','d_account')")
    public ResponseEntity<String> deleteAccount(@RequestHeader(AUTHORIZATION) String token,
                                                @PathVariable String username) throws NotFoundException {
        accountService.deleteAccount(username, token);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @DeleteMapping("/self")
    @PreAuthorize("hasAnyAuthority('d_all','d_self')")
    public ResponseEntity<String> deleteSelf(@RequestHeader(AUTHORIZATION) String token) throws NotFoundException {
        accountService.deleteSelfAccount(token);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<AccountDto> createAccount(@RequestBody RegistrationDto registrationDto)
            throws AlreadyExistException, NotFoundException {
        return ResponseEntity.ok(accountService.createAccount(registrationDto));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('w_all','w_account')")
    public ResponseEntity<AccountDto> createAccount(@RequestHeader(AUTHORIZATION) String token,
                                                    @RequestBody RegistrationDto registrationDto)
            throws AccessDeniedException, NotFoundException, AlreadyExistException {
        return ResponseEntity.ok(accountService.createAccountFromAnotherAccount(registrationDto, token));
    }

    @PutMapping("/change/password/logged")
    @PreAuthorize("hasAnyAuthority('u_all','u_self')")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto)
            throws NotFoundException {
        accountService.changeAccountPassword(changePasswordDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/change/fullname")
    @PreAuthorize("hasAnyAuthority('u_all','u_account_full_name','u_self')")
    public ResponseEntity<String> changeFullName(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestBody ChangeFullNameDto changeFullNameDto)
            throws NotFoundException {
        accountService.changeAccountFullName(changeFullNameDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/change/username")
    @PreAuthorize("hasAnyAuthority('u_all','u_account','u_self')")
    public ResponseEntity<String> changeUsername(@RequestHeader(AUTHORIZATION) String token,
                                                 @RequestBody ChangeEmailDto changeEmailDto)
            throws NotFoundException, AlreadyExistException {
        accountService.changeAccountUsername(changeEmailDto);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PutMapping("/{username}/change/image")
    @PreAuthorize("hasAnyAuthority('u_all','u_user_account_image','u_self')")
    public ResponseEntity<String> changeImage(@RequestHeader(AUTHORIZATION) String token,
                                              @PathVariable String username,
                                              @RequestPart MultipartFile file)
            throws NotFoundException, IOException {
        accountService.changeAccountImage(username, file);
        return ResponseEntity.ok(ControllerConstantUtils.OK);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) throws AuthenticationException {
        var tokenDto = authenticationService.login(loginDto);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/authentication/oauth")
    public ResponseEntity<TokenDto> loginOauth(@RequestBody OauthLoginDto oauthLoginDto) throws AuthenticationException {
        var tokenDto = authenticationService.oauthLogin(oauthLoginDto);
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/authentication/logout")
    public ResponseEntity<TokenDto> logout(@RequestHeader(AUTHORIZATION) String token,
                                           @RequestBody LogoutDto logoutDto) {
        var tokenDto = authenticationService.logout(logoutDto);
        return ResponseEntity.ok(tokenDto);
    }
}
