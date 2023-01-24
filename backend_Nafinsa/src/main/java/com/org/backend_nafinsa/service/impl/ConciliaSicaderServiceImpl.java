package com.org.backend_nafinsa.service.impl;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.entidad.SicaderConciliaciones;
import com.org.backend_nafinsa.entidad.SicaderValidacion;
import com.org.backend_nafinsa.exception.ErrorAplicacionControlado;
import com.org.backend_nafinsa.repository.CallPkgSicader;
import com.org.backend_nafinsa.repository.SicaderConciliacionesRepository;
import com.org.backend_nafinsa.repository.SicaderValidacionRepository;
import com.org.backend_nafinsa.service.ConciliaSicaderService;
import com.org.backend_nafinsa.util.CodigosRespuestaControlados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConciliaSicaderServiceImpl implements ConciliaSicaderService {
    @Autowired
    CallPkgSicader callPkgSicader;
    @Autowired
    SicaderValidacionRepository sicaderValidacionRepository;

    @Autowired
    SicaderConciliacionesRepository sicaderConciliacionesRepository;

    @Override
    public SalidaPkg ejecutarConciliacion(ConciliaPkg conciliaPkg) {
        if (conciliaPkg.getInDerivado() == 4)
            conciliaPkg.setInDerivado(null);
        return callPkgSicader.SicaderConciliaPkg(conciliaPkg);
    }

    @Autowired
    CodigosRespuestaControlados respuestaControlada;

    @Override
    public boolean ejecutarValidacion(ValidacionPkg validacionPkg) {
        List<Long> tipoDerivado = new ArrayList<Long>();
        List<Long> validacion = new ArrayList<Long>(Arrays.asList(0L, null));
        callPkgSicader.SicaderValidaPkg(validacionPkg);
        if (!sicaderValidacionRepository.existsByFechaOperacion(validacionPkg.getFechaOperacion())) {
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getFechaValidacion().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getFechaValidacion().get("mensaje")
            );
        }
        if (validacionPkg.getTipoValidacion() == 4) {
            tipoDerivado = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L));
        } else {
            tipoDerivado = new ArrayList<Long>(Arrays.asList(validacionPkg.getDerivado()));
        }
        return !sicaderValidacionRepository.existsByFechaOperacionAndAndTipoDerivadoInAndAndValidacionIn
                (validacionPkg.getFechaOperacion(), tipoDerivado, validacion);
    }

    @Override
    public List <ConciliacionFechaDto> getConciliacionFecha(LocalDate fechaOperacion, String tipoConciliacion, Long idDerivado) {
        List <ConciliacionFechaDto> conciliacionFechaDtoList = new ArrayList<>();
        if(idDerivado<=3 ){
        List<Object[]> objectList = sicaderConciliacionesRepository.getSicaderConciliaciones (fechaOperacion, tipoConciliacion, idDerivado);
        conciliacionFechaDtoList.addAll(
                objectList.stream()
                        .map(ob -> new ConciliacionFechaDto(ob))
                        .collect(Collectors.toList()));
        return conciliacionFechaDtoList;
        }else{
            List<Object[]> objectList = sicaderConciliacionesRepository.getAllSicaderConciliaciones(fechaOperacion, tipoConciliacion);
            conciliacionFechaDtoList.addAll(
                    objectList.stream()
                            .map(ob -> new ConciliacionFechaDto(ob))
                            .collect(Collectors.toList()));
            return conciliacionFechaDtoList;
        }

    }

    @Override
    public List<SicaderValidacion> getValidacion() {
        List<Long> exitosos =new ArrayList<Long>(Arrays.asList(1L));

        return sicaderValidacionRepository.findByValidacionNotIn(exitosos);
    }

    @Override
    public List<EstatusConciliacionDto> getEstatusConciliacion(EstatusConciliacionRequest estatusConciliacionRequest) {
       
     
        List <EstatusConciliacionDto> estatusConciliacionDtoList = new ArrayList<>();
        if(estatusConciliacionRequest.isUltimaConciliacion()){
            List<Object[]> objectList = sicaderConciliacionesRepository.getEstatusConciliacionesUltima();
            estatusConciliacionDtoList.addAll(
                    objectList.stream()
                            .map(ob -> new EstatusConciliacionDto(ob))
                            .collect(Collectors.toList()));
            return estatusConciliacionDtoList;

        }else{
            List<Object[]> objectList1 =new ArrayList<>();
            if (estatusConciliacionRequest.getFechaVencimientoFin() ==null){
             objectList1 = sicaderConciliacionesRepository.getEstatusConciliacionesSinFechaEjec(estatusConciliacionRequest.getFechaOperacionIni(), estatusConciliacionRequest.getFechaOperacionFin());
            }else{
             objectList1 = sicaderConciliacionesRepository.getEstatusConciliacionesFechaEjec(estatusConciliacionRequest.getFechaOperacionIni(), estatusConciliacionRequest.getFechaOperacionFin(),estatusConciliacionRequest.getFechaVencimientoIni(), estatusConciliacionRequest.getFechaVencimientoFin());
            }
            estatusConciliacionDtoList.addAll(
                    objectList1.stream()
                            .map(ob -> new EstatusConciliacionDto(ob))
                            .collect(Collectors.toList()));
            if(estatusConciliacionRequest.getUsuario()!= null){
                estatusConciliacionDtoList=estatusConciliacionDtoList
                        .stream()
                        .filter( c-> c.getUsuario().contains(estatusConciliacionRequest.getUsuario()))
                        .collect(Collectors.toList());
            }
            if(estatusConciliacionRequest.getTipoConciliacion()!= null){
                  
        String conciliacionIn="";
        if (estatusConciliacionRequest.getTipoConciliacion().contains("D")){
            conciliacionIn="Diaria";
        }else {
            conciliacionIn="Mensual";
        }
                String finalConciliacionIn = conciliacionIn;
                estatusConciliacionDtoList=estatusConciliacionDtoList
                        .stream()
                        .filter( c-> c.getTipoConciliacion().contains(finalConciliacionIn))
                        .collect(Collectors.toList());
            }
            if(estatusConciliacionRequest.getEstatus() != null){
                String estatusIn="";
                if (estatusConciliacionRequest.getEstatus().contains("E")){
                    estatusIn="Exitosa";
                }else if (estatusConciliacionRequest.getEstatus().contains("D")){
                    estatusIn="Con diferencias";
                }else {
                    estatusIn="Con Errores";
                }
                String finalEstatusIn = estatusIn;
                estatusConciliacionDtoList=estatusConciliacionDtoList
                        .stream()
                        .filter( c-> c.getEstatus().contains(finalEstatusIn))
                        .collect(Collectors.toList());
            }

            if(estatusConciliacionRequest.getDerivado() != null){
                String derivadoIn="";
                if (estatusConciliacionRequest.getDerivado().contains("1")){
                    derivadoIn="FORWARDS";
                }else if (estatusConciliacionRequest.getDerivado().contains("2")){
                    derivadoIn="FUTUROS";
                }else {
                    derivadoIn="SWAPS";
                }
                String finalDerivadoIn = derivadoIn;
                estatusConciliacionDtoList=estatusConciliacionDtoList
                        .stream()
                        .filter( c-> c.getTipoDerivado().contains(finalDerivadoIn))
                        .collect(Collectors.toList());
            }
            return estatusConciliacionDtoList;

        }

    }
}
