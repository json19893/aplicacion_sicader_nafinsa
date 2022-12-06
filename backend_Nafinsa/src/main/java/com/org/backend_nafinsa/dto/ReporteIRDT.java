package com.org.backend_nafinsa.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReporteIRDT {
    @CsvBindByName(column = "BR", required = true)
    private String br;

    @CsvBindByName(column = "DEALNO", required = true)
    private double dealNo;

    @CsvBindByName(column = "SEQ", required = true)
    private double seq;

    @CsvBindByName(column = "PRODUCT", required = true)
    private String product;

    @CsvBindByName(column = "PRODTYPE", required = true)
    private String prodtype;

    @CsvBindByName(column = "BRPRCINDTE", required = true)
    private Date brprcindte;

    @CsvBindByName(column = "DEALIND", required = true)
    private String dealind;

    @CsvBindByName(column = "CCY", required = true)
    private String ccy;

    @CsvBindByName(column = "BASECCY", required = true)
    private String baseccy;

    @CsvBindByName(column = "NPVAMT", required = true)
    private double npvamt;

    @CsvBindByName(column = "NPVBAMT", required = true)
    private double npvbamt;

    @CsvBindByName(column = "MTMAMT", required = true)
    private double mtmamt;

}
