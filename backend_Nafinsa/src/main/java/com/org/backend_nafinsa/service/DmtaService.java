package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface DmtaService {

    public ResponseDto cargaValuacionBanxico(ComplementoBanxicoRequest complementoBanxicoRequest);

    public ResponseDto cargaCuentaMargen(CuentaMargenRequest cuentaMargenRequest);

    ResponseDto cargaSicaderC10(SicaderC10Request sicaderC10Request);

    List<BanxicoDTO> getAllSicaderBanxico(LocalDate fechaOperacion);

    List<InteresGeneradoMargenDto> getAllSicaderInteresesMargen(LocalDate fechaOperacion);

    List<C10ReporteDto> getAllSicaderC10(LocalDate fechaOperacion);
}
