package com.org.backend_nafinsa.entidad;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.backend_nafinsa.dto.Reporte01;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

//@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_REPORTE01_DETALLE")
public class SicaderReporte01Detalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_REPORTE01_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_REPORTE01_DET_SEQ", sequenceName = "SICADER_REPORTE01_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "BR")
    private String br;
    @Column(name = "BROKNO")
    private String brokno;
    @Column(name = "CONTRACT")
    private String contract;
    @Column(name = "PORT")
    private String port;
    @Column(name = "NUMOPERA")
    private String numOpera;
    @Column(name = "SEQ")
    private String seq;
    @Column(name = "FECHAOPERAC")
    private LocalDate fechaOperac;
    @Column(name = "FECHAVENCIM")
    private LocalDate fechaVencim;
    @Column(name = "NUMCONTRATOS")
    private double numContratos;
    @Column(name = "TIPO_OPERACION")
    private String tipoOperacion;
    @Column(name = "PRECIO_PACTADO")
    private double precioPactado;
    @Column(name = "PRECIO_CIERRE_ANT")
    private double precioCierreAnt;
    @Column(name = "PRECIO_CIERRE")
    private double precioCierre;
    @Column(name = "MONTOPRECIOPACT")
    private double montoPrecioPact;
    @Column(name = "MONTODIVISA")
    private double montoDivisa;
    @Column(name = "BROKER")
    private String broker;
    @Column(name = "MONTOCUBIERTO")
    private double montoCubierto;
    @Column(name = "TDYUNREALPL")
    private double tdyunrealpl;
    @Column(name = "TDYREALPL")
    private String tdyrealpl;
    @Column(name = "TRAD")
    private String trad;


    @JsonIgnore
    @Column(name = "REPORTE_ID")
    private Long reporteId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
    private SicaderReporte01 sicaderReporte01;


    public SicaderReporte01Detalle(Reporte01 reporte01, SicaderReporte01 sicaderReporte01Save) {
        Utilidades utilidades = new Utilidades();
        this.br = reporte01.getBr();
        this.brokno = reporte01.getBrokno();
        this.contract = reporte01.getContract();
        this.port = reporte01.getPort();
        this.numOpera = reporte01.getNumOpera();
        this.seq = reporte01.getSeq();
        this.fechaOperac = utilidades.fechaGuion_DDMMMYYYY(reporte01.getFechaOperac());
        this.fechaVencim = utilidades.fechaGuion_DDMMMYYYY(reporte01.getFechaVencim());
        this.numContratos = reporte01.getNumContratos();
        this.tipoOperacion = reporte01.getTipoOperacion();
        this.precioPactado = reporte01.getPrecioPactado();
        this.precioCierreAnt = reporte01.getPrecioCierreAnt();
        this.precioCierre = reporte01.getPrecioCierre();
        this.montoPrecioPact = reporte01.getMontoPrecioPact();
        this.montoDivisa = reporte01.getMontoDivisa();
        this.broker = reporte01.getBroker();
        this.montoCubierto = reporte01.getMontoCubierto();
        this.tdyunrealpl = reporte01.getTdyunrealpl();
        this.tdyrealpl = reporte01.getTdyrealpl();
        this.trad = reporte01.getTrad();
        this.sicaderReporte01 = sicaderReporte01Save;

    }

}
