package com.org.backend_nafinsa.service.impl;


import com.org.backend_nafinsa.dto.ResumenDivisasDto;
import com.org.backend_nafinsa.repository.SicaderMonedaRepository;
import com.org.backend_nafinsa.service.CierreJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CierreJornadaServiceImpl implements CierreJornadaService {
    @Autowired
    SicaderMonedaRepository sicaderMonedaRepository;

    @Override
    public List<ResumenDivisasDto> getResumenDivisa(LocalDate fechaIni, LocalDate fechaFin, Long monClave) {
        List<ResumenDivisasDto> resumenDivisasDtos = new ArrayList<>();
        if(monClave== null){
            List<Object[]> objectList = sicaderMonedaRepository.getSicaderCierreJornadaFecha(fechaIni, fechaFin);
            resumenDivisasDtos.addAll(
                    objectList.stream()
                            .map(ob -> new ResumenDivisasDto(ob))
                            .collect(Collectors.toList()));
        }else {
            List<Object[]> objectList = sicaderMonedaRepository.getSicaderCierreJornada(fechaIni, fechaFin, monClave);
            resumenDivisasDtos.addAll(
                    objectList.stream()
                            .map(ob -> new ResumenDivisasDto(ob))
                            .collect(Collectors.toList()));
        }

        return resumenDivisasDtos;

    }
}
