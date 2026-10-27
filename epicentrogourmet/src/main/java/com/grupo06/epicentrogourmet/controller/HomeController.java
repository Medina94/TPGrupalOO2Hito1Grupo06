package com.grupo06.epicentrogourmet.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.entity.Usuario;
import com.grupo06.epicentrogourmet.service.FestivalService;
import com.grupo06.epicentrogourmet.service.IUsuarioActualService;
import com.grupo06.epicentrogourmet.service.UnidadVentaService;

@Controller
public class HomeController {

    private final FestivalService festivalService;
    private final UnidadVentaService unidadVentaService;
    private final IUsuarioActualService usuarioActualService;

    public HomeController(
            FestivalService festivalService,
            UnidadVentaService unidadVentaService,
            IUsuarioActualService usuarioActualService) {

        this.festivalService = festivalService;
        this.unidadVentaService = unidadVentaService;
        this.usuarioActualService = usuarioActualService;
    }

    // =====================================================
    // HOME PUBLICA
    // =====================================================

    @GetMapping("/")
    public String inicioPublico(
            Model model,
            Authentication authentication) {

        List<Festival> festivales =
                festivalService.listarFestivales();

        model.addAttribute(
                "festivales",
                festivales
        );

        // Fecha actual para mostrar el estado de cada Festival
        model.addAttribute(
                "hoy",
                LocalDate.now()
        );

        /*
         * La Home es pública.
         * Si existe un usuario autenticado, enviamos su rol
         * para mostrar accesos personalizados.
         */
        if (authentication != null
                && authentication.isAuthenticated()) {

            model.addAttribute(
                    "username",
                    authentication.getName()
            );

            model.addAttribute(
                    "autenticado",
                    true
            );

            model.addAttribute(
                    "esAdmin",
                    tieneRol(
                            authentication,
                            "ROLE_ADMINISTRADOR"
                    )
            );

            model.addAttribute(
                    "esResponsable",
                    tieneRol(
                            authentication,
                            "ROLE_RESPONSABLE"
                    )
            );

            model.addAttribute(
                    "esEmpleado",
                    tieneRol(
                            authentication,
                            "ROLE_EMPLEADO"
                    )
            );

        } else {

            model.addAttribute(
                    "autenticado",
                    false
            );
        }

        return "public-home";
    }

    // =====================================================
    // PANEL DEL ADMINISTRADOR
    // =====================================================

    @GetMapping("/admin")
    public String administradorInicio(
            Model model,
            Authentication authentication) {

        if (authentication == null) {
            return "redirect:/login";
        }

        List<Festival> festivales =
                festivalService.listarFestivales();

        model.addAttribute(
                "festivales",
                festivales
        );

        model.addAttribute(
                "username",
                authentication.getName()
        );

        model.addAttribute(
                "esAdmin",
                true
        );

        /*
         * Se reutiliza index.html como panel administrativo.
         */
        return "index";
    }

    // =====================================================
    // PANEL DEL RESPONSABLE
    // =====================================================

    @GetMapping("/responsable")
    public String responsableInicio(
            Model model,
            Authentication authentication) {

        if (authentication == null) {
            return "redirect:/login";
        }

        UnidadVenta unidad =
                unidadVentaService
                        .buscarUnidadDelResponsable(
                                authentication.getName()
                        );

        model.addAttribute(
                "username",
                authentication.getName()
        );

        model.addAttribute(
                "unidad",
                unidad
        );

        if (unidad != null) {

            model.addAttribute(
                    "festival",
                    unidad.getFestival()
            );
        }

        return "responsable-dashboard";
    }

    // =====================================================
    // PANEL DEL EMPLEADO
    // =====================================================

    @GetMapping("/empleado")
    public String empleadoInicio(
            Model model,
            Authentication authentication) {

        if (authentication == null) {
            return "redirect:/login";
        }

        Usuario usuario =
                usuarioActualService.obtenerUsuarioActual();

        UnidadVenta unidad =
                unidadVentaService
                        .buscarUnidadDelEmpleado(
                                authentication.getName()
                        );

        model.addAttribute(
                "username",
                authentication.getName()
        );

        model.addAttribute(
                "empleado",
                usuario.getEmpleado()
        );

        model.addAttribute(
                "unidad",
                unidad
        );

        if (unidad != null) {

            model.addAttribute(
                    "festival",
                    unidad.getFestival()
            );
        }

        return "empleado-dashboard";
    }

    // =====================================================
    // UTILIDAD
    // =====================================================

    private boolean tieneRol(
            Authentication authentication,
            String rol) {

        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority
                                .getAuthority()
                                .equals(rol)
                );
    }
}