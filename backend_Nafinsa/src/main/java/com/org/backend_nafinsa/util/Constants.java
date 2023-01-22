package com.org.backend_nafinsa.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    public static final String[] HEADER_REPORTE_01 = {"br", "brokno", "contract", "port", "numOpera", "seq", "fechaOperac", "fechaVencim", "numContratos", "tipoOperacion", "precioPactado", "precioCierreAnt", "precioCierre", "montoPrecioPact", "montoDivisa", "broker", "montoCubierto", "tdyunrealpl", "tdyrealpl", "trad"};
    public static final String[] HEADER_REPORTE_03 = {"br", "port", "product", "pr", "tipoOperacion", "ccy", "ctr", "moned", "contraparte", "nombrecont", "nooperac", "fechaInici", "fechaVenci", "fechaLiqui", "plazo", "dxv", "toper", "importePosicion", "contraImporte", "precioPactado", "precioMercado", "mon", "tcambiofixM1", "moneda2", "tcambiofixM2", "plusminusvalia", "trad", "corptrad"};
    public static final String[] HEADER_REPORTE_40 = {"broker", "dealNo", "seq", "type", "security", "valueDate", "maturityDate", "description", "ccy", "debit"};
    public static final String[] HEADER_REPORTE_42 = {"cno", "contraparte", "folio", "port", "prod", "ty", "fechaini", "fechavto", "dvr", "importerefvigenterec", "dve", "importerefvigenteent", "marktomarketmxn", "saldoctamargen", "fechacelebracion", "fechavalor", "fechavencimiento", "provisionimprecibir", "provisionimpentregar", "marktomarketmxn1", "saldogarantias"};
    public static final String[] HEADER_REPORTE_06 = {"contraparte", "folio", "port", "product", "ttasaent", "div", "typ", "importerRefRec", "importeRefVigenteRec", "tasaRec", "instrta", "spreadRec", "baseca", "importeRefEnt", "importeRefVigenteEnt", "tasaEnt", "insttas", "spreadEnt", "baseca2", "tcambioRecibo", "provisionImpRecibir", "valpresenteNetoRecibo", "tcambioEntrega", "provisionImpEntregar", "valpresenteNetoEntreg", "netoProvisionPesos", "plusminusvalia", "totalValuacion", "tradOp", "opertraspaso", "fechaConcertacion", "fechaInicio", "fechaVencimiento"};
    //public static final String[] HEADER_REPORTE_IRDT ={"BR","DEAL_NO","SEQ","PRODUCT","PRODTYPE","BRPRCINDTE","DEALIND","CCY","BASECCY","NPVAMT","NPVBAMT","MTMAMT"};
    public static final String[] HEADER_REPORTE_IRDT = {"BR", "DEALNO", "SEQ", "PRODUCT", "PRODTYPE", "BRPRCINDTE", "DEALIND", "CCY", "BASECCY", "NPVAMT", "NPVBAMT", "MTMAMT"};

    public static final String[] HEADER_REPORTE_MENSUAL = {"FOLIO", "CONTRATOS", "MONEDA", "ENTREGAR SL ", "RECIBIR SL", "NETO VAL SL", "ENTREGAR SIDECA", "RECIBIR SIDECA", "NETO VAL SIDECA", "DIFERENCIA"};

    public static final DateFormat FECHA_DD_MM_YYYY_DIAGONAL = new SimpleDateFormat("dd-MMM-yyyy");

    public static final String CARGA_INICIAL = "Pendiente";
    public static final String CARGA_ERROR = "Error en la Carga";
    public static final String CARGA_EXITOSA = "E";

    public static final String PAGINADOR_TAMAÑO = "3";

    public static Date diaActual = new Date();

    public static String MENSAJE_EXISTE = "Los datos ingresados ya existen, ¿desea sobrescribir la información?";
    
    public static String MENSAJE_EXISTE_COBERTURA = "Ya existe una cobertura con el nombre ";

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_SECRET = "Nafinsa981635Sicader";

    public static final int TOKEN_TIME_EXP = 2100000;

}

