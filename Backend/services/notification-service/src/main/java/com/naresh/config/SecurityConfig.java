package com.naresh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${jwt.secret}")
    private String base64Secret;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth->auth
                        .requestMatchers("/actuator/*").permitAll()
                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2->oauth2
                        .jwt(jwtConfigurer->jwtConfigurer
                                .decoder(jwtDecoder())));
        return http.build();
    }
    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] decodedSecret = Base64.getDecoder().decode(base64Secret);
        return NimbusJwtDecoder
                .withSecretKey(new SecretKeySpec(decodedSecret, "HmacSHA512"))
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

}
