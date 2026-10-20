package com.grupo06.epicentrogourmet.controller;

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
    // INICIO GENERAL
    // =====================================================

    @GetMapping("/")
    public String inicio(
            Model model,
            Authentication authentication) {

        if (authentication != null) {

            if (tieneRol(authentication, "ROLE_RESPONSABLE")) {
                return "redirect:/responsable";
            }

            if (tieneRol(authentication, "ROLE_EMPLEADO")) {
                return "redirect:/empleado";
            }
        }

        List<Festival> festivales =
                festivalService.listarFestivales();

        model.addAttribute(
                "festivales",
                festivales
        );

        if (authentication != null) {

            model.addAttribute(
                    "username",
                    authentication.getName()
            );

            model.addAttribute(
                    "esAdmin",
                    tieneRol(authentication, "ROLE_ADMINISTRADOR")
            );
        }

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
                unidadVentaService.buscarUnidadDelEmpleado(authentication.getName());

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
                        authority.getAuthority().equals(rol)
                );
    }
}