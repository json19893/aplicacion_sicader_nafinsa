package com.org.backend_nafinsa.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorServicioNoDisponible extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String codigoControlado;

    private String nombreClase;

    private String mensajeRespuesta;

}
