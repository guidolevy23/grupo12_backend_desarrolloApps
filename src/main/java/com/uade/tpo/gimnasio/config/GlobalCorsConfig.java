package com.uade.tpo.gimnasio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource; // <-- CAMBIO AQUÍ
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // <-- CAMBIO AQUÍ
// import org.springframework.web.filter.CorsFilter; // Ya no se usa

import java.util.Arrays;

@Configuration
public class GlobalCorsConfig {

    // NOTA: El método ahora se llama corsConfigurationSource
    // y devuelve un CorsConfigurationSource
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 🌐 Orígenes permitidos (los mismos que tenías)
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000",
                "http://192.168.0.146:3000",
                "http://localhost:8081",
                "http://192.168.0.146:8081",
                "http://localhost:19006",
                "http://192.168.0.146:19006",
                "exp://192.168.0.146"
                // Asegúrate de agregar el puerto de tu frontend web si no está
        ));

        // ✅ Métodos HTTP permitidos
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // ✅ Headers permitidos
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));

        // 🔓 Permitir credenciales
        config.setAllowCredentials(true);

        // ⚡ Exponer headers
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));

        // ⏱ Tiempo de cache
        config.setMaxAge(3600L);

        // 📍 Aplicar la config globalmente
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return source; // <-- CAMBIO AQUÍ
    }
}