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
@Table(schema = "SICADER", name = "SICADER_INTERESES_MARGEN_DETALLE")
public class SicaderInteresesMargenDetalle {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_INT_MARGEN_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_INT_MARGEN_DET_SEQ", sequenceName = "SICADER_INT_MARGEN_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "INGRESOS")
    private Double ingresos;

    @Column(name = "EGRESOS")
    private Double egresos;

    @Column(name = "CONTRAPARTE_ID")
    private Double contraparteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID")
    private SicaderInteresesMargen sicaderInteresesMargen;


}
