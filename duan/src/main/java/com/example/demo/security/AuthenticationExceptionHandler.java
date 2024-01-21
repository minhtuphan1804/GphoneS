package com.example.demo.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException e, Model model) {
        model.addAttribute("tb", "Tài khoản hoặc mật khẩu không đúng.");
        return "loginPage";
    }
}
