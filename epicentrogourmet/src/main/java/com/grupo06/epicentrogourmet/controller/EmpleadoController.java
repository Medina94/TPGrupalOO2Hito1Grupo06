package com.grupo06.epicentrogourmet.controller;

import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.Cocinero;
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
import com.grupo06.epicentrogourmet.dto.ModificarEmpleadoDTO;

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

    @PostMapping("/admin/empleados/{id}/eliminar")
    public String eliminarEmpleado(
            @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            empleadoService.eliminarEmpleado(id);
            redirectAttributes.addFlashAttribute("success", "Empleado dado de baja correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/empleados";
    }


    @GetMapping("/admin/empleados/{id}/modificar")
    public String mostrarFormularioModificar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {

        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            ModificarEmpleadoDTO dto = new ModificarEmpleadoDTO();

            dto.setNombre(empleado.getNombre());
            dto.setApellido(empleado.getApellido());
            dto.setDni(empleado.getDni());
            dto.setEmail(empleado.getEmail());
            dto.setFechaNacimiento(empleado.getFechaNacimiento());
            dto.setFechaIngreso(empleado.getFechaIngreso());

            if (empleado instanceof Cocinero cocinero) {
                dto.setTipoEmpleado("COCINERO");
                dto.setRolCocina(cocinero.getRolCocina().name());
            } else if (empleado instanceof Cajero cajero) {
                dto.setTipoEmpleado("CAJERO");
                dto.setTurno(cajero.getTurno().name());
            }

            model.addAttribute("registroEmpleado", dto);
            model.addAttribute("idEmpleado", id);

            return "modificar-empleado";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/empleados";
        }
    }

    @PostMapping("/admin/empleados/{id}/modificar")
    public String modificarEmpleado(
            @PathVariable Long id,
            @Valid @ModelAttribute("registroEmpleado") ModificarEmpleadoDTO dto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("idEmpleado", id);
            return "modificar-empleado";
        }

        try {
            empleadoService.modificarEmpleado(id, dto);
            redirectAttributes.addFlashAttribute("success", "Empleado modificado correctamente.");
            return "redirect:/admin/empleados";

        } catch (IllegalArgumentException e) {
            model.addAttribute("idEmpleado", id);
            model.addAttribute("errorGeneral", e.getMessage());
            return "modificar-empleado";
        }
    }
}