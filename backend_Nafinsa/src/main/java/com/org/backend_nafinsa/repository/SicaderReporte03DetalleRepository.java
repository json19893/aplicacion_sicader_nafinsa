package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte03Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderReporte03DetalleRepository extends JpaRepository<SicaderReporte03Detalle, Long> {

    void deleteByReporteId(Long reporteId);

    List<SicaderReporte03Detalle> findByReporteId(Long reporteId);
}
