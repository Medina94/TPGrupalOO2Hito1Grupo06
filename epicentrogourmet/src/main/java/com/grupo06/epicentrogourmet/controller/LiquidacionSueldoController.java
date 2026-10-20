package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.dto.LiquidacionSueldoDTO;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.service.LiquidacionSueldoService;

@Controller
public class LiquidacionSueldoController {

    private final LiquidacionSueldoService liquidacionSueldoService;

    public LiquidacionSueldoController(
            LiquidacionSueldoService liquidacionSueldoService) {

        this.liquidacionSueldoService =
                liquidacionSueldoService;
    }


    // =====================================================
    // PANTALLA DE LIQUIDACIÓN
    // =====================================================

    @GetMapping("/responsable/sueldos")
    public String mostrarLiquidacion(
            Model model) {

        try {

            UnidadVenta unidad =
                    liquidacionSueldoService
                            .obtenerUnidadDelResponsable();

            List<LiquidacionSueldoDTO> liquidaciones =
                    liquidacionSueldoService
                            .listarLiquidaciones();

            model.addAttribute(
                    "unidad",
                    unidad
            );

            model.addAttribute(
                    "festival",
                    unidad.getFestival()
            );

            model.addAttribute(
                    "liquidaciones",
                    liquidaciones
            );

            return "liquidacion-sueldos";

        } catch (IllegalArgumentException
                 | IllegalStateException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "liquidacion-sueldos";
        }
    }


    // =====================================================
    // EJECUTAR LIQUIDACIÓN
    // =====================================================

    @PostMapping("/responsable/sueldos/liquidar")
    public String liquidarSueldos(
            RedirectAttributes redirectAttributes) {

        try {

            int cantidad =
                    liquidacionSueldoService
                            .liquidarSueldos();

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Se liquidaron correctamente los sueldos de "
                            + cantidad
                            + " empleado(s)."
            );

        } catch (IllegalArgumentException
                 | IllegalStateException e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/responsable/sueldos";
    }
}