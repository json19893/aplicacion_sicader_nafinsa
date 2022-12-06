package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumenDivisasDto {
    private Date parFecha;
    private BigDecimal monClave;
    private String monNombre;
    private String nemonClave;

    public ResumenDivisasDto(Object[] object) {
        this.parFecha = (Date) object[0];
        this.monClave = (BigDecimal) object[1];
        this.monNombre = (String) object[2];
        this.nemonClave = (String) object[3];
    }

}
