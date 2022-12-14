package com.org.backend_nafinsa.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReporteMensualJs {

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
