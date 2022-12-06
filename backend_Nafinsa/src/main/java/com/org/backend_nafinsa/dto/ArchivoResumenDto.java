package com.org.backend_nafinsa.dto;

import com.org.backend_nafinsa.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoResumenDto {

    private String tipoReporte;
    private String nombreReporte;
    private Date fechaOperacion;
    private String estatusCarga;
    private BigDecimal id;

    public ArchivoResumenDto(Object[] object) {
        this.tipoReporte = (String) object[0];
        this.nombreReporte = (String) object[1];
        this.fechaOperacion = (Date) object[2];
        this.estatusCarga =  ((String) object[3]).equals("E")? "Carga exitosa" : (String) object[3];
        this.id = (BigDecimal) object[4];
    }
}
