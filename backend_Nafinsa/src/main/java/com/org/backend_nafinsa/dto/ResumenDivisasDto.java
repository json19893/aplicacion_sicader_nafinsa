package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumenDivisasDto {

    private String parFecha;
    private BigDecimal monClave;
    private String monNombre;
    private BigDecimal nemonClave;

    public ResumenDivisasDto(Object[] object) {
        this.parFecha =  new SimpleDateFormat("yyyy-MM-dd").format(  (Date) object[0]);
        this.monClave = (BigDecimal) object[1];
        this.monNombre = (String) object[2];
        this.nemonClave = (BigDecimal) object[3];
    }

}
