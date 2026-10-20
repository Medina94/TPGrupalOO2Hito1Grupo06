package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.service.FestivalService;
import com.grupo06.epicentrogourmet.service.UnidadVentaService;

@Controller
public class HomeController {

    private final FestivalService festivalService;
    private final UnidadVentaService unidadVentaService;

    public HomeController(
            FestivalService festivalService,
            UnidadVentaService unidadVentaService) {

        this.festivalService = festivalService;
        this.unidadVentaService = unidadVentaService;
    }

    // =====================================================
    // INICIO GENERAL
    // =====================================================

    @GetMapping("/")
    public String inicio(
            Model model,
            Authentication authentication) {

        if (authentication != null) {

            boolean esResponsable =
                    authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(authority ->
                                    authority
                                            .getAuthority()
                                            .equals(
                                                    "ROLE_RESPONSABLE"
                                            )
                            );

            if (esResponsable) {
                return "redirect:/responsable";
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

            boolean esAdmin =
                    authentication
                            .getAuthorities()
                            .stream()
                            .anyMatch(authority ->
                                    authority
                                            .getAuthority()
                                            .equals(
                                                    "ROLE_ADMINISTRADOR"
                                            )
                            );

            model.addAttribute(
                    "esAdmin",
                    esAdmin
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
}