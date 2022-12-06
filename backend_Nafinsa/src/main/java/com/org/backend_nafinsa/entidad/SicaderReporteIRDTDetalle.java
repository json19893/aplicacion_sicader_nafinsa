package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.backend_nafinsa.dto.ReporteIRDT;
import com.org.backend_nafinsa.dto.ReporteIRDTXlsx;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_IRDT_DETALLE")
public class SicaderReporteIRDTDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_IRDT_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_IRDT_DET_SEQ", sequenceName = "SICADER_IRDT_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "BR")
    private String br;

    @Column(name = "DEAL_NO")
    private double dealNo;

    @Column(name = "SEQ")
    private double seq;

    @Column(name = "PRODUCT")
    private String product;

    @Column(name = "PRODTYPE")
    private String prodtype;

    @Column(name = "BRPRCINDTE")
    private Date brprcindte;

    @Column(name = "DEALIND")
    private String dealind;

    @Column(name = "CCY")
    private String ccy;

    @Column(name = "BASECCY")
    private String baseccy;

    @Column(name = "NPVAMT")
    private double npvamt;

    @Column(name = "NPVBAMT")
    private double npvbamt;

    @Column(name = "MTMAMT")
    private double mtmamt;

    @Column(name = "REPORTE_ID")
    private Long reporteId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
    private SicaderReporteIRDT sicaderReporteIRDT;

    public SicaderReporteIRDTDetalle(ReporteIRDTXlsx reporteIRDT, SicaderReporteIRDT sicaderReporteIRDTSave) {
        this.br = reporteIRDT.getBr();
        this.dealNo = reporteIRDT.getDealNo();
        this.seq = reporteIRDT.getSeq();
        this.product = reporteIRDT.getProduct();
        this.prodtype = reporteIRDT.getProdtype();
        this.brprcindte = reporteIRDT.getBrprcindte();
        this.dealind = reporteIRDT.getDealind();
        this.ccy = reporteIRDT.getCcy();
        this.baseccy = reporteIRDT.getBaseccy();
        this.npvamt = reporteIRDT.getNpvamt();
        this.npvbamt = reporteIRDT.getNpvbamt();
        this.mtmamt = reporteIRDT.getMtmamt();
        this.sicaderReporteIRDT = sicaderReporteIRDTSave;
    }
}
