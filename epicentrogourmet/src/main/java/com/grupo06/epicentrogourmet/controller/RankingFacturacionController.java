package com.grupo06.epicentrogourmet.controller;

import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.repository.FestivalRepository;
import com.grupo06.epicentrogourmet.service.PedidoService;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RankingFacturacionController {

    private final FestivalRepository festivalRepository;
    private final PedidoService pedidoService;

    public RankingFacturacionController(FestivalRepository festivalRepository, PedidoService pedidoService) {
        this.festivalRepository = festivalRepository;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/admin/reportes/ranking-facturacion")
    public String mostrarRanking(@RequestParam(required = false) Long idFestival, Model model) {

        model.addAttribute("festivales", festivalRepository.findAllByOrderByFechaInicioAsc());

        if (idFestival != null) {
            Festival festival = festivalRepository.findById(idFestival).orElseThrow(() -> new IllegalArgumentException("El festival seleccionado no existe"));

            Map<UnidadVenta, BigDecimal> ranking = pedidoService.calcularRankingFacturacion(idFestival);

            model.addAttribute("festivalSeleccionado", festival);
            model.addAttribute("ranking", ranking);
        }

        return "ranking-facturacion";
    }
}
