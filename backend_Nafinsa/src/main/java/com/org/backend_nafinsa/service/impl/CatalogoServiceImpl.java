package com.org.backend_nafinsa.service.impl;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.entidad.*;
import com.org.backend_nafinsa.repository.*;
import com.org.backend_nafinsa.service.CatalogoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.cache.annotation.Cacheable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CatalogoServiceImpl implements CatalogoService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SicaderCatCoberturaRepository sicaderCatCoberturaRepository;

    @Autowired
    SicaderCatSociosLiquidadoresRepository sicaderCatSociosLiquidadoresRepository;

    @Autowired
    SicaderCatTipoCuentaRepository sicaderCatTipoCuentaRepository;

    @Autowired
    SicaderCatTipoDerivadosRepository sicaderCatTipoDerivadosRepository;

    @Autowired
    SicaderCatContraparteRepository sicaderCatContraparteRepository;

    @Autowired
    SicaderMonedaRepository sicaderMonedaRepository;

    @Autowired
    SicaderCuentasConciliarRepository sicaderCuentasConciliarRepository;

    @Override
    public ResponseDto cargaCobertura(CoberturaRequest coberturaRequest) {
        SicaderCuentasConciliar inCuentaActiva= coberturaRequest.getCuentaActiva() == null ? null : sicaderCuentasConciliarRepository.findById(coberturaRequest.getCuentaActiva()).get();
        SicaderCuentasConciliar inCuentaPasiva= coberturaRequest.getCuentaPasiva() == null ? null : sicaderCuentasConciliarRepository.findById(coberturaRequest.getCuentaPasiva()).get();
        SicaderCuentasConciliar inCuentaCapital=coberturaRequest.getCuentaCapital() == null ? null :  sicaderCuentasConciliarRepository.findById(coberturaRequest.getCuentaCapital()).get();
        SicaderCatCoberturas sicaderCatCoberturas = new SicaderCatCoberturas();
        sicaderCatCoberturas.setNombre(coberturaRequest.getNombre());
        sicaderCatCoberturas.setCuentaActiva(inCuentaActiva);
        sicaderCatCoberturas.setCuentaPasiva(inCuentaPasiva);
        sicaderCatCoberturas.setCuentaCapital(inCuentaCapital);
        sicaderCatCoberturaRepository.save(sicaderCatCoberturas);
        return new ResponseDto("OK");
    }

    @Cacheable("getSocioLiquidador")
    @Override
    public List<SicaderCatalogoDto> getSocioLiquidador() {
        List<SicaderCatSociosLiquidadores> sicaderCatSociosLiquidadorList = sicaderCatSociosLiquidadoresRepository.findAll();
        List<SicaderCatalogoDto> sicaderCatalogoDtoList = modelMapper.map(sicaderCatSociosLiquidadorList, new TypeToken<List<SicaderCatalogoDto>>() {
        }.getType());
        return sicaderCatalogoDtoList;
    }

    @Cacheable("getTipoCuenta")
    @Override
    public List<SicaderCatalogoDto> getObtenerTipoCuenta() {
        List<SicaderCatTipoCuenta> sicaderCatTipoCuentaList = sicaderCatTipoCuentaRepository.findAll();
        List<SicaderCatalogoDto> sicaderCatalogoDtoList = modelMapper.map(sicaderCatTipoCuentaList, new TypeToken<List<SicaderCatalogoDto>>() {
        }.getType());
        return sicaderCatalogoDtoList;
    }

    @Cacheable("getDerivado")
    @Override
    public List<SicaderCatalogoDto> getTipoDerivado() {
        List<SicaderCatTipoDerivados> sicaderCatTipoDerivados = sicaderCatTipoDerivadosRepository.findAll();
        List<SicaderCatalogoDto> sicaderCatalogoDtoList = modelMapper.map(sicaderCatTipoDerivados, new TypeToken<List<SicaderCatalogoDto>>() {
        }.getType());
        return sicaderCatalogoDtoList;
    }

    @Cacheable("getContraparte")
    @Override
    public List<SicaderCatalogoDto> getContraparte() {
        List<SicaderCatContraparte> sicaderCatContrapartes = sicaderCatContraparteRepository.findAll();
        List<SicaderCatalogoDto> sicaderCatalogoDtoList = modelMapper.map(sicaderCatContrapartes, new TypeToken<List<SicaderCatalogoDto>>() {
        }.getType());
        return sicaderCatalogoDtoList;
    }

    @Override
    public List<CatCoberturaConciliarDto> getCobertura() {
        List<Object[]> objectList = sicaderCatCoberturaRepository.getAllCatCoberturaConciliar();
        List<CatCoberturaConciliarDto> catCoberturaConciliarDtos = new ArrayList<>();
        catCoberturaConciliarDtos.addAll(
                objectList.stream()
                        .map(ob -> new CatCoberturaConciliarDto(ob))
                        .collect(Collectors.toList()));

        return catCoberturaConciliarDtos;
    }

    @Override
    public List<SicaderMonedaDto> getMoneda() {

        List<SicaderMoneda> sicaderMonedas = sicaderMonedaRepository.findAll();
        List<SicaderMonedaDto> sicaderMonedaDtoList = modelMapper.map(sicaderMonedas, new TypeToken<List<SicaderMonedaDto>>() {
        }.getType());
        return sicaderMonedaDtoList;
    }

    @Override
    public List<CuentaConciliarDto> getCuentasConciliar() {
        List<Object[]> objectList = sicaderCatTipoCuentaRepository.getAllcuentaConciliar();
        List<CuentaConciliarDto> cuentaConciliarDtos = new ArrayList<>();
        cuentaConciliarDtos.addAll(
                objectList.stream()
                        .map(ob -> new CuentaConciliarDto(ob))
                        .collect(Collectors.toList()));

        return cuentaConciliarDtos;
    }

    @Override
    public List<CuentasRequerimiento10Dto> obtenerCuentasConciliarReq10() {
        List<Object[]> objectList = sicaderCatTipoCuentaRepository.getAllRequerimiento10Cuenta();
        List<CuentasRequerimiento10Dto> requerimiento10Dtos = new ArrayList<>();
        requerimiento10Dtos.addAll(
                objectList.stream()
                        .map(ob -> new CuentasRequerimiento10Dto(ob))
                        .collect(Collectors.toList()));

        return requerimiento10Dtos;
    }
}
