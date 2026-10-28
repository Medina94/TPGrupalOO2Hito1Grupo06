package com.grupo06.epicentrogourmet.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

        model.addAttribute(
                "hoy",
                LocalDate.now()
        );

        agregarDatosUsuario(
                model,
                authentication
        );

        return "public-home";
    }


    // =====================================================
    // DETALLE PUBLICO DE FESTIVAL
    // =====================================================

    @GetMapping("/festivales/{idFestival}")
    public String detalleFestivalPublico(
            @PathVariable Long idFestival,
            Model model,
            Authentication authentication) {

        Festival festival =
                festivalService.buscarPorId(idFestival);

        List<UnidadVenta> unidades =
                unidadVentaService
                        .listarPorFestival(idFestival);

        model.addAttribute(
                "festival",
                festival
        );

        model.addAttribute(
                "unidades",
                unidades
        );

        model.addAttribute(
                "hoy",
                LocalDate.now()
        );

        agregarDatosUsuario(
                model,
                authentication
        );

        return "festival-publico";
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
    // DATOS DEL USUARIO PARA PAGINAS PUBLICAS
    // =====================================================

    private void agregarDatosUsuario(
            Model model,
            Authentication authentication) {

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