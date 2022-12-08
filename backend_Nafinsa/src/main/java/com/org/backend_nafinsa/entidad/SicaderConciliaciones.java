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
@Table(schema = "SICADER", name = "SICADER_CONCILIACIONES")
public class SicaderConciliaciones {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "TIPO_CONCILIACION")
    private String tipoconciliacion;

    @Column(name = "TIPO_DERIVADO_ID")
    private Long tipoderivadoid;

    @Column(name = "OFICINA")
    private Double oficina;

    @Column(name = "MONEDA")
    private Double moneda;

    @Column(name = "CUENTA")
    private Double cuenta;

    @Column(name = "SUBCUENTA1")
    private String subcuenta1;

    @Column(name = "SUBCUENTA2")
    private String subcuenta2;

    @Column(name = "SUBCUENTA3")
    private String subcuenta3;

    @Column(name = "SUBCUENTA4")
    private String subcuenta4;

    @Column(name = "SUBCUENTA5")
    private String subcuenta5;

    @Column(name = "SUBCUENTA6")
    private String subcuenta6;

    @Column(name = "SUBCUENTA7")
    private String subcuenta7;

    @Column(name = "TIPO_ENTE")
    private Double tipoente;

    @Column(name = "ENTE")
    private Double ente;

    @Column(name = "IMPORTE_SIF")
    private Double importesif;

    @Column(name = "IMPORTE_OP")
    private Double importeop;

    @Column(name = "FORMULA_CALCULO_ID")
    private Double formulacalculoid;

    @Column(name = "EJECUCION_ID")
    private Long ejecucionid;



}
