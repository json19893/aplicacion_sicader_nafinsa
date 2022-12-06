package com.org.backend_nafinsa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CoberturaDto implements Serializable {

    private Long id;

    private String nombre;

    private CatCoberturaConciliarDto cuentaActiva;

    private CatCoberturaConciliarDto cuentaPasiva;

    private CatCoberturaConciliarDto cuentaCapital;

}
