package com.grupo06.epicentrogourmet.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grupo06.epicentrogourmet.dto.RegistroPedidoDTO;
import com.grupo06.epicentrogourmet.entity.Plato;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.service.PedidoService;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(
            PedidoService pedidoService) {

        this.pedidoService =
                pedidoService;
    }

    // =====================================================
    // FORMULARIO
    // =====================================================

    @GetMapping("/empleado/pedidos/nuevo")
    public String mostrarFormulario(
            Model model,
            Authentication authentication) {

        if (authentication == null) {
            return "redirect:/login";
        }

        try {

            cargarDatosFormulario(
                    model,
                    authentication.getName()
            );

            if (!model.containsAttribute("registroPedido")) {

                model.addAttribute(
                        "registroPedido",
                        new RegistroPedidoDTO()
                );
            }

            return "alta-pedido";

        } catch (IllegalArgumentException e) {

            model.addAttribute(
                    "errorGeneral",
                    e.getMessage()
            );

            return "alta-pedido";
        }
    }

    // =====================================================
    // REGISTRAR
    // =====================================================

    @PostMapping("/empleado/pedidos/nuevo")
    public String registrarPedido(
            @Valid
            @ModelAttribute("registroPedido")
            RegistroPedidoDTO dto,

            BindingResult bindingResult,

            Model model,

            Authentication authentication,

            RedirectAttributes redirectAttributes) {

        if (authentication == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {

            cargarDatosFormulario(
                    model,
                    authentication.getName()
            );

            return "alta-pedido";
        }

        try {

            var pedido =
                    pedidoService.registrarPedido(
                            authentication.getName(),
                            dto
                    );

            redirectAttributes.addFlashAttribute(
                    "mensajeExito",
                    "Pedido registrado correctamente. "
                    + "Número de pedido: "
                    + pedido.getIdPedido()
            );

            return "redirect:/empleado/pedidos/nuevo";

        } catch (IllegalArgumentException e) {

            cargarDatosFormulario(
                    model,
                    authentication.getName()
            );

            model.addAttribute(
                    "errorGeneral",
                    e.getMessage()
            );

            return "alta-pedido";
        }
    }

    // =====================================================
    // DATOS PARA LA VISTA
    // =====================================================

    private void cargarDatosFormulario(
            Model model,
            String username) {

        UnidadVenta unidad =
                pedidoService
                        .obtenerUnidadEmpleado(username);

        List<Plato> platos =
                pedidoService
                        .listarPlatosDisponibles(username);

        model.addAttribute(
                "unidad",
                unidad
        );

        model.addAttribute(
                "festival",
                unidad.getFestival()
        );

        model.addAttribute(
                "platos",
                platos
        );

        model.addAttribute(
                "fechaActual",
                LocalDate.now()
        );
    }
}