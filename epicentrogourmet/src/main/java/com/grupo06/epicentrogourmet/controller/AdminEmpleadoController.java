package com.grupo06.epicentrogourmet.controller;

import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.service.EmpleadoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AdminEmpleadoController {

    private final EmpleadoService empleadoService;

    public AdminEmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/admin/empleados")
    public String listarEmpleados(Model model, Authentication authentication) {

        List<Empleado> empleados = empleadoService.listarTodosLosEmpleados();

        model.addAttribute("empleados", empleados);
        model.addAttribute("username", authentication.getName());
        model.addAttribute("esAdmin", true);

        return "admin-empleados.html";
    }
}
