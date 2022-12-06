package com.org.backend_nafinsa.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reporte03 {
    @CsvBindByName
    private String br;

    @CsvBindByName
    private String port;

    @CsvBindByName
    private String product;

    @CsvBindByName
    private String pr;

    @CsvBindByName
    private String tipoOperacion;

    @CsvBindByName
    private String ccy;

    @CsvBindByName
    private String ctr;

    @CsvBindByName
    private String moned;

    @CsvBindByName
    private String contraparte;

    @CsvBindByName
    private String nombrecont;

    @CsvBindByName
    private String nooperac;

    @CsvBindByName
    private String fechaInici;

    @CsvBindByName
    private String fechaVenci;
    @CsvBindByName
    private String fechaLiqui;

    @CsvBindByName
    private double plazo;

    @CsvBindByName
    private double dxv;

    @CsvBindByName
    private String toper;

    @CsvBindByName
    private BigDecimal importePosicion;

    @CsvBindByName
    private BigDecimal contraImporte;

    @CsvBindByName
    private double precioPactado;

    @CsvBindByName
    private String precioMercado;

    @CsvBindByName
    private String mon;

    @CsvBindByName
    private double tcambiofixM1;

    @CsvBindByName
    private String moneda2;

    @CsvBindByName
    private double tcambiofixM2;

    @CsvBindByName
    private BigDecimal plusminusvalia;

    @CsvBindByName
    private String trad;

    @CsvBindByName
    private String corptrad;
}
