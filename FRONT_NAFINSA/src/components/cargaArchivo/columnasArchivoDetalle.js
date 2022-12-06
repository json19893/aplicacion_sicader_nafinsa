export function getColumnasDetalleArchivo(tipoReporte) {
  switch (tipoReporte) {
    case 'REPORTE01':
      return REPORTE01;
    case 'REPORTE03':
      return REPORTE03;
    case 'REPORTE06':
      return REPORTE06;
    case 'REPORTE40':
      return REPORTE40;
    case 'REPORTE42':
      return REPORTE42;
    case 'REPORTEIRDT':
      return REPORTEIRDT;
  }
}

const REPORTE01 = [
  {
    title: "BR",
    dataIndex: "br",
    key: "br",
    align: "center"
  },
  {
    title: "BROKNO",
    dataIndex: "brokno",
    key: "brokno",
    align: "center"
  },
  {
    title: "CONTRACT",
    dataIndex: "contract",
    key: "contract",
    align: "center"
  },
  {
    title: "PORT",
    dataIndex: "port",
    key: "port",
    align: "center"
  },
  {
    title: "NUMOPERA",
    dataIndex: "numOpera",
    key: "numOpera",
    align: "center"
  },
  {
    title: "SEQ",
    dataIndex: "seq",
    key: "seq",
    align: "center"
  },
  {
    title: "FECHAOPERAC",
    dataIndex: "fechaOperac",
    key: "fechaOperac",
    align: "center"
  },
  {
    title: "FECHAVENCIM",
    dataIndex: "fechaVencim",
    key: "fechaVencim",
    align: "center"
  },
  {
    title: "NUMCONTRATOS",
    dataIndex: "numContratos",
    key: "numContratos",
    align: "center"
  },
  {
    title: "TIPO_OPERACION",
    dataIndex: "tipoOperacion",
    key: "tipoOperacion",
    align: "center"
  },
  {
    title: "PRECIO_PACTADO",
    dataIndex: "precioPactado",
    key: "precioPactado",
    align: "center"
  },
  {
    title: "PRECIO_CIERRE_ANT",
    dataIndex: "precioCierreAnt",
    key: "precioCierreAnt",
    align: "center"
  },
  {
    title: "PRECIO_CIERRE",
    dataIndex: "precioCierre",
    key: "precioCierre",
    align: "center"
  },
  {
    title: "MONTOPRECIOPACT",
    dataIndex: "montoPrecioPact",
    key: "montoPrecioPact",
    align: "center"
  },
  {
    title: "MONTODIVISA",
    dataIndex: "montoDivisa",
    key: "montoDivisa",
    align: "center"
  },
  {
    title: "BROKER",
    dataIndex: "broker",
    key: "broker",
    align: "center"
  },
  {
    title: "MONTOCUBIERTO",
    dataIndex: "montoCubierto",
    key: "montoCubierto",
    align: "center"
  },
  {
    title: "TDYUNREALPL",
    dataIndex: "tdyunrealpl",
    key: "tdyunrealpl",
    align: "center"
  },
  {
    title: "TDYREALPL",
    dataIndex: "tdyrealpl",
    key: "tdyrealpl",
    align: "center"
  },
  {
    title: "TRAD",
    dataIndex: "trad",
    key: "trad",
    align: "center"
  }
];

