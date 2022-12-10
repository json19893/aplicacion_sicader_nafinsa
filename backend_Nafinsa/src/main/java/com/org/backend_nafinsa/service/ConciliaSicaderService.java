package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.entidad.SicaderConciliaciones;
import com.org.backend_nafinsa.entidad.SicaderValidacion;

import java.time.LocalDate;
import java.util.List;

public interface ConciliaSicaderService {
    SalidaPkg ejecutarConciliacion(ConciliaPkg conciliaPkg);

    boolean ejecutarValidacion(ValidacionPkg validacionPkg);

    List <ConciliacionFechaDto> getConciliacionFecha(LocalDate fechaOperacion);

    List<SicaderValidacion> getValidacion();

    List<EstatusConciliacionDto> getEstatusConciliacion(EstatusConciliacionRequest estatusConciliacionRequest);
}
