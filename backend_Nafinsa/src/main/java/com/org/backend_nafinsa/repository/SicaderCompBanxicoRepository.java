package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCompBanxico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SicaderCompBanxicoRepository extends JpaRepository<SicaderCompBanxico, Long> {

    Optional<SicaderCompBanxico> findByFechaOperacion(LocalDate fechaOperacion);


    @Query(value = "SELECT a.FECHA_OP , b.VAL_BANXICO  FROM SICADER.SICADER_COMP_BANXICO a INNER JOIN  \n" +
            "SICADER.SICADER_COMP_BANXICO_DETALLE b ON a.ID = b.REPORTE_ID \n" +
            "WHERE A.FECHA_OP = ?1", nativeQuery = true)
    List<Object[]> getSicaderBanxico(LocalDate fechaOperacion);

}
