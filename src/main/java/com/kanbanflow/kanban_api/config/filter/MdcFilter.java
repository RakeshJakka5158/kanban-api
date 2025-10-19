package com.kanbanflow.kanban_api.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class MdcFilter extends OncePerRequestFilter {

    private static final String TRACE_ID_MDC_KEY = "traceId";
    private static final String USER_NAME_MDC_KEY = "userName";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Populate MDC
        String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID_MDC_KEY, traceId);

        // Get username from security context if available
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getPrincipal())) {
            MDC.put(USER_NAME_MDC_KEY, authentication.getName());
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
