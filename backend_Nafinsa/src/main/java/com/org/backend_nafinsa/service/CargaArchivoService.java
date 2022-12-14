package com.org.backend_nafinsa.service;

import com.org.backend_nafinsa.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CargaArchivoService {
    public ResponseDto cargarArchivo(MultipartFile archivoExcel, String usuario, LocalDate fechaOperacion, boolean forzar) throws IOException;

    ResponseDto cargarArchivoMensual(ArchivoMensualJSRequest archivoMensualJsDtoList) throws IOException;

    List<ArchivoResumenDto> getArchivoFecha(LocalDate fechaOperacion);

    List<ArchivoReporteMensualDto> getArchivoMensualFecha(LocalDate fechaOperacion);

    ResponseEntity<?> getArchivoDetalleFecha(Long id, String tipoReporte);

    ResponseDto cargarArchivo06IRDT(ArchivoMensualJSRequest archivoMensualJsDtoList);
}
