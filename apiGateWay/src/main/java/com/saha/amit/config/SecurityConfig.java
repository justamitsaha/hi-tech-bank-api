package com.saha.amit.config;

import com.saha.amit.constants.ApiGatewayCosntants;
import com.saha.amit.filter.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private static String[] unAuthorizedUrl = new String[]{
            "/public"
    };

    private static String[] authorizedUrl = new String[]{
            "/private"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors().configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(PropertiesConfig.ALLOWED_URLS);
                    configuration.setAllowedMethods(List.of("*"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setAllowCredentials(true);
                    configuration.setAllowedHeaders(Collections.singletonList("*"));
                    configuration.setExposedHeaders(Arrays.asList(ApiGatewayCosntants.JWT_HEADER));  //To set headers in different domain
                    configuration.setMaxAge(3600L);
                    return configuration;
                }).and().csrf().disable()
                .addFilterBefore(new JwtAuthorizationFilter(), SecurityWebFiltersOrder.LAST)
                .httpBasic()
                .and().httpBasic();
        return http.build();
    }
}
