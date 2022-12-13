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
            "WHERE sc.FECHA = ?1", nativeQuery = true)
    List<Object[]> getSicaderConciliaciones(LocalDate fechaOperacion);

    @Query(value = "select a.fecha_op, a.fecha_ejecucion, a.usu_ejecucion, case when a.tipo_conciliacion= 'D' then 'Diaria' else 'Mensual' end  as tipo, b.nomnbre, \n" +
            "case when a.estatus ='E' then 'Exitosa' \n" +
            "when a.estatus ='D' then 'Con diferencias' \n" +
            "else 'Con Errores' end  as  estatus   from  sicader.sicader_con_ejecuciones a \n" +
            "inner join sicader.sicader_cat_tipo_derivados b on a.derivado_id=b.id\n" +
            "where fecha_op BETWEEN ?1 and ?2\n" +
            "and fecha_ejecucion BETWEEN ?3 and ?4\n" +
            "and usu_ejecucion=?5\n" +
            "and tipo_conciliacion=?6\n" +
            "and estatus=?7\n" +
            "and derivado_id=?8", nativeQuery = true)
    List<Object[]> getEstatusConciliaciones(LocalDate fechaOperacionIni,
                                            LocalDate fechaOperacionFin,
                                            LocalDate fechaVencimientoIni,
                                            LocalDate fechaVencimientoFin,
                                            String usuario,
                                            String tipoConciliacion,
                                            String estatus,
                                            String derivado);

    @Query(value = "select a.fecha_op, a.fecha_ejecucion, a.usu_ejecucion, case when a.tipo_conciliacion= 'D' then 'Diaria' else 'Mensual' end  as www, b.nomnbre, \n" +
            "case when a.estatus ='E' then 'Exitosa' \n" +
            "when a.estatus ='D' then 'Con diferencias' \n" +
            "else 'Con Errores' end \n" +
            "from  sicader.sicader_con_ejecuciones a \n" +
            "inner join sicader.sicader_cat_tipo_derivados b on a.derivado_id=b.id \n" +
            "where a.id = (select max(id) from sicader.sicader_con_ejecuciones)", nativeQuery = true)
    List<Object[]> getEstatusConciliacionesUltima();



}
