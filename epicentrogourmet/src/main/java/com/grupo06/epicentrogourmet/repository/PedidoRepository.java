package com.grupo06.epicentrogourmet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.grupo06.epicentrogourmet.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("""
        SELECT DISTINCT p
        FROM Pedido p
        LEFT JOIN FETCH p.items i
        LEFT JOIN FETCH i.plato
        WHERE p.festival.idFestival = :idFestival
    """)

    List<Pedido> buscarPedidosPorFestivalConItems(@Param("idFestival") Long idFestival);

    @Query("""
        SELECT DISTINCT p
        FROM Pedido p
        LEFT JOIN FETCH p.items i
        LEFT JOIN FETCH i.plato
        WHERE p.unidadVenta.idUnidadVenta = :idUnidadVenta
        ORDER BY p.idPedido DESC
    """)
    List<Pedido> buscarPedidosPorUnidadConItems(@Param("idUnidadVenta") Long idUnidadVenta);
}
