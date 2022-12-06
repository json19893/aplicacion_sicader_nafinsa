package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_PERD_GANAN_INFLA_DETALLE")
public class SicaderPerdidaGananciaInflaDetalle {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_GANANCIA_PERD_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_GANANCIA_PERD_DET_SEQ", sequenceName = "SICADER_GANANCIA_PERD_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDate fechaVencimiento;

    @Column(name = "PERDIDA_INFLACIONARIA")
    private Double perdidaInflacionaria;

    @Column(name = "VALOR_UDI")
    private Double valorUdi;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID")
    private SicaderPerdidaGananciaInfla sicaderPerdidaGananciaInfla;

}
