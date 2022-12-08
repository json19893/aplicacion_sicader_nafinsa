package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderMoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface SicaderMonedaRepository extends JpaRepository<SicaderMoneda, Integer> {

    @Query(value = "SELECT A.PAR_FECHA , A.MON_CLAVE , B.MON_NOMBRE  , A.PAR_PESO   FROM PARIDADES_VALORIZACION A INNER JOIN\n" +
            "MONEDAS B ON A.MON_CLAVE =B.MON_CLAVE \n" +
            "WHERE A.PAR_FECHA  BETWEEN ?1 AND ?2\n" +
            "AND A.MON_CLAVE =?3", nativeQuery = true)
    public List<Object[]> getSicaderCierreJornada(LocalDate fechaIni, LocalDate FechaFin, Long monClave);

    @Query(value = "SELECT A.PAR_FECHA , A.MON_CLAVE , B.MON_NOMBRE  , A.PAR_PESO   FROM PARIDADES_VALORIZACION A INNER JOIN\n" +
            "MONEDAS B ON A.MON_CLAVE =B.MON_CLAVE \n" +
            "WHERE A.PAR_FECHA  BETWEEN ?1 AND ?2 " , nativeQuery = true)
    public List<Object[]> getSicaderCierreJornadaFecha(LocalDate fechaIni, LocalDate FechaFin );
}
