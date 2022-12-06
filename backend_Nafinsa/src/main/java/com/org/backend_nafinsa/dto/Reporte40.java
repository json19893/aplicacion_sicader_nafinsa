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
public class Reporte40 {
    @CsvBindByName
    private double broker;

    @CsvBindByName
    private double dealNo;

    @CsvBindByName
    private double seq;

    @CsvBindByName
    private String type;

    @CsvBindByName
    private String security;
    @CsvDate("YYYYMMDD")
    @CsvBindByName
    private Date valueDate;
    @CsvBindByName
    private Date maturityDate;

    @CsvBindByName
    private String description;

    @CsvBindByName
    private String ccy;

    @CsvBindByName
    private double debit;


}
