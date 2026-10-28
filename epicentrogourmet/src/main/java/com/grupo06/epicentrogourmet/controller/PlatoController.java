package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import jakarta.validation.Valid;

import com.grupo06.epicentrogourmet.dto.PlatoDTO;
import com.grupo06.epicentrogourmet.service.IPlatoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PlatoController {

    private final IPlatoService platoService;

    public PlatoController(IPlatoService platoService) {
        this.platoService = platoService;
    }

    @ModelAttribute("homePath")
    public String homePath(Authentication authentication) {
        if (hasRole(authentication, "ROLE_ADMINISTRADOR")) {
            return "/admin";
        }
        if (hasRole(authentication, "ROLE_RESPONSABLE")) {
            return "/responsable";
        }
        if (hasRole(authentication, "ROLE_EMPLEADO")) {
            return "/empleado";
        }
        return "/";
    }

    private boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
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

    @GetMapping("/plato/{idPlato}/editar")
    public String mostrarFormularioEdicion(
            @PathVariable Long idPlato,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            PlatoDTO plato = platoService.listarPlatos().stream()
                    .filter(item -> idPlato.equals(item.getIdPlato()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No se encontró el plato seleccionado."
                    ));
            model.addAttribute("registroPlato", plato);
            model.addAttribute("edicion", true);
            return "alta-plato";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorGeneral", e.getMessage());
            return "redirect:/plato/listar";
        }
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

    @PostMapping("/plato/{idPlato}/editar")
    public String actualizarPlato(
            @PathVariable Long idPlato,
            @Valid @ModelAttribute("registroPlato") PlatoDTO platoDTO,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        model.addAttribute("edicion", true);
        if (bindingResult.hasErrors()) {
            return "alta-plato";
        }

        try {
            platoService.actualizarPlato(idPlato, platoDTO);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorGeneral", e.getMessage());
            return "alta-plato";
        }

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "El plato se modificó correctamente."
        );
        return "redirect:/plato/listar";
    }

    @PostMapping("/plato/{idPlato}/eliminar")
    public String eliminarPlato(
            @PathVariable Long idPlato,
            RedirectAttributes redirectAttributes) {
        try {
            platoService.eliminarPlato(idPlato);
            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "El plato se eliminó correctamente."
            );
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorGeneral", e.getMessage());
        }
        return "redirect:/plato/listar";
    }

}
