package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.ResumenDivisasDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CierreJornadaService {
    List<ResumenDivisasDto> getResumenDivisa(LocalDate fechaIni, LocalDate fechaFin, Long monClave);
}
