package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderConciliaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SicaderConciliacionesRepository  extends JpaRepository<SicaderConciliaciones,Long> {

    @Query(value = "SELECT sc.FECHA , td.NOMNBRE  , sc.CUENTA , m.MON_NOMBRE  ,sc.ENTE , sc.IMPORTE_SIF , sc.IMPORTE_OP , sc.IMPORTE_SIF - sc.IMPORTE_OP FROM SICADER.SICADER_CONCILIACIONES sc \n" +
            "INNER JOIN SICADER.SICADER_CON_EJECUCIONES sce ON sc.EJECUCION_ID =SCE.ID \n" +
            "INNER JOIN SICADER.SICADER_CAT_TIPO_DERIVADOS  td ON td.ID = sc.TIPO_DERIVADO_ID  \n" +
            "INNER JOIN MONEDAS m ON m.MON_CLAVE = sc.MONEDA \n" +
            "WHERE sc.FECHA = 1?", nativeQuery = true)
    List<Object[]> getSicaderConciliaciones(LocalDate fechaOperacion);
}
