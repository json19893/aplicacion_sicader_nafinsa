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
public class Reporte06 {
    @CsvBindByName
    private String contraparte;

    @CsvBindByName
    private double folio;

    @CsvBindByName
    private String port;

    @CsvBindByName
    private String product;

    @CsvBindByName
    private String ttasaent;

    @CsvBindByName
    private String div;

    @CsvBindByName
    private String typ;

    @CsvBindByName
    private double importerRefRec;

    @CsvBindByName
    private double importeRefVigenteRec;

    @CsvBindByName
    private double tasaRec;

    @CsvBindByName
    private String instrta;

    @CsvBindByName
    private double spreadRec;

    @CsvBindByName
    private String baseca;

    @CsvBindByName
    private double importeRefEnt;

    @CsvBindByName
    private double importeRefVigenteEnt;

    @CsvBindByName
    private double tasaEnt;

    @CsvBindByName
    private String insttas;

    @CsvBindByName
    private double spreadEnt;

    @CsvBindByName
    private String baseca2;

    @CsvBindByName
    private double tcambioRecibo;

    @CsvBindByName
    private double provisionImpRecibir;

    @CsvBindByName
    private double valpresenteNetoRecibo;

    @CsvBindByName
    private double tcambioEntrega;

    @CsvBindByName
    private double provisionImpEntregar;

    @CsvBindByName
    private double valpresenteNetoEntreg;

    @CsvBindByName
    private double netoProvisionPesos;

    @CsvBindByName
    private double plusminusvalia;

    @CsvBindByName
    private double totalValuacion;

    @CsvBindByName
    private String tradOp;

    @CsvBindByName
    private String opertraspaso;

    @CsvBindByName
    private String fechaConcertacion;

    @CsvBindByName
    private String fechaInicio;

    @CsvBindByName
    private String fechaVencimiento;

}
