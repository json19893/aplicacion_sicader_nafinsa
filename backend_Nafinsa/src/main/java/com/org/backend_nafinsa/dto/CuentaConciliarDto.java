package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CuentaConciliarDto {

    private BigDecimal id;
    private String descripcion;


    public CuentaConciliarDto(Object[] object) {
        this.id = (BigDecimal) object[0];
        this.descripcion = (String) object[1];
    }

}
