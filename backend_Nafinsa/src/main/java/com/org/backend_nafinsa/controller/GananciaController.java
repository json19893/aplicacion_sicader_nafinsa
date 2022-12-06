package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.GananciaPerdidaUDIDto;
import com.org.backend_nafinsa.dto.GananciaRequest;
import com.org.backend_nafinsa.dto.ResponseDto;
import com.org.backend_nafinsa.service.GananciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("sicader/ganancia")
public class GananciaController {
    @Autowired
    GananciaService gananciaService;

    @PostMapping("/gananciaPerdidaUDI")
    public ResponseDto cargaGanancia(
            @RequestBody(required = true) GananciaRequest gananciaRequest
    ) {
        return gananciaService.cargaGanancia(gananciaRequest);
    }

    @GetMapping("/getGananciaPerdidaUDI")
    public List<GananciaPerdidaUDIDto> getGananciaPerdidaUDI(
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaOperacion
    ) {
        return gananciaService.getGananciaPerdidaUDI(fechaOperacion);
    }

}
