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
public class CuentaAsignaRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaOperacion;
    private int idSocioLiquidador;
    private double ivaCuenta;
    private double ivaOperacion;
    private double balanceFinal;
    private String usuario;
    private boolean forzar;
}
