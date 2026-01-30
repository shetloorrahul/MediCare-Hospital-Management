package com.example.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

	 @Bean
	    public CorsWebFilter corsWebFilter() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:5173","http://172.16.0.102:5173")); // React app origin
	        config.setMaxAge(3600L);
	        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	        config.setAllowedHeaders(Arrays.asList("*"));
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);

	        return new CorsWebFilter(source);
    }
}
