JSONObject jsonIdioma;

String rutaJsonPath;

String textInfoGPS;
String textLocalGPS;
String textUTCGPS;
String textCursoGPS;
String textSatActivosGPS;
String textSenalGPS;
String textLatGPS;
String textLonGPS;
String textAltGPS;
String textGSGPS;
String textMenuUni;
String textVelUni;
String textAltUni;
String textMetrosUni;
String textPiesUni;
String textVarioUni;
String textPresionUni;
String textTempUni;
String textMenuVisu;
String textRumboVisu;
String textFechaVisu;
String textAltitudVisu;
String textIASVisu;
String textTempVisu;
String textVarioVisu;
String textGSVisu;
String textBolaVisu;
String textQNHVisu;
String textAvisoVisu;
String textMenuInfoUpdate;
String textAvisoInfoUpdate;
String textBotonUpdateInfoUpdate;
String textIdiomaInfoUpdate;
String textIdiomaCambiadoInfoUpdate;
String textMenuConf;
String textValPropConf;
String textValActConf;
String textBotonEnviarConf;
String textAvisoConf;
String textMenuPower;
String textAvisoPruebas1;
String textAvisoPruebas2;
String textPortada1;
String textPortada2;
String textWarn1;
String textWarn2;
String textApagar1;
String textApagar2;
String textReiniciar1;
String textReiniciar2;
String textActualizar1;
String textActualizar2;
String textErrActualizar1;
String textErrActualizar2;
String textModeloEfis;
String textVersionSistema;
String textNumeroSerie;
String textHorasFuncionamiento;
String textFormatoHora;
String textFormatoFecha1;
String textFormatoFecha2;
String textoBotonTimezone;
String textErrorHDG;
String textErrorHA;
String textErrorTEMP;
String textErrorBOLA;
String textErrorANEMO;
String textErrorALT;
String textErrorVSI;
String textErrorGS;
String textErrorQNH;


void cargarIdiomaInicial(){
    if (jidioma == 0) {
        rutaJsonPath = "data/traduccion/spanishText.json";
    } else if (jidioma == 1) {
        rutaJsonPath = "data/traduccion/englishText.json";
    } else if (jidioma == 2) {
        rutaJsonPath = "data/traduccion/frenchText.json";
    } else {
        rutaJsonPath = "data/traduccion/germanText.json";
    }


    if ((jsonIdioma = loadJSONObject(rutaJsonPath)) != null) {
        textInfoGPS = jsonIdioma.getString("textInfoGPS");
        textLocalGPS = jsonIdioma.getString("textLocalGPS");
        textUTCGPS = jsonIdioma.getString("textUTCGPS");
        textCursoGPS = jsonIdioma.getString("textCursoGPS");
        textSatActivosGPS = jsonIdioma.getString("textSatActivosGPS");
        textSenalGPS = jsonIdioma.getString("textSenalGPS");
        textLatGPS = jsonIdioma.getString("textLatGPS");
        textLonGPS = jsonIdioma.getString("textLonGPS");
        textAltGPS = jsonIdioma.getString("textAltGPS");
        textGSGPS = jsonIdioma.getString("textGSGPS");
        textMenuUni = jsonIdioma.getString("textMenuUni");
        textVelUni = jsonIdioma.getString("textVelUni");
        textAltUni = jsonIdioma.getString("textAltUni");
        textMetrosUni = jsonIdioma.getString("textMetrosUni");
        textPiesUni = jsonIdioma.getString("textPiesUni");
        textVarioUni = jsonIdioma.getString("textVarioUni");
        textPresionUni = jsonIdioma.getString("textPresionUni");
        textTempUni = jsonIdioma.getString("textTempUni");
        textMenuVisu = jsonIdioma.getString("textMenuVisu");
        textRumboVisu = jsonIdioma.getString("textRumboVisu");
        textFechaVisu = jsonIdioma.getString("textFechaVisu");
        textAltitudVisu = jsonIdioma.getString("textAltitudVisu");
        textIASVisu = jsonIdioma.getString("textIASVisu");
        textTempVisu = jsonIdioma.getString("textTempVisu");
        textVarioVisu = jsonIdioma.getString("textVarioVisu");
        textGSVisu = jsonIdioma.getString("textGSVisu");
        textBolaVisu = jsonIdioma.getString("textBolaVisu");
        textQNHVisu = jsonIdioma.getString("textQNHVisu");
        textAvisoVisu = jsonIdioma.getString("textAvisoVisu");
        textMenuInfoUpdate = jsonIdioma.getString("textMenuInfoUpdate");
        textAvisoInfoUpdate = jsonIdioma.getString("textAvisoInfoUpdate");
        textBotonUpdateInfoUpdate = jsonIdioma.getString("textBotonUpdateInfoUpdate");
        textIdiomaInfoUpdate = jsonIdioma.getString("textIdiomaInfoUpdate");
        textIdiomaCambiadoInfoUpdate = jsonIdioma.getString("textIdiomaCambiadoInfoUpdate");
        textMenuConf = jsonIdioma.getString("textMenuConf");
        textValPropConf = jsonIdioma.getString("textValPropConf");
        textValActConf = jsonIdioma.getString("textValActConf");
        textBotonEnviarConf = jsonIdioma.getString("textBotonEnviarConf");
        textAvisoConf = jsonIdioma.getString("textAvisoConf");
        textMenuPower = jsonIdioma.getString("textMenuPower");
        textAvisoPruebas1 = jsonIdioma.getString("textAvisoPruebas1");
        textAvisoPruebas2 = jsonIdioma.getString("textAvisoPruebas2");
        textPortada1 = jsonIdioma.getString("textPortada1");
        textPortada2 = jsonIdioma.getString("textPortada2");
        textWarn1 = jsonIdioma.getString("textWarn1");
        textWarn2 = jsonIdioma.getString("textWarn2");
        textApagar1 = jsonIdioma.getString("textApagar1");
        textApagar2 = jsonIdioma.getString("textApagar2");
        textReiniciar1 = jsonIdioma.getString("textReiniciar1");
        textReiniciar2 = jsonIdioma.getString("textReiniciar2");
        textActualizar1 = jsonIdioma.getString("textActualizar1");
        textActualizar2 = jsonIdioma.getString("textActualizar2");
        textErrActualizar1 = jsonIdioma.getString("textErrActualizar1");
        textErrActualizar2 = jsonIdioma.getString("textErrActualizar2");
        textModeloEfis = jsonIdioma.getString("textModeloEfis");
        textVersionSistema = jsonIdioma.getString("textVersionSistema");
        textNumeroSerie = jsonIdioma.getString("textNumeroSerie");
        textHorasFuncionamiento = jsonIdioma.getString("textHorasFuncionamiento");
        textFormatoHora = jsonIdioma.getString("textFormatoHora");
        textFormatoFecha1 = jsonIdioma.getString("textFormatoFecha1");
        textFormatoFecha2 = jsonIdioma.getString("textFormatoFecha2");
        textoBotonTimezone = jsonIdioma.getString("textoBotonTimezone");
        textErrorHDG = jsonIdioma.getString("textErrorHDG");
        textErrorHA = jsonIdioma.getString("textErrorHA");
        textErrorTEMP = jsonIdioma.getString("textErrorTEMP");
        textErrorBOLA = jsonIdioma.getString("textErrorBOLA");
        textErrorANEMO = jsonIdioma.getString("textErrorANEMO");
        textErrorALT = jsonIdioma.getString("textErrorALT");
        textErrorVSI = jsonIdioma.getString("textErrorVSI");
        textErrorGS = jsonIdioma.getString("textErrorGS");
        textErrorQNH = jsonIdioma.getString("textErrorQNH");
    }

/*

    if ((jsonIdioma = loadJSONObject(rutaJsonPath)) != null) {
        jvelocidad = jsonIdioma.getString("velocidad");
        jaltitud = jsonIdioma.getString("altitud");
        jvsi = jsonIdioma.getString("vsi");
        jqnh = jsonIdioma.getString("qnh");
        jtemp = jsonIdioma.getString("temp");

        jverHDG = jsonIdioma.getString("verHDG");
        jverFECHA = jsonIdioma.getString("verFECHA");
        jverIAS = jsonIdioma.getString("verIAS");
        jverGS = jsonIdioma.getString("verGS");
        jverALT = jsonIdioma.getString("verALT");
        jverVSI = jsonIdioma.getString("verVSI");
        jverQNH = jsonIdioma.getString("verQNH");
        jverTemp = jsonIdioma.getString("verTemp");
        jverBola = jsonIdioma.getString("verBola");

        jLimiteBlanco = jsonIdioma.getString("limiteBlanco");
        jLimiteVerde = jsonIdioma.getString("limiteVerde");
        jLimiteAmarillo = jsonIdioma.getString("limiteAmarillo");
        jLimiteRojo = jsonIdioma.getString("limiteRojo");
        jLimiteVB = jsonIdioma.getString("limiteVB");
        jLimiteBV = jsonIdioma.getString("limiteBV");

        jValorVy = jsonIdioma.getString("valorVy");
    }
    */
}

