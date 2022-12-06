package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.backend_nafinsa.dto.Reporte03;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_REPORTE03_DETALLE")
public class SicaderReporte03Detalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_REPORTE03_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_REPORTE03_DET_SEQ", sequenceName = "SICADER_REPORTE03_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "BR")
    private String br;

    @Column(name = "PORT")
    private String port;

    @Column(name = "PRODUCT")
    private String product;

    @Column(name = "PR")
    private String pr;

    @Column(name = "TIPO_OPERACION")
    private String tipoOperacion;

    @Column(name = "CCY")
    private String ccy;

    @Column(name = "CTR")
    private String ctr;

    @Column(name = "MONED")
    private String moned;

    @Column(name = "CONTRAPARTE")
    private String contraparte;

    @Column(name = "NOMBRECONT")
    private String nombrecont;

    @Column(name = "NOOPERAC")
    private double nooperac;

    @Column(name = "FECHAINICI")
    private LocalDate fechaInici;

    @Column(name = "FECHAVENCI")
    private LocalDate fechaVenci;

    @Column(name = "FECHALIQUI")
    private LocalDate fechaLiqui;

    @Column(name = "PLAZO")
    private double plazo;

    @Column(name = "DXV")
    private double dxv;

    @Column(name = "TOPER")
    private String toper;

    @Column(name = "IMPORTE_POSICION")
    private BigDecimal importePosicion;

    @Column(name = "CONTRA_IMPORTE")
    private BigDecimal contraImporte;

    @Column(name = "PRECIO_PACTADO")
    private double precioPactado;

    @Column(name = "PRECIO_MERCADO")
    private double precioMercado;

    @Column(name = "MON")
    private String mon;

    @Column(name = "TCAMBIOFIX_M1")
    private double tcambiofixM1;

    @Column(name = "MONEDA2")
    private String moneda2;

    @Column(name = "TCAMBIOFIX_M2")
    private double tcambiofixM2;

    @Column(name = "PLUSMINUSVALIA")
    private BigDecimal plusminusvalia;

    @Column(name = "TRAD")
    private String trad;

    @Column(name = "CORPTRAD")
    private String corptrad;

    @JsonIgnore
    @Column(name = "REPORTE_ID")
    private Long reporteId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
    private SicaderReporte03 sicaderReporte03;

    public SicaderReporte03Detalle(Reporte03 reporte03, SicaderReporte03 sicaderReporte03Save) {
        Utilidades utilidades = new Utilidades();
        this.br = reporte03.getBr();
        this.port = reporte03.getPort();
        this.product = reporte03.getProduct();
        this.pr = reporte03.getPr();
        this.tipoOperacion = reporte03.getTipoOperacion();
        this.ccy = reporte03.getCcy();
        this.ctr = reporte03.getCtr();
        this.moned = reporte03.getMoned();
        this.contraparte = reporte03.getContraparte();
        this.nombrecont = reporte03.getNombrecont();
        this.nooperac = Double.parseDouble(reporte03.getNooperac());
        this.fechaInici = utilidades.fechaGuion_DDMMYYYY(reporte03.getFechaInici());
        this.fechaVenci = utilidades.fechaGuion_DDMMYYYY(reporte03.getFechaVenci());
        this.fechaLiqui = utilidades.fechaGuion_DDMMYYYY(reporte03.getFechaLiqui());
        this.plazo = reporte03.getPlazo();
        this.dxv = reporte03.getDxv();
        this.toper = reporte03.getToper();
        this.importePosicion = reporte03.getImportePosicion();
        this.contraImporte = reporte03.getContraImporte();
        this.precioPactado = reporte03.getPrecioPactado();
        this.precioMercado = Double.parseDouble((reporte03.getPrecioMercado().trim()));
        this.mon = reporte03.getMon();
        this.tcambiofixM1 = reporte03.getTcambiofixM1();
        this.moneda2 = reporte03.getMoneda2();
        this.tcambiofixM2 = reporte03.getTcambiofixM2();
        this.plusminusvalia = reporte03.getPlusminusvalia();
        this.trad = reporte03.getTrad();
        this.corptrad = reporte03.getCorptrad();

    }

}
