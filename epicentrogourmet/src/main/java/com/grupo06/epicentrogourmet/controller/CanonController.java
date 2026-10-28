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


    // =====================================================
    // CANON - ADMINISTRADOR
    // =====================================================

    @GetMapping("/admin/unidades/{idUnidadVenta}/canon")
    public String verCanon(
            @PathVariable Long idUnidadVenta,
            Model model,
            RedirectAttributes redirectAttributes) {


        UnidadVenta unidad =
                unidadVentaService.buscarPorId(
                        idUnidadVenta
                );


        /*
         * Si la Unidad no posee Festival o el Festival
         * todavía no tiene costos configurados,
         * no intentamos calcular el canon.
         */
        if (unidad.getFestival() == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "La Unidad de Venta no tiene un Festival asociado."
            );

            return "redirect:/admin";
        }


        if (unidad.getFestival().getCostos() == null) {

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
                unidadVentaService.calcularCanon(
                        idUnidadVenta
                );


        prepararModeloCanon(
                model,
                unidad,
                canon,
                false
        );


        return "canon-unidad";
    }


    // =====================================================
    // CANON - RESPONSABLE
    // =====================================================

    @GetMapping("/responsable/mi-unidad/canon")
    public String verCanonMiUnidad(
            Authentication authentication,
            Model model,
            RedirectAttributes redirectAttributes) {


        UnidadVenta unidad =
                unidadVentaService
                        .buscarUnidadDelResponsable(
                                authentication.getName()
                        );


        /*
         * El Responsable solamente puede consultar
         * el canon de su propia Unidad.
         */
        if (unidad == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No tenés una Unidad de Venta asignada."
            );

            return "redirect:/responsable";
        }


        if (unidad.getFestival() == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Tu Unidad de Venta no tiene un Festival asociado."
            );

            return "redirect:/responsable";
        }


        if (unidad.getFestival().getCostos() == null) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "No se puede calcular el canon porque el Festival "
                            + "todavía no tiene costos configurados."
            );

            return "redirect:/responsable";
        }


        BigDecimal canon =
                unidadVentaService.calcularCanon(
                        unidad.getIdUnidadVenta()
                );


        prepararModeloCanon(
                model,
                unidad,
                canon,
                true
        );


        return "canon-unidad";
    }


    // =====================================================
    // PREPARAR MODELO COMPARTIDO
    // =====================================================

    private void prepararModeloCanon(
            Model model,
            UnidadVenta unidad,
            BigDecimal canon,
            boolean esResponsable) {


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


        model.addAttribute(
                "esResponsable",
                esResponsable
        );
    }
}