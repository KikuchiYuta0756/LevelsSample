package com.example.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);
	
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	       logger.error("Authentication failed", exception);
        String errorMessage = "ログインに失敗しました。";
        if (exception instanceof CustomValidationException) {
            errorMessage = "システム管理者へ問い合わせてください。";
            response.sendRedirect("/login?error=validation");
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "ログインIDもしくはパスワードが間違っています。";
            response.sendRedirect("/login?error=bad_credentials");
        } else {
            response.sendRedirect("/login?error");
        }
    }
}