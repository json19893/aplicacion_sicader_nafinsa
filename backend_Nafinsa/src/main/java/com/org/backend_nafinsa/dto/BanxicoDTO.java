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
public class BanxicoDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaOperacion;
    private BigDecimal ValBanxico;

    public BanxicoDTO(Object[] object) {
        this.fechaOperacion = (Date) object[0];
        this.ValBanxico = (BigDecimal) object[1];
    }
}
