package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CuentasRequerimiento10Dto {
    private String cuenta;
    private String ente;
    private String moneda;
    private String tipoEnte;
    private String cueNombre;

    public CuentasRequerimiento10Dto(Object[] object) {
        this.cuenta = (String) object[0];
        this.ente = (String) object[1];
        this.moneda = (String) object[2];
        this.tipoEnte = (String) object[3];
        this.cueNombre = (String) object[4];
    }


}
