package com.grupo06.epicentrogourmet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo06.epicentrogourmet.dto.RegistroPedidoDTO;
import com.grupo06.epicentrogourmet.entity.Festival;
import com.grupo06.epicentrogourmet.entity.ItemPedido;
import com.grupo06.epicentrogourmet.entity.Pedido;
import com.grupo06.epicentrogourmet.entity.Plato;
import com.grupo06.epicentrogourmet.entity.UnidadVenta;
import com.grupo06.epicentrogourmet.repository.PedidoRepository;
import com.grupo06.epicentrogourmet.repository.PlatoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PlatoRepository platoRepository;
    private final UnidadVentaService unidadVentaService;

    public PedidoService(
            PedidoRepository pedidoRepository,
            PlatoRepository platoRepository,
            UnidadVentaService unidadVentaService) {

        this.pedidoRepository = pedidoRepository;
        this.platoRepository = platoRepository;
        this.unidadVentaService = unidadVentaService;
    }

    // =====================================================
    // OBTENER UNIDAD DEL EMPLEADO
    // =====================================================

    @Transactional(readOnly = true)
    public UnidadVenta obtenerUnidadEmpleado(
            String username) {

        UnidadVenta unidad =
                unidadVentaService
                        .buscarUnidadDelEmpleado(username);

        if (unidad == null) {
            throw new IllegalArgumentException(
                    "El empleado no tiene una Unidad de Venta asignada."
            );
        }

        return unidad;
    }

    // =====================================================
    // LISTAR PLATOS DE LA UNIDAD
    // =====================================================

    @Transactional(readOnly = true)
    public List<Plato> listarPlatosDisponibles(
            String username) {

        UnidadVenta unidad =
                obtenerUnidadEmpleado(username);

        return platoRepository
                .findAllByUnidadVenta_IdUnidadVenta(
                        unidad.getIdUnidadVenta()
                );
    }

    // =====================================================
    // REGISTRAR PEDIDO
    // =====================================================

    @Transactional
    public Pedido registrarPedido(
            String username,
            RegistroPedidoDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException(
                    "Los datos del pedido son obligatorios."
            );
        }

        UnidadVenta unidad =
                obtenerUnidadEmpleado(username);

        Festival festival =
                unidad.getFestival();

        if (festival == null) {
            throw new IllegalArgumentException(
                    "La Unidad de Venta no tiene un Festival asociado."
            );
        }

        Plato plato =
                platoRepository
                        .findById(dto.getIdPlato())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "El plato seleccionado no existe."
                                )
                        );

        if (plato.getUnidadVenta() == null
                || !plato.getUnidadVenta()
                         .getIdUnidadVenta()
                         .equals(unidad.getIdUnidadVenta())) {

            throw new IllegalArgumentException(
                    "El plato seleccionado no pertenece a tu Unidad de Venta."
            );
        }

        if (dto.getCantidad() <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor a cero."
            );
        }

        Pedido pedido =
                new Pedido(
                        festival,
                        unidad
                );

        ItemPedido item =
                new ItemPedido(
                        plato,
                        dto.getCantidad()
                );

        /*
         * agregarItem también establece:
         *
         * item.setPedido(this)
         *
         * Esto mantiene correctamente la relación
         * bidireccional Pedido <-> ItemPedido.
         */
        pedido.agregarItem(item);

        /*
         * No hace falta guardar ItemPedido manualmente.
         *
         * Pedido posee:
         * cascade = CascadeType.ALL
         *
         * Por lo tanto Hibernate guarda:
         *
         * Pedido
         *   +
         * ItemPedido
         */
        return pedidoRepository.save(pedido);
    }


    @Transactional(readOnly = true)
    public java.util.Map<UnidadVenta, java.math.BigDecimal> calcularRankingFacturacion(Long idFestival) {

        List<Pedido> pedidos = pedidoRepository.buscarPedidosPorFestivalConItems(idFestival);

        java.util.Map<UnidadVenta, java.math.BigDecimal> ranking = new java.util.LinkedHashMap<>();

        for (Pedido pedido : pedidos) {
            UnidadVenta unidad = pedido.getUnidadVenta();

            if (unidad == null) {
                continue;
            }

            java.math.BigDecimal total = pedido.calcularTotalVenta();

            ranking.merge(unidad, total, java.math.BigDecimal::add);
        }

        return ranking.entrySet().stream().sorted(java.util.Map.Entry.<UnidadVenta, java.math.BigDecimal>comparingByValue().reversed()).collect(
                java.util.stream.Collectors.toMap(java.util.Map.Entry::getKey, java.util.Map.Entry::getValue, (a, b) -> a, java.util.LinkedHashMap::new));
    }

}