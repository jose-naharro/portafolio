
GIcon iconRadio;
GIcon iconToggle;

GGroup groupMenu;
GButton botonUnidades;
GButton botonVisual;
GButton botonInfo;
GButton botonConfig;
GButton botonPower;
int numBotonesMenu = 5;

GGroup groupUnidades;
GLabel labelUVelocidad;
GToggleGroup opcionesUvelocidad;
GOption opcionKmh;
GOption opcionKt;
GOption opcionMph;
GLabel labelUAltitud;
GToggleGroup opcionesUaltitud;
GOption opcionMetros;
GOption opcionFt;
GLabel labelUVariometro;
GToggleGroup opcionesUvariometro;
GOption opcionMxS;
GOption opcionFtxMin;
GLabel labelUPresion;
GToggleGroup opcionesUpresion;
GOption opcionMMhg;
GOption opcionINhg;
GLabel labelUTemp;
GToggleGroup opcionesUtemp;
GOption opcionGc;
GOption opcionGf;

GGroup groupVisual;
GCheckbox toggleRumbo;
GCheckbox toggleFecha; 
GCheckbox toggleAlt;
GCheckbox toggleIAS;
GCheckbox toggleTemp; 
GCheckbox toggleVario;
GCheckbox toggleGS;
GCheckbox toggleBola; 
GCheckbox toggleQNH;
GLabel textLabelAvisoHorizonte;

GGroup groupInfo;
GLabel textLabelInfo;
GLabel textLabelIdioma;
GLabel textLabelIdiomaCambiado;
GToggleGroup opcionesIidioma;
GOption opcionEsp;
GOption opcionEng;
GOption opcionFran;
GOption opcionDeut;
GLabel textLabelIFormatoFH;
GToggleGroup opcionesIformatoFecha;
GOption opcionFecha1;
GOption opcionFecha2;
GToggleGroup opcionesIformatoHora;
GOption opcionHora24;
GOption opcionHora12;
GButton botonTimezone;
GLabel textLabelIModelo;
GLabel textLabelIVersion;
GLabel textLabelInSerie;
GLabel textLabelIhoras;
String modeloEfis;
String versionSistema;
String numeroSerie;
String horasFuncionamiento;
JSONObject jsonInfoSistema;
GButton botonUpdate;
File f;

GGroup groupConfig;
GLabel textLabelValProp;
GLabel textLabelValAct;
GLabel textLabelVne;
GLabel textLabelVno;
GLabel textLabelVfe;
GLabel textLabelVs;
GLabel textLabelVy;
GLabel textLabelVso;
int numSliders  = 6;
GSlider sliderVne;
GSlider sliderVno;
GSlider sliderVfe;
GSlider sliderVs;
GSlider sliderVy;
GSlider sliderVso;
GButton botonMasVne;
GButton botonMenosVne;
GButton botonMasVno;
GButton botonMenosVno;
GButton botonMasVfe;
GButton botonMenosVfe;
GButton botonMasVs;
GButton botonMenosVs;
GButton botonMasVy;
GButton botonMenosVy;
GButton botonMasVso;
GButton botonMenosVso;
GLabel textLabelPropVne;
GLabel textLabelPropVno;
GLabel textLabelPropVfe;
GLabel textLabelPropVs;
GLabel textLabelPropVy;
GLabel textLabelPropVso;
GButton botonEnviarVne;
GButton botonEnviarVno;
GButton botonEnviarVfe;
GButton botonEnviarVs;
GButton botonEnviarVy;
GButton botonEnviarVso;
GLabel textLabelActVne;
GLabel textLabelActVno;
GLabel textLabelActVfe;
GLabel textLabelActVs;
GLabel textLabelActVy;
GLabel textLabelActVso;

GLabel textLabelAvisoValores;

GGroup groupPower;
GImageButton botonOnOff;
GImageButton botonReset;

int activeGroup;

//cosas del json preferencias
JSONObject jsonPreferencias;
int jvelocidad;
int jaltitud;
int jvsi;
int jqnh;
int jtemp;

boolean jverHDG;
boolean jverFECHA;
boolean jverIAS;
boolean jverGS;
boolean jverALT;
boolean jverVSI;
boolean jverQNH;
boolean jverTemp;
boolean jverBola;

int jLimiteBlanco;
int jLimiteVerde;
int jLimiteAmarillo;
int jLimiteRojo;
int jLimiteVB;
//int jLimiteBV;
int jValorVy;

int jidioma;
int idiomaOriginal;

int jFormatoFecha;
int jFormatoHora;

