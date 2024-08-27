
package com.example.config;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "ログインに失敗しました。";
        if (exception instanceof BadCredentialsException) {
            BadCredentialsException badCredentialsException = (BadCredentialsException) exception;
            if (badCredentialsException.getMessage().contains("システム管理者に問い合わせしてください。")) {
                errorMessage = "システム管理者に問い合わせしてください。";
            } else {
                errorMessage = "ログインIDもしくはパスワードが正しくありません。";
            }
        }
        request.getSession().setAttribute("errorMessage", errorMessage);
        response.sendRedirect("/login?error");
    }
}