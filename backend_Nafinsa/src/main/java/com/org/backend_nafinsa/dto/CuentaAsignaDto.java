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
public class CuentaAsignaDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaOperacion;
    private String nombre;
    private BigDecimal ivaManejoCuenta;
    private BigDecimal ivaComisionOperacion;
    private BigDecimal balanceFinal;

    public CuentaAsignaDto(Object[] object) {
        this.fechaOperacion = (Date) object[0];
        this.nombre = (String) object[1];
        this.ivaManejoCuenta = (BigDecimal) object[2];
        this.ivaComisionOperacion = (BigDecimal) object[3];
        this.balanceFinal = (BigDecimal) object[4];
    }

}
