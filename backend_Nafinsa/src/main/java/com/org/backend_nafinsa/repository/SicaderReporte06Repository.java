package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte06;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface SicaderReporte06Repository extends JpaRepository<SicaderReporte06, Long> {

    Optional<SicaderReporte06> findByFechaOperacion(LocalDate fechaOperacion);
}
