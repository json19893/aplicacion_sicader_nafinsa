package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;


public interface InformacionSidecaService {
    public ResponseDto cargaCartaConfirmacion(CartaConfirmacionRequest cartaConfirmacionRequest);

    public ResponseDto CargaCuentaAsigna(CuentaAsignaRequest cuentaAsignaRequest);

    ResponseEntity<Map<String, Object>> getAllCartaConfirmacion(int page, int size);

    List<CartaConfirmacionDto> getAllCartaConfirmacion(LocalDate fechaOperacion);

    List<CuentaAsignaDto> getAllCuentaAsigna(LocalDate fechaOperacion);

    //public
}
