package com.grupo06.epicentrogourmet.controller;

import com.grupo06.epicentrogourmet.entity.Pedido;
import com.grupo06.epicentrogourmet.service.PedidoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ReporteVentasController {

    private final PedidoService pedidoService;

    public ReporteVentasController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/responsable/ventas")
    public String mostrarReporteVentas(Authentication authentication, Model model) {

        List<Pedido> pedidos = pedidoService.listarPedidosDeUnidad(authentication.getName());

        BigDecimal recaudacion = pedidoService.calcularRecaudacionUnidad(authentication.getName());

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("recaudacion", recaudacion);
        model.addAttribute("cantidadPedidos", pedidos.size());

        return "reportes-ventas";
    }
}
