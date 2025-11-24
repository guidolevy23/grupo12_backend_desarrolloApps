package com.uade.tpo.gimnasio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 🌐 Orígenes permitidos - incluyendo todas las variantes para Expo/Android
        config.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:*",
                "http://127.0.0.1:*",
                "http://10.0.2.2:*",  // 🤖 Android Emulator específico
                "http://192.168.*.*:*",
                "http://10.*.*.*:*",
                "http://172.16.*.*:*",
                "exp://*",
                "capacitor://*",
                "ionic://*"
        ));

        // ✅ Métodos HTTP permitidos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // ✅ Headers permitidos - IMPORTANTE: usar "*" para aceptar todos
        config.setAllowedHeaders(Arrays.asList("*"));

        // 🔓 Permitir credenciales (false para compatibilidad con origin patterns)
        config.setAllowCredentials(false);

        // ⚡ Exponer headers
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));

        // ⏱ Tiempo de cache para preflight
        config.setMaxAge(3600L);

        // 📍 Aplicar la config globalmente
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return source;
    }

    // Agregar CorsFilter con alta prioridad para que se ejecute ANTES de Spring Security
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}