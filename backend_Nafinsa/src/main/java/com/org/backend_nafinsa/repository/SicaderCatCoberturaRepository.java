package com.org.backend_nafinsa.repository;

import com.org.backend_nafinsa.entidad.SicaderCatCoberturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SicaderCatCoberturaRepository extends JpaRepository<SicaderCatCoberturas, Long> {

    @Query(value = "SELECT id,nombre , \n" +
            "  (SELECT CUENTA||'-'||SUBCUENTA1 ||'-'||SUBCUENTA2 ||'-'||SUBCUENTA3 ||'-'|| SUBCUENTA4 ||'-'||SUBCUENTA5 ||'-'||SUBCUENTA6 ||'-'||SUBCUENTA7   FROM SICADER.SICADER_CUENTAS_CONCILIAR CA WHERE CA.ID= A.CUENTA_ACTIVA_ID  ) AS ACTIVO,\n" +
            "  (SELECT CUENTA||'-'||SUBCUENTA1 ||'-'||SUBCUENTA2 ||'-'||SUBCUENTA3 ||'-'|| SUBCUENTA4 ||'-'||SUBCUENTA5 ||'-'||SUBCUENTA6 ||'-'||SUBCUENTA7   FROM SICADER.SICADER_CUENTAS_CONCILIAR CA WHERE CA.ID= A.CUENTA_PASIVA_ID   ) AS PASIVO,\n" +
            "  (SELECT CUENTA||'-'||SUBCUENTA1 ||'-'||SUBCUENTA2 ||'-'||SUBCUENTA3 ||'-'|| SUBCUENTA4 ||'-'||SUBCUENTA5 ||'-'||SUBCUENTA6 ||'-'||SUBCUENTA7   FROM SICADER.SICADER_CUENTAS_CONCILIAR CA WHERE CA.ID= A.CUENTA_CAPITAL_ID    ) AS CAPITAL\n" +
            "  FROM SICADER.SICADER_CAT_COBERTURAS A", nativeQuery = true)
    public List<Object[]> getAllCatCoberturaConciliar();
    
    public List<Object[]> findByNombre(String nombre);

}
