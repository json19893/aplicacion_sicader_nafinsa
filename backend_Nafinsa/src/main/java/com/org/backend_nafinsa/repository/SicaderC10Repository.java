package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderC10;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SicaderC10Repository extends JpaRepository<SicaderC10, Long> {

    Optional<SicaderC10> findByFechaOperacion(LocalDate mesAnio);

    @Query(value = "    SELECT NOMBRE ,FECHA_OP,\n" +
            "    AVG(DECODE(TIPO_CUENTA_ID,'1',nvl(IMPORTE,0))) ACTIVO,\n" +
            "    AVG(DECODE(TIPO_CUENTA_ID,'2',nvl(IMPORTE,0))) PASIVO,\n" +
            "    AVG(DECODE(TIPO_CUENTA_ID,'3',nvl(IMPORTE,0))) CAPITAL\n" +
            "    FROM (\n" +
            "                    SELECT  c.NOMBRE , a.FECHA_OP , b.IMPORTE , b.TIPO_CUENTA_ID  FROM SICADER.SICADER_C10 a INNER JOIN sicader.SICADER_C10_DETALLE b ON \n" +
            "                    a.id=b.REPORTE_ID INNER JOIN SICADER.SICADER_CAT_COBERTURAS c on b.COBERTURA_ID =c.ID \n" +
            "                    WHERE a.FECHA_OP =?1 \n" +
            ") XD \n" +
            "    GROUP BY NOMBRE, FECHA_OP ORDER BY 1", nativeQuery = true)
    public List<Object[]> getAllSicaderC10(LocalDate fechaOperacion);


}
