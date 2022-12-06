package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.service.InformacionSidecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/sicader/informacion")
public class informacionSidecaController {
    @Autowired
    InformacionSidecaService informacionSidecaService;

    @PostMapping("/cartaConfirmacion")
    public ResponseDto cargaCartaConfirmacion(
            @RequestBody(required = true) CartaConfirmacionRequest cartaConfirmacionRequest
    ) {
        return informacionSidecaService.cargaCartaConfirmacion(cartaConfirmacionRequest);
    }

    @PostMapping("/cuentaAsigna")
    public ResponseDto ResponseDto(
            @RequestBody(required = true) CuentaAsignaRequest cuentaAsignaRequest
    ) {
        return informacionSidecaService.CargaCuentaAsigna(cuentaAsignaRequest);
    }

    /*EJEMPLO PAGINADOR
    @GetMapping("/cartaConfirmacion")
    public ResponseEntity<Map<String, Object>> getAllTutorials(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = Constants.PAGINADOR_TAMAÑO) int size
    ) {
        return informacionSidecaService.getAllCartaConfirmacion(page,size);

    }
    */

    @GetMapping("/getCartaConfirmacion")
    public List<CartaConfirmacionDto> getAllCartaConfirmacion(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) {
        return informacionSidecaService.getAllCartaConfirmacion(fechaOperacion);
    }

    @GetMapping("/getCuentaAsigna")
    public List<CuentaAsignaDto> getAllCuentaAsigna(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) {
        return informacionSidecaService.getAllCuentaAsigna(fechaOperacion);
    }

}