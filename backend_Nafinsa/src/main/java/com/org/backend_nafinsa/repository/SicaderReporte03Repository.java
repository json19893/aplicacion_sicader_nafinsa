package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte03;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository("sicaderReporte03Repository")
public interface SicaderReporte03Repository extends JpaRepository<SicaderReporte03, Long> {
    Optional<SicaderReporte03> findByFechaOperacion(LocalDate fechaOperacion);
}
