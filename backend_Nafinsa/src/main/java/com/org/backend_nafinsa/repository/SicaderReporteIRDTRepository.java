package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporteIRDT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Repository
public interface SicaderReporteIRDTRepository extends JpaRepository<SicaderReporteIRDT, Long> {

    Optional<SicaderReporteIRDT> findByFechaOperacion(LocalDate fechaOperacion);
}
