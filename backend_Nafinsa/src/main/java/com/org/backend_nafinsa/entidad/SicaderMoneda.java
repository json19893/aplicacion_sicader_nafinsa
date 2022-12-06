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
@Table(name = "MONEDAS")
public class SicaderMoneda {

    @Id
    @Column(name = "MON_CLAVE")
    private int monClave;

    @Column(name = "MON_NOMBRE")
    private String monNombre;

    @Column(name = "TMO_CLAVE")
    private String tmoClave;

    @Column(name = "NEMON_CLAVE")
    private String nemonClave;

    @Column(name = "MON_CLAVE_SAT")
    private String monClaveSat;

    @Column(name = "MON_CLAVE_SIOR")
    private String monClaveSior;

    @Column(name = "MON_CLAVE_FFON")
    private String monClaveFfon;

    @Column(name = "MON_CLAVE_LOBO")
    private String monClaveLobo;

    @Column(name = "FECHA_HORA_ALTA")
    private Date fechaHoraAlta;

    @Column(name = "USUARIO_ALTA")
    private String usuarioAlta;

    @Column(name = "FECHA_HORA_MOD")
    private Date fechaHoraMod;

    @Column(name = "USUARIO_MOD")
    private String usuarioMod;

}
