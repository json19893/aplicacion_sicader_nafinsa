package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderInteresesMargen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SicaderInteresesMargenRepository extends JpaRepository<SicaderInteresesMargen, Long> {

    Optional<SicaderInteresesMargen> findByFechaOperacion(LocalDate fechaOperacion);

    @Query(value = "SELECT TO_CHAR(A.FECHA_OP,'YYYY-MM-DD') , C.NOMBRE  , NVL( B.INGRESOS,0) , nvl(B.EGRESOS,0)  FROM SICADER.SICADER_INTERESES_MARGEN A\n" +
            "INNER JOIN SICADER.SICADER_INTERESES_MARGEN_DETALLE B ON \n" +
            "A.ID =B.REPORTE_ID INNER JOIN SICADER.SICADER_CAT_CONTRAPARTES C ON C.ID =B.CONTRAPARTE_ID \n" +
            "WHERE A.FECHA_OP = ?1", nativeQuery = true)
    public List<Object[]> getSicaderInteresesMargen(LocalDate fechaOperacion);


}
