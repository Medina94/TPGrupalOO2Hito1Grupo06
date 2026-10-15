package com.grupo06.epicentrogourmet.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.dto.RegistroUnidadResponsableDTO;
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

    @GetMapping("/admin/unidades/nueva")
    public String mostrarFormulario(Model model) {

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

        return "unidad-form";
    }

    @PostMapping("/admin/unidades/nueva")
    public String registrarUnidad(
            @Valid
            @ModelAttribute("registroUnidad")
            RegistroUnidadResponsableDTO dto,

            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "festivales",
                    festivalService.listarFestivales()
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

            model.addAttribute(
                    "festivales",
                    festivalService.listarFestivales()
            );

            return "unidad-form";
        }
    }
}