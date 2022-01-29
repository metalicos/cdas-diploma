package ua.com.cyberdone.accountmicroservice.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LogAndSuppressRequestIllegalCharactersFilter extends GenericFilterBean {

    private static final String MALICIOUS_STRING =
            "The request was rejected because the URL contained a potentially malicious string.";
    private static final String LOG_ILLEGAL_CHARACTERS_IN_REQUEST =
            "The request contains illegal characters: {}";
    private static final String[] INVALID_CHARACTERS_ARRAY = {"!", "$"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var httpServletResponse = (HttpServletResponse) response;
        var httpServletRequest = (HttpServletRequest) request;
        var requestUri = httpServletRequest.getRequestURI();
        if (Arrays.stream(INVALID_CHARACTERS_ARRAY).anyMatch(requestUri::contains)) {
            sendBadRequest(httpServletResponse, MALICIOUS_STRING);
            return;
        }
        try {
            chain.doFilter(request, response);
        } catch (RequestRejectedException ex) {
            sendBadRequest(httpServletResponse, ex.getMessage());
        }
    }

    private void sendBadRequest(HttpServletResponse httpServletResponse, String message) {
        log.error(LOG_ILLEGAL_CHARACTERS_IN_REQUEST, message);
        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    }
}
