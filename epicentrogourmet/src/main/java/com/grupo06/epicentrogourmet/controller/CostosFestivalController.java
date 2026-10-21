package com.grupo06.epicentrogourmet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.grupo06.epicentrogourmet.dto.CostosFestivalDTO;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.service.CostosFestivalService;
import com.grupo06.epicentrogourmet.service.FestivalService;

import jakarta.validation.Valid;

@Controller
public class CostosFestivalController {

    private final CostosFestivalService costosFestivalService;
    private final FestivalService festivalService;

    public CostosFestivalController(
            CostosFestivalService costosFestivalService,
            FestivalService festivalService) {

        this.costosFestivalService = costosFestivalService;
        this.festivalService = festivalService;
    }


    /*
     * Pantalla general para seleccionar
     * a qué Festival configurar sus costos.
     */
    @GetMapping("/admin/festivales/costos")
    public String listarFestivalesParaCostos(Model model) {

        model.addAttribute(
                "festivales",
                festivalService.listarFestivales()
        );

        return "costos-festivales";
    }


    /*
     * Formulario de costos de un Festival específico.
     */
    @GetMapping("/admin/festivales/{idFestival}/costos")
    public String mostrarFormulario(
            @PathVariable Long idFestival,
            Model model) {

        Festival festival =
                festivalService.buscarPorId(idFestival);

        CostosFestivalDTO costosDTO =
                costosFestivalService.obtenerCostosDTO(idFestival);

        model.addAttribute("festival", festival);
        model.addAttribute("costosDTO", costosDTO);

        return "costos-festival-form";
    }


    /*
     * Guarda o actualiza los costos.
     */
    @PostMapping("/admin/festivales/{idFestival}/costos")
    public String guardarCostos(
            @PathVariable Long idFestival,
            @Valid
            @ModelAttribute("costosDTO")
            CostosFestivalDTO costosDTO,
            BindingResult bindingResult,
            Model model) {

        Festival festival =
                festivalService.buscarPorId(idFestival);

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "festival",
                    festival
            );

            return "costos-festival-form";
        }

        costosFestivalService.guardarCostos(
                idFestival,
                costosDTO
        );

        return "redirect:/admin/festivales/"
                + idFestival
                + "/costos?success";
    }
}