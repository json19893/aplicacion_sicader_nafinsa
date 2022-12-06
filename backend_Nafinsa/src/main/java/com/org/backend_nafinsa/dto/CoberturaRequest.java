package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoberturaRequest {

    private String nombre;
    private Long cuentaActiva;
    private Long cuentaPasiva;
    private Long cuentaCapital;
}
