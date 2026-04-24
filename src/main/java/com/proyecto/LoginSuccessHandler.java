package com.proyecto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        var roles = authentication.getAuthorities();

        for (var rol : roles) {
            if (rol.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/dashboard");
                return;
            }
            if (rol.getAuthority().equals("ROLE_VENDEDOR")) {
                response.sendRedirect("/vendedor");
                return;
            }
            if (rol.getAuthority().equals("ROLE_CLIENTE")) {
                response.sendRedirect("/cliente");
                return;
            }
        }
    }
}
