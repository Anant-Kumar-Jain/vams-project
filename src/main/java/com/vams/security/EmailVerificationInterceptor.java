package com.vams.security;

import com.vams.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class EmailVerificationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        // Skip static resources and specific endpoints to prevent infinite redirect loops
        if (uri.startsWith("/css/") || 
            uri.startsWith("/js/") || 
            uri.startsWith("/images/") ||
            uri.equals("/") ||
            uri.equals("/home") ||
            uri.equals("/login") || 
            uri.equals("/register") || 
            uri.equals("/verify") ||
            uri.equals("/api/verify-success") ||
            uri.startsWith("/verify-email-notice") ||
            uri.equals("/logout") ||
            uri.startsWith("/error") ||
            uri.startsWith("/api/scan")) {
            return true;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // If the user is authenticated and not anonymous
        if (authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser")) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails) {
                User user = ((CustomUserDetails) principal).getUser();
                // Check if email is verified
                if (!user.isEmailVerified()) {
                    // Redirect them to the notice page
                    response.sendRedirect("/verify-email-notice");
                    return false;
                }
            }
        }
        
        return true;
    }

    private boolean isPublicResource(String uri) {
        return uri.startsWith("/css/") || uri.startsWith("/js/") || uri.equals("/login") || uri.equals("/register") || uri.equals("/verify-email-notice");
    }

}
