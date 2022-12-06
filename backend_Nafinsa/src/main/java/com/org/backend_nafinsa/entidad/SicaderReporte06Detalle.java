package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.backend_nafinsa.dto.Reporte06;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_REPORTE06_DETALLE")
public class SicaderReporte06Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_REPORTE06_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_REPORTE06_DET_SEQ", sequenceName = "SICADER_REPORTE06_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CONTRAPARTE")
    private String contraparte;

    @Column(name = "FOLIO")
    private double folio;

    @Column(name = "PORT")
    private String port;

    @Column(name = "PRODUCT")
    private String product;

    @Column(name = "TTASAENT")
    private String ttasaent;

    @Column(name = "DIV")
    private String div;

    @Column(name = "TYP")
    private String typ;

    @Column(name = "IMPORTER_REF_REC")
    private double importerRefRec;

    @Column(name = "IMPORTE_REF_VIGENTE_REC")
    private double importeRefVigenteRec;

    @Column(name = "TASA_REC")
    private double tasaRec;

    @Column(name = "INSTRTA")
    private String instrta;

    @Column(name = "SPREAD_REC")
    private double spreadRec;

    @Column(name = "BASECA")
    private String baseca;

    @Column(name = "IMPORTE_REF_ENT")
    private double importeRefEnt;

    @Column(name = "IMPORTE_REF_VIGENTE_ENT")
    private double importeRefVigenteEnt;

    @Column(name = "TASA_ENT")
    private double tasaEnt;

    @Column(name = "INSTTAS")
    private String insttas;

    @Column(name = "SPREAD_ENT")
    private double spreadEnt;

    @Column(name = "BASECA_2")
    private String baseca2;

    @Column(name = "TCAMBIO_RECIBO")
    private double tcambioRecibo;

    @Column(name = "PROVISION_IMP_RECIBIR")
    private double provisionImpRecibir;

    @Column(name = "VAL_PRESENTE_NETO_RECIBO")
    private double valpresenteNetoRecibo;

    @Column(name = "TCAMBIO_ENTREGA")
    private double tcambioEntrega;

    @Column(name = "PROVISION_IMP_ENTREGAR")
    private double provisionImpEntregar;

    @Column(name = "VAL_PRESENTE_NETO_ENTREG")
    private double valpresenteNetoEntreg;

    @Column(name = "NETO_PROVISION_PESOS")
    private double netoProvisionPesos;

    @Column(name = "PLUSMINUSVALIA")
    private double plusminusvalia;

    @Column(name = "TOTAL_VALUACION")
    private double totalValuacion;

    @Column(name = "TRAD_OP")
    private String tradOp;

    @Column(name = "OPERTRASPASO")
    private String opertraspaso;

    @Column(name = "FECHA_CONCERTACION")
    private LocalDate fechaConcertacion;

    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDate fechaVencimiento;

    @Column(name = "REPORTE_ID")
    private Long reporteId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
    private SicaderReporte06 sicaderReporte06;

    public SicaderReporte06Detalle(Reporte06 reporte06, SicaderReporte06 sicaderReporte06) {
        Utilidades utilidades = new Utilidades();
        this.contraparte = reporte06.getContraparte();
        this.folio = reporte06.getFolio();
        this.port = reporte06.getPort();
        this.product = reporte06.getProduct();
        this.ttasaent = reporte06.getTtasaent();
        this.div = reporte06.getDiv();
        this.typ = reporte06.getTyp();
        this.importerRefRec = reporte06.getImporterRefRec();
        this.importeRefVigenteRec = reporte06.getImporteRefVigenteRec();
        this.tasaRec = reporte06.getTasaRec();
        this.instrta = reporte06.getInstrta();
        this.spreadRec = reporte06.getSpreadRec();
        this.baseca = reporte06.getBaseca();
        this.importeRefEnt = reporte06.getImporteRefEnt();
        this.importeRefVigenteEnt = reporte06.getImporteRefVigenteEnt();
        this.tasaEnt = reporte06.getTasaEnt();
        this.insttas = reporte06.getInsttas();
        this.spreadEnt = reporte06.getSpreadEnt();
        this.baseca2 = reporte06.getBaseca2();
        this.tcambioRecibo = reporte06.getTcambioRecibo();
        this.provisionImpRecibir = reporte06.getProvisionImpRecibir();
        this.valpresenteNetoRecibo = reporte06.getValpresenteNetoRecibo();
        this.tcambioEntrega = reporte06.getTcambioEntrega();
        this.provisionImpEntregar = reporte06.getProvisionImpEntregar();
        this.valpresenteNetoEntreg = reporte06.getValpresenteNetoEntreg();
        this.netoProvisionPesos = reporte06.getNetoProvisionPesos();
        this.plusminusvalia = reporte06.getPlusminusvalia();
        this.totalValuacion = reporte06.getTotalValuacion();
        this.tradOp = reporte06.getTradOp();
        this.opertraspaso = reporte06.getOpertraspaso();
        this.fechaConcertacion = utilidades.fechaDDMMYYYY(reporte06.getFechaConcertacion());
        this.fechaInicio = utilidades.fechaDDMMYYYY(reporte06.getFechaInicio());
        this.fechaVencimiento = utilidades.fechaDDMMYYYY(reporte06.getFechaVencimiento());
        this.sicaderReporte06 = sicaderReporte06;

    }
}
