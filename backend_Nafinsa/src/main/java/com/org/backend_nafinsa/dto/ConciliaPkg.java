package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ConciliaPkg {
    private String inTipoConcilia;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inFecha;
    private String inUsuario;
    private Long inDerivado;

}
