package com.org.backend_nafinsa.entidad;

import lombok.Data;
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
@Table(schema = "SICADER", name = "SICADER_RESUMEN_ARCHIVO")
public class SicaderResumenArchivo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SICADER_RESUMEN_ARCHIVO_SEQ")
    @SequenceGenerator(schema = "SICADER", name = "SICADER_RESUMEN_ARCHIVO_SEQ", sequenceName = "SICADER_RESUMEN_ARCHIVO_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "TIPO_REPORTE")
    private String tipoReporte;
    @Column(name = "NOMBRE_REPORTE")
    private String nombreReporte;
    @Column(name = "FECHA_OPERACION")
    private LocalDate fechaOperacion;
    @Column(name = "ESTATUS_CARGA")
    private String estatusCarga;
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;
}
