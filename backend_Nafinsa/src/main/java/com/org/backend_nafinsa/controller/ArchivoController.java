package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.service.CargaArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("sicader/carga")
public class ArchivoController {
    @Autowired
    public CargaArchivoService cargaArchivo;

    @PostMapping("/archivo")
    public ResponseDto cargarArchivo(
            @RequestParam(required = true) MultipartFile file,
            @RequestParam(required = true) String usuario,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion,
            @RequestParam(required = true) boolean forzar
    ) throws IOException {
        return cargaArchivo.cargarArchivo(file, usuario, fechaOperacion, forzar);
    }

    @PostMapping("/archivoMensual")
    public ResponseDto cargarArchivoMensual(
            @RequestBody (required = true) ArchivoMensualJSRequest archivoMensualJsDtoList
            ) throws IOException {
        return cargaArchivo.cargarArchivoMensual(archivoMensualJsDtoList);
    }

    @GetMapping("/archivoMensual")
    public List<ArchivoReporteMensualDto> getArchivoMensualFecha(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) throws IOException {
        return cargaArchivo.getArchivoMensualFecha(fechaOperacion);
    }

    @GetMapping("/archivo")
    public List<ArchivoResumenDto> getArchivoFecha(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) {
        return cargaArchivo.getArchivoFecha(fechaOperacion);
    }

    @PostMapping("/archivoDetalle")
    public ResponseEntity<?> getArchivoDetalleFecha(
            @RequestParam(required = true) Long id,
            @RequestParam(required = true) String tipoReporte
    ) {
        return cargaArchivo.getArchivoDetalleFecha(id, tipoReporte);
    }


}