const REPORTE03 = [
  { title: 'BR', dataIndex: 'br', key: 'br', align: 'center' },
  { title: 'PORT', dataIndex: 'port', key: 'port', align: 'center' },
  { title: 'PRODUCT', dataIndex: 'product', key: 'product', align: 'center' },
  { title: 'PR', dataIndex: 'pr', key: 'pr', align: 'center' },
  { title: 'TIPO_OPERACION', dataIndex: 'tipoOperacion', key: 'tipoOperacion', align: 'center' },
  { title: 'CCY', dataIndex: 'ccy', key: 'ccy', align: 'center' },
  { title: 'CTR', dataIndex: 'ctr', key: 'ctr', align: 'center' },
  { title: 'MONED', dataIndex: 'moned', key: 'moned', align: 'center' },
  { title: 'CONTRAPARTE', dataIndex: 'contraparte', key: 'contraparte', align: 'center' },
  { title: 'NOMBRECONT', dataIndex: 'nombrecont', key: 'nombrecont', align: 'center' },
  { title: 'NOOPERAC', dataIndex: 'nooperac', key: 'nooperac', align: 'center' },
  { title: 'FECHAINICI', dataIndex: 'fechaInici', key: 'fechaInici', align: 'center' },
  { title: 'FECHAVENCI', dataIndex: 'fechaVenci', key: 'fechaVenci', align: 'center' },
  { title: 'FECHALIQUI', dataIndex: 'fechaLiqui', key: 'fechaLiqui', align: 'center' },
  { title: 'PLAZO', dataIndex: 'plazo', key: 'plazo', align: 'center' },
  { title: 'DXV', dataIndex: 'dxv', key: 'dxv', align: 'center' },
  { title: 'TOPER', dataIndex: 'toper', key: 'toper', align: 'center' },
  { title: 'IMPORTE_POSICION', dataIndex: 'importePosicion', key: 'importePosicion', align: 'center' },
  { title: 'CONTRA_IMPORTE', dataIndex: 'contraImporte', key: 'contraImporte', align: 'center' },
  { title: 'PRECIO_PACTADO', dataIndex: 'precioPactado', key: 'precioPactado', align: 'center' },
  { title: 'PRECIO_MERCADO', dataIndex: 'precioMercado', key: 'precioMercado', align: 'center' },
  { title: 'MON', dataIndex: 'mon', key: 'mon', align: 'center' },
  { title: 'TCAMBIOFIX_M1', dataIndex: 'tcambiofixM1', key: 'tcambiofixM1', align: 'center' },
  { title: 'MONEDA2', dataIndex: 'moneda2', key: 'moneda2', align: 'center' },
  { title: 'TCAMBIOFIX_M2', dataIndex: 'tcambiofixM2', key: 'tcambiofixM2', align: 'center' },
  { title: 'PLUSMINUSVALIA', dataIndex: 'plusminusvalia', key: 'plusminusvalia', align: 'center' },
  { title: 'TRAD', dataIndex: 'trad', key: 'trad', align: 'center' },
  { title: 'CORPTRAD', dataIndex: 'corptrad', key: 'corptrad', align: 'center' },
];

