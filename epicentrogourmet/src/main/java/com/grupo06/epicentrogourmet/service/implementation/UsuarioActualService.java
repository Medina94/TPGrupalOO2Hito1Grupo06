package com.grupo06.epicentrogourmet.service.implementation;

import com.grupo06.epicentrogourmet.service.IUsuarioActualService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.repository.UsuarioRepository;

/**
 * Servicio para obtener el usuario actualmente autenticado.
 * Lo obtiene del contexto Spring Security y lo busca en la BD.
 */
@Service
public class UsuarioActualService implements IUsuarioActualService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioActualService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario obtenerUsuarioActual() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException(
                    "No hay un usuario autenticado."
            );
        }

        return usuarioRepository
                .findByUsername(authentication.getName())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No se encontró el usuario autenticado."
                        )
                );
    }
}
