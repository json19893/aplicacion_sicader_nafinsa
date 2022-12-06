package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderEdoCtaAsignaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderEdoCtaAsignaDetalleRepository extends JpaRepository<SicaderEdoCtaAsignaDetalle, Long> {
}
