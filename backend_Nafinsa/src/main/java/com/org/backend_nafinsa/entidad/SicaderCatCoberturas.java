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
@Table(schema = "SICADER", name = "SICADER_CAT_COBERTURAS")
public class SicaderCatCoberturas {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_CAT_COB_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_CAT_COB_SEQ", sequenceName = "SICADER_CAT_COB_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUENTA_ACTIVA_ID")
    private SicaderCuentasConciliar cuentaActiva;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUENTA_PASIVA_ID")
    private SicaderCuentasConciliar cuentaPasiva;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUENTA_CAPITAL_ID")
    private SicaderCuentasConciliar cuentaCapital;


}
