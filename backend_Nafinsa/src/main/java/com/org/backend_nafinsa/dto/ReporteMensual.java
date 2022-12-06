package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReporteMensual {

    private double folio;
    private double contrato;
    private String moneda;
    private double entregarSl;
    private double recibirSl;
    private double netoValSl;
    private double entregarSideca;
    private double recibirSideca;
    private double netovalSideca;
    private double diferencia;
    private Long socioLiquidador;

}
