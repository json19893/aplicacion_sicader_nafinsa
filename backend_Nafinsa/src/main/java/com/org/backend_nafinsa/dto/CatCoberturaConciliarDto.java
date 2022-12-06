package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CatCoberturaConciliarDto {
    private BigDecimal id;
    private String nombre;
    private String activo;
    private String pasivo;
    private String capital;

    public CatCoberturaConciliarDto(Object[] object) {
        this.id = (BigDecimal) object[0];
        this.nombre = (String) object[1];
        this.activo = (String) object[2];
        this.pasivo = (String) object[3];
        this.capital = (String) object[4];
    }
}
