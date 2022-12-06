package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderValidacion;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SicaderValidacionRepository extends JpaRepository<SicaderValidacion, Long> {

    boolean existsByFechaOperacion(LocalDate fechaOperacion);

    boolean existsByFechaOperacionAndAndTipoDerivadoInAndAndValidacionIn(LocalDate fechaOperacion, List<Long> tipoDerivado, List<Long> Validacion);
}
