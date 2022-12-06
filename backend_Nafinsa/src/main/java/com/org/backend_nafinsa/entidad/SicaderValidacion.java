package com.org.backend_nafinsa.entidad;

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
@Table(schema = "SICADER", name = "SICADER_VALIDACIONES")
public class SicaderValidacion {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "INSUMO")
    private String insumo;
    @Column(name = "TIPO_CONCILIA")
    private String tipoConcilia;
    @Column(name = "TIPO_DERIVADO")
    private Long tipoDerivado;
    @Column(name = "FECHA_OP")
    private LocalDate fechaOperacion;
    @Column(name = "VALIDACION")
    private Long validacion;


}
