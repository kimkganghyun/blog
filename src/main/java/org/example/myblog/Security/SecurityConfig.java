package org.example.myblog.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/","/api/users/register", "/api/users/login",
                                        "/css/**", "/js/**", "/images/**","/register",
                                        "/api/users/name/**", "/api/users/email/**",
                                        "/login").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()); // 필요에 따라 CSRF 보호 비활성화
        return http.build();
    }
}
