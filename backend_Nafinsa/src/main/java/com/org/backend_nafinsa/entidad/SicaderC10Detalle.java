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
@Table(schema = "SICADER", name = "SICADER_C10_DETALLE")
public class SicaderC10Detalle {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_C10_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_C10_DET_SEQ", sequenceName = "SICADER_C10_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "IMPORTE")
    private double importe;

    @Column(name = "COBERTURA_ID")
    private double coberturaId;

    @Column(name = "TIPO_CUENTA_ID")
    private double tipoCuentaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID")
    private SicaderC10 sicaderC10;

}
