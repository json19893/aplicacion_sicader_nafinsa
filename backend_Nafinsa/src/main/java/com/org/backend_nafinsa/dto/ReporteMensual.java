package com.org.backend_nafinsa.dto;

import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReporteMensual {

    private int folio;
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
