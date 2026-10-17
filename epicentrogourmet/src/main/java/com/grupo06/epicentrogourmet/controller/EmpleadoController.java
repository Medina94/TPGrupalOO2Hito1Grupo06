package com.grupo06.epicentrogourmet.controller;

import jakarta.validation.Valid;
import java.util.List;

import com.grupo06.epicentrogourmet.entity.Empleado;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;
import com.grupo06.epicentrogourmet.service.EmpleadoService;

@Controller
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/admin/empleados")
    public String empleadosInicio(Model model,
                                  Authentication authentication){

        List<Empleado> empleados = empleadoService.listarEmpleados();

        model.addAttribute(
                "empleados",
                empleados
        );

        if(authentication!=null){
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

        return "empleados";
    }

    @GetMapping("/admin/empleados/nuevo")
    public String mostrarFormulario(Model model) {

        if (!model.containsAttribute("registroEmpleado")) {
            model.addAttribute(
                    "registroEmpleado",
                    new RegistroEmpleadoDTO()
            );
        }

        return "alta-empleado";
    }

    @PostMapping("/admin/empleados/nuevo")
    public String registrarEmpleado(
            @Valid
            @ModelAttribute("registroEmpleado")
            RegistroEmpleadoDTO dto,

            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "alta-empleado";
        }

        try {

            empleadoService.registrarEmpleado(dto);

            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "El empleado se registró correctamente."
            );

            return "redirect:/admin/empleados/nuevo";

        } catch (IllegalArgumentException e) {

            model.addAttribute("errorGeneral", e.getMessage());

            return "alta-empleado";
        }
    }
}