package com.grupo06.epicentrogourmet.controller;

import java.math.BigDecimal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.service.UnidadVentaService;

@Controller
public class CanonController {

    private final UnidadVentaService unidadVentaService;

    public CanonController(
            UnidadVentaService unidadVentaService) {

        this.unidadVentaService = unidadVentaService;
    }

    @GetMapping("/admin/unidades/{idUnidadVenta}/canon")
    public String verCanon(
            @PathVariable Long idUnidadVenta,
            Model model,
            RedirectAttributes redirectAttributes) {

        UnidadVenta unidad =
                unidadVentaService.buscarPorId(idUnidadVenta);

        /*
         * Si el Festival todavía no tiene costos configurados,
         * no intentamos calcular el canon.
         */
        if (unidad.getFestival() == null
                || unidad.getFestival().getCostos() == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No se puede calcular el canon porque el Festival "
                            + "todavía no tiene costos configurados."
            );

            return "redirect:/admin/festivales/"
                    + unidad.getFestival().getIdFestival()
                    + "/unidades";
        }

        BigDecimal canon =
                unidadVentaService.calcularCanon(idUnidadVenta);

        model.addAttribute(
                "unidad",
                unidad
        );

        model.addAttribute(
                "festival",
                unidad.getFestival()
        );

        model.addAttribute(
                "costos",
                unidad.getFestival().getCostos()
        );

        model.addAttribute(
                "canon",
                canon
        );

        return "canon-unidad";
    }

    @GetMapping("/responsable/mi-unidad/canon")
    public String verCanonMiUnidad(
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {

        UnidadVenta unidad =
                unidadVentaService.buscarUnidadDelResponsable(
                        authentication.getName()
                );

        if (unidad == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No tenés una unidad de venta asignada."
            );

            return "redirect:/responsable/empleados";
        }

        if (unidad.getFestival() == null
                || unidad.getFestival().getCostos() == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No se puede calcular el canon porque el Festival "
                            + "todavía no tiene costos configurados."
            );

            return "redirect:/responsable/empleados";
        }

        BigDecimal canon =
                unidadVentaService.calcularCanon(
                        unidad.getIdUnidadVenta()
                );

        model.addAttribute("unidad", unidad);
        model.addAttribute("festival", unidad.getFestival());
        model.addAttribute("costos", unidad.getFestival().getCostos());
        model.addAttribute("canon", canon);
        model.addAttribute("esResponsable", true);

        return "canon-unidad";
    }
}