void opciones(){
    G4P.messagesEnabled(false);
    G4P.setGlobalColorScheme(8);
    G4P.setMouseOverEnabled(false);
    G4P.getCursorOff();

    groupMenu = new GGroup(this);
    groupMenu.setVisible(false);

    botonUnidades = new GButton(this, 0, 0, width/numBotonesMenu, 60);
    botonUnidades.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonUnidades.setText(textMenuUni);
    botonUnidades.useRoundCorners(false);
    botonUnidades.setLocalColor(4, colorBotonActivo);
    botonUnidades.addEventHandler(this, "botonUnidadesClick");

    botonVisual = new GButton(this, width/numBotonesMenu, 0, width/numBotonesMenu, 60);
    botonVisual.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonVisual.setText(textMenuVisu);
    botonVisual.useRoundCorners(false);
    botonVisual.setLocalColor(4, colorBotonInactivo);
    botonVisual.addEventHandler(this, "botonVisualClick");

    botonInfo = new GButton(this, width/numBotonesMenu*2, 0, width/numBotonesMenu, 60);
    botonInfo.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonInfo.setText(textMenuInfoUpdate);
    botonInfo.useRoundCorners(false);
    botonInfo.setLocalColor(4, colorBotonInactivo);
    botonInfo.addEventHandler(this, "botonInfoClick");

    botonConfig = new GButton(this, width/numBotonesMenu*3, 0, width/numBotonesMenu, 60);
    botonConfig.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonConfig.setText(textMenuConf);
    botonConfig.useRoundCorners(false);
    botonConfig.setLocalColor(4, colorBotonInactivo);
    botonConfig.addEventHandler(this, "botonConfigClick");

    botonPower = new GButton(this, width/numBotonesMenu*4, 0, width/numBotonesMenu, 60);
    botonPower.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonPower.setText(textMenuPower);
    botonPower.useRoundCorners(false);
    botonPower.setLocalColor(4, colorBotonInactivo);
    botonPower.addEventHandler(this, "botonPowerClick");

    groupMenu.addControls(botonUnidades, botonVisual, botonInfo, botonConfig, botonPower);

    // group Unidades
    //////////////////////////
    groupUnidades = new GGroup(this);
    groupUnidades.setVisible(false);
    
    opcionesUvelocidad = new GToggleGroup();
    labelUVelocidad = new GLabel(this, width/16, height/16*4, 165, 45, textVelUni);
    labelUVelocidad.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    labelUVelocidad.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionKmh = new GOption(this, width/16*5, height/16*4, 120, 45);
    opcionKmh.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionKmh.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionKmh.setText("Km/h");
    opcionKmh.setIcon(iconRadio, null, null, null);
    //opcionKmh.addEventHandler(this, "opcionKmhClick");
    opcionKt = new GOption(this, width/16*8, height/16*4, 120, 45);
    opcionKt.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionKt.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionKt.setText("Kt");
    opcionKt.setIcon(iconRadio, null, null, null);
    //opcionKt.addEventHandler(this, "opcionKtClick");
    opcionMph = new GOption(this, width/16*11, height/16*4, 120, 45);
    opcionMph.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionMph.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionMph.setText("Mph");
    opcionMph.setIcon(iconRadio, null, null, null);
    opcionesUvelocidad.addControls(opcionKmh, opcionKt, opcionMph);
    groupUnidades.addControls(labelUVelocidad, opcionKmh, opcionKt, opcionMph);
    if (jvelocidad == 0) {
        opcionKmh.setSelected(true);
    } else if (jvelocidad == 1) {
        opcionKt.setSelected(true);
    } else if (jvelocidad == 2) {
        opcionMph.setSelected(true);
    }

    opcionesUaltitud = new GToggleGroup();
    labelUAltitud = new GLabel(this, width/16, height/16*6, 150, 45, textAltUni);
    labelUAltitud.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    labelUAltitud.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionMetros = new GOption(this, width/16*5, height/16*6, 120, 45);
    opcionMetros.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionMetros.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionMetros.setText(textMetrosUni);
    opcionMetros.setIcon(iconRadio, null, null, null);
    opcionFt = new GOption(this, width/16*8, height/16*6, 120, 45);
    opcionFt.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFt.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFt.setText(textPiesUni);
    opcionFt.setIcon(iconRadio, null, null, null);
    opcionesUaltitud.addControls(opcionMetros, opcionFt);
    groupUnidades.addControls(labelUAltitud, opcionMetros, opcionFt);
    if (jaltitud == 0) {
        opcionMetros.setSelected(true);
    } else if (jaltitud == 1) {
        opcionFt.setSelected(true);
    }
    

    opcionesUvariometro = new GToggleGroup();
    labelUVariometro = new GLabel(this, width/16, height/16*8, 150, 45, textVarioUni);
    labelUVariometro.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    labelUVariometro.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionMxS = new GOption(this, width/16*5, height/16*8, 120, 45);
    opcionMxS.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionMxS.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionMxS.setText("m/s");
    opcionMxS.setIcon(iconRadio, null, null, null);
    opcionFtxMin = new GOption(this, width/16*8, height/16*8, 120, 45);
    opcionFtxMin.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFtxMin.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFtxMin.setText("ft/m");
    opcionFtxMin.setIcon(iconRadio, null, null, null);
    opcionesUvariometro.addControls(opcionMxS, opcionFtxMin);
    groupUnidades.addControls(labelUVariometro, opcionMxS, opcionFtxMin);
    if (jvsi== 0) {
        opcionMxS.setSelected(true);
    } else if (jvsi == 1) {
        opcionFtxMin.setSelected(true);
    }
    

    opcionesUpresion = new GToggleGroup();
    labelUPresion = new GLabel(this, width/16, height/16*10, 150, 45, textPresionUni);
    labelUPresion.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    labelUPresion.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionMMhg = new GOption(this, width/16*5, height/16*10, 120, 45);
    opcionMMhg.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionMMhg.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionMMhg.setText("mmHg");
    opcionMMhg.setIcon(iconRadio, null, null, null);
    opcionINhg = new GOption(this, width/16*8, height/16*10, 120, 45);
    opcionINhg.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionINhg.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionINhg.setText("inHg");
    opcionINhg.setIcon(iconRadio, null, null, null);
    opcionesUpresion.addControls(opcionMMhg, opcionINhg);
    groupUnidades.addControls(labelUPresion, opcionMMhg, opcionINhg);
    if (jqnh == 0) {
        opcionMMhg.setSelected(true);
    } else if (jqnh == 1) {
        opcionINhg.setSelected(true);
    }
    

    opcionesUtemp = new GToggleGroup();
    labelUTemp = new GLabel(this, width/16, height/16*12, 150, 45, textTempUni);
    labelUTemp.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    labelUTemp.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionGc = new GOption(this, width/16*5, height/16*12, 120, 45);
    opcionGc.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionGc.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionGc.setText("ºC");
    opcionGc.setIcon(iconRadio, null, null, null);
    opcionGf = new GOption(this, width/16*8, height/16*12, 120, 45);
    opcionGf.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionGf.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionGf.setText("ºF");
    opcionGf.setIcon(iconRadio, null, null, null);
    opcionesUtemp.addControls(opcionGc, opcionGf);
    groupUnidades.addControls(labelUTemp, opcionGc, opcionGf);
    if (jtemp == 0) {
        opcionGc.setSelected(true);
    } else if (jtemp == 1) {
        opcionGf.setSelected(true);
    }
    

    // group Visual
    ///////////////////////
    groupVisual = new GGroup(this);
    groupVisual.setVisible(false);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleRumbo = new GCheckbox(this, width/25*2, height/16*3, 200, 70);
    toggleRumbo.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleRumbo.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleRumbo.setText(textRumboVisu);
    toggleRumbo.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleRumbo);
    toggleRumbo.setSelected(jverHDG);
    /*
    if (jverHDG == 0) {
        toggleRumbo.setSelected(false);
    } else if (jverHDG == 1) {
        toggleRumbo.setSelected(true);
    }
    */

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleFecha = new GCheckbox(this, width/25*9.5, height/16*3, 200, 70);
    toggleFecha.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleFecha.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleFecha.setText(textFechaVisu);
    toggleFecha.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleFecha);
    toggleFecha.setSelected(jverFECHA);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleAlt = new GCheckbox(this, width/25*17, height/16*3, 200, 70);
    toggleAlt.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleAlt.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleAlt.setText(textAltitudVisu);
    toggleAlt.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleAlt);
    toggleAlt.setSelected(jverALT);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleIAS = new GCheckbox(this, width/25*2, height/16*7, 200, 70);
    toggleIAS.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleIAS.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleIAS.setText(textIASVisu);
    toggleIAS.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleIAS);
    toggleIAS.setSelected(jverIAS);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleTemp = new GCheckbox(this, width/25*9.5, height/16*7, 200, 70);
    toggleTemp.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleTemp.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleTemp.setText(textTempVisu);
    toggleTemp.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleTemp);
    toggleTemp.setSelected(jverTemp);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleVario = new GCheckbox(this, width/25*17, height/16*7, 200, 70);
    toggleVario.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleVario.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleVario.setText(textVarioVisu);
    toggleVario.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleVario);
    toggleVario.setSelected(jverVSI);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleGS = new GCheckbox(this, width/25*2, height/16*11, 200, 70);
    toggleGS.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleGS.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleGS.setText(textGSVisu);
    toggleGS.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleGS);
    toggleGS.setSelected(jverGS);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleBola = new GCheckbox(this, width/25*9.5, height/16*11, 200, 70);
    toggleBola.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleBola.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleBola.setText(textBolaVisu);
    toggleBola.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleBola);
    toggleBola.setSelected(jverBola);

    iconToggle = new GIcon(this, "user_toggle.png", 2, 1);
    toggleQNH = new GCheckbox(this, width/25*17, height/16*11, 200, 70);
    toggleQNH.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    toggleQNH.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    toggleQNH.setText(textQNHVisu);
    toggleQNH.setIcon(iconToggle, GAlign.SOUTH, GAlign.CENTER, GAlign.MIDDLE);
    groupVisual.addControls(toggleQNH);
    toggleQNH.setSelected(jverQNH);

    textLabelAvisoHorizonte = new GLabel(this, 0, height-50, 800, 25);
    textLabelAvisoHorizonte.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    textLabelAvisoHorizonte.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelAvisoHorizonte.setText(textAvisoVisu);
    groupVisual.addControls(textLabelAvisoHorizonte);

    // group Info
    //////////////////
    groupInfo = new GGroup(this);
    groupInfo.setVisible(false);

    textLabelInfo = new GLabel(this, width/16-30, height/16*2-20, 700+60, 220);
    textLabelInfo.setTextAlign(GAlign.JUSTIFY, GAlign.JUSTIFY);
    textLabelInfo.setFont(new Font("B612 Regular", Font.PLAIN, 15));
    textLabelInfo.setText(textAvisoInfoUpdate);
    groupInfo.addControls(textLabelInfo);

    opcionesIidioma = new GToggleGroup();
    textLabelIdioma = new GLabel(this, width/25-10, height/6*3.3, 180, 25);
    textLabelIdioma.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIdioma.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelIdioma.setText(textIdiomaInfoUpdate);
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionEsp = new GOption(this, width/25-10, height/6*3.3+50, 100, 45);
    opcionEsp.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionEsp.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionEsp.setText("Español");
    opcionEsp.setIcon(iconRadio, null, null, null);
    opcionEng = new GOption(this, width/25*4, height/6*3.3+50, 100, 45);
    opcionEng.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionEng.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionEng.setText("English");
    opcionEng.setIcon(iconRadio, null, null, null);
    opcionFran = new GOption(this, width/25-10, height/6*3.3+50+60, 105, 45);
    opcionFran.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFran.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFran.setText("Français");
    opcionFran.setIcon(iconRadio, null, null, null);
    opcionDeut = new GOption(this, width/25*4, height/6*3.3+50+60, 100, 45);
    opcionDeut.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionDeut.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionDeut.setText("Deutsch");
    opcionDeut.setIcon(iconRadio, null, null, null);
    opcionesIidioma.addControls(opcionEsp, opcionEng, opcionFran, opcionDeut);
    groupInfo.addControls(textLabelIdioma, opcionEsp, opcionEng, opcionFran, opcionDeut);
    if (jidioma == 0) {
        opcionEsp.setSelected(true);
    } else if (jidioma == 1) {
        opcionEng.setSelected(true);
    } else if (jidioma == 2) {
        opcionFran.setSelected(true);
    } else if (jidioma == 3) {
        opcionDeut.setSelected(true);
    } 
    textLabelIdiomaCambiado = new GLabel(this, width/25-10, height/6*5.5, 250, 25);
    textLabelIdiomaCambiado.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    textLabelIdiomaCambiado.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    groupInfo.addControls(textLabelIdiomaCambiado);

    textLabelIFormatoFH = new GLabel(this, width/25*8, height/6*3.3, 250, 25);
    textLabelIFormatoFH.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIFormatoFH.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelIFormatoFH.setText(textFormatoHora);
    opcionesIformatoFecha = new GToggleGroup();
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionFecha1 = new GOption(this, width/25*8, height/6*3.3+50, 140, 45);
    opcionFecha1.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFecha1.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFecha1.setText(textFormatoFecha1);
    opcionFecha1.setIcon(iconRadio, null, null, null);
    opcionFecha2 = new GOption(this, width/25*12+20, height/6*3.3+50, 140, 45);
    opcionFecha2.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFecha2.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFecha2.setText(textFormatoFecha2);
    opcionFecha2.setIcon(iconRadio, null, null, null);
    opcionesIformatoFecha.addControls(opcionFecha1, opcionFecha2);
    opcionesIformatoHora = new GToggleGroup();
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionHora24 = new GOption(this, width/25*8, height/6*3.3+50+60, 120, 45);
    opcionHora24.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionHora24.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionHora24.setText("24H");
    opcionHora24.setIcon(iconRadio, null, null, null);
    opcionHora12 = new GOption(this, width/25*12+20, height/6*3.3+50+60, 120, 45);
    opcionHora12.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionHora12.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionHora12.setText("12H");
    opcionHora12.setIcon(iconRadio, null, null, null);
    opcionesIformatoHora.addControls(opcionHora24, opcionHora12);
    groupInfo.addControls(textLabelIFormatoFH, opcionFecha1, opcionFecha2, opcionHora24, opcionHora12);
    if (jFormatoHora == 0) {
        opcionHora24.setSelected(true);
    } else if (jFormatoHora == 1) {
        opcionHora12.setSelected(true);
    }
    if (jFormatoFecha == 0) {
        opcionFecha1.setSelected(true);
    } else if (jFormatoFecha == 1) {
        opcionFecha2.setSelected(true);
    }

    botonTimezone = new GButton(this,width/25*10, height/6*5.3, 120, 40);
    botonTimezone.setFont(new Font("B612", Font.BOLD, 14));
    botonTimezone.setText(textoBotonTimezone);
    botonTimezone.useRoundCorners(false);
    botonTimezone.setLocalColor(4, colorBotonInactivo);
    botonTimezone.addEventHandler(this, "botonTimezoneClick");
    groupInfo.addControls(botonTimezone);

    textLabelIModelo = new GLabel(this, width/25*18, height/6*3.3, 250, 25);
    textLabelIModelo.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIModelo.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    textLabelIModelo.setText(textModeloEfis+" "+modeloEfis);
    textLabelIVersion = new GLabel(this, width/25*18, height/6*3.6, 250, 25);
    textLabelIVersion.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIVersion.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    textLabelIVersion.setText(textVersionSistema+" "+versionSistema);
    textLabelInSerie = new GLabel(this, width/25*18, height/6*3.9, 250, 25);
    textLabelInSerie.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelInSerie.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    textLabelInSerie.setText(textNumeroSerie+" "+numeroSerie);
    textLabelIhoras = new GLabel(this, width/25*18, height/6*4.2, 250, 25);
    textLabelIhoras.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIhoras.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    textLabelIhoras.setText(textHorasFuncionamiento+" "+horasFuncionamiento);
    groupInfo.addControls(textLabelIModelo, textLabelIVersion, textLabelInSerie, textLabelIhoras);

    botonUpdate = new GButton(this, width/6*5-60, height-90, 120, 40);
    botonUpdate.setFont(new Font("B612 Bold", Font.BOLD, 14));
    botonUpdate.setText(textBotonUpdateInfoUpdate);
    botonUpdate.useRoundCorners(false);
    botonUpdate.setLocalColor(4, colorBotonInactivo);
    botonUpdate.addEventHandler(this, "botonUpdateClick");
    groupInfo.addControls(botonUpdate);
    //f = new File("C:\\formatter.log");//OOOOJOOO aqui poner la ruta que nosostros queremos o la buscariamos
    f = new File("/home/pi/updaterESP.sh");

    // group Config
    //////////////////
    groupConfig = new GGroup(this);
    groupConfig.setVisible(false);

    textLabelValProp = new GLabel(this, width/16*8.6, height/25*4, 200, 25);
    textLabelValProp.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelValProp.setFont(new Font("B612 Regular", Font.PLAIN, 16));
    textLabelValProp.setText(textValPropConf);
    textLabelValAct = new GLabel(this, width/16*13, height/25*4, 150, 25);
    textLabelValAct.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelValAct.setFont(new Font("B612 Regular", Font.PLAIN, 16));
    textLabelValAct.setText(textValActConf);

    textLabelVne = new GLabel(this, width/16, height/25*5+15, 50, 25);
    textLabelVne.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelVne.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelVne.setText("Vne");
    sliderVne = new GSlider(this, width/16*4, height/25*5, 200, 60, 20);
    sliderVne.setLimits(jLimiteRojo, 0, 400);
    sliderVne.setEasing(1);//Esto es la cadencia con la que se mueve. 1 es el minimo, es decir, sin "retardo"
    //sliderVne.setShowValue(true);
    sliderVne.setLocalColor(3, colorBotonActivo);
    botonMasVne = new GButton(this, width/16*4+210, height/25*5+15, 40, 30);
    botonMasVne.setFont(new Font("B612 Bold", Font.BOLD, 20));
    botonMasVne.setText("+");
    botonMasVne.useRoundCorners(false);
    botonMasVne.setLocalColor(4, colorBotonInactivo);
    botonMasVne.addEventHandler(this, "botonMasVneClick");
    botonMenosVne = new GButton(this, width/16*4-50, height/25*5+15, 40, 30);
    botonMenosVne.setFont(new Font("B612 Bold", Font.BOLD, 30));
    botonMenosVne.setText("-");
    botonMenosVne.useRoundCorners(false);
    botonMenosVne.setLocalColor(4, colorBotonInactivo);
    botonMenosVne.addEventHandler(this, "botonMenosVneClick");
    textLabelPropVne = new GLabel(this, width/16*9+30, height/25*5+15, 50, 25);
    textLabelPropVne.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelPropVne.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelPropVne.setText(sliderVne.getValueS());
    botonEnviarVne = new GButton(this, width/16*11, height/25*5+10, 80, 40);
    botonEnviarVne.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonEnviarVne.setText(textBotonEnviarConf);
    botonEnviarVne.useRoundCorners(false);
    botonEnviarVne.setLocalColor(4, colorBotonInactivo);
    botonEnviarVne.addEventHandler(this, "botonEnviarVneClick");
    textLabelActVne = new GLabel(this, width/16*14-30, height/25*5+15, 50, 25);
    textLabelActVne.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelActVne.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelActVne.setText(sliderVne.getValueS());//Modificar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    textLabelVno = new GLabel(this, width/16, height/25*8+15, 50, 25);
    textLabelVno.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelVno.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelVno.setText("Vno");
    sliderVno = new GSlider(this, width/16*4, height/25*8, 200, 60, 20);
    sliderVno.setLimits(jLimiteAmarillo, 0, 400);
    sliderVno.setEasing(1);
    //sliderVno.setShowValue(true);
    sliderVno.setLocalColor(3, colorBotonActivo);
    botonMasVno = new GButton(this, width/16*4+210, height/25*8+15, 40, 30);
    botonMasVno.setFont(new Font("B612 Bold", Font.BOLD, 20));
    botonMasVno.setText("+");
    botonMasVno.useRoundCorners(false);
    botonMasVno.setLocalColor(4, colorBotonInactivo);
    botonMasVno.addEventHandler(this, "botonMasVnoClick");
    botonMenosVno = new GButton(this, width/16*4-50, height/25*8+15, 40, 30);
    botonMenosVno.setFont(new Font("B612 Bold", Font.BOLD, 30));
    botonMenosVno.setText("-");
    botonMenosVno.useRoundCorners(false);
    botonMenosVno.setLocalColor(4, colorBotonInactivo);
    botonMenosVno.addEventHandler(this, "botonMenosVnoClick");
    textLabelPropVno = new GLabel(this, width/16*9+30, height/25*8+15, 50, 25);
    textLabelPropVno.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelPropVno.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelPropVno.setText(sliderVno.getValueS());
    botonEnviarVno = new GButton(this, width/16*11, height/25*8+10, 80, 40);
    botonEnviarVno.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonEnviarVno.setText(textBotonEnviarConf);
    botonEnviarVno.useRoundCorners(false);
    botonEnviarVno.setLocalColor(4, colorBotonInactivo);
    botonEnviarVno.addEventHandler(this, "botonEnviarVnoClick");
    textLabelActVno = new GLabel(this, width/16*14-30, height/25*8+15, 50, 25);
    textLabelActVno.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelActVno.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelActVno.setText(sliderVno.getValueS());//Modificar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    textLabelVfe = new GLabel(this, width/16, height/25*11+15, 50, 25);
    textLabelVfe.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelVfe.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelVfe.setText("Vfe");
    sliderVfe = new GSlider(this, width/16*4, height/25*11, 200, 60, 20);
    sliderVfe.setLimits(jLimiteVerde, 0, 400);
    sliderVfe.setEasing(1);
    //sliderVfe.setShowValue(true);
    sliderVfe.setLocalColor(3, colorBotonActivo);
    botonMasVfe = new GButton(this, width/16*4+210, height/25*11+15, 40, 30);
    botonMasVfe.setFont(new Font("B612 Bold", Font.BOLD, 20));
    botonMasVfe.setText("+");
    botonMasVfe.useRoundCorners(false);
    botonMasVfe.setLocalColor(4, colorBotonInactivo);
    botonMasVfe.addEventHandler(this, "botonMasVfeClick");
    botonMenosVfe = new GButton(this, width/16*4-50, height/25*11+15, 40, 30);
    botonMenosVfe.setFont(new Font("B612 Bold", Font.BOLD, 30));
    botonMenosVfe.setText("-");
    botonMenosVfe.useRoundCorners(false);
    botonMenosVfe.setLocalColor(4, colorBotonInactivo);
    botonMenosVfe.addEventHandler(this, "botonMenosVfeClick");
    textLabelPropVfe = new GLabel(this, width/16*9+30, height/25*11+15, 50, 25);
    textLabelPropVfe.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelPropVfe.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelPropVfe.setText(sliderVfe.getValueS());
    botonEnviarVfe = new GButton(this, width/16*11, height/25*11+10, 80, 40);
    botonEnviarVfe.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonEnviarVfe.setText(textBotonEnviarConf);
    botonEnviarVfe.useRoundCorners(false);
    botonEnviarVfe.setLocalColor(4, colorBotonInactivo);
    botonEnviarVfe.addEventHandler(this, "botonEnviarVfeClick");
    textLabelActVfe = new GLabel(this, width/16*14-30, height/25*11+15, 50, 25);
    textLabelActVfe.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelActVfe.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelActVfe.setText(sliderVfe.getValueS());//Modificar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    textLabelVs = new GLabel(this, width/16, height/25*14+15, 50, 25);
    textLabelVs.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelVs.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelVs.setText("Vs");
    sliderVs = new GSlider(this, width/16*4, height/25*14, 200, 60, 20);
    sliderVs.setLimits(jLimiteVB, 0, 400);
    sliderVs.setEasing(1);
    //sliderVs.setShowValue(true);
    sliderVs.setLocalColor(3, colorBotonActivo);
    botonMasVs = new GButton(this, width/16*4+210, height/25*14+15, 40, 30);
    botonMasVs.setFont(new Font("B612 Bold", Font.BOLD, 20));
    botonMasVs.setText("+");
    botonMasVs.useRoundCorners(false);
    botonMasVs.setLocalColor(4, colorBotonInactivo);
    botonMasVs.addEventHandler(this, "botonMasVsClick");
    botonMenosVs = new GButton(this, width/16*4-50, height/25*14+15, 40, 30);
    botonMenosVs.setFont(new Font("B612 Bold", Font.BOLD, 30));
    botonMenosVs.setText("-");
    botonMenosVs.useRoundCorners(false);
    botonMenosVs.setLocalColor(4, colorBotonInactivo);
    botonMenosVs.addEventHandler(this, "botonMenosVsClick");
    textLabelPropVs = new GLabel(this, width/16*9+30, height/25*14+15, 50, 25);
    textLabelPropVs.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelPropVs.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelPropVs.setText(sliderVs.getValueS());
    botonEnviarVs = new GButton(this, width/16*11, height/25*14+10, 80, 40);
    botonEnviarVs.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonEnviarVs.setText(textBotonEnviarConf);
    botonEnviarVs.useRoundCorners(false);
    botonEnviarVs.setLocalColor(4, colorBotonInactivo);
    botonEnviarVs.addEventHandler(this, "botonEnviarVsClick");
    textLabelActVs = new GLabel(this, width/16*14-30, height/25*14+15, 50, 25);
    textLabelActVs.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelActVs.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelActVs.setText(sliderVs.getValueS());//Modificar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    textLabelVy = new GLabel(this, width/16, height/25*17+15, 50, 25);
    textLabelVy.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelVy.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelVy.setText("Vy");
    sliderVy = new GSlider(this, width/16*4, height/25*17, 200, 60, 20);
    sliderVy.setLimits(jValorVy, 0, 400);
    sliderVy.setEasing(1);
    //sliderVy.setShowValue(true);
    sliderVy.setLocalColor(3, colorBotonActivo);
    botonMasVy = new GButton(this, width/16*4+210, height/25*17+15, 40, 30);
    botonMasVy.setFont(new Font("B612 Bold", Font.BOLD, 20));
    botonMasVy.setText("+");
    botonMasVy.useRoundCorners(false);
    botonMasVy.setLocalColor(4, colorBotonInactivo);
    botonMasVy.addEventHandler(this, "botonMasVyClick");
    botonMenosVy = new GButton(this, width/16*4-50, height/25*17+15, 40, 30);
    botonMenosVy.setFont(new Font("B612 Bold", Font.BOLD, 30));
    botonMenosVy.setText("-");
    botonMenosVy.useRoundCorners(false);
    botonMenosVy.setLocalColor(4, colorBotonInactivo);
    botonMenosVy.addEventHandler(this, "botonMenosVyClick");
    textLabelPropVy = new GLabel(this, width/16*9+30, height/25*17+15, 50, 25);
    textLabelPropVy.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelPropVy.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelPropVy.setText(sliderVy.getValueS());
    botonEnviarVy = new GButton(this, width/16*11, height/25*17+10, 80, 40);
    botonEnviarVy.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonEnviarVy.setText(textBotonEnviarConf);
    botonEnviarVy.useRoundCorners(false);
    botonEnviarVy.setLocalColor(4, colorBotonInactivo);
    botonEnviarVy.addEventHandler(this, "botonEnviarVyClick");
    textLabelActVy = new GLabel(this, width/16*14-30, height/25*17+15, 50, 25);
    textLabelActVy.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelActVy.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelActVy.setText(sliderVy.getValueS());//Modificar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    textLabelVso = new GLabel(this, width/16, height/25*20+15, 50, 25);
    textLabelVso.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelVso.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelVso.setText("Vso");
    sliderVso = new GSlider(this, width/16*4, height/25*20, 200, 60, 20);
    sliderVso.setLimits(jLimiteBlanco, 0, 400);
    sliderVso.setEasing(1);
    //sliderVso.setShowValue(true);
    sliderVso.setLocalColor(3, colorBotonActivo);
    botonMasVso = new GButton(this, width/16*4+210, height/25*20+15, 40, 30);
    botonMasVso.setFont(new Font("B612 Bold", Font.BOLD, 20));
    botonMasVso.setText("+");
    botonMasVso.useRoundCorners(false);
    botonMasVso.setLocalColor(4, colorBotonInactivo);
    botonMasVso.addEventHandler(this, "botonMasVsoClick");
    botonMenosVso = new GButton(this, width/16*4-50, height/25*20+15, 40, 30);
    botonMenosVso.setFont(new Font("B612 Bold", Font.BOLD, 30));
    botonMenosVso.setText("-");
    botonMenosVso.useRoundCorners(false);
    botonMenosVso.setLocalColor(4, colorBotonInactivo);
    botonMenosVso.addEventHandler(this, "botonMenosVsoClick");
    textLabelPropVso = new GLabel(this, width/16*9+30, height/25*20+15, 50, 25);
    textLabelPropVso.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelPropVso.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelPropVso.setText(sliderVso.getValueS());
    botonEnviarVso = new GButton(this, width/16*11, height/25*20+10, 80, 40);
    botonEnviarVso.setFont(new Font("B612 Bold", Font.BOLD, 16));
    botonEnviarVso.setText(textBotonEnviarConf);
    botonEnviarVso.useRoundCorners(false);
    botonEnviarVso.setLocalColor(4, colorBotonInactivo);
    botonEnviarVso.addEventHandler(this, "botonEnviarVsoClick");
    textLabelActVso = new GLabel(this, width/16*14-30, height/25*20+15, 50, 25);
    textLabelActVso.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelActVso.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelActVso.setText(sliderVso.getValueS());//Modificar!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


    textLabelAvisoValores = new GLabel(this, 0, height/25*23.5, 800, 25);
    textLabelAvisoValores.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
    textLabelAvisoValores.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelAvisoValores.setText(textAvisoConf);

    groupConfig.addControls(textLabelValProp, textLabelValAct);
    groupConfig.addControls(sliderVne, sliderVno, sliderVfe, sliderVs, sliderVy, sliderVso);
    groupConfig.addControls(textLabelVne,textLabelVno, textLabelVfe, textLabelVs, textLabelVy, textLabelVso);
    groupConfig.addControls(botonMasVne, botonMenosVne, botonMasVno, botonMenosVno, botonMasVfe, botonMenosVfe, botonMasVs, botonMenosVs, botonMasVy, botonMenosVy, botonMasVso, botonMenosVso);
    groupConfig.addControls(textLabelPropVne, textLabelPropVno, textLabelPropVfe, textLabelPropVs, textLabelPropVy, textLabelPropVso);
    groupConfig.addControls(botonEnviarVne, botonEnviarVno, botonEnviarVfe, botonEnviarVs, botonEnviarVy, botonEnviarVso);
    groupConfig.addControls(textLabelActVne, textLabelActVno, textLabelActVfe, textLabelActVs, textLabelActVy, textLabelActVso);

    groupConfig.addControls(textLabelAvisoValores);
    
    // group Power
    //////////////////
    groupPower = new GGroup(this);
    groupPower.setVisible(false);

    String[] filesPower = new String[] {"data/images/imagesButtons/power150.png"};
    botonOnOff =  new GImageButton(this, width/25*5, height/25*10, 150, 150, filesPower);
    botonOnOff.addEventHandler(this, "botonOnOffClick");

    String[] filesReset = new String[] {"data/images/imagesButtons/reset150.png"};
    botonReset =  new GImageButton(this, width/25*15, height/25*10, 150, 150, filesReset);
    botonReset.addEventHandler(this, "botonResetClick");

    groupPower.addControls(botonOnOff, botonReset);

    //añadimos todo al grupo menu
    //////////////////
    groupMenu.addControls(labelUVelocidad, opcionKmh, opcionKt, opcionMph);
    groupMenu.addControls(labelUAltitud, opcionMetros, opcionFt);
    groupMenu.addControls(labelUVariometro, opcionMxS, opcionFtxMin);
    groupMenu.addControls(labelUPresion, opcionMMhg, opcionINhg);
    groupMenu.addControls(labelUTemp, opcionGc, opcionGf);
    
    groupMenu.addControls(toggleRumbo, toggleFecha, toggleAlt, toggleIAS, toggleTemp, toggleVario, toggleGS, toggleBola, toggleQNH, textLabelAvisoHorizonte);
    
    groupMenu.addControls(textLabelInfo, textLabelIdioma, opcionEsp, opcionEng, opcionFran, opcionDeut, textLabelIdiomaCambiado);
    groupMenu.addControls(textLabelIFormatoFH, opcionFecha1, opcionFecha2, opcionHora24, opcionHora12, botonTimezone);
    groupMenu.addControls(textLabelIModelo, textLabelIVersion, textLabelInSerie, textLabelIhoras, botonUpdate);
    
    groupMenu.addControls(textLabelValProp, textLabelValAct);
    groupMenu.addControls(sliderVne, sliderVno, sliderVfe, sliderVs, sliderVy, sliderVso);
    groupMenu.addControls(textLabelVne,textLabelVno, textLabelVfe, textLabelVs, textLabelVy, textLabelVso);
    groupMenu.addControls(botonMasVne, botonMenosVne, botonMasVno, botonMenosVno, botonMasVfe, botonMenosVfe, botonMasVs, botonMenosVs, botonMasVy, botonMenosVy, botonMasVso, botonMenosVso);
    groupMenu.addControls(textLabelPropVne, textLabelPropVno, textLabelPropVfe, textLabelPropVs, textLabelPropVy, textLabelPropVso);
    groupMenu.addControls(botonEnviarVne, botonEnviarVno, botonEnviarVfe, botonEnviarVs, botonEnviarVy, botonEnviarVso);
    groupMenu.addControls(textLabelActVne, textLabelActVno, textLabelActVfe, textLabelActVs, textLabelActVy, textLabelActVso);
    groupMenu.addControls(textLabelAvisoValores);

    groupMenu.addControls(botonOnOff, botonReset);

}
//Fin de opciones /////////////////////////////////////////////////////////////////////


