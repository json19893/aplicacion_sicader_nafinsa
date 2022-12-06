package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderReporte01;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository("sicaderReporte01Repository")
public interface SicaderReporte01Repository extends JpaRepository<SicaderReporte01, Long> {

    Optional<SicaderReporte01> findByFechaOperacion(LocalDate fechaOperacion);

    @Query(value = "select 'REPORTE01',  '01_REP_FUTUROS_POSICION.TXT',FECHA_OP, ESTATUS, ID  from sicader.sicader_reporte01  where fecha_op = ?1 UNION \n" +
            "select 'REPORTE03', '03_FX_FORWARD_POSITIONS.TXT',FECHA_OP, ESTATUS  ,ID from sicader.sicader_reporte03  where fecha_op = ?1 UNION\n" +
            "select 'REPORTE06', '06_SWAP_RESUMEN_POSICION.TXT', FECHA_OP, ESTATUS ,ID from sicader.sicader_reporte06  where fecha_op = ?1 UNION\n" +
            "select 'REPORTE40', '40_JOURNAL_ENTRIES_DETAIL.TXT',FECHA_OP, ESTATUS  ,ID from sicader.sicader_reporte40  where fecha_op = ?1 UNION\n" +
            "select 'REPORTE42','42_GARANTIAS_CONTRAPARTE.TXT' , FECHA_OP, ESTATUS  ,ID from sicader.sicader_reporte42  where fecha_op = ?1 UNION\n" +
            "select 'REPORTEIRDT', 'IRDT.xlsx' , FECHA_OP, ESTATUS ,ID  from sicader.sicader_irdt WHERE FECHA_OP= ?1 ", nativeQuery = true)
    public List<Object[]> getSicaderResumenCarga(LocalDate fechaOperacion);


}

