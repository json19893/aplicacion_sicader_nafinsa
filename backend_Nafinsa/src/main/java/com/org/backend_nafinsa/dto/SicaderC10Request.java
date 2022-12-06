package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SicaderC10Request {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate mesAnio;
    private double coberturaId;
    private String usuario;
    private boolean forzar;
    private List<SicaderC10SubListRequest> sicaderC10SubList;

}