public void cargarPreferencias() {
    jsonPreferencias = loadJSONObject("data/preferences.json");

    jvelocidad = 1;
    jaltitud = 1;
    jvsi = 1;
    jqnh = 1;
    jtemp = 1;

    jverHDG = true;
    jverFECHA = true;
    jverIAS = true;
    jverGS = true;
    jverALT = true;
    jverVSI = true;
    jverQNH = true;
    jverTemp = true;
    jverBola = true;

    jLimiteBlanco = 65;
    jLimiteVerde = 120;
    jLimiteAmarillo = 260;
    jLimiteRojo = 285;
    jLimiteVB = 80;
    //jLimiteBV = vLimit - jLimiteVerde;

    jValorVy = 70;

    jidioma =  0;
    idiomaOriginal = jidioma;

    jFormatoHora = 1;
    jFormatoFecha = 1;
    
    //cargamos el json si es que existe
    if ((jsonPreferencias = loadJSONObject("data/preferences.json")) != null) {
        jvelocidad = jsonPreferencias.getInt("velocidad");
        jaltitud = jsonPreferencias.getInt("altitud");
        jvsi = jsonPreferencias.getInt("vsi");
        jqnh = jsonPreferencias.getInt("qnh");
        jtemp = jsonPreferencias.getInt("temp");

        jverHDG = jsonPreferencias.getBoolean("verHDG");
        jverFECHA = jsonPreferencias.getBoolean("verFECHA");
        jverIAS = jsonPreferencias.getBoolean("verIAS");
        jverGS = jsonPreferencias.getBoolean("verGS");
        jverALT = jsonPreferencias.getBoolean("verALT");
        jverVSI = jsonPreferencias.getBoolean("verVSI");
        jverQNH = jsonPreferencias.getBoolean("verQNH");
        jverTemp = jsonPreferencias.getBoolean("verTemp");
        jverBola = jsonPreferencias.getBoolean("verBola");

        jLimiteBlanco = jsonPreferencias.getInt("limiteBlanco");
        jLimiteVerde = jsonPreferencias.getInt("limiteVerde");
        jLimiteAmarillo = jsonPreferencias.getInt("limiteAmarillo");
        jLimiteRojo = jsonPreferencias.getInt("limiteRojo");
        jLimiteVB = jsonPreferencias.getInt("limiteVB");
        //jLimiteBV = jsonPreferencias.getInt("limiteBV");

        jValorVy = jsonPreferencias.getInt("valorVy");

        jidioma = jsonPreferencias.getInt("idioma");

        idiomaOriginal = jidioma;

        jFormatoFecha = jsonPreferencias.getInt("formatoFecha");
        jFormatoHora = jsonPreferencias.getInt("formatoHora");

        //cargarVelocidades();
    }
}

