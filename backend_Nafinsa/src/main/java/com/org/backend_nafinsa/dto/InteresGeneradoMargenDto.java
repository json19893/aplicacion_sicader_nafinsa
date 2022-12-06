package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InteresGeneradoMargenDto {

    private String fechaOperacion;
    private String contraparte;
    private BigDecimal ingresos;
    private BigDecimal egresos;

    public InteresGeneradoMargenDto(Object[] object) {
        this.fechaOperacion = (String) object[0];
        this.contraparte = (String) object[1];
        this.ingresos = (BigDecimal) object[2];
        this.egresos = (BigDecimal) object[3];

    }

}
