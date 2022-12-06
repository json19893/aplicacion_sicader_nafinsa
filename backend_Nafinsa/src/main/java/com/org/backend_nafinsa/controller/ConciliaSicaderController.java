package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.ConciliaPkg;
import com.org.backend_nafinsa.dto.SalidaPkg;
import com.org.backend_nafinsa.dto.ValidacionPkg;
import com.org.backend_nafinsa.service.ConciliaSicaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