void cargarInfoSistema(){
    if ((jsonInfoSistema = loadJSONObject("data/infoSistema.json")) != null) {
        modeloEfis = jsonInfoSistema.getString("textModeloEfis");
        versionSistema = jsonInfoSistema.getString("textVersionSistema");
        numeroSerie = jsonInfoSistema.getString("textNumeroSerie");
        horasFuncionamiento = jsonInfoSistema.getString("floatHorasFuncionamiento");
    } else {
        modeloEfis = "XXXXXXXX";
        versionSistema = "XXXXXXXX";
        numeroSerie = "XXXXXXXX";
        horasFuncionamiento = "99999.9";
    }
}

public void botonUnidadesClick(GButton button, GEvent event){
    //mostramos la capa del grupo boton general y ocultamos las otras dos
    groupUnidades.setVisible(true);
    groupVisual.setVisible(false);
    groupInfo.setVisible(false);
    groupConfig.setVisible(false);
    groupPower.setVisible(false);
    //cambiamos de color de fondo el boton general y cambiamos de color los otros dos
    botonUnidades.setLocalColor(4, colorBotonActivo);
    botonVisual.setLocalColor(4, colorBotonInactivo);
    botonInfo.setLocalColor(4, colorBotonInactivo);
    botonConfig.setLocalColor(4, colorBotonInactivo);
    botonPower.setLocalColor(4, colorBotonInactivo);

    activeGroup = 1;
}

