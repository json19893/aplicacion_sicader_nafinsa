package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderPerdidaGananciaInflaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SicaderPerdidaGananciaInflaDetalleRepo extends JpaRepository<SicaderPerdidaGananciaInflaDetalle, Long> {
}
