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

            boolean esAdministrador =
                    authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(a ->
                                    a.getAuthority()
                                            .equals(
                                                    "ROLE_ADMINISTRADOR"
                                            )
                            );

            boolean esResponsable =
                    authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(a ->
                                    a.getAuthority()
                                            .equals(
                                                    "ROLE_RESPONSABLE"
                                            )
                            );

            boolean esEmpleado =
                    authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(a ->
                                    a.getAuthority()
                                            .equals(
                                                    "ROLE_EMPLEADO"
                                            )
                            );


            String destino = "/";


            if (esAdministrador) {

                destino = "/admin";

            } else if (esResponsable) {

                destino = "/responsable";

            } else if (esEmpleado) {

                destino = "/empleado";
            }


            response.sendRedirect(
                    request.getContextPath()
                            + destino
            );
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationSuccessHandler loginSuccessHandler)
            throws Exception {

        http

                .authorizeHttpRequests(auth -> auth

                        // =====================================
                        // CONTENIDO PUBLICO
                        // =====================================

                        .requestMatchers(
                                "/",
                                "/login",
                                "/registro/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        )
                        .permitAll()


                        // =====================================
                        // ADMINISTRADOR
                        // =====================================

                        .requestMatchers(
                                "/admin",
                                "/admin/**"
                        )
                        .hasRole(
                                "ADMINISTRADOR"
                        )


                        // =====================================
                        // RESPONSABLE
                        // =====================================

                        .requestMatchers(
                                "/responsable",
                                "/responsable/**"
                        )
                        .hasRole(
                                "RESPONSABLE"
                        )


                        // =====================================
                        // EMPLEADO
                        // =====================================

                        .requestMatchers(
                                "/empleado",
                                "/empleado/**"
                        )
                        .hasAnyRole(
                                "EMPLEADO",
                                "RESPONSABLE"
                        )


                        // =====================================
                        // RESTO DEL SISTEMA
                        // =====================================

                        .anyRequest()
                        .authenticated()
                )


                .formLogin(form -> form

                        .loginPage(
                                "/login"
                        )

                        .loginProcessingUrl(
                                "/login"
                        )

                        .successHandler(
                                loginSuccessHandler
                        )

                        .failureUrl(
                                "/login?error"
                        )

                        .permitAll()
                )


                .logout(logout -> logout

                        /*
                         * Al cerrar sesión volvemos al sitio
                         * público y no directamente al login.
                         */
                        .logoutSuccessUrl(
                                "/"
                        )

                        .permitAll()
                );


        return http.build();
    }
}