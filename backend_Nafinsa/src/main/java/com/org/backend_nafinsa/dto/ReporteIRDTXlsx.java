package com.org.backend_nafinsa.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReporteIRDTXlsx {
    private String br;
    private double dealNo;
    private double seq;
    private String product;
    private String prodtype;
    private Date brprcindte;
    private String dealind;
    private String ccy;
    private String baseccy;
    private double npvamt;
    private double npvbamt;
    private double mtmamt;


}
