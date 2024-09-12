package com.example.city_Taxi.security;

import com.example.city_Taxi.util.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

/**
 * The type Jwt authentication entry point.
 */
@Component

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Serial
    private static final long serialVersionUID = -7858869558953243875L;

    private final ObjectMapper objectMapper;

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        final ResponseMessage responseMessage = new ResponseMessage(401,"Invalid token",null);

        final ServletOutputStream writer = response.getOutputStream();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        writer.write(objectMapper.writeValueAsBytes(responseMessage));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        writer.flush();
    }
}
