package com.grupo06.epicentrogourmet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {

            boolean esResponsable = authentication
                    .getAuthorities()
                    .stream()
                    .anyMatch(authority ->
                            authority.getAuthority().equals("ROLE_RESPONSABLE"));

            String destino = esResponsable ? "/responsable/empleados" : "/";

            response.sendRedirect(request.getContextPath() + destino);
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationSuccessHandler loginSuccessHandler)
            throws Exception {

        http
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/images/**"
                ).permitAll()

                .requestMatchers("/admin/**")
                    .hasRole("ADMINISTRADOR")

                .requestMatchers("/responsable/**")
                    .hasRole("RESPONSABLE")

                .requestMatchers("/empleado/**")
                    .hasAnyRole("EMPLEADO", "RESPONSABLE")

                .anyRequest()
                    .authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                    .successHandler(loginSuccessHandler)
                .failureUrl("/login?error")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}