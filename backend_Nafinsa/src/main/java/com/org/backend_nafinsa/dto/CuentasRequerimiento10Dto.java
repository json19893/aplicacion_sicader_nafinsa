package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CuentasRequerimiento10Dto {
    private String cuenta;
    private BigDecimal ente;
    private BigDecimal moneda;
    private BigDecimal tipoEnte;
    private String cueNombre;

    public CuentasRequerimiento10Dto(Object[] object) {
        this.cuenta = (String) object[0];
        this.ente = (BigDecimal) object[1];
        this.moneda = (BigDecimal) object[2];
        this.tipoEnte = (BigDecimal) object[3];
        this.cueNombre = (String) object[4];
    }


}
