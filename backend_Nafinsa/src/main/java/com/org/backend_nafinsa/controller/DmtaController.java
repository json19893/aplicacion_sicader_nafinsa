package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.service.DmtaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("sicader/dmta")
public class DmtaController {
    @Autowired
    DmtaService dmtaService;

    @PostMapping("/complementoBanxico")
    public ResponseDto cargaValuacionBanxico(
            @RequestBody(required = true) ComplementoBanxicoRequest complementoBanxicoRequest
    ) {
        return dmtaService.cargaValuacionBanxico(complementoBanxicoRequest);
    }

    @PostMapping("/cuentaMargen")
    public ResponseDto cargaCuentaMargen(
            @RequestBody(required = true) CuentaMargenRequest cuentaMargenRequest
    ) {
        return dmtaService.cargaCuentaMargen(cuentaMargenRequest);
    }

    @PostMapping("/sicaderC10")
    public ResponseDto cargaSicaderC10(
            @RequestBody(required = true) SicaderC10Request sicaderC10Request
    ) {

        return dmtaService.cargaSicaderC10(sicaderC10Request);
    }

    @GetMapping("/getComplementoBanxico")
    public List<BanxicoDTO> getAllSicaderBanxico(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) {
        return dmtaService.getAllSicaderBanxico(fechaOperacion);
    }


    @GetMapping("/getSicaderC10")
    public List<C10ReporteDto> getAllSicaderC10(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) {
        return dmtaService.getAllSicaderC10(fechaOperacion);
    }

    @GetMapping("/getCuentaMargen")
    public List<InteresGeneradoMargenDto> getAllSicaderInteresesMargen(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) {
        return dmtaService.getAllSicaderInteresesMargen(fechaOperacion);
    }


}
