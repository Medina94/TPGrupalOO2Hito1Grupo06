package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.grupo06.epicentrogourmet.dto.HistorialLaboralDTO;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.service.HistorialLaboralService;

@Controller
public class HistorialLaboralController {

    private final HistorialLaboralService historialLaboralService;

    public HistorialLaboralController(
            HistorialLaboralService historialLaboralService) {

        this.historialLaboralService =
                historialLaboralService;
    }

    @GetMapping("/empleado/historial")
    public String consultarHistorial(
            Model model) {

        try {

            Empleado empleado =
                    historialLaboralService
                            .obtenerEmpleadoActual();

            List<HistorialLaboralDTO> historial =
                    historialLaboralService
                            .obtenerHistorial();

            model.addAttribute(
                    "empleado",
                    empleado
            );

            model.addAttribute(
                    "historial",
                    historial
            );

            return "historial-laboral";

        } catch (IllegalArgumentException
                 | IllegalStateException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "historial-laboral";
        }
    }
}