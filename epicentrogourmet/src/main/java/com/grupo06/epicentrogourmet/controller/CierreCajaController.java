package com.grupo06.epicentrogourmet.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

import com.grupo06.epicentrogourmet.dto.CierreCajaDTO;
import com.grupo06.epicentrogourmet.service.interfaces.ICierreCajaService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CierreCajaController {

    private final ICierreCajaService cierreCajaService;

    public CierreCajaController(ICierreCajaService cierreCajaService) {
        this.cierreCajaService = cierreCajaService;
    }

    @GetMapping("/cierre-caja/listar")
    public String listarCierres(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaDesde,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fechaHasta,

            Model model) {

        List<CierreCajaDTO> cierres = List.of();

        try {
            if (fechaDesde == null && fechaHasta == null) {

                cierres = cierreCajaService.listarCierres();

            } else if (fechaDesde == null || fechaHasta == null) {

                model.addAttribute(
                        "errorGeneral",
                        "Debés ingresar ambas fechas para realizar la búsqueda."
                );

            } else if (fechaDesde.isAfter(fechaHasta)) {

                model.addAttribute(
                        "errorGeneral",
                        "La fecha desde no puede ser posterior a la fecha hasta."
                );

            } else {

                cierres = cierreCajaService.listarCierresPorFecha(
                        fechaDesde,
                        fechaHasta
                );
            }

        } catch (Exception e) {

            model.addAttribute(
                    "errorGeneral",
                    "No se pudieron consultar los cierres de caja."
            );
        }

        // Calcular la recaudación total del listado mostrado.
        BigDecimal recaudacionTotal = BigDecimal.ZERO;

        for (CierreCajaDTO cierre : cierres) {

            Number monto = cierre.getMontoTotal();

            if (monto != null) {
                recaudacionTotal = recaudacionTotal.add(
                        new BigDecimal(monto.toString())
                );
            }
        }

        // Calcular el promedio por cierre.
        BigDecimal promedioCierre = BigDecimal.ZERO;

        if (!cierres.isEmpty()) {
            promedioCierre = recaudacionTotal.divide(
                    BigDecimal.valueOf(cierres.size()),
                    2,
                    RoundingMode.HALF_UP
            );
        }

        // Enviar los resultados y el resumen a la vista.
        model.addAttribute("cierres", cierres);
        model.addAttribute("cantidadCierres", cierres.size());
        model.addAttribute("recaudacionTotal", recaudacionTotal);
        model.addAttribute("promedioCierre", promedioCierre);

        model.addAttribute("fechaDesde", fechaDesde);
        model.addAttribute("fechaHasta", fechaHasta);

        return "cierres";
    }

    @GetMapping("/cierre-caja/agregar")
    public String mostrarFormulario(Model model) {

        if (!model.containsAttribute("registroCierre")) {
            model.addAttribute(
                    "registroCierre",
                    new CierreCajaDTO()
            );
        }

        return "alta-cierre";
    }

    @PostMapping("/cierre-caja/agregar")
    public String agregarCierre(
            @Valid @ModelAttribute("registroCierre")
            CierreCajaDTO cierreCajaDTO,

            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "alta-cierre";
        }

        try {

            cierreCajaService.guardarCierre(cierreCajaDTO);

        } catch (Exception e) {

            model.addAttribute(
                    "errorGeneral",
                    "Error al guardar el cierre de caja. Verifique que no exista uno igual."
            );

            return "alta-cierre";
        }

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "El cierre de caja se registró correctamente."
        );

        return "redirect:/cierre-caja/agregar";
    }

    @GetMapping("/cierre-caja/{id}/pdf")
    public ResponseEntity<byte[]> descargarPdf(
            @PathVariable Long id) {

        try {

            byte[] pdf = cierreCajaService.generarPdfCierre(id);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"cierre-caja-" + id + ".pdf\""
                    )
                    .body(pdf);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build();
        }
    }
}