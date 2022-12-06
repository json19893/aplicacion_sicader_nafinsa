package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderEdoCtaAsigna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SicaderEdoCtaAsignaRepository extends JpaRepository<SicaderEdoCtaAsigna, Long> {
    Optional<SicaderEdoCtaAsigna> findByFechaOperacion(LocalDate fechaOperacion);

    @Query(value = "SELECT A.FECHA_OP , C.NOMBRE , B.IVA_COM_MANEJO_CTA  , B.IVA_COM_OPERACION, B.BALANCE_FINAL  \n" +
            "FROM SICADER.SICADER_EDOS_CTA_ASIGNA A INNER JOIN \n" +
            "SICADER.SICADER_EDOS_CTA_ASIGNA_DETALLE B ON A.ID=B.REPORTE_ID\n" +
            "INNER JOIN  SICADER.SICADER_CAT_SOCIOS_LIQUIDADORES C ON B.SOCIO_ID =C.ID \n" +
            "WHERE A.FECHA_OP =  ?1", nativeQuery = true)
    public List<Object[]> getSicaderCtaAsigna(LocalDate fechaOperacion);


}