void cargarIdioma(){
    if (opcionEsp.isSelected()) {
        rutaJsonPath = "data/traduccion/spanishText.json";
    } else if (opcionEng.isSelected()) {
        rutaJsonPath = "data/traduccion/englishText.json";
    } else if (opcionFran.isSelected()) {
        rutaJsonPath = "data/traduccion/frenchText.json";
    } else {
        rutaJsonPath = "data/traduccion/germanText.json";
    }


    if ((jsonIdioma = loadJSONObject(rutaJsonPath)) != null) {
        textAvisoInfoUpdate = jsonIdioma.getString("textAvisoInfoUpdate");
        println(textAvisoInfoUpdate);
    }

/*

    if ((jsonIdioma = loadJSONObject(rutaJsonPath)) != null) {
        jvelocidad = jsonIdioma.getString("velocidad");
        jaltitud = jsonIdioma.getString("altitud");
        jvsi = jsonIdioma.getString("vsi");
        jqnh = jsonIdioma.getString("qnh");
        jtemp = jsonIdioma.getString("temp");

        jverHDG = jsonIdioma.getString("verHDG");
        jverFECHA = jsonIdioma.getString("verFECHA");
        jverIAS = jsonIdioma.getString("verIAS");
        jverGS = jsonIdioma.getString("verGS");
        jverALT = jsonIdioma.getString("verALT");
        jverVSI = jsonIdioma.getString("verVSI");
        jverQNH = jsonIdioma.getString("verQNH");
        jverTemp = jsonIdioma.getString("verTemp");
        jverBola = jsonIdioma.getString("verBola");

        jLimiteBlanco = jsonIdioma.getString("limiteBlanco");
        jLimiteVerde = jsonIdioma.getString("limiteVerde");
        jLimiteAmarillo = jsonIdioma.getString("limiteAmarillo");
        jLimiteRojo = jsonIdioma.getString("limiteRojo");
        jLimiteVB = jsonIdioma.getString("limiteVB");
        jLimiteBV = jsonIdioma.getString("limiteBV");

        jValorVy = jsonIdioma.getString("valorVy");
    }
    */
}

