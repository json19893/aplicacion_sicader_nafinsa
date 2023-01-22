package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoReporteMensualDto {
    private String nombre;
    private String fechaOperacion;
    private BigDecimal recibirSl;
    private BigDecimal entregarSl;
    private BigDecimal recibirSideca;
    private BigDecimal entregarSideca;

    public ArchivoReporteMensualDto(Object[] object) {
        this.nombre = (String) object[0];
        this.fechaOperacion = new SimpleDateFormat("yyyy-MM-dd").format( (Date) object[1]);
        this.recibirSl = (BigDecimal) object[2];
        this.entregarSl = (BigDecimal) object[3];
        this.recibirSideca = (BigDecimal) object[4];
        this.entregarSideca = (BigDecimal) object[5];
    }
}
