package com.uade.tpo.gimnasio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // 👈 importante: matchea con tu context-path
                        .allowedOriginPatterns(
                                "http://localhost:8081",
                                "http://192.168.0.146:8081",
                                "http://localhost:3000",
                                "http://192.168.0.146:3000",
                                "http://localhost:19006",
                                "http://192.168.0.146:19006",
                                "exp://192.168.0.146",
                                "*"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .exposedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }


}
