package com.org.backend_nafinsa.service;


import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.entidad.SicaderCatCoberturas;

import java.util.List;

public interface CatalogoService {
    ResponseDto cargaCobertura(CoberturaRequest coberturaRequest);

    List<SicaderCatalogoDto> getSocioLiquidador();

    List<SicaderCatalogoDto> getObtenerTipoCuenta();

    List<SicaderCatalogoDto> getTipoDerivado();

    List<SicaderCatalogoDto> getContraparte();

    List<CatCoberturaConciliarDto> getCobertura();

    List<SicaderMonedaDto> getMoneda();

    List<CuentaConciliarDto> getCuentasConciliar();

    List<CuentasRequerimiento10Dto> obtenerCuentasConciliarReq10();
}
