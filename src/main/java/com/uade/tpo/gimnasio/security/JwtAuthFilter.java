package com.uade.tpo.gimnasio.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    // Permitir acceso sin autenticación a /auth/** y /api/courses/**
    private final RequestMatcher unSecurePaths = new AntPathRequestMatcher("/auth/**");
    private final RequestMatcher publicCourses = new AntPathRequestMatcher("/api/courses/**");

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String username = null;
            String jwt = authHeader.substring(7);
            if (jwtUtil.isTokenValid(jwt)) {
                username = jwtUtil.extractUsername(jwt);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails;
                try {
                    userDetails = userDetailsService.loadUserByUsername(username);
                } catch (Exception e) {
                    unauthorized(response);
                    return;
                }

                if (jwtUtil.isTokenValid(jwt)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities()
                            );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (ExpiredJwtException e) {
            unauthorized(response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // AntPathRequestMatcher normally compares against the servletPath (that may not include
        // the context-path). Check both the matcher and the raw request URI so endpoints
        // exposed under the app context path (e.g. /api) are correctly treated as public.
        if (unSecurePaths.matches(request)) return true;
        if (publicCourses.matches(request)) return true;

        String uri = request.getRequestURI(); // includes context-path
        String context = request.getContextPath() != null ? request.getContextPath() : "";
        String pathWithoutContext = uri.startsWith(context) ? uri.substring(context.length()) : uri;
        // Accept /courses and /courses/... (useful when context-path is /api)
        if (pathWithoutContext.equals("/courses") || pathWithoutContext.startsWith("/courses/")) return true;

        return false;
    }
}
