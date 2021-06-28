package com.vavilon.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Log LOG = LogFactory.getLog(RequestBodyReaderAuthenticationFilter.class);

    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";

    private final ObjectMapper objectMapper = new ObjectMapper();

    public RequestBodyReaderAuthenticationFilter() {
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
        try {
            final String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            final AuthForm authRequest = objectMapper.readValue(requestBody, AuthForm.class);

            final UsernamePasswordAuthenticationToken token
                    = new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword());

            setDetails(request, token);
            return this.getAuthenticationManager().authenticate(token);
        } catch (Exception e) {
            LOG.error(ERROR_MESSAGE, e);
            throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);
        }
    }
}