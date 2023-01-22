package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartaConfirmacionDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String fechaOperacion;
    private String nombre;
    private BigDecimal endingBalance;
    private BigDecimal ivaTax;

    public CartaConfirmacionDto(Object[] object) {
        this.fechaOperacion = new SimpleDateFormat("yyyy-MM-dd").format( (Date) object[0]);
        this.nombre = (String) object[1];
        this.endingBalance = (BigDecimal) object[2];
        this.ivaTax = (BigDecimal) object[3];
    }
}
