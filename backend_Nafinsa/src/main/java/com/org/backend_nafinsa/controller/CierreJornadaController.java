package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.ResumenDivisasDto;
import com.org.backend_nafinsa.service.CierreJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("sicader/cierreJornada")
public class CierreJornadaController {

    @Autowired
    CierreJornadaService cierreJornadaService;

    @GetMapping("/resumenDivisa")
    public List<ResumenDivisasDto> getResumenDivisa(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaIni,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            @RequestParam(required = false) Long monClave
    ) {
        return cierreJornadaService.getResumenDivisa(fechaIni, fechaFin, monClave);
    }

}
