package ua.com.cyberdone.accountmicroservice.security.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.accountmicroservice.security.JwtService;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenAuthenticationFilter extends AuthenticationFilter {

    public TokenAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        super(jwtService, userDetailsService);
    }

    @Override
    public void authenticate(HttpServletRequest request, String username) {
        var userDetails = userDetailsService.loadUserByUsername(username);
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