const REPORTE06 = [
  { title: 'CONTRAPARTE', dataIndex: 'contraparte', key: 'contraparte', align: 'center' },
  { title: 'FOLIO', dataIndex: 'folio', key: 'folio', align: 'center' },
  { title: 'PORT', dataIndex: 'port', key: 'port', align: 'center' },
  { title: 'PRODUCT', dataIndex: 'product', key: 'product', align: 'center' },
  { title: 'TTASAENT', dataIndex: 'ttasaent', key: 'ttasaent', align: 'center' },
  { title: 'DIV', dataIndex: 'div', key: 'div', align: 'center' },
  { title: 'TYP', dataIndex: 'typ', key: 'typ', align: 'center' },
  { title: 'IMPORTER_REF_REC', dataIndex: 'importerRefRec', key: 'importerRefRec', align: 'center' },
  { title: 'IMPORTE_REF_VIGENTE_REC', dataIndex: 'importeRefVigenteRec', key: 'importeRefVigenteRec', align: 'center' },
  { title: 'TASA_REC', dataIndex: 'tasaRec', key: 'tasaRec', align: 'center' },
  { title: 'INSTRTA', dataIndex: 'instrta', key: 'instrta', align: 'center' },
  { title: 'SPREAD_REC', dataIndex: 'spreadRec', key: 'spreadRec', align: 'center' },
  { title: 'BASECA', dataIndex: 'baseca', key: 'baseca', align: 'center' },
  { title: 'IMPORTE_REF_ENT', dataIndex: 'importeRefEnt', key: 'importeRefEnt', align: 'center' },
  { title: 'IMPORTE_REF_VIGENTE_ENT', dataIndex: 'importeRefVigenteEnt', key: 'importeRefVigenteEnt', align: 'center' },
  { title: 'TASA_ENT', dataIndex: 'tasaEnt', key: 'tasaEnt', align: 'center' },
  { title: 'INSTTAS', dataIndex: 'insttas', key: 'insttas', align: 'center' },
  { title: 'SPREAD_ENT', dataIndex: 'spreadEnt', key: 'spreadEnt', align: 'center' },
  { title: 'BASECA_2', dataIndex: 'baseca2', key: 'baseca2', align: 'center' },
  { title: 'TCAMBIO_RECIBO', dataIndex: 'tcambioRecibo', key: 'tcambioRecibo', align: 'center' },
  { title: 'PROVISION_IMP_RECIBIR', dataIndex: 'provisionImpRecibir', key: 'provisionImpRecibir', align: 'center' },
  { title: 'VAL_PRESENTE_NETO_RECIBO', dataIndex: 'valpresenteNetoRecibo', key: 'valpresenteNetoRecibo', align: 'center' },
  { title: 'TCAMBIO_ENTREGA', dataIndex: 'tcambioEntrega', key: 'tcambioEntrega', align: 'center' },
  { title: 'PROVISION_IMP_ENTREGAR', dataIndex: 'provisionImpEntregar', key: 'provisionImpEntregar', align: 'center' },
  { title: 'VAL_PRESENTE_NETO_ENTREG', dataIndex: 'valpresenteNetoEntreg', key: 'valpresenteNetoEntreg', align: 'center' },
  { title: 'NETO_PROVISION_PESOS', dataIndex: 'netoProvisionPesos', key: 'netoProvisionPesos', align: 'center' },
  { title: 'PLUSMINUSVALIA', dataIndex: 'plusminusvalia', key: 'plusminusvalia', align: 'center' },
  { title: 'TOTAL_VALUACION', dataIndex: 'totalValuacion', key: 'totalValuacion', align: 'center' },
  { title: 'TRAD_OP', dataIndex: 'tradOp', key: 'tradOp', align: 'center' },
  { title: 'OPERTRASPASO', dataIndex: 'opertraspaso', key: 'opertraspaso', align: 'center' },
  { title: 'FECHA_CONCERTACION', dataIndex: 'fechaConcertacion', key: 'fechaConcertacion', align: 'center' },
  { title: 'FECHA_INICIO', dataIndex: 'fechaInicio', key: 'fechaInicio', align: 'center' },
  { title: 'FECHA_VENCIMIENTO', dataIndex: 'fechaVencimiento', key: 'fechaVencimiento', align: 'center' },
  { title: 'REPORTE_ID', dataIndex: 'reporteId', key: 'reporteId', align: 'center' },
];

const REPORTE40 = [
  {title: 'BROKER', dataIndex: 'broker', key: 'broker', align: 'center'},
  {title: 'DEAL_NO', dataIndex: 'dealNo', key: 'dealNo', align: 'center'},
  {title: 'SEQ', dataIndex: 'seq', key: 'seq', align: 'center'},
  {title: 'TYPE', dataIndex: 'type', key: 'type', align: 'center'},
  {title: 'SECURITY', dataIndex: 'security', key: 'security', align: 'center'},
  {title: 'VALUE_DATE', dataIndex: 'valueDate', key: 'valueDate', align: 'center'},
  {title: 'MATURITY_DATE', dataIndex: 'maturityDate', key: 'maturityDate', align: 'center'},
  {title: 'DESCRIPTION', dataIndex: 'description', key: 'description', align: 'center'},
  {title: 'CCY', dataIndex: 'ccy', key: 'ccy', align: 'center'},
  {title: 'DEBIT', dataIndex: 'debit', key: 'debit', align: 'center'},
];

