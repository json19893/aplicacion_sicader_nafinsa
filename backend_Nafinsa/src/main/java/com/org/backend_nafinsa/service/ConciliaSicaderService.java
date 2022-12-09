package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.ConciliaPkg;
import com.org.backend_nafinsa.dto.ConciliacionFechaDto;
import com.org.backend_nafinsa.dto.SalidaPkg;
import com.org.backend_nafinsa.dto.ValidacionPkg;
import com.org.backend_nafinsa.entidad.SicaderConciliaciones;
import com.org.backend_nafinsa.entidad.SicaderValidacion;

import java.time.LocalDate;
import java.util.List;

public interface ConciliaSicaderService {
    SalidaPkg ejecutarConciliacion(ConciliaPkg conciliaPkg);

    boolean ejecutarValidacion(ValidacionPkg validacionPkg);

    List <ConciliacionFechaDto> getConciliacionFecha(LocalDate fechaOperacion);

    List<SicaderValidacion> getValidacion();
}
