package com.grupo06.epicentrogourmet.repository;

import java.util.List;

import com.grupo06.epicentrogourmet.entity.Plato;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {

    Plato findByIdPlato(Long idPlato);

    List<Plato> findAllByUnidadVenta_IdUnidadVenta(Long idUnidadVenta);

    @Query("""
            SELECT item.plato AS plato,
                COUNT(DISTINCT pedido.idPedido) AS cantidadPedidos
            FROM ItemPedido item
            JOIN item.pedido pedido
            WHERE pedido.unidadVenta.idUnidadVenta = :idUnidadVenta
                AND pedido.festival.idFestival = :idFestival
                AND item.plato.unidadVenta.idUnidadVenta = :idUnidadVenta
            GROUP BY item.plato
            ORDER BY COUNT(DISTINCT pedido.idPedido) DESC, item.plato.idPlato ASC
            """)
    List<PlatoEstrellaProjection> buscarPlatoEstrella(
            @Param("idUnidadVenta") Long idUnidadVenta,
            @Param("idFestival") Long idFestival,
            Pageable pageable);
}
