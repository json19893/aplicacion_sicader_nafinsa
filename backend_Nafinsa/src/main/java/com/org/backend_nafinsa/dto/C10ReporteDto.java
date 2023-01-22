package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class C10ReporteDto {

    private String nombre;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaOperacion;

    private BigDecimal activo;

    private BigDecimal pasivo;

    private BigDecimal capital;

    public C10ReporteDto(Object[] object) {
        this.nombre = (String) object[0];
        this.fechaOperacion = new SimpleDateFormat("yyyy-MM-dd").format( (Date) object[1]);
        try {
            this.activo = (BigDecimal) object[2];
        } catch (Exception e) {
            this.activo = BigDecimal.valueOf(0);
        }
        try {
            this.pasivo = (BigDecimal) object[3];
        } catch (Exception e) {
            this.pasivo = BigDecimal.valueOf(0);
        }
        try {
            this.capital = (BigDecimal) object[4];
        } catch (Exception e) {
            this.capital = BigDecimal.valueOf(0);
        }
    }
}
