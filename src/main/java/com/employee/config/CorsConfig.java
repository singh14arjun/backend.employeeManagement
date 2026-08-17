package com.employee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(List.of("GET",
                "POST",
                "PUT",
                "PATCH",
                "DELETE",
                "DELETE",
                "OPTIONS"
        )
        );
        corsConfiguration.setAllowedHeaders(
                List.of("*")
        );
        UrlBasedCorsConfigurationSource source=
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        return  source;
    }
}
