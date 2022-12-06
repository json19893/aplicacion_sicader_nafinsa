package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.GananciaPerdidaUDIDto;
import com.org.backend_nafinsa.dto.GananciaRequest;
import com.org.backend_nafinsa.dto.ResponseDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface GananciaService {
    ResponseDto cargaGanancia(GananciaRequest gananciaRequest);

    List<GananciaPerdidaUDIDto> getGananciaPerdidaUDI(LocalDate fechaOperacion);
}
