package com.grupo06.epicentrogourmet.controller;

import com.grupo06.epicentrogourmet.dto.RentabilidadDTO;
import com.grupo06.epicentrogourmet.service.interfaces.IRentabilidadService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class RentabilidadController {

    private final IRentabilidadService rentabilidadService;

    public RentabilidadController(IRentabilidadService rentabilidadService) {
        this.rentabilidadService = rentabilidadService;
    }

    // Muestra el formulario vacío para elegir fechas
    @GetMapping("/rentabilidad/consultar")
    public String mostrarFormulario() {
        return "rentabilidad-form";
    }

    // Procesa el cálculo y devuelve la misma vista pero con los resultados
    @PostMapping("/rentabilidad/consultar")
    public String calcularRentabilidad(
            @RequestParam Long idUnidadVenta,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta,
            Model model) {

        try {
            RentabilidadDTO resultado = rentabilidadService.calcularRentabilidad(idUnidadVenta, fechaDesde, fechaHasta);
            model.addAttribute("resultado", resultado);
            // Volvemos a mandar los datos ingresados para que no se borren de los inputs
            model.addAttribute("idUnidadVenta", idUnidadVenta);
            model.addAttribute("fechaDesde", fechaDesde);
            model.addAttribute("fechaHasta", fechaHasta);
            
        } catch (Exception e) {
            model.addAttribute("errorGeneral", "Error al calcular la rentabilidad: " + e.getMessage());
        }

        return "rentabilidad-form";
    }
}