package com.grupo06.epicentrogourmet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.grupo06.epicentrogourmet.entity.RolUsuario;
import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner inicializarAdministrador(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${app.admin.username}") String adminUsername,
            @Value("${app.admin.password}") String adminPassword) {

        return args -> {

            if (usuarioRepository.findByUsername(adminUsername).isEmpty()) {

                Usuario administrador = new Usuario(
                        adminUsername,
                        passwordEncoder.encode(adminPassword),
                        RolUsuario.ADMINISTRADOR
                );

                usuarioRepository.save(administrador);

                System.out.println(
                        "Usuario administrador inicial creado correctamente."
                );
            }
        };
    }
}