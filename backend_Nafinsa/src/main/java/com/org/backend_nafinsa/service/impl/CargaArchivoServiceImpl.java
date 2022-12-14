package com.org.backend_nafinsa.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvValidationException;
import com.org.backend_nafinsa.dto.*;
import com.org.backend_nafinsa.entidad.*;
import com.org.backend_nafinsa.exception.ErrorAplicacionControlado;
import com.org.backend_nafinsa.repository.*;
import com.org.backend_nafinsa.service.CargaArchivoService;
import com.org.backend_nafinsa.util.CodigosRespuestaControlados;
import com.org.backend_nafinsa.util.Constants;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.extern.java.Log;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CargaArchivoServiceImpl implements CargaArchivoService {
    private static final Logger logger = LoggerFactory.getLogger(CargaArchivoServiceImpl.class);
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SicaderReporte01Repository sicaderReporte01Repository;
    @Autowired
    SicaderReporte03Repository sicaderReporte03Repository;
    @Autowired
    SicaderReporte06Repository sicaderReporte06Repository;
    @Autowired
    SicaderReporte40Repository sicaderReporte40Repository;
    @Autowired
    SicaderReporte42Repository sicaderReporte42Repository;
    @Autowired
    SicaderReporteIRDTRepository sicaderReporteIRDTRepository;
    @Autowired
    SicaderReporte01DetalleRepository sicaderReporte01DetalleRepository;
    @Autowired
    SicaderReporte03DetalleRepository sicaderReporte03DetalleRepository;
    @Autowired
    SicaderReporte06DetalleRepository sicaderReporte06DetalleRepository;
    @Autowired
    SicaderReporte40DetalleRepository sicaderReporte40DetalleRepository;
    @Autowired
    SicaderReporte42DetalleRepository sicaderReporte42DetalleRepository;
    @Autowired
    SicaderReporteIRDTDetalleRepository sicaderReporteIRDTDetalleRepository;

    @Autowired
    CodigosRespuestaControlados respuestaControlada;

    @Autowired
    SicaderCatSociosLiquidadoresRepository sociosLiquidadoresRepository;

    @Autowired
    SicaderSlVsSidecaRepository sicaderSlVsSidecaRepository;
    @Autowired
    SicaderSlVsSidecaDetalleRepository sicaderSlVsSidecaDetalleRepository;

    @Autowired
    Utilidades utilidades;

    @Override
    public ResponseDto cargarArchivo(MultipartFile file, String usuario, LocalDate fechaOperacion, boolean forzar) {
        ResponseDto responseDto = new ResponseDto("OK");
        String reporte[];
        reporte = validaTipoReporte(file.getOriginalFilename().substring(0, 2), file.getOriginalFilename().substring(0, 4));
        UUID uuid = UUID.randomUUID();
        logger.info("IDPROCESO:" + uuid + " file:" + file.getOriginalFilename() + " usuario:" + usuario + " fechaOperacion:" + fechaOperacion);
        if (file.isEmpty()) {
            logger.info("IDPROCESO:" + uuid + " codigo:" + respuestaControlada.getArchivovacio().get("codigo"));
            logger.info("IDPROCESO:" + uuid + " mensaje:" + respuestaControlada.getArchivovacio().get("mensaje"));
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getArchivovacio().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getArchivovacio().get("mensaje")
            );
        }
        switch (reporte[0]) {
            case "01":
                logger.info("IDPROCESO:" + uuid + " tipo de Archivo a procesar : 01");
                Optional<SicaderReporte01> reporte01Repo = sicaderReporte01Repository.findByFechaOperacion(fechaOperacion);
                if (reporte01Repo.isPresent() && !forzar) {
                    return new ResponseDto(Constants.MENSAJE_EXISTE);
                } else {
                    procesarReporte01(file, usuario, fechaOperacion, reporte01Repo);
                }
                break;
            case "03":
                logger.info("IDPROCESO:" + uuid + " tipo de Archivo a procesar : 03");
                Optional<SicaderReporte03> reporte03Repo = sicaderReporte03Repository.findByFechaOperacion(fechaOperacion);
                if (reporte03Repo.isPresent() && !forzar) {
                    responseDto.setRespuesta(Constants.MENSAJE_EXISTE);
                } else {
                    procesarReporte03(file, usuario, fechaOperacion, reporte03Repo);
                }
                break;
            case "40":
                logger.info("IDPROCESO:" + uuid + " tipo de Archivo a procesar : 40");
                Optional<SicaderReporte40> reporte40Repo = sicaderReporte40Repository.findByFechaOperacion(fechaOperacion);
                if (reporte40Repo.isPresent() && !forzar) {
                    responseDto.setRespuesta(Constants.MENSAJE_EXISTE);
                } else {
                    procesarReporte40(file, usuario, fechaOperacion, reporte40Repo);
                }
                break;
            case "06":
                logger.info("IDPROCESO:" + uuid + " tipo de Archivo a procesar : 06");
                Optional<SicaderReporte06> reporte06Repo = sicaderReporte06Repository.findByFechaOperacion(fechaOperacion);
                if (reporte06Repo.isPresent() && !forzar) {
                    responseDto.setRespuesta(Constants.MENSAJE_EXISTE);
                } else {
                    procesarReporte06(file, usuario, fechaOperacion, reporte06Repo);
                }
                break;
            case "42":
                logger.info("IDPROCESO:" + uuid + " tipo de Archivo a procesar : 42");
                Optional<SicaderReporte42> reporte42Repo = sicaderReporte42Repository.findByFechaOperacion(fechaOperacion);
                if (reporte42Repo.isPresent() && !forzar) {
                    responseDto.setRespuesta(Constants.MENSAJE_EXISTE);
                } else {
                    procesarReporte42(file, usuario, fechaOperacion, reporte42Repo);
                }
                break;
            default:
                logger.info("IDPROCESO:" + uuid + " tipo de Archivo a procesar : IRDT");
                Optional<SicaderReporteIRDT> reporteIRDTRepo = sicaderReporteIRDTRepository.findByFechaOperacion(fechaOperacion);
                if (reporteIRDTRepo.isPresent() && !forzar) {
                    responseDto.setRespuesta(Constants.MENSAJE_EXISTE);
                } else {
                    procesarReporteIRDT(file, usuario, fechaOperacion, reporteIRDTRepo);
                }
        }
        logger.info("IDPROCESO:" + uuid + " RESULTADO : OK");
        //updateResumen(sicaderResumenArchivo, Constants.CARGA_EXITOSA);
        return responseDto;

    }

    @Override
    public ResponseDto cargarArchivoMensual(ArchivoMensualJSRequest archivoMensualJsDtoList) {

        Optional<SicaderSlVsSideca> sicaderSlVsSideca = sicaderSlVsSidecaRepository.findByFechaOperacion(archivoMensualJsDtoList.getFechaOperacion());
        if (sicaderSlVsSideca.isPresent() && ! archivoMensualJsDtoList.isForzar()) {
            return new ResponseDto(Constants.MENSAJE_EXISTE);
        }
        SicaderSlVsSideca sicaderSlVsSidecaSave = new SicaderSlVsSideca();
        if (sicaderSlVsSideca.isPresent()) {
            sicaderSlVsSidecaSave = sicaderSlVsSideca.get();
            sicaderSlVsSidecaSave.setFechaModificacion(Constants.diaActual);
            sicaderSlVsSidecaSave.setUsuModificacion(archivoMensualJsDtoList.getUsuario());
            sicaderSlVsSidecaSave.getSicaderSlVsSidecaDetalles().clear();
            sicaderSlVsSidecaSave.setEstatus("E");
        } else {
            sicaderSlVsSidecaSave.setUsuRegistro(archivoMensualJsDtoList.getUsuario());
            sicaderSlVsSidecaSave.setFechaRegistro(Constants.diaActual);
            sicaderSlVsSidecaSave.setFechaOperacion(archivoMensualJsDtoList.getFechaOperacion());
            sicaderSlVsSidecaSave.setEstatus("E");

        }
        List<ReporteMensualJs> reporteMensualLista = new ArrayList<>();

        for ( ArchivoMensualJsDto  objeto : archivoMensualJsDtoList.getArchivoMensualJsDtoList()) {
            objeto.getSocio();
            SicaderCatSociosLiquidadores sicaderCatSociosLiquidadores =
                    sociosLiquidadoresRepository.findByNombreLike("%" + objeto.getSocio() + "%");
            if (sicaderCatSociosLiquidadores == null) {
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getSocioLiquidador().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getSocioLiquidador().get("mensaje")
                );
            }

            Iterator itr = objeto.getData().iterator();
            int numCelda =0;
            while(itr.hasNext())
            {
                Object[] elemento = (Object[]) itr.next();
                if(numCelda==0 ){
                    if (elemento.length  != Constants.HEADER_REPORTE_MENSUAL.length) {
                        throw new ErrorAplicacionControlado(
                                respuestaControlada.getArchivocolumnas().get("codigo"),
                                this.getClass().getName(),
                                respuestaControlada.getArchivocolumnas().get("mensaje" +" columna:"+numCelda)
                        );
                    }
                }else {
                   if(elemento.length!=0){
                    if (elemento.length  != Constants.HEADER_REPORTE_MENSUAL.length) {
                        throw new ErrorAplicacionControlado(
                                respuestaControlada.getArchivocolumnas().get("codigo"),
                                this.getClass().getName(),
                                respuestaControlada.getArchivocolumnas().get("mensaje" +" columna:"+numCelda)
                        );
                    }
                    ReporteMensualJs reporteMensualAdd1 = new ReporteMensualJs();
                    reporteMensualAdd1.setFolio((Integer) elemento[0]);
                    reporteMensualAdd1.setContrato((Integer) elemento[1] );
                    reporteMensualAdd1.setMoneda((String) elemento[2] );
                    reporteMensualAdd1.setEntregarSl( Double.parseDouble(elemento[3].toString()));
                    reporteMensualAdd1.setRecibirSl(Double.parseDouble( elemento[4].toString()) );
                    reporteMensualAdd1.setNetoValSl(Double.parseDouble( elemento[5].toString()));
                    reporteMensualAdd1.setEntregarSideca(Double.parseDouble( elemento[6].toString()) );
                    reporteMensualAdd1.setRecibirSideca(Double.parseDouble( elemento[7].toString()));
                    reporteMensualAdd1.setNetovalSideca(Double.parseDouble( elemento[8].toString()));
                    reporteMensualAdd1.setDiferencia(Double.parseDouble( elemento[9].toString()));
                    reporteMensualAdd1.setSocioLiquidador(sicaderCatSociosLiquidadores.getId());
                    reporteMensualLista.add(reporteMensualAdd1);
                }
                   }
                numCelda=numCelda+1;
            }

        }

        List<SicaderSlVsSidecaDetalle> sicaderSlVsSidecaDetalles = new ArrayList<>();
        for (ReporteMensualJs reporteMensual : (Iterable<ReporteMensualJs>) reporteMensualLista) {
            SicaderSlVsSidecaDetalle sicaderSlVsSidecaDetalletmp = new SicaderSlVsSidecaDetalle(reporteMensual, sicaderSlVsSidecaSave);
            sicaderSlVsSidecaDetalles.add(sicaderSlVsSidecaDetalletmp);
        }
        guardarReporteMensual(sicaderSlVsSidecaSave, sicaderSlVsSidecaDetalles);
        return new ResponseDto("OK");
    }

    @Override
    public List<ArchivoResumenDto> getArchivoFecha(LocalDate fechaOperacion) {
        List<Object[]> objectList = sicaderReporte01Repository.getSicaderResumenCarga(fechaOperacion);
        List<ArchivoResumenDto> archivoResumenDtos = new ArrayList<>();
        archivoResumenDtos.addAll(
                objectList.stream()
                        .map(ob -> new ArchivoResumenDto(ob))
                        .collect(Collectors.toList()));
        return archivoResumenDtos;

    }

    @Override
    public List<ArchivoReporteMensualDto> getArchivoMensualFecha(LocalDate fechaOperacion) {
        List<Object[]> objectList = sicaderSlVsSidecaRepository.getSicaderReporteMensual(fechaOperacion);
        List<ArchivoReporteMensualDto> archivoReporteMensualDtos = new ArrayList<>();
        archivoReporteMensualDtos.addAll(
                objectList.stream()
                        .map(ob -> new ArchivoReporteMensualDto(ob))
                        .collect(Collectors.toList()));
        return archivoReporteMensualDtos;
    }

    @Override
    public ResponseEntity<?> getArchivoDetalleFecha(Long id, String tipoReporte) {
        switch (tipoReporte) {
            case "REPORTE01":
                List<SicaderReporte01Detalle> reporte01DetalleList = sicaderReporte01DetalleRepository.findByReporteId(id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(reporte01DetalleList);
            case "REPORTE03":
                List<SicaderReporte03Detalle> reporte03DetalleList = sicaderReporte03DetalleRepository.findByReporteId(id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(reporte03DetalleList);
            case "REPORTE06":
                List<SicaderReporte06Detalle> sicaderReporte06DetalleList = sicaderReporte06DetalleRepository.findByReporteId(id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(sicaderReporte06DetalleList);
            case "REPORTE40":
                List<SicaderReporte40Detalle> reporte40DetalleList = sicaderReporte40DetalleRepository.findByReporteId(id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(reporte40DetalleList);
            case "REPORTE42":
                List<SicaderReporte42Detalle> reporte42DetalleList = sicaderReporte42DetalleRepository.findByReporteId(id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(reporte42DetalleList);
            case "REPORTEIRDT":
                List<SicaderReporteIRDTDetalle> reporteIRDTDetalleList = sicaderReporteIRDTDetalleRepository.findByReporteId(id);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(reporteIRDTDetalleList);
            default:
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"Message\":\"No se encontró información.\"}");
        }
    }

    @Override
    public ResponseDto cargarArchivo06IRDT(ArchivoMensualJSRequest archivoMensualJsDtoList) {
        ResponseDto responseDto = new ResponseDto("OK");
        String reporte[];
        reporte = validaTipoReporte(archivoMensualJsDtoList.getNombreArchivo().substring(0, 2), archivoMensualJsDtoList.getNombreArchivo().substring(0, 4));
        if (archivoMensualJsDtoList.getArchivoMensualJsDtoList()== null) {
            logger.info(respuestaControlada.getArchivovacio().get("codigo"));
            logger.info(respuestaControlada.getArchivovacio().get("mensaje"));
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getArchivovacio().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getArchivovacio().get("mensaje")
            );
        }

        Optional<SicaderReporteIRDT> reporteIRDTRepo = sicaderReporteIRDTRepository.findByFechaOperacion(archivoMensualJsDtoList.getFechaOperacion());
        if (reporteIRDTRepo.isPresent() && !archivoMensualJsDtoList.isForzar()) {
            responseDto.setRespuesta(Constants.MENSAJE_EXISTE);
        } else {
            procesarReporteIRDTJs(archivoMensualJsDtoList, reporteIRDTRepo);
        }

        return responseDto;
    }


    public void procesarReporte01(MultipartFile file, String usuario, LocalDate fechaOperacion, Optional<SicaderReporte01> reporte01Repo) {
        Long numeroReporte = 0L;
        SicaderReporte01 sicaderReporte01Save = new SicaderReporte01();
        try {
            if (reporte01Repo.isPresent()) {
                sicaderReporte01Save = reporte01Repo.get();
                sicaderReporte01Save.setFechaModificacion(Constants.diaActual);
                sicaderReporte01Save.setUsuModificacion(usuario);
                sicaderReporte01Save.setEstatus("Pendiente");
                sicaderReporte01Save.getSicaderReporte01Detalles().clear();
                numeroReporte = sicaderReporte01Save.getId();
            } else {
                sicaderReporte01Save.setUsuRegistro(usuario);
                sicaderReporte01Save.setFechaRegistro(Constants.diaActual);
                sicaderReporte01Save.setFechaOperacion(fechaOperacion);
                sicaderReporte01Save.setEstatus("Pendiente");
            }
            sicaderReporte01Save = sicaderReporte01Repository.saveAndFlush(sicaderReporte01Save);
            numeroReporte = sicaderReporte01Save.getId();
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Reporte01.class);
            strategy.setColumnMapping(Constants.HEADER_REPORTE_01);
            CsvToBean csvToBean = cargaCsvToBeanSinHeader(file, reader, strategy, Constants.HEADER_REPORTE_01, csvReader);
            validaNomenclatura(csvToBean);
            List<SicaderReporte01Detalle> sicaderReporte01Detalles = new ArrayList<>();
            for (Reporte01 reporte01 : (Iterable<Reporte01>) csvToBean) {
                SicaderReporte01Detalle sicaderReporte01Detalletmp = new SicaderReporte01Detalle(reporte01, sicaderReporte01Save);
                sicaderReporte01Detalles.add(sicaderReporte01Detalletmp);
            }
            guardarReporte01(sicaderReporte01Save, sicaderReporte01Detalles);
            csvReader.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (numeroReporte != 0) {
                sicaderReporte01Save.setEstatus(Constants.CARGA_ERROR);
                sicaderReporte01Repository.saveAndFlush(sicaderReporte01Save);
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getLecturaarchivos().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getLecturaarchivos().get("mensaje")
                );
            }
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );
        }
    }

    public void procesarReporte03(MultipartFile file, String usuario, LocalDate fechaOperacion, Optional<SicaderReporte03> reporte03Repo) {
        Long numeroReporte = 0L;
        SicaderReporte03 sicaderReporte03Save = new SicaderReporte03();
        try {
            if (reporte03Repo.isPresent()) {
                sicaderReporte03Save = reporte03Repo.get();
                sicaderReporte03Save.setFechaModificacion(Constants.diaActual);
                sicaderReporte03Save.setUsuModificacion(usuario);
                sicaderReporte03Save.setEstatus(Constants.CARGA_INICIAL);
                sicaderReporte03Save.getSicaderReporte03Detalles().clear();

            } else {
                sicaderReporte03Save.setUsuRegistro(usuario);
                sicaderReporte03Save.setFechaRegistro(Constants.diaActual);
                sicaderReporte03Save.setFechaOperacion(fechaOperacion);
                sicaderReporte03Save.setEstatus(Constants.CARGA_INICIAL);
            }
            sicaderReporte03Repository.saveAndFlush(sicaderReporte03Save);
            numeroReporte = sicaderReporte03Save.getId();
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Reporte03.class);
            strategy.setColumnMapping(Constants.HEADER_REPORTE_03);
            CsvToBean csvToBean = cargaCsvToBeanSinHeader(file, reader, strategy, Constants.HEADER_REPORTE_03, csvReader);
            validaNomenclatura(csvToBean);
            List<SicaderReporte03Detalle> sicaderReporte03Detalles = new ArrayList<>();
            for (Reporte03 reporte03 : (Iterable<Reporte03>) csvToBean) {
                SicaderReporte03Detalle sicaderReporte03Detalletmp = new SicaderReporte03Detalle(reporte03, sicaderReporte03Save);
                sicaderReporte03Detalles.add(sicaderReporte03Detalletmp);
            }
            reporte03Repo = sicaderReporte03Repository.findByFechaOperacion(fechaOperacion);
            guardarReporte03(sicaderReporte03Save, sicaderReporte03Detalles);
            csvReader.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (numeroReporte != 0) {
                sicaderReporte03Save.setEstatus(Constants.CARGA_ERROR);
                sicaderReporte03Repository.saveAndFlush(sicaderReporte03Save);
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getLecturaarchivos().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getLecturaarchivos().get("mensaje")
                );
            }
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );

        }
    }

    public void procesarReporte06(MultipartFile file, String usuario, LocalDate fechaOperacion, Optional<SicaderReporte06> reporte06Repo) {
        Long numeroReporte = 0L;
        SicaderReporte06 sicaderReporte06Save = new SicaderReporte06();
        try {
            if (reporte06Repo.isPresent()) {
                sicaderReporte06Save = reporte06Repo.get();
                sicaderReporte06Save.setFechaModificacion(Constants.diaActual);
                sicaderReporte06Save.setUsuModificacion(usuario);
                sicaderReporte06Save.setEstatus(Constants.CARGA_INICIAL);
                sicaderReporte06Save.getSicaderReporte06Detalles().clear();
            } else {
                sicaderReporte06Save.setUsuRegistro(usuario);
                sicaderReporte06Save.setFechaRegistro(Constants.diaActual);
                sicaderReporte06Save.setFechaOperacion(fechaOperacion);
                sicaderReporte06Save.setEstatus(Constants.CARGA_INICIAL);
            }
            sicaderReporte06Save = sicaderReporte06Repository.saveAndFlush(sicaderReporte06Save);
            numeroReporte = sicaderReporte06Save.getId();
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Reporte06.class);
            strategy.setColumnMapping(Constants.HEADER_REPORTE_06);
            CsvToBean csvToBean = cargaCsvToBeanSinHeader(file, reader, strategy, Constants.HEADER_REPORTE_06, csvReader);
            validaNomenclatura(csvToBean);
            List<SicaderReporte06Detalle> sicaderReporte06Detalles = new ArrayList<>();
            for (Reporte06 reporte06 : (Iterable<Reporte06>) csvToBean) {
                SicaderReporte06Detalle sicaderReporte06Detalletmp = new SicaderReporte06Detalle(reporte06, sicaderReporte06Save);
                sicaderReporte06Detalles.add(sicaderReporte06Detalletmp);
            }
            guardarReporte06(sicaderReporte06Save, sicaderReporte06Detalles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (numeroReporte != 0) {
                sicaderReporte06Save.setEstatus(Constants.CARGA_ERROR);
                sicaderReporte06Repository.saveAndFlush(sicaderReporte06Save);
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getLecturaarchivos().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getLecturaarchivos().get("mensaje")
                );
            }
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );
        }
    }

    public void procesarReporte40(MultipartFile file, String usuario, LocalDate fechaOperacion, Optional<SicaderReporte40> reporte40Repo) {
        Long numeroReporte = 0L;
        SicaderReporte40 sicaderReporte40Save = new SicaderReporte40();
        try {
            if (reporte40Repo.isPresent()) {
                sicaderReporte40Save = reporte40Repo.get();
                sicaderReporte40Save.setFechaModificacion(Constants.diaActual);
                sicaderReporte40Save.setUsuModificacion(usuario);
                sicaderReporte40Save.setEstatus(Constants.CARGA_INICIAL);
                sicaderReporte40Save.getSicaderReporte40Detalle().clear();
            } else {
                sicaderReporte40Save.setUsuRegistro(usuario);
                sicaderReporte40Save.setFechaRegistro(Constants.diaActual);
                sicaderReporte40Save.setFechaOperacion(fechaOperacion);
                sicaderReporte40Save.setEstatus(Constants.CARGA_INICIAL);
            }
            sicaderReporte40Save = sicaderReporte40Repository.saveAndFlush(sicaderReporte40Save);
            numeroReporte = sicaderReporte40Save.getId();
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Reporte40.class);
            strategy.setColumnMapping(Constants.HEADER_REPORTE_40);
            CsvToBean csvToBean = cargaCsvToBeanSinHeader(file, reader, strategy, Constants.HEADER_REPORTE_40, csvReader);
            validaNomenclatura(csvToBean);
            List<SicaderReporte40Detalle> sicaderReporte40Detalles = new ArrayList<>();
            for (Reporte40 reporte40 : (Iterable<Reporte40>) csvToBean) {
                SicaderReporte40Detalle sicaderReporte40Detalletmp = new SicaderReporte40Detalle(reporte40, sicaderReporte40Save);
                sicaderReporte40Detalles.add(sicaderReporte40Detalletmp);
            }
            guardarReporte40(sicaderReporte40Save, sicaderReporte40Detalles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (numeroReporte != 0) {
                sicaderReporte40Save.setEstatus(Constants.CARGA_ERROR);
                sicaderReporte40Repository.saveAndFlush(sicaderReporte40Save);
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getLecturaarchivos().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getLecturaarchivos().get("mensaje")
                );
            }
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );
        }
    }

    public void procesarReporte42(MultipartFile file, String usuario, LocalDate fechaOperacion, Optional<SicaderReporte42> reporte42Repo) {
        Long numeroReporte = 0L;
        SicaderReporte42 sicaderReporte42Save = new SicaderReporte42();
        try {
            if (reporte42Repo.isPresent()) {
                sicaderReporte42Save = reporte42Repo.get();
                sicaderReporte42Save.setFechaModificacion(Constants.diaActual);
                sicaderReporte42Save.setUsuModificacion(usuario);
                sicaderReporte42Save.setEstatus(Constants.CARGA_INICIAL);
                sicaderReporte42Save.getSicaderReporte42Detalles().clear();
            } else {
                sicaderReporte42Save.setUsuRegistro(usuario);
                sicaderReporte42Save.setFechaRegistro(Constants.diaActual);
                sicaderReporte42Save.setFechaOperacion(fechaOperacion);
                sicaderReporte42Save.setEstatus(Constants.CARGA_INICIAL);
            }
            sicaderReporte42Save = sicaderReporte42Repository.saveAndFlush(sicaderReporte42Save);
            numeroReporte = sicaderReporte42Save.getId();
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(Reporte42.class);
            strategy.setColumnMapping(Constants.HEADER_REPORTE_42);
            CsvToBean csvToBean = cargaCsvToBeanSinHeader(file, reader, strategy, Constants.HEADER_REPORTE_42, csvReader);
            validaNomenclatura(csvToBean);
            List<SicaderReporte42Detalle> sicaderReporte42Detalles = new ArrayList<>();
            for (Reporte42 reporte42 : (Iterable<Reporte42>) csvToBean) {
                SicaderReporte42Detalle sicaderReporte42Detalletmp = new SicaderReporte42Detalle(reporte42, sicaderReporte42Save);
                sicaderReporte42Detalles.add(sicaderReporte42Detalletmp);
            }
            guardarReporte42(sicaderReporte42Save, sicaderReporte42Detalles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (numeroReporte != 0) {
                sicaderReporte42Save.setEstatus(Constants.CARGA_ERROR);
                sicaderReporte42Repository.saveAndFlush(sicaderReporte42Save);
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getLecturaarchivos().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getLecturaarchivos().get("mensaje")
                );
            }
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );

        }
    }

    public void procesarReporteIRDT(MultipartFile file, String usuario, LocalDate fechaOperacion, Optional<SicaderReporteIRDT> reporteIRDTRepo) {
        Long numeroReporte = 0L;
        SicaderReporteIRDT sicaderReporteIRDTSave = new SicaderReporteIRDT();
        try {
            if (reporteIRDTRepo.isPresent()) {
                sicaderReporteIRDTSave = reporteIRDTRepo.get();
                sicaderReporteIRDTSave.setFechaModificacion(Constants.diaActual);
                sicaderReporteIRDTSave.setUsuModificacion(usuario);
                sicaderReporteIRDTSave.setEstatus(Constants.CARGA_INICIAL);
                sicaderReporteIRDTSave.getSicaderReporteIRDTDetalles().clear();
            } else {
                sicaderReporteIRDTSave.setUsuRegistro(usuario);
                sicaderReporteIRDTSave.setFechaRegistro(Constants.diaActual);
                sicaderReporteIRDTSave.setFechaOperacion(fechaOperacion);
                sicaderReporteIRDTSave.setEstatus(Constants.CARGA_INICIAL);
            }
            sicaderReporteIRDTSave = sicaderReporteIRDTRepository.saveAndFlush(sicaderReporteIRDTSave);
            numeroReporte = sicaderReporteIRDTSave.getId();
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            String hoja = "Hoja1";
            Iterator<Row> rowIterator = sheet.iterator();
            Row columna;
            List<ReporteIRDTXlsx> listaReporteIRDTXlsx = new ArrayList<>();
            while (rowIterator.hasNext()) {
                columna = rowIterator.next();
                if (columna.getPhysicalNumberOfCells() != Constants.HEADER_REPORTE_IRDT.length) {
                    throw new ErrorAplicacionControlado(
                            respuestaControlada.getArchivocolumnas().get("codigo"),
                            this.getClass().getName(),
                            respuestaControlada.getArchivocolumnas().get("mensaje"+" en la columna:"+columna)
                    );
                }
                ReporteIRDTXlsx reporteIRDTXlsx = new ReporteIRDTXlsx();
                Iterator<Cell> iterarCelda = columna.cellIterator();
                Cell celda;
                while (iterarCelda.hasNext()) {
                    celda = iterarCelda.next();
                    if (columna.getRowNum() == 0) { //necesario para que unicamente entre en el cabezal si no mandara error  por string en la fecha
                        if (!celda.getStringCellValue().equals(Constants.HEADER_REPORTE_IRDT[celda.getColumnIndex()])) {
                            throw new ErrorAplicacionControlado(
                                    respuestaControlada.getErrornombreheaderexcel().get("codigo"),
                                    this.getClass().getName(),
                                    respuestaControlada.getErrornombreheaderexcel().get("mensaje")
                            );
                        }
                    }

                    if (columna.getRowNum() >= 1) {
                        switch (celda.getColumnIndex()) {
                            case 0:
                                reporteIRDTXlsx.setBr(celda.getStringCellValue());
                                break;
                            case 1:
                                reporteIRDTXlsx.setDealNo(Double.parseDouble(celda.getStringCellValue()));
                                break;
                            case 2:
                                reporteIRDTXlsx.setSeq(Double.parseDouble(celda.getStringCellValue()));
                                break;
                            case 3:
                                reporteIRDTXlsx.setProduct(celda.getStringCellValue());
                                break;
                            case 4:
                                reporteIRDTXlsx.setProdtype(celda.getStringCellValue());
                                break;
                            case 5:
                                //reporteIRDTXlsx.setBrprcindte( celda.getDateCellValue());
                                break;
                            case 6:
                                reporteIRDTXlsx.setDealind(celda.getStringCellValue());
                                break;
                            case 7:
                                reporteIRDTXlsx.setCcy(celda.getStringCellValue());
                                break;
                            case 8:
                                reporteIRDTXlsx.setBaseccy(celda.getStringCellValue());
                                break;
                            case 9:
                                reporteIRDTXlsx.setNpvamt(celda.getNumericCellValue());
                                break;
                            case 10:
                                reporteIRDTXlsx.setNpvbamt(celda.getNumericCellValue());
                                break;
                            case 11:
                                reporteIRDTXlsx.setMtmamt(celda.getNumericCellValue());
                                break;
                        }
                    }
                    if (celda.getColumnIndex() == 11 && columna.getRowNum() > 1)
                        listaReporteIRDTXlsx.add(reporteIRDTXlsx);
                }
            }

            List<SicaderReporteIRDTDetalle> sicaderReporteIRDTDetalles = new ArrayList<>();
            for (ReporteIRDTXlsx reporteIRDTXlsx : (Iterable<ReporteIRDTXlsx>) listaReporteIRDTXlsx) {
                SicaderReporteIRDTDetalle sicaderReporteIRDTDetalletmp = new SicaderReporteIRDTDetalle(reporteIRDTXlsx, sicaderReporteIRDTSave);
                sicaderReporteIRDTDetalles.add(sicaderReporteIRDTDetalletmp);
            }
            guardarReporteIRDT(sicaderReporteIRDTSave, sicaderReporteIRDTDetalles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (numeroReporte != 0) {
                sicaderReporteIRDTSave.setEstatus(Constants.CARGA_ERROR);
                sicaderReporteIRDTRepository.saveAndFlush(sicaderReporteIRDTSave);
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getLecturaarchivos().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getLecturaarchivos().get("mensaje")
                );
            }
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );

        }
    }

    public void procesarReporteIRDTJs(ArchivoMensualJSRequest archivoMensualJsDtoList, Optional<SicaderReporteIRDT> reporteIRDTRepo) {
        Long numeroReporte = 0L;
        SicaderReporteIRDT sicaderReporteIRDTSave = new SicaderReporteIRDT();
        try {
            if (reporteIRDTRepo.isPresent()) {
                sicaderReporteIRDTSave = reporteIRDTRepo.get();
                sicaderReporteIRDTSave.setFechaModificacion(Constants.diaActual);
                sicaderReporteIRDTSave.setUsuModificacion(archivoMensualJsDtoList.getUsuario());
                sicaderReporteIRDTSave.setEstatus(Constants.CARGA_INICIAL);
                sicaderReporteIRDTSave.getSicaderReporteIRDTDetalles().clear();
            } else {
                sicaderReporteIRDTSave.setUsuRegistro(archivoMensualJsDtoList.getUsuario());
                sicaderReporteIRDTSave.setFechaRegistro(Constants.diaActual);
                sicaderReporteIRDTSave.setFechaOperacion(archivoMensualJsDtoList.getFechaOperacion());
                sicaderReporteIRDTSave.setEstatus(Constants.CARGA_INICIAL);
            }
            sicaderReporteIRDTSave = sicaderReporteIRDTRepository.saveAndFlush(sicaderReporteIRDTSave);
            numeroReporte = sicaderReporteIRDTSave.getId();
            List<ReporteIRDTXlsx> listaReporteIRDTXlsx = new ArrayList<>();

            for ( ArchivoMensualJsDto  objeto : archivoMensualJsDtoList.getArchivoMensualJsDtoList()) {

                Iterator itr = objeto.getData().iterator();
                int numCelda =0;
                while(itr.hasNext())
                {
                    Object[] elemento = (Object[]) itr.next();
                    if(numCelda==0 ){
                        if (elemento.length  != Constants.HEADER_REPORTE_IRDT.length) {
                            throw new ErrorAplicacionControlado(
                                    respuestaControlada.getArchivocolumnas().get("codigo"),
                                    this.getClass().getName(),
                                    respuestaControlada.getArchivocolumnas().get("mensaje" +" columna:"+numCelda)
                            );
                        }
                    }else {
                        if(elemento.length!=0){
                            if (elemento.length  != Constants.HEADER_REPORTE_IRDT.length) {
                                throw new ErrorAplicacionControlado(
                                        respuestaControlada.getArchivocolumnas().get("codigo"),
                                        this.getClass().getName(),
                                        respuestaControlada.getArchivocolumnas().get("mensaje" +" columna:"+numCelda)
                                );
                            }
                            ReporteIRDTXlsx reporteIRDTXlsx = new ReporteIRDTXlsx();
                            LocalDate fechaInicial = LocalDate.parse("1900-01-01");
                            reporteIRDTXlsx.setBr(elemento[0].toString());
                            reporteIRDTXlsx.setDealNo(Double.parseDouble(elemento[1].toString()));
                            reporteIRDTXlsx.setSeq(Double.parseDouble(elemento[2].toString()));
                            reporteIRDTXlsx.setProduct(elemento[3].toString());
                            reporteIRDTXlsx.setProdtype(elemento[4].toString());
                            reporteIRDTXlsx.setBrprcindte( utilidades.fechaGuion_DDMMYYYY2(elemento[5].toString()));
                            reporteIRDTXlsx.setDealind(elemento[6].toString());
                            reporteIRDTXlsx.setCcy(elemento[7].toString());
                            reporteIRDTXlsx.setBaseccy(elemento[8].toString());
                            reporteIRDTXlsx.setNpvamt(Double.parseDouble(elemento[9].toString()));
                            reporteIRDTXlsx.setNpvbamt(Double.parseDouble(elemento[10].toString()));
                            reporteIRDTXlsx.setMtmamt(Double.parseDouble(elemento[11].toString()));
                            listaReporteIRDTXlsx.add(reporteIRDTXlsx);
                        }
                    }
                    numCelda=numCelda+1;
                }

            }

            List<SicaderReporteIRDTDetalle> sicaderReporteIRDTDetalles = new ArrayList<>();
            for (ReporteIRDTXlsx reporteIRDTXlsx : (Iterable<ReporteIRDTXlsx>) listaReporteIRDTXlsx) {
                SicaderReporteIRDTDetalle sicaderReporteIRDTDetalletmp = new SicaderReporteIRDTDetalle(reporteIRDTXlsx, sicaderReporteIRDTSave);
                sicaderReporteIRDTDetalles.add(sicaderReporteIRDTDetalletmp);
            }
            guardarReporteIRDT(sicaderReporteIRDTSave, sicaderReporteIRDTDetalles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (numeroReporte != 0) {
                sicaderReporteIRDTSave.setEstatus(Constants.CARGA_ERROR);
                sicaderReporteIRDTRepository.saveAndFlush(sicaderReporteIRDTSave);
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getLecturaarchivos().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getLecturaarchivos().get("mensaje")
                );
            }
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );

        }
    }

    public CsvToBean cargaCsvToBeanSinHeader(MultipartFile file, Reader reader, ColumnPositionMappingStrategy strategy, String[] tipoHeader, CSVReader csvReader) throws IOException, CsvValidationException {

        String[] record = new String[0];
        while ((record = csvReader.readNext()) != null) {
            if (record.length != tipoHeader.length) {
                return null;
            }
        }
        // create csv bean reader
        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withSkipLines(0)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return csvToBean;
    }

    public CsvToBean cargaCsvToBeanHeader(MultipartFile file, Reader reader, String[] tipoHeader, CSVReader csvReader) throws IOException, CsvValidationException {

        String[] record = new String[0];
        while ((record = csvReader.readNext()) != null) {
            if (record.length != tipoHeader.length) {
                return null;
            }
        }
        // create csv bean reader
        CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                .withType(ReporteIRDT.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return csvToBean;

    }

    void validaNomenclatura(CsvToBean csvToBean) {
        if (csvToBean == null) {
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getArchivocolumnas().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getArchivocolumnas().get("mensaje")
            );
        }
    }

    @Transactional
    public void guardarReporte01(SicaderReporte01 sicaderReporte01Save, List<SicaderReporte01Detalle> sicaderReporte01Detalles) {
        sicaderReporte01Save.setEstatus(Constants.CARGA_EXITOSA);
        sicaderReporte01Repository.saveAndFlush(sicaderReporte01Save);
        sicaderReporte01Detalles.forEach(r -> r.setReporteId(sicaderReporte01Save.getId()));
        sicaderReporte01DetalleRepository.saveAll(sicaderReporte01Detalles);
    }

    @Transactional
    public void guardarReporte03(SicaderReporte03 sicaderReporte03Save, List<SicaderReporte03Detalle> sicaderReporte03Detalles) {
        sicaderReporte03Save.setEstatus(Constants.CARGA_EXITOSA);
        sicaderReporte03Repository.saveAndFlush(sicaderReporte03Save);
        sicaderReporte03Detalles.forEach(r -> r.setReporteId(sicaderReporte03Save.getId()));
        sicaderReporte03DetalleRepository.saveAll(sicaderReporte03Detalles);
    }

    @Transactional
    public void guardarReporte06(SicaderReporte06 sicaderReporte06Save, List<SicaderReporte06Detalle> sicaderReporte06Detalles) {
        sicaderReporte06Save.setEstatus(Constants.CARGA_EXITOSA);
        sicaderReporte06Repository.saveAndFlush(sicaderReporte06Save);
        sicaderReporte06Detalles.forEach(r -> r.setReporteId(sicaderReporte06Save.getId()));
        sicaderReporte06DetalleRepository.saveAll(sicaderReporte06Detalles);
    }

    @Transactional
    public void guardarReporte40(SicaderReporte40 sicaderReporte40Save, List<SicaderReporte40Detalle> sicaderReporte40Detalles) {
        sicaderReporte40Save.setEstatus(Constants.CARGA_EXITOSA);
        sicaderReporte40Repository.saveAndFlush(sicaderReporte40Save);
        sicaderReporte40Detalles.forEach(r -> r.setReporteId(sicaderReporte40Save.getId()));
        sicaderReporte40DetalleRepository.saveAll(sicaderReporte40Detalles);
    }

    @Transactional
    public void guardarReporte42(SicaderReporte42 sicaderReporte42Save, List<SicaderReporte42Detalle> sicaderReporte42Detalles) {
        sicaderReporte42Save.setEstatus(Constants.CARGA_EXITOSA);
        sicaderReporte42Repository.saveAndFlush(sicaderReporte42Save);
        sicaderReporte42Detalles.forEach(r -> r.setReporteId(sicaderReporte42Save.getId()));
        sicaderReporte42DetalleRepository.saveAll(sicaderReporte42Detalles);
    }

    @Transactional
    public void guardarReporteIRDT(SicaderReporteIRDT sicaderReporteIRDTSave, List<SicaderReporteIRDTDetalle> sicaderReporteIRDTDetalles) {
        sicaderReporteIRDTSave.setEstatus(Constants.CARGA_EXITOSA);
        sicaderReporteIRDTRepository.saveAndFlush(sicaderReporteIRDTSave);
        sicaderReporteIRDTDetalles.forEach(r -> r.setReporteId(sicaderReporteIRDTSave.getId()));
        sicaderReporteIRDTDetalleRepository.saveAll(sicaderReporteIRDTDetalles);
    }


    private void guardarReporteMensual(SicaderSlVsSideca sicaderSlVsSidecaSave, List<SicaderSlVsSidecaDetalle> sicaderSlVsSidecaDetalles) {
        //sicaderSlVsSidecaSave.getSicaderSlVsSidecaDetalles().clear();
        sicaderSlVsSidecaSave = sicaderSlVsSidecaRepository.saveAndFlush(sicaderSlVsSidecaSave);
        SicaderSlVsSideca finalSicaderSlVsSidecaSave = sicaderSlVsSidecaSave;
        sicaderSlVsSidecaDetalles.forEach(r -> r.setReporteId(finalSicaderSlVsSidecaSave.getId()));
        sicaderSlVsSidecaDetalleRepository.saveAll(sicaderSlVsSidecaDetalles);
    }

    public String[] validaTipoReporte(String fileDosDigitos, String fileCuatroDigitos) {
        String reporte[] = new String[2];
        switch (fileDosDigitos) {
            case "01":
                reporte[0] = "01";
                reporte[1] = "Reporte01";
                return reporte;
            case "03":
                reporte[0] = "03";
                reporte[1] = "Reporte03";
                return reporte;
            case "06":
                reporte[0] = "06";
                reporte[1] = "Reporte06";
                return reporte;
            case "40":
                reporte[0] = "40";
                reporte[1] = "Reporte40";
                return reporte;
            case "42":
                reporte[0] = "42";
                reporte[1] = "Reporte42";
                return reporte;
            default:
                if (fileCuatroDigitos.equals("IRDT")) {
                    reporte[0] = "IRDT";
                    reporte[1] = "ReporteIRDT";
                    return reporte;
                } else {
                    throw new ErrorAplicacionControlado(
                            respuestaControlada.getNomenclatura().get("codigo"),
                            this.getClass().getName(),
                            respuestaControlada.getNomenclatura().get("mensaje")
                    );
                }

        }
    }

    public List<ReporteMensual> procesaReporteMensual(MultipartFile file, String usuario, Date fechaOperacion) throws Exception {
        List<ReporteMensual> reporteMensualLista = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        ;
        //Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            SicaderCatSociosLiquidadores sicaderCatSociosLiquidadores =
                    sociosLiquidadoresRepository.findByNombreLike("%" + sheet.getSheetName() + "%");
            if (sicaderCatSociosLiquidadores == null) {
                throw new ErrorAplicacionControlado(
                        respuestaControlada.getSocioLiquidador().get("codigo"),
                        this.getClass().getName(),
                        respuestaControlada.getSocioLiquidador().get("mensaje")
                );
            }
            Iterator<Row> rowIterator = sheet.iterator();
            Row columna;
            int numeroColumna = 0;
            while (rowIterator.hasNext()) {

                columna = rowIterator.next();
                Iterator<Cell> iterarCeldaVacia = columna.cellIterator();
                Cell celdaVacia;
                String valorCadenaValidacion="";
                boolean guardarRegistro= true;
                while (iterarCeldaVacia.hasNext()) {
                    celdaVacia = iterarCeldaVacia.next();
                    try{
                    valorCadenaValidacion = valorCadenaValidacion + celdaVacia.getStringCellValue();
                    }catch (Exception e){
                        valorCadenaValidacion="CONTIENE INFORMACION";
                    }
                }
                if (valorCadenaValidacion.isEmpty()){
                    guardarRegistro= false;
                }

                if(guardarRegistro){

                numeroColumna = numeroColumna + 1;
                System.out.println("numero Columna:"+numeroColumna);
                System.out.println("columna.getLastCellNum():"+columna.getLastCellNum());
                System.out.println("columna.getPhysicalNumberOfCells():"+numeroColumna);
                System.out.println("sheet:"+sheet.getSheetName());
                System.out.println("Constants.HEADER_REPORTE_MENSUAL.length:"+Constants.HEADER_REPORTE_MENSUAL.length);
                if (columna.getPhysicalNumberOfCells() != 0) {
                    if (columna.getLastCellNum()  != Constants.HEADER_REPORTE_MENSUAL.length) {
                        throw new ErrorAplicacionControlado(
                                respuestaControlada.getArchivocolumnas().get("codigo"),
                                this.getClass().getName(),
                                respuestaControlada.getArchivocolumnas().get("mensaje" +" columna:"+columna)
                        );
                    }
                    ReporteMensual reporteMensual = new ReporteMensual();
                    Iterator<Cell> iterarCelda = columna.cellIterator();
                    Cell celda;
                    while (iterarCelda.hasNext()) {
                        celda = iterarCelda.next();
                        if (columna.getRowNum() == 0) { //necesario para que unicamente entre en el cabezal si no mandara error  por string en la fecha
                            if (!celda.getStringCellValue().equals(Constants.HEADER_REPORTE_MENSUAL[celda.getColumnIndex()])) {
                                throw new ErrorAplicacionControlado(
                                        respuestaControlada.getErrornombreheaderexcel().get("codigo"),
                                        this.getClass().getName(),
                                        respuestaControlada.getErrornombreheaderexcel().get("mensaje")
                                );
                            }
                        }

                        if (columna.getRowNum() > 0) {
                            switch (celda.getColumnIndex()) {
                                case 0:
                                    reporteMensual.setFolio((int) celda.getNumericCellValue());
                                    break;
                                case 1:
                                    reporteMensual.setContrato(celda.getNumericCellValue());
                                    break;
                                case 2:
                                    reporteMensual.setMoneda(celda.getStringCellValue());
                                    break;
                                case 3:
                                    reporteMensual.setEntregarSl(celda.getNumericCellValue());
                                    break;
                                case 4:
                                    reporteMensual.setRecibirSl(celda.getNumericCellValue());
                                    break;
                                case 5:
                                    reporteMensual.setNetovalSideca(celda.getNumericCellValue());
                                    break;
                                case 6:
                                    reporteMensual.setEntregarSideca(celda.getNumericCellValue());
                                    break;
                                case 7:
                                    reporteMensual.setRecibirSideca(celda.getNumericCellValue());
                                    break;
                                case 8:
                                    reporteMensual.setNetovalSideca(celda.getNumericCellValue());
                                    break;
                                case 9:
                                    reporteMensual.setDiferencia(celda.getNumericCellValue());
                                    break;
                            }
                        }
                        if (celda.getColumnIndex() == 9 && columna.getRowNum() > 0) {
                            reporteMensual.setSocioLiquidador(sicaderCatSociosLiquidadores.getId());
                            reporteMensualLista.add(reporteMensual);
                        }
                    }
                } else {
                    break;
                }

                }
            }
        }
        return reporteMensualLista;
    }


}