const REPORTE42 = [
  {title: 'CNO', dataIndex: 'cno', key: 'cno', align: 'center'},
  {title: 'CONTRAPARTE', dataIndex: 'contraparte', key: 'contraparte', align: 'center'},
  {title: 'FOLIO', dataIndex: 'folio', key: 'folio', align: 'center'},
  {title: 'PORT', dataIndex: 'port', key: 'port', align: 'center'},
  {title: 'PROD', dataIndex: 'prod', key: 'prod', align: 'center'},
  {title: 'TY', dataIndex: 'ty', key: 'ty', align: 'center'},
  {title: 'FECHAINI', dataIndex: 'fechaini', key: 'fechaini', align: 'center'},
  {title: 'FECHAVTO', dataIndex: 'fechavto', key: 'fechavto', align: 'center'},
  {title: 'DVR', dataIndex: 'dvr', key: 'dvr', align: 'center'},
  {title: 'IMPORTE_REF_VIGENTE_REC', dataIndex: 'importerefvigenterec', key: 'importerefvigenterec', align: 'center'},
  {title: 'DVE', dataIndex: 'dve', key: 'dve', align: 'center'},
  {title: 'IMPORTE_REF_VIGENTE_ENT', dataIndex: 'importerefvigenteent', key: 'importerefvigenteent', align: 'center'},
  {title: 'MARK_TO_MARKET_MXN', dataIndex: 'marktomarketmxn', key: 'marktomarketmxn', align: 'center'},
  {title: 'SALDO_CTAMARGEN', dataIndex: 'saldoctamargen', key: 'saldoctamargen', align: 'center'},
  {title: 'FECHA_CELEBRACION', dataIndex: 'fechacelebracion', key: 'fechacelebracion', align: 'center'},
  {title: 'FECHA_VALOR', dataIndex: 'fechavalor', key: 'fechavalor', align: 'center'},
  {title: 'FECHA_VENCIMIENTO', dataIndex: 'fechavencimiento', key: 'fechavencimiento', align: 'center'},
  {title: 'PROVISION_IMP_RECIBIR', dataIndex: 'provisionimprecibir', key: 'provisionimprecibir', align: 'center'},
  {title: 'PROVISION_IMP_ENTREGAR', dataIndex: 'provisionimpentregar', key: 'provisionimpentregar', align: 'center'},
  {title: 'MARK_TO_MARKET_MXN_1', dataIndex: 'marktomarketmxn1', key: 'marktomarketmxn1', align: 'center'},
  {title: 'SALDO_GARANTIAS', dataIndex: 'saldogarantias', key: 'saldogarantias', align: 'center'},
];

const REPORTEIRDT = [
  {title: 'BR', dataIndex: 'br', key: 'br', align: 'center'},
  {title: 'DEAL_NO', dataIndex: 'dealNo', key: 'dealNo', align: 'center'},
  {title: 'SEQ', dataIndex: 'seq', key: 'seq', align: 'center'},
  {title: 'PRODUCT', dataIndex: 'product', key: 'product', align: 'center'},
  {title: 'PRODTYPE', dataIndex: 'prodtype', key: 'prodtype', align: 'center'},
  {title: 'BRPRCINDTE', dataIndex: 'brprcindte', key: 'brprcindte', align: 'center'},
  {title: 'DEALIND', dataIndex: 'dealind', key: 'dealind', align: 'center'},
  {title: 'CCY', dataIndex: 'ccy', key: 'ccy', align: 'center'},
  {title: 'BASECCY', dataIndex: 'baseccy', key: 'baseccy', align: 'center'},
  {title: 'NPVAMT', dataIndex: 'npvamt', key: 'npvamt', align: 'center'},
  {title: 'NPVBAMT', dataIndex: 'npvbamt', key: 'npvbamt', align: 'center'},
  {title: 'MTMAMT', dataIndex: 'mtmamt', key: 'mtmamt', align: 'center'},
];

