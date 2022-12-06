package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderSlVsSideca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SicaderSlVsSidecaRepository extends JpaRepository<SicaderSlVsSideca, Long> {

    Optional<SicaderSlVsSideca> findByFechaOperacion(Date fechaOperacion);

    @Query(value = "select  nombre, fecha_op, RECIBIR_SL, ENTREGAR_SL, RECIBIR_SIDECA, ENTREGAR_SIDECA from  \n" +
            "sicader.sicader_sl_vs_sideca a inner  join   sicader.SICADER_SL_VS_SIDECA_detalle b\n" +
            "on a.id =b.reporte_id  inner join sicader.sicader_cat_socios_liquidadores c on b.socio_id=c.id\n" +
            "where  a.fecha_op= ?1  ", nativeQuery = true)

    public List<Object[]> getSicaderReporteMensual(LocalDate fechaOperacion);
}
