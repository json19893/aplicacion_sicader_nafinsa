package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.ConciliaPkg;
import com.org.backend_nafinsa.dto.ConciliacionFechaDto;
import com.org.backend_nafinsa.dto.SalidaPkg;
import com.org.backend_nafinsa.dto.ValidacionPkg;
import com.org.backend_nafinsa.entidad.SicaderConciliaciones;
import com.org.backend_nafinsa.entidad.SicaderValidacion;
import com.org.backend_nafinsa.service.ConciliaSicaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/sicader/conciliacion")
public class ConciliaSicaderController {


    @Autowired
    ConciliaSicaderService conciliaSicaderService;

    @PostMapping("/ejecutaConciliacion")
    public SalidaPkg ejecutarConciliacion(
            @RequestBody ConciliaPkg conciliaPkg
    ) {
        return conciliaSicaderService.ejecutarConciliacion(conciliaPkg);
    }

    @PostMapping("/ejecutaValidacion")
    public boolean ejecutarConciliacion(
            @RequestBody ValidacionPkg validacionPkg
    ) {
        return conciliaSicaderService.ejecutarValidacion(validacionPkg);
    }

    @GetMapping("getConciliacionFecha")
    public List <ConciliacionFechaDto> getConciliacionFecha(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ){
        return conciliaSicaderService.getConciliacionFecha(fechaOperacion);
    }

    @GetMapping("getValidacion")
    public SicaderValidacion getValidacion(
    ){
        return conciliaSicaderService.getValidacion();
    }


}
