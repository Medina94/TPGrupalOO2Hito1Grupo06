package com.grupo06.epicentrogourmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo06.epicentrogourmet.dto.EmpleadoActualDTO;
import com.grupo06.epicentrogourmet.service.EmpleadoActualService;

@Controller
public class EmpleadoActualController {

    private final EmpleadoActualService empleadoActualService;

    public EmpleadoActualController(
            EmpleadoActualService empleadoActualService) {

        this.empleadoActualService =
                empleadoActualService;
    }

    @GetMapping("/empleado/actual")
    public String consultarActual(
            Model model) {

        try {

            EmpleadoActualDTO informacion =
                    empleadoActualService
                            .obtenerInformacionActual();

            model.addAttribute(
                    "informacion",
                    informacion
            );

            return "festival-unidad-actual";

        } catch (IllegalArgumentException
                 | IllegalStateException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "festival-unidad-actual";
        }
    }
}