package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.dto.RegistroUnidadResponsableDTO;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.service.FestivalService;
import com.grupo06.epicentrogourmet.service.UnidadVentaService;

@Controller
public class UnidadVentaController {

    private final UnidadVentaService unidadVentaService;
    private final FestivalService festivalService;

    public UnidadVentaController(
            UnidadVentaService unidadVentaService,
            FestivalService festivalService) {

        this.unidadVentaService = unidadVentaService;
        this.festivalService = festivalService;
    }


    // =====================================================
    // LISTADO DE UNIDADES POR FESTIVAL - ADMIN
    // =====================================================

    @GetMapping("/admin/festivales/{idFestival}/unidades")
    public String listarUnidadesPorFestival(
            @PathVariable Long idFestival,
            Model model) {

        Festival festival =
                festivalService.buscarPorId(idFestival);

        List<UnidadVenta> unidades =
                unidadVentaService.listarPorFestival(idFestival);

        model.addAttribute(
                "festival",
                festival
        );

        model.addAttribute(
                "unidades",
                unidades
        );

        return "unidades-por-festival";
    }


    // =====================================================
    // FORMULARIO DE ALTA - ADMIN
    // =====================================================

    @GetMapping("/admin/unidades/nueva")
    public String mostrarFormularioAdmin(Model model) {

        prepararFormulario(model);

        model.addAttribute(
                "modoPublico",
                false
        );

        return "unidad-form";
    }


    // =====================================================
    // REGISTRO DE UNIDAD + RESPONSABLE - ADMIN
    // =====================================================

    @PostMapping("/admin/unidades/nueva")
    public String registrarUnidadAdmin(
            @Valid
            @ModelAttribute("registroUnidad")
            RegistroUnidadResponsableDTO dto,

            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            prepararFormulario(model);

            model.addAttribute(
                    "modoPublico",
                    false
            );

            return "unidad-form";
        }

        try {

            unidadVentaService
                    .registrarUnidadConResponsable(dto);

            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "La unidad de venta y su responsable "
                    + "se registraron correctamente."
            );

            return "redirect:/admin/unidades/nueva";

        } catch (IllegalArgumentException e) {

            model.addAttribute(
                    "errorGeneral",
                    e.getMessage()
            );

            prepararFormulario(model);

            model.addAttribute(
                    "modoPublico",
                    false
            );

            return "unidad-form";
        }
    }


    // =====================================================
    // FORMULARIO PUBLICO
    // REGISTRO DE RESPONSABLE + NEGOCIO
    // =====================================================

    @GetMapping("/registro/responsable")
    public String mostrarRegistroPublico(Model model) {

        prepararFormulario(model);

        model.addAttribute(
                "modoPublico",
                true
        );

        return "unidad-form";
    }


    // =====================================================
    // REGISTRO PUBLICO
    // RESPONSABLE + UNIDAD DE VENTA
    // =====================================================

    @PostMapping("/registro/responsable")
    public String registrarResponsablePublico(
            @Valid
            @ModelAttribute("registroUnidad")
            RegistroUnidadResponsableDTO dto,

            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            prepararFormulario(model);

            model.addAttribute(
                    "modoPublico",
                    true
            );

            return "unidad-form";
        }

        try {

            unidadVentaService
                    .registrarUnidadConResponsable(dto);

            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "Tu cuenta y tu Unidad de Venta "
                    + "se registraron correctamente. "
                    + "Ya podés iniciar sesión."
            );

            return "redirect:/login";

        } catch (IllegalArgumentException e) {

            model.addAttribute(
                    "errorGeneral",
                    e.getMessage()
            );

            prepararFormulario(model);

            model.addAttribute(
                    "modoPublico",
                    true
            );

            return "unidad-form";
        }
    }


    // =====================================================
    // MÉTODO AUXILIAR
    // =====================================================

    private void prepararFormulario(Model model) {

        if (!model.containsAttribute("registroUnidad")) {

            model.addAttribute(
                    "registroUnidad",
                    new RegistroUnidadResponsableDTO()
            );
        }

        model.addAttribute(
                "festivales",
                festivalService.listarFestivales()
        );
    }
}