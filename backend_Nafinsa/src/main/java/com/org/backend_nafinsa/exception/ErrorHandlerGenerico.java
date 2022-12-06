package com.org.backend_nafinsa.exception;

import com.org.backend_nafinsa.dto.RespuestaError;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandlerGenerico extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlerGenerico.class);

    @Value("${spring.application.name:Backend_Nafinsa}")
    private String applicationName;


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        logger.error(
                String.format("[%s][%s][%s][%s][%s][%s][%s] 1", new Object[]{this.applicationName, ex.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR, "N/A", ex
                        .getCause(), ex.getMessage(), ""}));
        RespuestaError response = new RespuestaError(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), "Consulte el error con el administrador del sistema.");
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ErrorAplicacionControlado.class})
    public ResponseEntity<Object> handleConflictException(ErrorAplicacionControlado ex, WebRequest request) throws Exception {
        logger.error(String.format("[%s][%s][%s][%s][%s][%s][%s] 2", new Object[]{this.applicationName, ex.getNombreClase(), HttpStatus.CONFLICT, ex
                , ex.getCodigoControlado(), ex.getMensajeRespuesta(), ex
                .getMessage()}));
        RespuestaError response = new RespuestaError(ex.getCodigoControlado(), ex.getMensajeRespuesta());
        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ErrorServicioNoDisponible.class})
    public ResponseEntity<Object> handleConflictException(ErrorServicioNoDisponible ex, WebRequest request) throws Exception {
        logger.error(String.format("[%s][%s][%s][%s][%s][%s][%s] 3", new Object[]{this.applicationName, ex.getNombreClase(), HttpStatus.SERVICE_UNAVAILABLE, ex
                , ex.getCodigoControlado(), ex.getMensajeRespuesta(), ex
                .getMessage()}));
        RespuestaError response = new RespuestaError(ex.getCodigoControlado(), ex.getMensajeRespuesta());
        return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler({ErrorTimeOut.class})
    public ResponseEntity<Object> handleConflictException(ErrorTimeOut ex, WebRequest request) throws Exception {
        logger.error(String.format("[%s][%s][%s][%s][%s][%s][%s] 4", new Object[]{this.applicationName, ex.getNombreClase(), HttpStatus.GATEWAY_TIMEOUT, ex
                , ex.getCodigoControlado(), ex.getMensajeRespuesta(), ex
                .getMessage()}));
        RespuestaError response = new RespuestaError(ex.getCodigoControlado(), ex.getMensajeRespuesta());
        return new ResponseEntity(response, HttpStatus.GATEWAY_TIMEOUT);
    }


}
