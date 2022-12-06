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
@Table(schema = "SICADER", name = "SICADER_EDOS_CTA_ASIGNA_DETALLE")
public class SicaderEdoCtaAsignaDetalle {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_EDO_CTA_ASI_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_EDO_CTA_ASI_DET_SEQ", sequenceName = "SICADER_EDO_CTA_ASI_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "IVA_COM_MANEJO_CTA")
    private double ivaComManejoCta;

    @Column(name = "IVA_COM_OPERACION")
    private double ivaComOperacion;

    @Column(name = "BALANCE_FINAL")
    private double balanceFinal;

    @Column(name = "SOCIO_ID")
    private double socioId;
    /*
        @Column(name = "REPORTE_ID")
        private Long reporteId;
        @JsonIgnore

     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID")
    private SicaderEdoCtaAsigna sicaderEdoCtaAsigna;
}
