package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte42Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderReporte42DetalleRepository extends JpaRepository<SicaderReporte42Detalle, Long> {

    void deleteByReporteId(Long reporteId);

    List<SicaderReporte42Detalle> findByReporteId(Long reporteId);

}
