package com.grupo06.epicentrogourmet.controller;

import com.grupo06.epicentrogourmet.entity.Cajero;
import com.grupo06.epicentrogourmet.entity.Cocinero;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

import com.grupo06.epicentrogourmet.entity.Empleado;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.dto.RegistroEmpleadoDTO;
import com.grupo06.epicentrogourmet.service.EmpleadoService;
import com.grupo06.epicentrogourmet.service.UnidadVentaService;
import com.grupo06.epicentrogourmet.dto.ModificarEmpleadoDTO;

@Controller
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    private final UnidadVentaService unidadVentaService;

    public EmpleadoController(EmpleadoService empleadoService,
                              UnidadVentaService unidadVentaService) {
        this.empleadoService = empleadoService;
        this.unidadVentaService = unidadVentaService;
    }

    @GetMapping("/responsable/empleados")
    public String empleadosInicio(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String dni,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            Model model,
            Authentication authentication){

        if (fechaDesde != null && fechaHasta != null
                && fechaDesde.isAfter(fechaHasta)) {
            model.addAttribute("error",
                    "La fecha 'desde' no puede ser posterior a la fecha 'hasta'.");
            fechaDesde = null;
            fechaHasta = null;
        }

        List<Empleado> empleados =
                empleadoService.buscarEmpleados(nombre, dni, fechaDesde, fechaHasta);

        model.addAttribute("empleados", empleados);
        model.addAttribute("filtroNombre", nombre);
        model.addAttribute("filtroDni", dni);
        model.addAttribute("filtroDesde", fechaDesde);
        model.addAttribute("filtroHasta", fechaHasta);
        model.addAttribute("hayFiltros",
                (nombre != null && !nombre.isBlank())
                        || (dni != null && !dni.isBlank())
                        || fechaDesde != null
                        || fechaHasta != null);

        if(authentication!=null){
            model.addAttribute(
                    "username",
                    authentication.getName()
            );


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

            model.addAttribute(
                    "esResponsable",
                    esResponsable
            );
        }

        return "empleados";
    }

    @GetMapping("/responsable/empleados/nuevo")
    public String mostrarFormulario(Model model) {

        if (!model.containsAttribute("registroEmpleado")) {
            model.addAttribute(
                    "registroEmpleado",
                    new RegistroEmpleadoDTO()
            );
        }

        return "alta-empleado";
    }

    @PostMapping("/responsable/empleados/nuevo")
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

            return "redirect:/responsable/empleados/nuevo";

        } catch (IllegalArgumentException e) {

            model.addAttribute("errorGeneral", e.getMessage());

            return "alta-empleado";
        }
    }

    @PostMapping("/responsable/empleados/{id}/eliminar")
    public String eliminarEmpleado(
            @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            empleadoService.eliminarEmpleado(id);
            redirectAttributes.addFlashAttribute("success", "Empleado dado de baja correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/responsable/empleados";
    }


    @PostMapping("/responsable/empleados/{id}/reactivar")
    public String reactivarEmpleado(
            @PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            empleadoService.reactivarEmpleado(id);
            redirectAttributes.addFlashAttribute("success", "Empleado dado de alta correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/responsable/empleados";
    }


    @GetMapping("/responsable/empleados/{id}/modificar")
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
            return "redirect:/responsable/empleados";
        }
    }

    @PostMapping("/responsable/empleados/{id}/modificar")
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
            return "redirect:/responsable/empleados";

        } catch (IllegalArgumentException e) {
            model.addAttribute("idEmpleado", id);
            model.addAttribute("errorGeneral", e.getMessage());
            return "modificar-empleado";
        }
    }
}