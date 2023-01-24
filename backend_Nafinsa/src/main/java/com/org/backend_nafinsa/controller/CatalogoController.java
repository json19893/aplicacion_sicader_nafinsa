package com.org.backend_nafinsa.controller;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/sicader/catalogo")
public class CatalogoController {

    @Autowired
    CatalogoService catalogoService;

    @PostMapping("/cobertura")
    public ResponseDto cargaCobertura(
            @RequestBody(required = true) CoberturaRequest coberturaRequest
    ) {
        return catalogoService.cargaCobertura(coberturaRequest);
    }

    @GetMapping("/getCobertura")
    public List<CatCoberturaConciliarDto> getCobertura() {
        return catalogoService.getCobertura();
    }

    @GetMapping("/getCoberturaId")
    public List<CatCoberturaConciliarDto> getCoberturaId(
    		@RequestParam(required = true) Long id
    		) {
        return catalogoService.getCoberturaById(id);
    }
    
    @PostMapping("/deleteCoberturaId")
    public ResponseDto deleteCoberturaId(
    		@RequestParam(required = true) Long id
    		) {
        return catalogoService.deleteCoberturaId(id);
    }

    @GetMapping("/getSocioLiquidador")
    public List<SicaderCatalogoDto> getSocioLiquidador() {
        return catalogoService.getSocioLiquidador();
    }

    @GetMapping("/getTipoCuenta")
    public List<SicaderCatalogoDto> getTipoCuenta() {
        return catalogoService.getObtenerTipoCuenta();
    }

    @GetMapping("/getTipoDerivado")
    public List<SicaderCatalogoDto> getTipoDerivado() {
        return catalogoService.getTipoDerivado();
    }

    @GetMapping("/getContraparte")
    public List<SicaderCatalogoDto> obtenerContraparte() {
        return catalogoService.getContraparte();
    }

    @GetMapping("/getMoneda")
    public List<SicaderMonedaDto> obtenerMoneda() {
        return catalogoService.getMoneda();
    }

    @GetMapping("/getCuentasConciliar")
    public List<CuentaConciliarDto> obtenerCuentasConciliar() {
        return catalogoService.getCuentasConciliar();
    }

    @GetMapping("/getCuentasConciliarReq10")
    public List<CuentasRequerimiento10Dto> obtenerCuentasConciliarReq10() {
        return catalogoService.obtenerCuentasConciliarReq10();
    }

}
