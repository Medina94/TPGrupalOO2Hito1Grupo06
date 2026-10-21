package com.grupo06.epicentrogourmet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.dto.ReporteMayoresCanonDTO;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.service.FestivalService;
import com.grupo06.epicentrogourmet.service.ReporteCanonService;

@Controller
public class ReporteCanonController {

    private final ReporteCanonService reporteCanonService;
    private final FestivalService festivalService;

    public ReporteCanonController(
            ReporteCanonService reporteCanonService,
            FestivalService festivalService) {

        this.reporteCanonService = reporteCanonService;
        this.festivalService = festivalService;
    }


    /*
     * Pantalla inicial del reporte.
     *
     * Muestra los Festivales disponibles para que
     * el Administrador seleccione cuál desea consultar.
     */
    @GetMapping("/admin/reportes/mayores-canones")
    public String seleccionarFestival(Model model) {

        model.addAttribute(
                "festivales",
                festivalService.listarFestivales()
        );

        return "reporte-mayores-canones";
    }


    /*
     * Genera el reporte de mayores cánones
     * para un Festival determinado.
     */
    @GetMapping("/admin/reportes/mayores-canones/{idFestival}")
    public String verReporte(
            @PathVariable Long idFestival,
            Model model,
            RedirectAttributes redirectAttributes) {

        Festival festival =
                festivalService.buscarPorId(idFestival);


        /*
         * Para calcular cánones necesitamos que
         * el Festival tenga sus costos configurados.
         */
        if (festival.getCostos() == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No se puede generar el reporte porque el Festival "
                            + "todavía no tiene costos configurados."
            );

            return "redirect:/admin/reportes/mayores-canones";
        }


        List<ReporteMayoresCanonDTO> reporte =
                reporteCanonService.obtenerMayoresCanones(
                        idFestival
                );


        model.addAttribute(
                "festival",
                festival
        );

        model.addAttribute(
                "reporte",
                reporte
        );

        return "reporte-mayores-canones-detalle";
    }
}