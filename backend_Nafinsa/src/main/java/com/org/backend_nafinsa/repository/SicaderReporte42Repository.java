package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte42;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface SicaderReporte42Repository extends JpaRepository<SicaderReporte42, Long> {

    Optional<SicaderReporte42> findByFechaOperacion(LocalDate fechaOperacion);
}
