package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import jakarta.validation.Valid;

import com.grupo06.epicentrogourmet.dto.PlatoDTO;
import com.grupo06.epicentrogourmet.service.IPlatoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PlatoController {

    private final IPlatoService platoService;

    public PlatoController(IPlatoService platoService) {
        this.platoService = platoService;
    }

    @GetMapping("/plato/listar")
    public String listarPlatos(Model model) {
        try {
            model.addAttribute(
                    "platos",
                    platoService.listarPlatos()
            );
        } catch (IllegalArgumentException e) {
            model.addAttribute("platos", List.of());
            model.addAttribute("errorGeneral", e.getMessage());
        }
        return "platos";
    }

    @GetMapping("/plato/agregar")
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("registroPlato")) {
            model.addAttribute("registroPlato", new PlatoDTO());
        }
        return "alta-plato";
    }

    @PostMapping("/plato/agregar")
    public String agregarPlato(
            @Valid @ModelAttribute("registroPlato") PlatoDTO platoDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "alta-plato";
        }

        try {
            platoService.agregarPlato(platoDTO);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorGeneral", e.getMessage());
            return "alta-plato";
        }

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "El plato se registró correctamente."
        );
        return "redirect:/plato/agregar";
    }


}
