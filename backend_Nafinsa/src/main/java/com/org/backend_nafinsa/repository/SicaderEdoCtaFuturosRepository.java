package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderEdoCtaFuturos;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SicaderEdoCtaFuturosRepository extends JpaRepository<SicaderEdoCtaFuturos, Long> {

    Optional<SicaderEdoCtaFuturos> findByFechaOperacion(LocalDate fechaOperacion);

    @Query(value = "SELECT A.FECHA_OP , C.NOMBRE , B.ENDING_BALANCE , B.IVA_TAX  \n" +
            "FROM SICADER.SICADER_EDO_CTA_FUTUROS A INNER JOIN \n" +
            "SICADER.SICADER_EDO_CTA_FUTUROS_DETALLE B ON A.ID=B.REPORTE_ID\n" +
            "INNER JOIN  SICADER.SICADER_CAT_SOCIOS_LIQUIDADORES C ON B.SOCIO_ID =C.ID \n" +
            "WHERE A.FECHA_OP = ?1", nativeQuery = true)
    public List<Object[]> getSicaderEdoCtaFuturos(LocalDate fechaOperacion);


}
