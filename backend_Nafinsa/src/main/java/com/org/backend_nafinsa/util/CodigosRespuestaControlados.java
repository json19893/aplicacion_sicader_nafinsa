package com.org.backend_nafinsa.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@ConfigurationProperties(prefix = "respuesta")
@Data
public class CodigosRespuestaControlados {

    private Map<String, String> gatewaytimeout = new HashMap<>();

    private Map<String, String> servicionodisponible = new HashMap<>();

    private Map<String, String> archivovacio = new HashMap<>();
    private Map<String, String> nomenclatura = new HashMap<>();
    private Map<String, String> lecturaarchivos = new HashMap<>();
    private Map<String, String> archivocolumnas = new HashMap<>();
    private Map<String, String> errornombreheaderexcel = new HashMap<>();
    private Map<String, String> errorbd = new HashMap<>();
    private Map<String, String> socioLiquidador = new HashMap<>();
    private Map<String, String> extensionXlsx = new HashMap<>();
    private Map<String, String> fechaValidacion = new HashMap<>();


}
