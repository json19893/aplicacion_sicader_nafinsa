package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartaConfirmacionRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaOperacion;
    private int idSocioLiquidador;
    private double montoBalance;
    private String usuario;
    private double montoIva;
    private boolean forzar;
}
