package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte06Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderReporte06DetalleRepository extends JpaRepository<SicaderReporte06Detalle, Long> {
    void deleteByReporteId(Long reporteId);

    List<SicaderReporte06Detalle> findByReporteId(Long reporteId);
}
