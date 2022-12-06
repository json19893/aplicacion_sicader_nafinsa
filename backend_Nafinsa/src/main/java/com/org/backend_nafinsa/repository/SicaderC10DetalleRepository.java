package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderC10Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderC10DetalleRepository extends JpaRepository<SicaderC10Detalle, Long> {
}
