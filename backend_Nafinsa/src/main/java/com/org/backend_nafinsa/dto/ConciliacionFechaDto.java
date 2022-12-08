package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConciliacionFechaDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaOperacion;

    private String nombre;

    private BigDecimal cuenta;

    private String moneda;

    private BigDecimal ente;

    private BigDecimal importeSif;

    private BigDecimal importeOp;

    private BigDecimal diferencia;

    public ConciliacionFechaDto(Object[] object) {
        this.fechaOperacion = (Date) object[0];
        this.nombre = (String) object[1];
        this.cuenta = (BigDecimal) object[2];
        this.moneda = (String) object[3];
        this.ente = (BigDecimal) object[4];
        this.importeSif = (BigDecimal) object[5];
        this.importeOp = (BigDecimal) object[6];
        this.diferencia = (BigDecimal) object[7];

    }


}
