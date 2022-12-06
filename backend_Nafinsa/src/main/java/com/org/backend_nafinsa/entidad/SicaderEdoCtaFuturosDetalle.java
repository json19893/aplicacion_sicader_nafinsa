package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(schema = "SICADER", name = "SICADER_EDO_CTA_FUTUROS_DETALLE")
public class SicaderEdoCtaFuturosDetalle {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_EDO_CTA_FUT_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_EDO_CTA_FUT_DET_SEQ", sequenceName = "SICADER_EDO_CTA_FUT_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "ENDING_BALANCE")
    private double endingBalance;

    @Column(name = "IVA_TAX")
    private double ivaTax;

    @Column(name = "SOCIO_ID")
    private int socioId;

    /*
        @JsonIgnore
        @Column(name = "REPORTE_ID")
        private Long reporteId;

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
        private  SicaderEdoCtaFuturos sicaderEdoCtaFuturos;
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID")
    private SicaderEdoCtaFuturos sicaderEdoCtaFuturos;
}
