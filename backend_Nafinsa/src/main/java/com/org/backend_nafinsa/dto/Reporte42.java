package com.org.backend_nafinsa.dto;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reporte42 {
    @CsvBindByName
    private double cno;

    @CsvBindByName
    private String contraparte;

    @CsvBindByName
    private String folio;

    @CsvBindByName
    private String port;

    @CsvBindByName
    private String prod;

    @CsvBindByName
    private String ty;

    @CsvBindByName
    private String fechaini;

    @CsvBindByName
    private String fechavto;

    @CsvBindByName
    private String dvr;

    @CsvBindByName
    private double importerefvigenterec;

    @CsvBindByName
    private String dve;

    @CsvBindByName
    private double importerefvigenteent;

    @CsvBindByName
    private double marktomarketmxn;

    @CsvBindByName
    private double saldoctamargen;


    @CsvBindByName
    private String fechacelebracion;

    @CsvBindByName
    private String fechavalor;

    @CsvBindByName
    private String fechavencimiento;

    @CsvBindByName
    private double provisionimprecibir;

    @CsvBindByName
    private double provisionimpentregar;

    @CsvBindByName
    private double marktomarketmxn1;

    @CsvBindByName
    private double saldogarantias;
}
