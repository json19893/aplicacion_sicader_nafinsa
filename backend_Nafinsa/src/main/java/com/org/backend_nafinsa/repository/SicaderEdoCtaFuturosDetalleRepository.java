package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderEdoCtaFuturosDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderEdoCtaFuturosDetalleRepository extends JpaRepository<SicaderEdoCtaFuturosDetalle, Long> {
}