public void botonVisualClick(GButton button, GEvent event){
    //mostramos la capa del grupo boton general y ocultamos las otras dos
    groupUnidades.setVisible(false);
    groupVisual.setVisible(true);
    groupInfo.setVisible(false);
    groupConfig.setVisible(false);
    groupPower.setVisible(false);
    //cambiamos de color de fondo el boton general y cambiamos de color los otros dos
    botonUnidades.setLocalColor(4, colorBotonInactivo);
    botonVisual.setLocalColor(4, colorBotonActivo);
    botonInfo.setLocalColor(4, colorBotonInactivo);
    botonConfig.setLocalColor(4, colorBotonInactivo);
    botonPower.setLocalColor(4, colorBotonInactivo);

    activeGroup = 2;
}

public void botonInfoClick(GButton button, GEvent event){
    //mostramos la capa del grupo boton general y ocultamos las otras dos
    groupUnidades.setVisible(false);
    groupVisual.setVisible(false);
    groupInfo.setVisible(true);
    groupConfig.setVisible(false);
    groupPower.setVisible(false);
    //cambiamos de color de fondo el boton general y cambiamos de color los otros dos
    botonUnidades.setLocalColor(4, colorBotonInactivo);
    botonVisual.setLocalColor(4, colorBotonInactivo);
    botonInfo.setLocalColor(4, colorBotonActivo);
    botonConfig.setLocalColor(4, colorBotonInactivo);
    botonPower.setLocalColor(4, colorBotonInactivo);

    activeGroup = 3;
}

