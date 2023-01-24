package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.dto.SicaderCatalogoDto;
import com.org.backend_nafinsa.entidad.SicaderCatTipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SicaderCatTipoCuentaRepository extends JpaRepository<SicaderCatTipoCuenta, Long> {

    @Query(value = "SELECT id,CUENTA||'-'||SUBCUENTA1 ||'-'||SUBCUENTA2 ||'-'||SUBCUENTA3 ||'-'|| SUBCUENTA4 ||'-'||SUBCUENTA5 ||'-'||SUBCUENTA6 ||'-'||SUBCUENTA7 AS nombre  FROM SICADER.SICADER_CUENTAS_CONCILIAR ORDER BY cuenta", nativeQuery = true)
    public List<Object[]> getAllcuentaConciliar();

    @Query(value = "SELECT CUENTA||'-'||SUBCUENTA1||SUBCUENTA2||SUBCUENTA3||SUBCUENTA4||SUBCUENTA5||SUBCUENTA6||SUBCUENTA7 AS CUENTA, ENTE, MONEDA, TIPO_ENTE, C2.CUE_NOMBRE\n" +
            "    FROM SICADER.SICADER_CUENTAS_CONCILIAR C, CUENTAS C2\n" +
            "    WHERE C.CUENTA = C2.CUE_MAYOR\n" +
            "    AND C.SUBCUENTA1 = CUE_SCTA1\n" +
            "    AND  C.SUBCUENTA2 = CUE_SCTA2\n" +
            "    AND C.SUBCUENTA3 = CUE_SCTA3\n" +
            "    AND C.SUBCUENTA4 = CUE_SCTA4\n" +
            "    AND C.SUBCUENTA5 = CUE_SCTA5\n" +
            "    AND C.SUBCUENTA6 = CUE_SCTA6\n" +
            "    AND C.SUBCUENTA7 = CUE_SCTA7 ORDER BY CUENTA||'-'||SUBCUENTA1||SUBCUENTA2||SUBCUENTA3||SUBCUENTA4||SUBCUENTA5||SUBCUENTA6||SUBCUENTA7", nativeQuery = true)
    public List<Object[]> getAllRequerimiento10Cuenta();

    List<SicaderCatTipoCuenta> findAllByOrderByNombreAsc();


}
