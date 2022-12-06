package com.org.backend_nafinsa.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.org.backend_nafinsa.dto.Reporte42;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(schema = "SICADER", name = "SICADER_REPORTE42_DETALLE")

public class SicaderReporte42Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_REPORTE42_DET_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_REPORTE42_DET_SEQ", sequenceName = "SICADER_REPORTE42_DET_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "CNO")
    private double cno;

    @Column(name = "CONTRAPARTE")
    private String contraparte;

    @Column(name = "FOLIO")
    private Double folio;

    @Column(name = "PORT")
    private String port;

    @Column(name = "PROD")
    private String prod;

    @Column(name = "TY")
    private String ty;

    @Column(name = "FECHAINI")
    private LocalDate fechaini;

    @Column(name = "FECHAVTO")
    private LocalDate fechavto;

    @Column(name = "DVR")
    private String dvr;

    @Column(name = "IMPORTE_REF_VIGENTE_REC")
    private double importerefvigenterec;

    @Column(name = "DVE")
    private String dve;

    @Column(name = "IMPORTE_REF_VIGENTE_ENT")
    private double importerefvigenteent;

    @Column(name = "MARK_TO_MARKET_MXN")
    private double marktomarketmxn;

    @Column(name = "SALDO_CTAMARGEN")
    private double saldoctamargen;

    @Column(name = "FECHA_CELEBRACION")
    private LocalDate fechacelebracion;

    @Column(name = "FECHA_VALOR")
    private LocalDate fechavalor;

    @Column(name = "FECHA_VENCIMIENTO")
    private LocalDate fechavencimiento;

    @Column(name = "PROVISION_IMP_RECIBIR")
    private double provisionimprecibir;

    @Column(name = "PROVISION_IMP_ENTREGAR")
    private double provisionimpentregar;

    @Column(name = "MARK_TO_MARKET_MXN_1")
    private double marktomarketmxn1;

    @Column(name = "SALDO_GARANTIAS")
    private double saldogarantias;

    @Column(name = "REPORTE_ID")
    private Long reporteId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPORTE_ID", insertable = false, updatable = false)
    private SicaderReporte42 sicaderReporte42;

    public SicaderReporte42Detalle(Reporte42 reporte42, SicaderReporte42 sicaderReporte42) {
        Utilidades utilidades = new Utilidades();
        this.cno = reporte42.getCno();
        this.contraparte = reporte42.getContraparte();
        this.folio = reporte42.getFolio().equals("--") ? null : Double.valueOf(reporte42.getFolio());
        this.port = reporte42.getPort().equals("--") ? null : reporte42.getPort();
        this.prod = reporte42.getProd().equals("--") ? null : reporte42.getProd();
        this.ty = reporte42.getTy().equals("--") ? null : reporte42.getTy();
        this.fechaini = reporte42.getFechaini().equals("--") ? null : utilidades.fechaDDMMYYYY(reporte42.getFechaini());
        this.fechavto = reporte42.getFechavto().equals("--") ? null : utilidades.fechaDDMMYYYY(reporte42.getFechavto());
        this.dvr = reporte42.getDvr().equals("--") ? null : reporte42.getDvr();
        this.importerefvigenterec = reporte42.getImporterefvigenterec();
        this.dve = reporte42.getDve().equals("--") ? null : reporte42.getDve();
        this.importerefvigenteent = reporte42.getImporterefvigenteent();
        this.marktomarketmxn = reporte42.getMarktomarketmxn();
        this.saldoctamargen = reporte42.getSaldoctamargen();
        this.fechacelebracion = reporte42.getFechacelebracion().equals("--") ? null : utilidades.fechaGuion_DDMMYYYY(reporte42.getFechacelebracion());
        this.fechavalor = reporte42.getFechavalor().equals("--") ? null : utilidades.fechaGuion_DDMMYYYY(reporte42.getFechavalor());
        this.fechavencimiento = reporte42.getFechavencimiento().equals("--") ? null : utilidades.fechaGuion_DDMMYYYY(reporte42.getFechavencimiento());
        this.provisionimprecibir = reporte42.getProvisionimprecibir();
        this.provisionimpentregar = reporte42.getProvisionimpentregar();
        this.marktomarketmxn1 = reporte42.getMarktomarketmxn1();
        this.saldogarantias = reporte42.getSaldogarantias();
        this.sicaderReporte42 = sicaderReporte42;
    }
}
