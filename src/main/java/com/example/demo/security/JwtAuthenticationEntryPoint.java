package com.example.demo.security;

import com.example.demo.dtos.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String token= request.getHeader("Authorization");
        ApiResponse<Object> apiResponse= new ApiResponse<>(
                false,
                token==null ?" Missing token":"Invalid token",
                HttpStatus.UNAUTHORIZED,
                null
        );
        ServletOutputStream out = response.getOutputStream();
        new ObjectMapper().writeValue(out,apiResponse);
        out.flush();
    }
}
