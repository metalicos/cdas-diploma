package ua.com.cyberdone.accountmicroservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.accountmicroservice.common.exception.AlreadyExistException;
import ua.com.cyberdone.accountmicroservice.common.exception.AuthenticationException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.common.util.ImageConverterUtils;
import ua.com.cyberdone.accountmicroservice.dto.account.AccountDto;
import ua.com.cyberdone.accountmicroservice.dto.account.LoginDto;
import ua.com.cyberdone.accountmicroservice.dto.account.LogoutDto;
import ua.com.cyberdone.accountmicroservice.dto.account.OauthLoginDto;
import ua.com.cyberdone.accountmicroservice.dto.account.RegistrationDto;
import ua.com.cyberdone.accountmicroservice.dto.token.TokenDto;
import ua.com.cyberdone.accountmicroservice.security.JwtService;

import java.io.IOException;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public static final String BEARER = "Bearer ";
    private static final String BASE_PATH = "http://cyberdone.store";
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenDto login(LoginDto loginDto) throws AuthenticationException {
        try {
            var authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            var authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var account = accountService.getAccount(loginDto.getUsername());
            var token = jwtService.generateToken(account);
            return TokenDto.builder()
                    .authToken(BEARER + token)
                    .tokenLiveTimeInSeconds(MILLISECONDS.toSeconds(jwtService.expTimeInMs()))
                    .build();
        } catch (BadCredentialsException | NotFoundException | JsonProcessingException ex) {
            throw new AuthenticationException("Bad Username or Password. ", ex);
        }
    }

    public TokenDto oauthLogin(OauthLoginDto oauthLoginDto) throws AuthenticationException {
        AccountDto accountDto = null;
        try {
            try {
                accountDto = accountService.getAccount(oauthLoginDto.getEmail());
            } catch (NotFoundException ex) {
                var tempPassword = UUID.randomUUID() + "aA@2";
                var registration = new RegistrationDto();
                registration.setFirstName(oauthLoginDto.getFirstName());
                registration.setLastName(oauthLoginDto.getLastName());
                registration.setPatronymic("Not specified");
                registration.setPassword(tempPassword);
                registration.setPasswordCheck(tempPassword);
                registration.setUsername(oauthLoginDto.getEmail());
                registration.setPhoto(ImageConverterUtils.getImageBytes(oauthLoginDto.getPhotoUrl()));

                accountDto = accountService.createAccount(registration);
            }
            var token = jwtService.generateToken(accountDto);
            return TokenDto.builder().authToken(BEARER + token)
                    .tokenLiveTimeInSeconds(MILLISECONDS.toSeconds(jwtService.expTimeInMs())).build();
        } catch (NotFoundException | AlreadyExistException | IOException ex) {
            log.error("Error, while creating account via Oauth");
            throw new AuthenticationException("Problems with authentication via Oauth", ex);
        }
    }

    public TokenDto logout(LogoutDto logoutDto) {
        log.info("Logout user={}", jwtService.getUsername(logoutDto.getToken()));
        return jwtService.getEmptyToken();
    }
}
