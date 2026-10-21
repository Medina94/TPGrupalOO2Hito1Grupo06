package com.grupo06.epicentrogourmet.repository;

import com.grupo06.epicentrogourmet.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
    // Busca todos los ítems que pertenecen a un pedido específico
    List<ItemPedido> findByPedido_IdPedido(Long idPedido);
}