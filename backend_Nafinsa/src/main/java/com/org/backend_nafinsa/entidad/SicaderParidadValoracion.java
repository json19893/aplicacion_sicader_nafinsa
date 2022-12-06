package com.org.backend_nafinsa.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "PARIDADES_VALORIZACION")

public class SicaderParidadValoracion {

    @Id
    @Column(name = "MON_CLAVE")
    private Long monClave;
    @Temporal(TemporalType.DATE)
    @Column(name = "PAR_FECHA")
    private Date parFecha;

    @Column(name = "PAR_PESO")
    private double parPeso;

    @Column(name = "PAR_DOLLAR")
    private double parDollar;

}
