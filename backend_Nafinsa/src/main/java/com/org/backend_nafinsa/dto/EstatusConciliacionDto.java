package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstatusConciliacionDto {

    private String fechaOperacion;
    private String fechaVencimiento;
    private String usuario;

    private String tipoConciliacion;
    private String tipoDerivado;
    private String estatus;

    private BigDecimal id;


    public EstatusConciliacionDto(Object[] object) {
        this.fechaOperacion =  new SimpleDateFormat("yyyy-MM-dd").format( (Date) object[0]);
        this.fechaVencimiento = new SimpleDateFormat("yyyy-MM-dd").format((Date) object[1]);
        this.usuario = (String) object[2];
        this.tipoConciliacion = (String) object[3];
        this.tipoDerivado = (String) object[4];
        this.estatus = (String) object[5];
        this.id=(BigDecimal) object[6];

    }



}
