package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo06.epicentrogourmet.dto.SueldoEmpleadoDTO;
import com.grupo06.epicentrogourmet.entity.Empleado;
import com.grupo06.epicentrogourmet.service.IPdfService;
import com.grupo06.epicentrogourmet.service.SueldoEmpleadoService;

@Controller
public class SueldoEmpleadoController {

    private final SueldoEmpleadoService sueldoEmpleadoService;
    private final IPdfService pdfService;

    public SueldoEmpleadoController(
            SueldoEmpleadoService sueldoEmpleadoService,
            IPdfService pdfService) {

        this.sueldoEmpleadoService =
                sueldoEmpleadoService;
        this.pdfService = pdfService;
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

    @GetMapping("/empleado/sueldos/{idAsignacion}/descargar")
    public ResponseEntity<byte[]> descargarReciboSueldo(
            @PathVariable Long idAsignacion) {
        byte[] pdf = pdfService.generarReciboSueldo(
                sueldoEmpleadoService.obtenerReciboSueldo(idAsignacion)
        );
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=recibo-sueldo-"
                                + idAsignacion
                                + ".pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdf.length)
                .body(pdf);
    }
}