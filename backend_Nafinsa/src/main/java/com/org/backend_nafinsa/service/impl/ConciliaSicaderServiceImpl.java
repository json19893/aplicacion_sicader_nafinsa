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
    public List <ConciliacionFechaDto> getConciliacionFecha(LocalDate fechaOperacion) {
        List <ConciliacionFechaDto> conciliacionFechaDtoList = new ArrayList<>();
        List<Object[]> objectList = sicaderConciliacionesRepository.getSicaderConciliaciones (fechaOperacion);
        conciliacionFechaDtoList.addAll(
                objectList.stream()
                        .map(ob -> new ConciliacionFechaDto(ob))
                        .collect(Collectors.toList()));
        return conciliacionFechaDtoList;
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
            List<Object[]> objectList = sicaderConciliacionesRepository.getEstatusConciliaciones(estatusConciliacionRequest.getFechaOperacionIni(), estatusConciliacionRequest.getFechaOperacionFin(),estatusConciliacionRequest.getFechaVencimientoIni(), estatusConciliacionRequest.getFechaVencimientoFin(), estatusConciliacionRequest.getUsuario(), estatusConciliacionRequest.getTipoConciliacion(),estatusConciliacionRequest.getEstatus(), estatusConciliacionRequest.getDerivado());
            estatusConciliacionDtoList.addAll(
                    objectList.stream()
                            .map(ob -> new EstatusConciliacionDto(ob))
                            .collect(Collectors.toList()));
            return estatusConciliacionDtoList;

        }

    }
}
