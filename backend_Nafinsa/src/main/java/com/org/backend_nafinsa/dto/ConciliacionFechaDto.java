package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConciliacionFechaDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaOperacion;

    private String nombre;

    private String cuenta;

    private BigDecimal moneda;

    private BigDecimal ente;

    private BigDecimal importeSif;

    private BigDecimal importeOp;

    private BigDecimal diferencia;
    
    private String tipoConciliacion;

    public ConciliacionFechaDto(Object[] object) {
        this.fechaOperacion = new SimpleDateFormat("yyyy-MM-dd").format( (Date) object[0]);
        this.nombre = (String) object[1];
        this.cuenta = (String) object[2];
        this.moneda = (BigDecimal) object[3];
        this.ente = (BigDecimal) object[4];
        this.importeSif = (BigDecimal) object[5];
        this.importeOp = (BigDecimal) object[6];
        this.diferencia = (BigDecimal) object[7];
        this.tipoConciliacion = (String) object[8];

    }


}
