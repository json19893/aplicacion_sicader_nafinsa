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
public class Reporte01 {

    @CsvBindByName
    private String br;
    @CsvBindByName
    private String brokno;
    @CsvBindByName
    private String contract;
    @CsvBindByName
    private String port;
    @CsvBindByName
    private String numOpera;
    @CsvBindByName
    private String seq;


    @CsvBindByName
    private String fechaOperac;

    @CsvBindByName
    private String fechaVencim;
    @CsvBindByName
    private double numContratos;
    @CsvBindByName
    private String tipoOperacion;
    @CsvBindByName
    private double precioPactado;
    @CsvBindByName
    private double precioCierreAnt;
    @CsvBindByName
    private double precioCierre;
    @CsvBindByName
    private double montoPrecioPact;
    @CsvBindByName
    private double montoDivisa;
    @CsvBindByName
    private String broker;
    @CsvBindByName
    private double montoCubierto;
    @CsvBindByName
    private double tdyunrealpl;
    @CsvBindByName
    private String tdyrealpl;
    @CsvBindByName
    private String trad;

}
