package com.grupo06.epicentrogourmet.repository;

import com.grupo06.epicentrogourmet.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

    // Para el punto 6: todos los pedidos de la unidad.
    List<Pedido> findByUnidadVenta_IdUnidadVenta(Long idUnidadVenta);

    // Para el punto 7: pedidos de la unidad entre dos fechas.
    List<Pedido> findByUnidadVenta_IdUnidadVentaAndFechaBetween(
            Long idUnidadVenta,
            LocalDate fechaDesde,
            LocalDate fechaHasta
    );

    // Para el cierre de caja: pedidos de la unidad en una fecha específica.
    List<Pedido> findByUnidadVenta_IdUnidadVentaAndFecha(
            Long idUnidadVenta,
            LocalDate fecha
    );
}