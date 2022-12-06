package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.dto.ArchivoResumenDto;
import com.org.backend_nafinsa.entidad.SicaderResumenArchivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SicaderResumenArchivoRepository extends JpaRepository<SicaderResumenArchivo, Long> {

    List<SicaderResumenArchivo> findByFechaOperacionOrderById(LocalDate fechaOperacion);

}
