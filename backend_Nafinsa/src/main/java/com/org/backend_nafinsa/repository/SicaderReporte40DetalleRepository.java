package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte40Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderReporte40DetalleRepository extends JpaRepository<SicaderReporte40Detalle, Long> {

    void deleteByReporteId(Long reporteId);

    List<SicaderReporte40Detalle> findByReporteId(Long reporteId);
}
