package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.backend_nafinsa.dto.Reporte40;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_REPORTE40_DETALLE")
public class SicaderReporte40Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_REPORTE_40_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_REPORTE_40_DET_SEQ", sequenceName = "SICADER_REPORTE_40_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "BROKER")
    private double broker;

    @Column(name = "DEAL_NO")
    private double dealNo;

    @Column(name = "SEQ")
    private double seq;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SECURITY")
    private String security;

    @Column(name = "VALUE_DATE")
    private Date valueDate;

    @Column(name = "MATURITY_DATE")
    private Date maturityDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CCY")
    private String ccy;

    @Column(name = "DEBIT")
    private double debit;

    @Column(name = "REPORTE_ID")
    private Long reporteId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
    private SicaderReporte40 sicaderReporte40;

    public SicaderReporte40Detalle(Reporte40 reporte40, SicaderReporte40 sicaderReporte40) {
        this.broker=reporte40.getBroker() ;
        this.dealNo = reporte40.getDealNo();
        this.seq = reporte40.getSeq();
        this.type = reporte40.getType();
        this.security = reporte40.getSecurity();
        this.valueDate = reporte40.getValueDate();
        this.maturityDate = reporte40.getMaturityDate();
        this.description = reporte40.getDescription();
        this.ccy = reporte40.getCcy();
        this.debit = reporte40.getDebit();
        this.sicaderReporte40 = sicaderReporte40;
    }

}
