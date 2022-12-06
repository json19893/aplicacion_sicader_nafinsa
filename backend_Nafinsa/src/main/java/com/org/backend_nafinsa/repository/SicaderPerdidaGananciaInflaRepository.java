package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderPerdidaGananciaInfla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SicaderPerdidaGananciaInflaRepository extends JpaRepository<SicaderPerdidaGananciaInfla, Long> {
    Optional<SicaderPerdidaGananciaInfla> findByFechaOperacion(LocalDate fechaOperacion);

    @Query(value = "SELECT B.FECHA_VENCIMIENTO , A.FECHA_OP , B.VALOR_UDI ,B.PERDIDA_INFLACIONARIA  FROM SICADER.SICADER_PERDIDA_GANANCIA_INFLA A INNER JOIN \n" +
            "SICADER.SICADER_PERD_GANAN_INFLA_DETALLE B ON A.ID =B.REPORTE_ID \n" +
            "WHERE A.FECHA_OP = ?1 ", nativeQuery = true)
    public List<Object[]> getGananciaPerdidaUDI(LocalDate fechaOperacion);

}
