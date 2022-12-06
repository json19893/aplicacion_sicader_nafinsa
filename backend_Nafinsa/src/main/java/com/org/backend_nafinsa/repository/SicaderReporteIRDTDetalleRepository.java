package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporteIRDTDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderReporteIRDTDetalleRepository extends JpaRepository<SicaderReporteIRDTDetalle, Long> {

    void deleteByReporteId(Long reporteId);

    List<SicaderReporteIRDTDetalle> findByReporteId(Long reporteId);
}
