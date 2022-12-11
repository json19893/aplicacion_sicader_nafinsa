package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstatusConciliacionRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaOperacionIni;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaOperacionFin;
    private LocalDate fechaVencimientoIni;
    private LocalDate fechaVencimientoFin;
    private String usuario;
    private String tipoConciliacion;
    private String estatus;
    private String derivado;
    private boolean ultimaConciliacion;

}
