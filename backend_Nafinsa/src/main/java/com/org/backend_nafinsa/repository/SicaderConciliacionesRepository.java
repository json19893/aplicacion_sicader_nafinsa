package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderConciliaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SicaderConciliacionesRepository  extends JpaRepository<SicaderConciliaciones,Long> {

    @Query(value = "SELECT sc.FECHA , td.NOMNBRE  ,  \n" +
    		"sc.CUENTA||'-'||sc.SUBCUENTA1||'-'||sc.SUBCUENTA2||'-'||sc.SUBCUENTA3||'-'||sc.SUBCUENTA4||'-'||sc.SUBCUENTA5||'-'||sc.SUBCUENTA6||'-'||sc.SUBCUENTA7 AS cuenta , \n" +
    		"sc.MONEDA  ,sc.ENTE , sc.IMPORTE_SIF , sc.IMPORTE_OP , sc.IMPORTE_SIF - sc.IMPORTE_OP, sce.TIPO_CONCILIACION FROM SICADER.SICADER_CONCILIACIONES sc \n" +
            "INNER JOIN SICADER.SICADER_CON_EJECUCIONES sce ON sc.EJECUCION_ID =SCE.ID \n" +
            "INNER JOIN SICADER.SICADER_CAT_TIPO_DERIVADOS  td ON td.ID = sc.TIPO_DERIVADO_ID  \n" +
            "INNER JOIN MONEDAS m ON m.MON_CLAVE = sc.MONEDA \n" +
            "WHERE sc.FECHA = ?1 \n" +
            "AND sc.TIPO_CONCILIACION = ?2 \n" +
            "AND sc.TIPO_DERIVADO_ID = ?3 order by sc.FECHA, sc.TIPO_DERIVADO_ID", nativeQuery = true)
    List<Object[]> getSicaderConciliaciones(LocalDate fechaOperacion,String  tipoConciliacion, Long idDerivado);

    @Query(value = "SELECT sc.FECHA , td.NOMNBRE  ,  \n" +
            "sc.CUENTA||'-'||sc.SUBCUENTA1||'-'||sc.SUBCUENTA2||'-'||sc.SUBCUENTA3||'-'||sc.SUBCUENTA4||'-'||sc.SUBCUENTA5||'-'||sc.SUBCUENTA6||'-'||sc.SUBCUENTA7 AS cuenta , \n" +
            "sc.MONEDA  ,sc.ENTE , sc.IMPORTE_SIF , sc.IMPORTE_OP , sc.IMPORTE_SIF - sc.IMPORTE_OP, sce.TIPO_CONCILIACION FROM SICADER.SICADER_CONCILIACIONES sc \n" +
            "INNER JOIN SICADER.SICADER_CON_EJECUCIONES sce ON sc.EJECUCION_ID =SCE.ID \n" +
            "INNER JOIN SICADER.SICADER_CAT_TIPO_DERIVADOS  td ON td.ID = sc.TIPO_DERIVADO_ID  \n" +
            "INNER JOIN MONEDAS m ON m.MON_CLAVE = sc.MONEDA \n" +
            "WHERE sc.FECHA = ?1 \n" +
            "AND sc.TIPO_CONCILIACION = ?2 order by sc.FECHA, sc.TIPO_DERIVADO_ID" , nativeQuery = true)
    List<Object[]> getAllSicaderConciliaciones(LocalDate fechaOperacion,String  tipoConciliacion);

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

    @Query(value = "select a.fecha_op, a.fecha_ejecucion, a.usu_ejecucion, case when a.tipo_conciliacion= 'D' then 'Diaria' else 'Mensual' end  as tipo, b.nomnbre, \n" +
            "case when a.estatus ='E' then 'Exitosa' \n" +
            "when a.estatus ='D' then 'Con diferencias' \n" +
            "else 'Con Errores' end  as  estatus , a.id  from  sicader.sicader_con_ejecuciones a \n" +
            "inner join sicader.sicader_cat_tipo_derivados b on a.derivado_id=b.id\n" +
            "where fecha_op BETWEEN ?1 and ?2\n" +
            "and fecha_ejecucion BETWEEN ?3 and ?4 ORDER BY a.fecha_op,a.derivado_id ", nativeQuery = true)
    List<Object[]> getEstatusConciliacionesFechaEjec(LocalDate fechaOperacionIni,
                                            LocalDate fechaOperacionFin,
                                            LocalDate fechaVencimientoIni,
                                            LocalDate fechaVencimientoFin);

    @Query(value = "select a.fecha_op, a.fecha_ejecucion, a.usu_ejecucion, case when a.tipo_conciliacion= 'D' then 'Diaria' else 'Mensual' end  as tipo, b.nomnbre, \n" +
            "case when a.estatus ='E' then 'Exitosa' \n" +
            "when a.estatus ='D' then 'Con diferencias' \n" +
            "else 'Con Errores' end  as  estatus , a.id  from  sicader.sicader_con_ejecuciones a \n" +
            "inner join sicader.sicader_cat_tipo_derivados b on a.derivado_id=b.id\n" +
            "where fecha_op BETWEEN ?1 and ?2  ORDER BY a.fecha_op,a.derivado_id ", nativeQuery = true)
    List<Object[]> getEstatusConciliacionesSinFechaEjec(LocalDate fechaOperacionIni,
                                                     LocalDate fechaOperacionFin);

    @Query(value = "select a.fecha_op, a.fecha_ejecucion, a.usu_ejecucion, case when a.tipo_conciliacion= 'D' then 'Diaria' else 'Mensual' end  as www, b.nomnbre, \n" +
            "case when a.estatus ='E' then 'Exitosa' \n" +
            "when a.estatus ='D' then 'Con diferencias' \n" +
            "else 'Con Errores' end, a.id\n" +
            "from  sicader.sicader_con_ejecuciones a \n" +
            "inner join sicader.sicader_cat_tipo_derivados b on a.derivado_id=b.id \n" +
            "where a.id = (select max(id) from sicader.sicader_con_ejecuciones) ORDER BY a.fecha_op,a.derivado_id", nativeQuery = true)
    List<Object[]> getEstatusConciliacionesUltima();

    List<SicaderConciliaciones> findAllByEjecucionid(Long id);



}