public void botonConfigClick(GButton button, GEvent event){
    //mostramos la capa del grupo boton general y ocultamos las otras dos
    groupUnidades.setVisible(false);
    groupVisual.setVisible(false);
    groupInfo.setVisible(false);
    groupConfig.setVisible(true);
    groupPower.setVisible(false);
    //cambiamos de color de fondo el boton general y cambiamos de color los otros dos
    botonUnidades.setLocalColor(4, colorBotonInactivo);
    botonVisual.setLocalColor(4, colorBotonInactivo);
    botonInfo.setLocalColor(4, colorBotonInactivo);
    botonConfig.setLocalColor(4, colorBotonActivo);
    botonPower.setLocalColor(4, colorBotonInactivo);

    activeGroup = 4;
}

public void botonPowerClick(GButton button, GEvent event){
    //mostramos la capa del grupo boton general y ocultamos las otras dos
    groupUnidades.setVisible(false);
    groupVisual.setVisible(false);
    groupInfo.setVisible(false);
    groupConfig.setVisible(false);
    groupPower.setVisible(true);
    //cambiamos de color de fondo el boton general y cambiamos de color los otros dos
    botonUnidades.setLocalColor(4, colorBotonInactivo);
    botonVisual.setLocalColor(4, colorBotonInactivo);
    botonInfo.setLocalColor(4, colorBotonInactivo);
    botonConfig.setLocalColor(4, colorBotonInactivo);
    botonPower.setLocalColor(4, colorBotonActivo);

    activeGroup = 5;
}


