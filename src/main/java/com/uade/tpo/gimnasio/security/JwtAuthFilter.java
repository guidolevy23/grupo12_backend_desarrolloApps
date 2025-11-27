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
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    // 🟢 RUTAS PÚBLICAS (SE MANTIENE TODO LO TUYO + TODO LO QUE PUSHEARON TUS AMIGOS)
    private final RequestMatcher unSecurePaths = new OrRequestMatcher(
            new AntPathRequestMatcher("/auth/**"),
            new AntPathRequestMatcher("/api/qr/**"),
            new AntPathRequestMatcher("/qr/**"),
            new AntPathRequestMatcher("/images/**")     // 👈 agregado SOLO para imágenes, NO se toca nada más
    );

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("🔎 PATH LLEGANDO AL JWT FILTER: " + request.getServletPath());

        // SI LA RUTA ES PÚBLICA -> NO FILTRAR
        if (shouldNotFilter(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            unauthorized(response);
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
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

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
        return unSecurePaths.matches(request);
    }
}
