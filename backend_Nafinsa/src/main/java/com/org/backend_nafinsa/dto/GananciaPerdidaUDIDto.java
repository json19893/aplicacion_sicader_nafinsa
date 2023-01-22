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
public class GananciaPerdidaUDIDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaOperacion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaVencimiento;
    private BigDecimal valorUdi;
    private BigDecimal perdida;

    public GananciaPerdidaUDIDto(Object[] object) {
        this.fechaOperacion = new SimpleDateFormat("yyyy-MM-dd").format( (Date) object[0]);
        this.fechaVencimiento =new SimpleDateFormat("yyyy-MM-dd").format( (Date) object[1]);
        this.valorUdi = (BigDecimal) object[2];
        this.perdida = (BigDecimal) object[3];
    }
}
