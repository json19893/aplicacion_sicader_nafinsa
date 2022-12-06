package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte01Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderReporte01DetalleRepository extends JpaRepository<SicaderReporte01Detalle, Long> {

    void deleteByReporteId(Long reporteId);

    List<SicaderReporte01Detalle> findByReporteId(Long reporteId);

}
