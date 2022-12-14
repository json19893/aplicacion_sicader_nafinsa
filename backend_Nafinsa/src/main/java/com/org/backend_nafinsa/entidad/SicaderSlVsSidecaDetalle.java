package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.backend_nafinsa.dto.ReporteMensual;
import com.org.backend_nafinsa.dto.ReporteMensualJs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_SL_VS_SIDECA_DETALLE")
public class SicaderSlVsSidecaDetalle {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_SL_SIDECA_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_SL_SIDECA_DET_SEQ", sequenceName = "SICADER_SL_SIDECA_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FOLIO")
    private double folio;

    @Column(name = "CONTRATO")
    private double contrato;

    @Column(name = "MONEDA")
    private String moneda;

    @Column(name = "ENTREGAR_SL")
    private double entregarSl;

    @Column(name = "RECIBIR_SL")
    private double recibirSl;

    @Column(name = "NETO_VAL_SL")
    private double netoValSl;

    @Column(name = "ENTREGAR_SIDECA")
    private double entregarSideca;

    @Column(name = "RECIBIR_SIDECA")
    private double recibirSideca;

    @Column(name = "NETO_VAL_SIDECA")
    private double netovalSideca;

    @Column(name = "DIFERENCIA")
    private double diferencia;

    @Column(name = "SOCIO_ID")
    private Long socioId;

    @Column(name = "REPORTE_ID")
    private double reporteId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
    private SicaderSlVsSideca sicaderSlVsSideca;

    public SicaderSlVsSidecaDetalle(ReporteMensual reporteMensual, SicaderSlVsSideca sicaderSlVsSideca) {
        this.folio = reporteMensual.getFolio();
        this.contrato = reporteMensual.getContrato();
        this.moneda = reporteMensual.getMoneda();
        this.entregarSl = reporteMensual.getEntregarSl();
        this.recibirSl = reporteMensual.getRecibirSl();
        this.netoValSl = reporteMensual.getNetoValSl();
        this.entregarSideca = reporteMensual.getEntregarSideca();
        this.recibirSideca = reporteMensual.getRecibirSideca();
        this.netovalSideca = reporteMensual.getNetovalSideca();
        this.diferencia = reporteMensual.getDiferencia();
        this.sicaderSlVsSideca = sicaderSlVsSideca;
        this.socioId = reporteMensual.getSocioLiquidador();
    }

    public SicaderSlVsSidecaDetalle(ReporteMensualJs reporteMensual, SicaderSlVsSideca sicaderSlVsSideca) {
        this.folio = reporteMensual.getFolio();
        this.contrato = reporteMensual.getContrato();
        this.moneda = reporteMensual.getMoneda();
        this.entregarSl = reporteMensual.getEntregarSl();
        this.recibirSl = reporteMensual.getRecibirSl();
        this.netoValSl = reporteMensual.getNetoValSl();
        this.entregarSideca = reporteMensual.getEntregarSideca();
        this.recibirSideca = reporteMensual.getRecibirSideca();
        this.netovalSideca = reporteMensual.getNetovalSideca();
        this.diferencia = reporteMensual.getDiferencia();
        this.sicaderSlVsSideca = sicaderSlVsSideca;
        this.socioId = reporteMensual.getSocioLiquidador();
    }
}
