package com.org.backend_nafinsa.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_CUENTAS_CONCILIAR")
public class SicaderCuentasConciliar {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUENTA")
    private String cuenta;

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
    @Column(name = "ENTE")
    private Long ente;
    @Column(name = "NEMONICO")
    private String nemonico;
    @Column(name = "MONEDA")
    private Long moneda;
    @Column(name = "TIPO_ENTE")
    private Long tipoEnte;
}
