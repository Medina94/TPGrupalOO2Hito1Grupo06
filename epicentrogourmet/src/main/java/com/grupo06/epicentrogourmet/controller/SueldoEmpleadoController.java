package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo06.epicentrogourmet.dto.SueldoEmpleadoDTO;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.service.SueldoEmpleadoService;

@Controller
public class SueldoEmpleadoController {

    private final SueldoEmpleadoService sueldoEmpleadoService;

    public SueldoEmpleadoController(
            SueldoEmpleadoService sueldoEmpleadoService) {

        this.sueldoEmpleadoService =
                sueldoEmpleadoService;
    }

    @GetMapping("/empleado/sueldos")
    public String consultarSueldos(
            Model model) {

        try {

            Empleado empleado =
                    sueldoEmpleadoService
                            .obtenerEmpleadoActual();

            List<SueldoEmpleadoDTO> sueldos =
                    sueldoEmpleadoService
                            .listarSueldosPorFestival();

            model.addAttribute(
                    "empleado",
                    empleado
            );

            model.addAttribute(
                    "sueldos",
                    sueldos
            );

            return "mis-sueldos";

        } catch (IllegalArgumentException
                 | IllegalStateException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "mis-sueldos";
        }
    }
}