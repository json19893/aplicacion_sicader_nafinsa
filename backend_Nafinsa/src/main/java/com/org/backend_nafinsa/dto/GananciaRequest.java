package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GananciaRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaOperacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVencimiento;
    private double valorUid;
    private double perdida;
    private String usuario;
    private boolean forzar;
}
