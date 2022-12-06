package com.org.backend_nafinsa.util;

import com.org.backend_nafinsa.exception.ErrorAplicacionControlado;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utilidades {
    @Autowired
    CodigosRespuestaControlados respuestaControlada;
    final DateTimeFormatter formatoDDMMYYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    final DateTimeFormatter fechaGuion_DDMMYYYY = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final DateTimeFormatter fechaGuion_DDMMMYYYY = DateTimeFormatter.ofPattern("dd-MMM-yyyy", new Locale("es", "ES"));

    public LocalDate fechaDDMMYYYY(String fecha) {
        try {
            LocalDate localDate = LocalDate.parse(fecha, formatoDDMMYYYY);
            return localDate;
        } catch (Exception e) {
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );
        }
    }

    public LocalDate fechaGuion_DDMMYYYY(String fecha) {
        try {
            LocalDate localDate = LocalDate.parse(fecha, fechaGuion_DDMMYYYY);
            return localDate;
        } catch (Exception e) {
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );
        }

    }

    public LocalDate fechaGuion_DDMMMYYYY(String fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.forLanguageTag("es_ES"));
            Date date = sdf.parse(fecha);
            LocalDate localDate = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            return localDate;
        } catch (Exception e) {
            throw new ErrorAplicacionControlado(
                    respuestaControlada.getServicionodisponible().get("codigo"),
                    this.getClass().getName(),
                    respuestaControlada.getServicionodisponible().get("mensaje")
            );
        }

    }
}