public void handleToggleControlEvents(GToggleControl option, GEvent event) {
    if(option == opcionKmh){
        if (opcionKmh.isSelected()) {
            unidadesVel = 1;
            jsonPreferencias.setInt("velocidad", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionKt){
        if (opcionKt.isSelected()) {
            unidadesVel = 2;
            jsonPreferencias.setInt("velocidad", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionMph){
        if (opcionMph.isSelected()) {
            unidadesVel = 3;
            jsonPreferencias.setInt("velocidad", 2);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }

    if(option == opcionMetros){
        if (opcionMetros.isSelected()) {
            unidadesAlt = 1;
            jsonPreferencias.setInt("altitud", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionFt){
        if (opcionFt.isSelected()) {
            unidadesAlt = 2;
            jsonPreferencias.setInt("altitud", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }

    if(option == opcionMxS){
        if (opcionMxS.isSelected()) {
            unidadesVsi = 1;
            jsonPreferencias.setInt("vsi", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionFtxMin){
        if (opcionFtxMin.isSelected()) {
            unidadesVsi = 2;
            jsonPreferencias.setInt("vsi", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }

    if(option == opcionMMhg){
        if (opcionMMhg.isSelected()) {
            unidadesQnh = 1;
            jsonPreferencias.setInt("qnh", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionINhg){
        if (opcionINhg.isSelected()) {
            unidadesQnh = 2;
            jsonPreferencias.setInt("qnh", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }

    if(option == opcionGc){
        if (opcionGc.isSelected()) {
            unidadesTemp = 1;
            jsonPreferencias.setInt("temp", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionGf){
        if (opcionGf.isSelected()) {
            unidadesTemp = 2;
            jsonPreferencias.setInt("temp", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }

    if(option == toggleRumbo){
        if (toggleRumbo.isSelected()) {
            jsonPreferencias.setBoolean("verHDG", true);
        } else {
            jsonPreferencias.setBoolean("verHDG", false);
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleFecha){
        if (toggleFecha.isSelected()) {
            jsonPreferencias.setBoolean("verFECHA", true);
        } else {
            jsonPreferencias.setBoolean("verFECHA", false);
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleAlt){
        if (toggleAlt.isSelected()) {
            jsonPreferencias.setBoolean("verALT", true);
        } else {
            jsonPreferencias.setBoolean("verALT", false);
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleIAS){
        if (toggleIAS.isSelected()) {
            jsonPreferencias.setBoolean("verIAS", true);
        } else {
            jsonPreferencias.setBoolean("verIAS", false);
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleTemp){
        if (toggleTemp.isSelected()) {
            jsonPreferencias.setBoolean("verTemp", true);
        } else {
            jsonPreferencias.setBoolean("verTemp", false); 
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleVario){
        if (toggleVario.isSelected()) {
            jsonPreferencias.setBoolean("verVSI", true);
        } else {
            jsonPreferencias.setBoolean("verVSI", false);  
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleGS){
        if (toggleGS.isSelected()) {
            jsonPreferencias.setBoolean("verGS", true);
        } else {
            jsonPreferencias.setBoolean("verGS", false);  
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleBola){
        if (toggleBola.isSelected()) {
            jsonPreferencias.setBoolean("verBola", true);
        } else {
            jsonPreferencias.setBoolean("verBola", false);  
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == toggleQNH){
        if (toggleQNH.isSelected()) {
            jsonPreferencias.setBoolean("verQNH", true);
        } else {
            jsonPreferencias.setBoolean("verQNH", false);  
        }
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    }

    if(option == opcionEsp){
        if (opcionEsp.isSelected()) {
            jsonPreferencias.setInt("idioma", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
            exec("sudo", "localectl", "set-locale", "LANG=es_ES.utf8");
        }
    }
    if(option == opcionEng){
        if (opcionEng.isSelected()) {
            jsonPreferencias.setInt("idioma", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
            exec("sudo", "localectl", "set-locale", "LANG=en_GB.utf8");
        }
    }
    if(option == opcionFran){
        if (opcionFran.isSelected()) {
            jsonPreferencias.setInt("idioma", 2);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
            exec("sudo", "localectl", "set-locale", "LANG=fr_FR.utf8");
        }
    }
    if(option == opcionDeut){
        if (opcionDeut.isSelected()) {
            jsonPreferencias.setInt("idioma", 3);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
            exec("sudo", "localectl", "set-locale", "LANG=de_DE.utf8");
        }
    }
    if (jsonPreferencias.getInt("idioma") == idiomaOriginal) {
        textLabelIdiomaCambiado.setVisible(false);
    } else {
        textLabelIdiomaCambiado.setText(textIdiomaCambiadoInfoUpdate);
        textLabelIdiomaCambiado.setVisible(true);
    }
    cargarIdioma();

    if(option == opcionHora24){
        if (opcionHora24.isSelected()) {
            jsonPreferencias.setInt("formatoHora", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionHora12){
        if (opcionHora12.isSelected()) {
            jsonPreferencias.setInt("formatoHora", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }

    if(option == opcionFecha1){
        if (opcionFecha1.isSelected()) {
            jsonPreferencias.setInt("formatoFecha", 0);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
    if(option == opcionFecha2){
        if (opcionFecha2.isSelected()) {
            jsonPreferencias.setInt("formatoFecha", 1);
            saveJSONObject(jsonPreferencias, "data/preferences.json");
        }
    }
}

public void botonTimezoneClick(GButton button, GEvent event){
    //G4P.showMessage(this, "Lanzamos la ventana de configuración Timezone en primer plano", "Timezone Info", G4P.INFO);
    exec("sudo" ,"time-admin");
}

public void botonUpdateClick(GButton button, GEvent event){
    if (f.exists()) {//buscar el archivo concreto y si existe mostrar dialogo de confirmacion o cancelacion
        println("existe");
        if (G4P.selectOption(this, textActualizar2, textActualizar1, G4P.QUERY, G4P.YES_NO) == G4P.YES) {
            //exec("lxterminal", "/home/pi/updaterESP.sh");
            launch("/home/pi/updater.desktop");
            System.exit(0);
        }
        //if (G4P.selectOption(this, "¿Confirma la actualización del sistema?", "¿Actualizar?", G4P.QUERY, G4P.YES_NO) == G4P.YES) { 
        //    System.exit(0);
        //}
      
    } else {//si no se encuentra mostrar dialogo con Ok que diga que no se ha encontrado porque no habia usb o en el usb no se encontraba el archivo
        println("no existe");
        //G4P.showMessage(this, "No se puede actualizar el sistema\n"+"porque no hay un usb introducido\n"+"o este no contiene un archivo\n"+"de actualización válido.", "Error actualización", G4P.ERROR);
        G4P.showMessage(this, textErrActualizar2, textErrActualizar1, G4P.ERROR);
        //G4P.showMessage(this, "No se ha podido actualizar el sistema.", "Error actualización", G4P.ERROR);
    }
}

public void handleSliderEvents(GValueControl slider, GEvent event) { 
    if (slider == sliderVne){
        textLabelPropVne.setText(sliderVne.getValueS());   
    } else if (slider == sliderVno) {
        textLabelPropVno.setText(sliderVno.getValueS()); 
    } else if (slider == sliderVfe) {
        textLabelPropVfe.setText(sliderVfe.getValueS()); 
    } else if (slider == sliderVs) {
        textLabelPropVs.setText(sliderVs.getValueS()); 
    } else if (slider == sliderVy) {
        textLabelPropVy.setText(sliderVy.getValueS()); 
    } else if (slider == sliderVso) {
        textLabelPropVso.setText(sliderVso.getValueS()); 
    }  
}


public void botonMasVneClick(GButton button, GEvent event){
    float value = float(sliderVne.getValueS());
    value++;
    sliderVne.setValue(value);
}
public void botonMenosVneClick(GButton button, GEvent event){
    float value = float(sliderVne.getValueS());
    value--;
    sliderVne.setValue(value);
}
public void botonEnviarVneClick(GButton button, GEvent event){
    println("BangVne!! " + sliderVne.getValueS());
    if (int(sliderVne.getValueS()) > jLimiteAmarillo) { //Si es mayor que el límite amarillo podemos escribir por pantalla y guardar en el json
        jLimiteRojo = int(sliderVne.getValueS());//asignamos el valor
        textLabelActVne.setText(sliderVne.getValueS());
        myAnemometro.setLimiteRojoKmh(jLimiteRojo);//Actualizamos el valor en la interfaz grafica
        jsonPreferencias.setFloat("limiteRojo", jLimiteRojo);//guardamos en el json
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    } else {
        sliderVne.setValue(jLimiteRojo);
        G4P.showMessage(this, textWarn1, textWarn2, G4P.ERROR);
    }
}

public void botonMasVnoClick(GButton button, GEvent event){
    float value = float(sliderVno.getValueS());
    value++;
    sliderVno.setValue(value);
}
public void botonMenosVnoClick(GButton button, GEvent event){
    float value = float(sliderVno.getValueS());
    value--;
    sliderVno.setValue(value);
}
public void botonEnviarVnoClick(GButton button, GEvent event){
    println("BangVno!! " + sliderVno.getValueS());
    if ((int(sliderVno.getValueS()) > jLimiteVerde) && (int(sliderVno.getValueS()) < jLimiteRojo)) {
        jLimiteAmarillo = int(sliderVno.getValueS());//asignamos el valor
        textLabelActVno.setText(sliderVno.getValueS());//mostramos en la pantalla
        myAnemometro.setLimiteAmarilloKmh(jLimiteAmarillo);//Actualizamos el valor en la interfaz grafica
        jsonPreferencias.setFloat("limiteAmarillo", jLimiteAmarillo);//guardamos en el json
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    } else {
        sliderVno.setValue(jLimiteAmarillo);
        G4P.showMessage(this, textWarn1, textWarn2, G4P.ERROR);
    }
}

public void botonMasVfeClick(GButton button, GEvent event){
    float value = float(sliderVfe.getValueS());
    value++;
    sliderVfe.setValue(value);
}
public void botonMenosVfeClick(GButton button, GEvent event){
    float value = float(sliderVfe.getValueS());
    value--;
    sliderVfe.setValue(value);
}
public void botonEnviarVfeClick(GButton button, GEvent event){
    println("BangVfe!! " + sliderVfe.getValueS());
    if ((int(sliderVfe.getValueS()) > jLimiteVB) && (int(sliderVfe.getValueS()) < jLimiteAmarillo)) {
        jLimiteVerde = int(sliderVfe.getValueS());//asignamos el valor
        textLabelActVfe.setText(sliderVfe.getValueS());//mostramos en la pantalla
        myAnemometro.setLimiteVerdeKmh(jLimiteVerde);//Actualizamos el valor en la interfaz grafica
        jsonPreferencias.setFloat("limiteVerde", jLimiteVerde);//guardamos en el json
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    } else {
        sliderVfe.setValue(jLimiteVerde);
        G4P.showMessage(this, textWarn1, textWarn2, G4P.ERROR);
    }
}

public void botonMasVsClick(GButton button, GEvent event){
    float value = float(sliderVs.getValueS());
    value++;
    sliderVs.setValue(value);
}
public void botonMenosVsClick(GButton button, GEvent event){
    float value = float(sliderVs.getValueS());
    value--;
    sliderVs.setValue(value);
}
public void botonEnviarVsClick(GButton button, GEvent event){
    println("BangVs!! " + sliderVs.getValueS());
    if ((int(sliderVs.getValueS()) > jLimiteBlanco) && (int(sliderVs.getValueS()) < jLimiteVerde)) {
        jLimiteVB = int(sliderVs.getValueS());//asignamos el valor
        textLabelActVs.setText(sliderVs.getValueS());//mostramos en la pantalla
        myAnemometro.setLimiteVBKmh(jLimiteVB);//Actualizamos el valor en la interfaz grafica
        jsonPreferencias.setFloat("limiteVB", jLimiteVB);//guardamos en el json
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    } else {
        sliderVs.setValue(jLimiteVB);
        G4P.showMessage(this, textWarn1, textWarn2, G4P.ERROR);
    }
}

public void botonMasVyClick(GButton button, GEvent event){
    float value = float(sliderVy.getValueS());
    value++;
    sliderVy.setValue(value);
}
public void botonMenosVyClick(GButton button, GEvent event){
    float value = float(sliderVy.getValueS());
    value--;
    sliderVy.setValue(value);
}
public void botonEnviarVyClick(GButton button, GEvent event){
    println("BangVy!! " + sliderVy.getValueS());
    if (int(sliderVy.getValueS()) > jLimiteBlanco) {
        jValorVy = int(sliderVy.getValueS());//asignamos el valor
        textLabelActVy.setText(sliderVy.getValueS());//mostramos en la pantalla
        myAnemometro.setValorVyKmh(jValorVy);//Actualizamos el valor en la interfaz grafica
        jsonPreferencias.setFloat("valorVy", jValorVy);//guardamos en el json
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    } else {
        sliderVy.setValue(jValorVy);
        G4P.showMessage(this, textWarn1, textWarn2, G4P.ERROR);
    }
}

public void botonMasVsoClick(GButton button, GEvent event){
    float value = float(sliderVso.getValueS());
    value++;
    sliderVso.setValue(value);
}
public void botonMenosVsoClick(GButton button, GEvent event){
    float value = float(sliderVso.getValueS());
    value--;
    sliderVso.setValue(value);
}
public void botonEnviarVsoClick(GButton button, GEvent event){
    println("BangVso!! " + sliderVso.getValueS());
    if ((int(sliderVso.getValueS()) > 0) && (int(sliderVso.getValueS()) < jLimiteVB)) {
        jLimiteBlanco = int(sliderVso.getValueS());//asignamos el valor
        textLabelActVso.setText(sliderVso.getValueS());//mostramos en la pantalla
        myAnemometro.setLimiteBlancoKmh(jLimiteBlanco);//Actualizamos el valor en la interfaz grafica
        jsonPreferencias.setFloat("limiteBlanco", jLimiteBlanco);//guardamos en el json
        saveJSONObject(jsonPreferencias, "data/preferences.json");
    } else {
        sliderVso.setValue(jLimiteBlanco);
        G4P.showMessage(this, textWarn1, textWarn2, G4P.ERROR);
    }
}


public void botonOnOffClick(GImageButton button, GEvent event){
    if (G4P.selectOption(this, textApagar2, textApagar1, G4P.QUERY, G4P.YES_NO) == G4P.YES) {
        exec("sudo" ,"shutdown" ,"now");
        System.exit(0);
    }
    //if (G4P.selectOption(this, "¿Quiere realmente apagar el sistema?", "¿Apagar?", G4P.QUERY, G4P.YES_NO) == G4P.YES) {
    //    System.exit(0);
    //}
}

public void botonResetClick(GImageButton button, GEvent event){
    if (G4P.selectOption(this, textReiniciar2, textReiniciar1, G4P.QUERY, G4P.YES_NO) == G4P.YES) {
        exec("sudo", "shutdown", "-r", "now");
        System.exit(0);
    }
    //if (G4P.selectOption(this, "¿Quiere realmente reiniciar el sistema?", "¿Reiniciar?", G4P.QUERY, G4P.YES_NO) == G4P.YES) {
    //    System.exit(0);
    //}
}
