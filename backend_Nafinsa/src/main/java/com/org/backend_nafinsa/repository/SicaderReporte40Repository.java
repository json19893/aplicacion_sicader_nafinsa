package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte40;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface SicaderReporte40Repository extends JpaRepository<SicaderReporte40, Long> {

    Optional<SicaderReporte40> findByFechaOperacion(LocalDate fechaOperacion);
}
