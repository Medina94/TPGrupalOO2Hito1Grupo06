package com.grupo06.epicentrogourmet.controller;

import java.util.List;
import jakarta.validation.Valid;
import com.grupo06.epicentrogourmet.dto.CierreCajaDTO;
import com.grupo06.epicentrogourmet.service.interfaces.ICierreCajaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CierreCajaController {

    private final ICierreCajaService cierreCajaService;

    public CierreCajaController(ICierreCajaService cierreCajaService) {
        this.cierreCajaService = cierreCajaService;
    }

    @GetMapping("/cierre-caja/listar")
    public String listarCierres(Model model) {
        try {
            model.addAttribute("cierres", cierreCajaService.listarCierres());
        } catch (Exception e) {
            model.addAttribute("cierres", List.of());
            model.addAttribute("errorGeneral", e.getMessage());
        }
        return "cierres";
    }

    @GetMapping("/cierre-caja/agregar")
    public String mostrarFormulario(Model model) {
        if (!model.containsAttribute("registroCierre")) {
            model.addAttribute("registroCierre", new CierreCajaDTO());
        }
        return "alta-cierre";
    }

    @PostMapping("/cierre-caja/agregar")
    public String agregarCierre(
            @Valid @ModelAttribute("registroCierre") CierreCajaDTO cierreCajaDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "alta-cierre";
        }

        try {
            cierreCajaService.guardarCierre(cierreCajaDTO);
        } catch (Exception e) {
            model.addAttribute("errorGeneral", "Error al guardar el cierre de caja. Verifique que no exista uno igual.");
            return "alta-cierre";
        }

        redirectAttributes.addFlashAttribute("mensajeExito", "El cierre de caja se registró correctamente.");
        return "redirect:/cierre-caja/agregar";
    }
}