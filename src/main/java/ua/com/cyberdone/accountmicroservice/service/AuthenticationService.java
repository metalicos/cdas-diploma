package ua.com.cyberdone.accountmicroservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.accountmicroservice.common.exception.AuthenticationException;
import ua.com.cyberdone.accountmicroservice.common.exception.NotFoundException;
import ua.com.cyberdone.accountmicroservice.dto.account.LoginDto;
import ua.com.cyberdone.accountmicroservice.dto.account.LogoutDto;
import ua.com.cyberdone.accountmicroservice.dto.token.TokenDto;
import ua.com.cyberdone.accountmicroservice.security.JwtService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public static final String BEARER = "Bearer ";
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
            throw new AuthenticationException("Bad Username or Password. Exception=" + ex.getMessage());
        }
    }

    public TokenDto logout(LogoutDto logoutDto) {
        log.info("Logout user={}", jwtService.getUsername(logoutDto.getToken()));
        return jwtService.getEmptyToken();
    }
}
