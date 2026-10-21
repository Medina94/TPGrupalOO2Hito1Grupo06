package com.grupo06.epicentrogourmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo06.epicentrogourmet.entity.CierreCaja;
import java.util.List;

@Repository
public interface ICierreCajaRepository extends JpaRepository<CierreCaja, Long> {
    
    List<CierreCaja> findByUnidadVentaIdUnidadVenta(Long idUnidadVenta);
}