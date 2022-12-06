package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderInteresesMargenDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderInteresesMargenDetalleRepository extends JpaRepository<SicaderInteresesMargenDetalle, Long> {
}
