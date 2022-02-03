package ua.com.cyberdone.accountmicroservice.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.com.cyberdone.accountmicroservice.security.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RequiredArgsConstructor
public abstract class AuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER = "Bearer ";
    protected final JwtService jwtService;
    protected final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            var token = parseJwt(request);
            if (jwtService.isValidToken(token)) {
                authenticate(request, jwtService.getUsername(token));
            }
            filterChain.doFilter(request, response);
        } catch (ServletException | IOException ex) {
            log.error("Account authentication cannot be set:", ex);
        }
    }

    public abstract void authenticate(HttpServletRequest request, String username);

    private String parseJwt(HttpServletRequest request) {
        var headerAuth = request.getHeader(AUTHORIZATION);
        return StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER) ?
                headerAuth.substring(BEARER.length()) : null;
    }
}
