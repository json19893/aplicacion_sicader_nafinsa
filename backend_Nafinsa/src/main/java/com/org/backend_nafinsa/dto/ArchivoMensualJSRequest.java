package com.org.backend_nafinsa.dto;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoMensualJSRequest {

    private List<ArchivoMensualJsDto> archivoMensualJsDtoList;
    private String usuario;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaOperacion;

    private boolean forzar;
}
