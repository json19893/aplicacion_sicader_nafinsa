package com.org.backend_nafinsa.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.backend_nafinsa.exception.ErrorAplicacionControlado;

@Service
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

    public LocalDate fechaGuion_DDMMYYYY2(String fecha) {
        try {
           
        	ZoneId sourceTimeZone = ZoneOffset.ofHours(1); // Use real zone of source, e.g. ZoneId.of("Europe/Paris");

        	// Parse Zulu date string as zoned date/time in source time zone
        	Instant sourceInstant = Instant.parse(fecha);
        	ZonedDateTime sourceZoned = sourceInstant.atZone(sourceTimeZone);

        	// Convert to util.Date in local time zone
        	ZonedDateTime localZoned = sourceZoned.withZoneSameLocal(ZoneId.systemDefault());
        	Instant localInstant = localZoned.toInstant();
        	Date localDate = Date.from(localInstant); // <== This is your desired result

        	// Print value in ISO 8601 format
        	String localStr = new SimpleDateFormat("dd/MM/yyyy").format(localDate);
        	System.out.println(localStr);
           
        	  LocalDate localDates = LocalDate.parse(localStr, formatoDDMMYYYY);
        	  System.out.println(localDates);
            return localDates;
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
           
        	  LocalDate localDates = LocalDate.parse(fecha, formatoDDMMYYYY);
            return localDates;
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
