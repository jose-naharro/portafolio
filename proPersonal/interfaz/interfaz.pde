import java.util.*;
//import java.time.*;
/*
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
*/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Font;
import g4p_controls.*;
//import vsync.*;
import processing.serial.*;
//import processing.io.*;

//Robot robot;

float M_PI = 3.14159265359;

//PGraphics horizonte;
//PGraphics hdg;
//PGraphics topLbar;
//PGraphics topRbar;
//PGraphics spd;
//PGraphics alt;
//PGraphics vsi;

PFont font9Ver;
PFont font10Ver;
PFont font11Ver;
PFont font12Ver;
PFont font13Ver;
PFont font14Ver;
PFont font14VerBold;
PFont font15Ver;
PFont font16Ver;
PFont font16VerBold;
PFont font18Ver;
PFont font18VerBold;
PFont font20Ver;
PFont font20VerBold;
PFont font22Ver;
PFont font25Ver;
PFont font25VerBold;

////////////////////////////////  Valores para la velocidad  ////////////////////////////////////////
float vmo = 272;        
float vne = 252;        
float vman = 161;        
float greendot = 130;  
float vf = 128;         
float stallFspeed = 63;  
float stallspeed = 82; 
float althi = 0;        
int   althi1 = 0;        
float altlo = 0;        
float calc1 = 0;        
float calc2 = 0;        
float calc3 = 0;        
float calc4 = 0;       
float calc5 = 0;        

float vario;        

float accel = 0;        
float deltaspeed = 0;   
float vmo4 =-(vmo*4);   
/////////////////////////////////// Fin Velocidad  //////////////////////////////////////////////////

////////////////////////////////  Colores  ////////////////////////////////////////
color colorRojo = color(255, 0, 0);
color colorNaranja = color(255, 127, 0);
color colorVerde = color(70, 140, 0);
color colorVerdeFosfo = color(0, 247, 0);
color colorBlanco = color(255);
color colorNegro = color(0);
color colorNegroTransp = color(0, 0, 0, 127);
color colorTransp1 = color(0, 0, 0, 1);
color colorTransp2 = color(255, 255, 255, 1);
color colorGris = color(83, 83, 98);
color colorGrisClaro = color(153, 153, 153);
color colorAzul = color(41, 128, 185);
color colorAzulCielo = color(0, 243, 235);
color colorAzulHorizonte = color(57, 117, 187);
color colorVioleta = color(102, 0, 153);
color colorAmarillo = color(255, 217, 15);
color colorMarron = color(81, 66, 52);
color colorMarronHorizonte = color(80, 50, 20);
color colorFucsia = color(255,0,255);//el del garmin usa 253...

color colorMaterialGrisOscuro = color(51, 51, 51);
color colorBotonActivo = color(72, 175, 242);
color colorBotonInactivo = color(23, 102, 166);

color colorAzulBotonNoSelec = color(0, 102, 204);

color c1 = color(255, 255); //blanco transparente
color c2 = color(255, 0); //blanco solido
/////////////////////////////////// Fin Colores  //////////////////////////////////////////////////


int idPantalla;
color colorTextoFormas = colorBlanco;
boolean textoNegrita = false;

/////////////////////////////////// Altimetro  //////////////////////////////////////////////////
Altimeter myAltimeter0;
float vLimit = 400; //Velocidad limite en km/h
int altLimit = 40000; //Altitud limite en pies
/////////////////////////////////// Fin Altimetro  //////////////////////////////////////////////////

float vsiLimit = 2000; //es 1500 para la version 480x230


ArtificialHorizon myArtificialHorizon;

/////////////////////////////////// gps  //////////////////////////////////////////////////
Gps myGPS;
// GPS Variables
//float hdop, lat, longt, cog, sog, gpsalt, gpschars, gpsdbl, gpsnum;
float gpsdbl, gpsnum;
long oldTime;
/////////////////////////////////// Fin gps  //////////////////////////////////////////////////

//Opciones myOpciones;
boolean mostrarOpciones = false;

Bola myBola;

Qnh myQnh;

Temp myTemp;

GSpeed myGSpeed;

VerticalSpeed myVerticalSpeed0;

TopLbar myTopLbar;
TopRbar myTopRbar;

Fecha myFecha;

Anemometro myAnemometro;

//Brujula myBrujula;
Rumbo myRumbo;

//unidades de cada elemento
float unidadesVel;//1.0 Kmh //2.0 Kt      // 3.0 Mph
float unidadesAlt;//1.0 m   //2.0 ft
float unidadesVsi;//1.0 m/s //2.0 ft/min
float unidadesQnh;//1.0 mmHg//2.0 inHg
float unidadesTemp;//1.0 mmHg//2.0 inHg

String unidadesVelocidad = "";
String unidadesALT = "";
String unidadesVSI = "";
String unidadesQNH = "";
String unidadesTEMP = "";

String otrasUdVel1 = "";
String otrasUdVel2 = "";
String otrasUdALT1 = "";
String otrasUdVSI1 = "";
String otrasUdQNH1 = "";
String otrasUdTEMP1 = "";

boolean mostrarOtrasUni;
OtrasUnidades myOtrasUnidades;

int otrasIAS1 = 0;
int otrasIAS2 = 0;
int otrasGS1 = 0;
int otrasGS2 = 0;
int otrasALT1 = 0;
float otrasVSI1 = 0.0;
String otrasQNH1 = "";//inhg
int otrasQNH2 = 0;//mmhg
int otrasTEMP1 = 0;//
int otrasTEMP2 = 0;//

//boolean[] listaOnOff; //Lista con los estados de los elementos, visibles o no visibles.

int pinMenu;

//////////////////////////////////////////////// INICIO Lo que viene de ARDUINO
Serial puerto10DofOatIas;

final String serialPort = "COM10";//"COM13";//"/dev/ttyUSB0" verificar esto con ls /dev/tty*

Serial puerto10DofOatIas2;

final String serialPort2 = "COM12";//"COM10";//"/dev/ttyUSB1" verificar esto con ls /dev/tty*

int BaudRate = 115200;
int HAS_GPS = 0;

boolean saltePrimera = false;

// These are needed for the moving average calculation
float[] data = new float[32];
float total = 0, average = 0;
int p = 0, n = 0;

//LPF
float filterFactor = 0.05;
float heading_f = 0.;

//Moving average Heading
float corr_heading;
float heading_avg;
float heading = 0;
float oldHeading = 0.0;
int windSize = 96;

//set motiondetect types
float motionDetect;

float [] q = new float [4];
float [] acc = new float [3];
float [] gyro = new float [3];
float [] magn = new float [3];
float [] ypr = new float [3];
float temp; float press; float altitude; 
float dt;
float tnew;
float told = 0;

// Altitude - Accel Complimentary filter setup
float[] dyn_acc = new float[3];
float fused_alt;

// GPS Variables
float hdop, lat, longt, cog, sog, gpsalt, gpschars;
float hdop_val, loc_val, gpsalt_val, sog_val, cog_val;
float satNum = 0;
float satHH = 12.0;
float satMM = 00.0;
float satSS = 00.0;
final int MAX_SATELLITES = 40;
float satsElevation[] = new float[40];
float satsAzimuth[] = new float[40];
float satsSignals[] = new float[40];

float angx, angy, angz, angyLevelControl;

float S;
float A;

float sea_press = 1013.25;           //Input local sea level pressure
String seapresscmd = "99";
float declinationAngle = 2.5;
float STATIONALTFT = 385.;           //adjust altitude here
float SEA_PRESS  = 1013.25;          //default sea level pressure level in mb
float KNOWNALT   = 65.0;             //default known altitude, 
float INHG       = 0.02952998751;    //convert mb to in/Hg constant
float MB         = 33.8638815;       //convert in/Hg to mb constant
float FTMETERS   = 0.3048;
float METERS2FT  = 3.2808399;
float KMH2KT     = 0.539957;
float PI         = 3.14159;
float deg2rads   = PI/180;
float rad2degs    = 180/PI;

float [] hq = null;
float [] Euler = new float [3]; // psi, theta, phi

int lf = 10;
byte[] inBuffer = new byte[22];

//---------------------------------------------------
float Sample_X;
float Sample_Y; 
float Sample_Z; 
long [] Sensor_Data = new long [8]; 
short countx; short county ;

float [] accelerationx = new float [2];
float [] accelerationy = new float [2];
float [] accelerationz = new float [3];
float [] velocityx = new float [2];
float [] velocityy = new float [2];
float [] velocityz = new float [2];
float [] positionX = new float [2]; 
float [] positionY = new float [2]; 
float [] positionZ = new float [2]; 
float dts;
float statex,statey, statez;
float statex_avg, statey_avg,statez_avg;
float motionDetect_transition, motionDetect_old;
int state_cnt;
float tempxxx = 0;
  
//-------------------------------------
//

final int burst = 32;
int count = 0;

String skpath;

void myDelay(int time) {
    try {
        Thread.sleep(time);
    } catch (InterruptedException e) { }
}

float presionQNH = -9999.0;
float valorVSI = -9999.0;
float ias = -9999.0;
float tas = -9999.0;
float gForce = -9999.0;
float tempOAT = -9999.0;
//////////////////////////////////////////////// FIN Lo que viene de ARDUINO

boolean isactivoVEL;
boolean isactivoALT;
boolean isactivo10DOF;
boolean isactivoGPS;
boolean isactivoTEMP;
boolean valoresDemo;
int now = millis();// para los parpadeos, para todos!
boolean cambiar = false;//flag para los parpadeos, para todos!
int delay = 500;//delay para los parpadeos, para todos!

PImage uranoImageCompleto;

PImage Top, Bottom;

// Instrument settings
float size = 38.0;
int tabHeight=80; // Extra height needed for Tabs
int xGraph      = 100;        int yGraph      = 180+tabHeight;
int xObj        = 520;        int yObj        = 293+tabHeight;
int xCompass    = 920;        int yCompass    = 341+tabHeight;
int xLevelObj   = 920;        int yLevelObj   = 80+tabHeight; 
int xGPS        = 853;        int yGPS        = 438+tabHeight;

int horizonInstrSize, GPS_distanceToHome, GPS_directionToHome,
    GPS_numSat, GPS_fix, GPS_update, GPS_altitude, GPS_speed,
    GPS_latitude, GPS_longitude, graph_on;

float [] angles = new float [3];

float heading1 = 0;

boolean showDebugValues = false;
float maxPitchUp, maxPitchDn, maxRollLft, maxRollRgt = 0.0;

void setup(){
    textureMode(NORMAL);
    Top = loadImage("Top.png");
    Bottom = loadImage("Bottom.png");
    loadTextureAttitudeIndicator();
    //fullScreen();//Esto lo pondremos cuando la aplicación se lance sola al iniciar la Raspberry Pi,
    //size(480, 320);//vamos a probar sin el p2d a ver que pasa
    size(800, 480);
    //size(1024, 600, P2D);
    //noCursor();
    smooth();
    strokeCap(SQUARE);
    //horizonte = createGraphics(480, 320, JAVA2D);
    //hdg = createGraphics (300, 50, JAVA2D);
    //topLbar = createGraphics (80, 50, JAVA2D);
    //topRbar = createGraphics (100, 50, JAVA2D);
    //spd = createGraphics(80, 220, JAVA2D);
    //alt = createGraphics(100, 220, P2D);
    //vsi = createGraphics(100, 220, JAVA2D);

    font9Ver = loadFont("data/fonts/Verdana-9.vlw");
    font10Ver = loadFont("data/fonts/Verdana-10.vlw");
    font11Ver = loadFont("data/fonts/Verdana-11.vlw");
    font12Ver = loadFont("data/fonts/Verdana-12.vlw");
    font13Ver = loadFont("data/fonts/Verdana-13.vlw");
    font14Ver = loadFont("data/fonts/Verdana-14.vlw");
    font15Ver = loadFont("data/fonts/Verdana-15.vlw");
    font16Ver = loadFont("data/fonts/Verdana-16.vlw");
    font18Ver = loadFont("data/fonts/Verdana-18.vlw");
    font20Ver = loadFont("data/fonts/Verdana-20.vlw");
    font22Ver = loadFont("data/fonts/Verdana-22.vlw");
    font25Ver = loadFont("data/fonts/Verdana-25.vlw");
    font14VerBold = loadFont("data/fonts/Verdana-Bold-14.vlw");
    font16VerBold = loadFont("data/fonts/Verdana-Bold-16.vlw");
    font18VerBold = loadFont("data/fonts/Verdana-Bold-18.vlw");
    font20VerBold = loadFont("data/fonts/Verdana-Bold-20.vlw");
    font25VerBold = loadFont("data/fonts/Verdana-Bold-25.vlw");

    idPantalla = 1;

    mostrarOtrasUni = false;

    activeGroup = 1;

    uranoImageCompleto = loadImage("data/images/UranoCompleto2.png");

    cargarPreferencias();
    cargarInfoSistema();
    cargarIdiomaInicial();
    opciones();

    

    myArtificialHorizon = new ArtificialHorizon();

    myAltimeter0 = new Altimeter();

    myGPS = new Gps(); 
    //lat = 42.083384;
    //longt = -8.500278; 
    //cog = heading+2.1;
    //sog = airspeed; 
    //gpsalt = altin;
    gpsdbl = 0.0;
    gpsnum = 5.0;

    //myOpciones = new Opciones();

    myBola = new Bola();

    myQnh = new Qnh();

    myTemp = new Temp();

    myGSpeed = new GSpeed();

    myTopLbar = new TopLbar();
    myTopRbar = new TopRbar();

    myFecha = new Fecha();

    myVerticalSpeed0 = new VerticalSpeed();

    myAnemometro = new Anemometro();

    //myBrujula = new Brujula();
    myRumbo = new Rumbo();

    

    myOtrasUnidades = new OtrasUnidades();
    
    
    cargarUnidades();
    
    //cargarVelocidades();
/*
    pinMenu = 6;
    GPIO.pinMode(pinMenu, GPIO.INPUT);
    GPIO.attachInterrupt(pinMenu, this, "pinMenuInterrupt", GPIO.RISING);
*/

    reader = createReader("data/efis-hours");
    try {
        efisTimeString = reader.readLine();
        efisTimeCargadoLong = Long.parseLong(efisTimeString);
    } catch (Exception e) {
        e.printStackTrace();
        efisTimeString = null;
    }

    

    printArray(Serial.list());

    

    try {
        //serial port set up
        puerto10DofOatIas = new Serial(this, serialPort, BaudRate);

        println("Waiting IMU...");

        myDelay(2000);
        
        //while (puerto10DofOatIas.available() == 0) {
        //    println(puerto10DofOatIas.available());
        //    puerto10DofOatIas.write("v");
        //    myDelay(1000);
        //}
        
        //puerto10DofOatIas.write("z");
        //puerto10DofOatIas.bufferUntil('\n');
        valoresDemo = false;
        isactivoVEL = true;
        isactivoALT = true;
        isactivo10DOF = true;
        isactivoGPS = true;
        isactivoTEMP = true;
    } catch (Exception e) {
        println("No hay serial pero sigo adelante!!");
        println("Espero a que se conecte el cable y pongo avisos!!");
        valoresDemo = false;
        isactivoVEL = false;
        isactivoALT = false;
        isactivo10DOF = false;
        isactivoGPS = false;
        isactivoTEMP = false;
    }


    try {
        //serial port set up
        puerto10DofOatIas2 = new Serial(this, serialPort2, BaudRate);

        println("Waiting IMU...");

        myDelay(2000);
        
        while (puerto10DofOatIas2.available() == 0) {
            println(puerto10DofOatIas2.available());
            puerto10DofOatIas2.write("v");
            myDelay(1000);
        }
        
        puerto10DofOatIas2.write("z");
        puerto10DofOatIas2.bufferUntil('\n');
    } catch (Exception e) {
        println("No hay serial pero sigo adelante 2!!");
        println("Espero a que se conecte el cable y pongo avisos 2!!");
    }
/*
    try {
        //serial port set up
        puerto10DofOatIas = new Serial(this, serialPort, BaudRate);

        println("Waiting IMU...");

        myDelay(2000);
        
        while (puerto10DofOatIas.available() == 0) {
            println(puerto10DofOatIas.available());
            puerto10DofOatIas.write("v");
            myDelay(1000);
        }
        
        puerto10DofOatIas.write("z");
        puerto10DofOatIas.bufferUntil('\n');
        valoresDemo = false;
        isactivoVEL = true;
        isactivoALT = true;
        isactivo10DOF = true;
        isactivoGPS = true;
        isactivoTEMP = true;
    } catch (Exception e) {
        println("No hay serial pero sigo adelante!!");
        println("Espero a que se conecte el cable y pongo avisos!!");
        valoresDemo = false;
        isactivoVEL = false;
        isactivoALT = false;
        isactivo10DOF = false;
        isactivoGPS = false;
        isactivoTEMP = false;
    }
*/
    /*
    try { 
        robot = new Robot();
    } catch (AWTException e) {
        e.printStackTrace();
        exit();
    }
    */
}

void draw() {
    background(colorNegro);

    //comprobarConexionSerial();

    comprobarValoresSensores();
    
    imuData();
    actualizarValores();//no se si habra que quitarlo despues cuando esten los sensores...estos son de prueba
    
    tiempoEfisEncendido();

    unidades2string();
    
    drawHorizonte();
    
    switch (idPantalla) {
        case 1:
            drawSeleccionado();
            if (mostrarOtrasUni) {
                drawOtrasUnidades();
            }
            break;
        case 2:
            drawGPS();
            break;
        case 3:
            //drawSeleccionado();
            noStroke();
            fill(colorMaterialGrisOscuro);
            rect(0, 0, width, height);
            break;
        default :
            drawSeleccionado();
            break;    
    }

    if (valoresDemo) {
        dibujarAviso();
    }

    //yprDebug();
    //text(nfs(tempOAT, 2, 1), 90, 90);
    //text(nfs(hdop, 2, 1), 90, 90);
    //drawCube();

    maxPitchRoll();
    if (showDebugValues) {
        debugValuesWindow();
    }

    //portada();
}

public void pinMenuInterrupt(int pinMenu) {
    idPantalla++;
    if (idPantalla == 3) {
        groupMenu.setVisible(true);
        if (activeGroup == 1) {
            groupUnidades.setVisible(true);
            groupVisual.setVisible(false);
            groupInfo.setVisible(false);
            groupConfig.setVisible(false);
            groupPower.setVisible(false);
        } else if (activeGroup == 2) {
            groupUnidades.setVisible(false);
            groupVisual.setVisible(true);
            groupInfo.setVisible(false);
            groupConfig.setVisible(false);
            groupPower.setVisible(false);
        } else if (activeGroup == 3) {
            groupUnidades.setVisible(false);
            groupVisual.setVisible(false);
            groupInfo.setVisible(true);
            groupConfig.setVisible(false);
            groupPower.setVisible(false);
        } else if (activeGroup == 4) {
            groupUnidades.setVisible(false);
            groupVisual.setVisible(false);
            groupInfo.setVisible(false);
            groupConfig.setVisible(true);
            groupPower.setVisible(false);
        } else {
            groupUnidades.setVisible(false);
            groupVisual.setVisible(false);
            groupInfo.setVisible(false);
            groupConfig.setVisible(false);
            groupPower.setVisible(true);
        }
    } else if ((idPantalla == 1) || (idPantalla == 2)) {
        groupMenu.setVisible(false);
    } else if (idPantalla > 3) {
        idPantalla = 1;
        groupMenu.setVisible(false);
    }
    println("idPantalla: " + idPantalla);
}

public void drawSeleccionado() {
    if (toggleBola.isSelected()) {
        drawBola();
    }
    if (toggleIAS.isSelected()) {
        drawVelocidad();
    }
    if (toggleRumbo.isSelected()) {
        drawRumbo();
    }
    if (toggleFecha.isSelected()) {
        drawFecha();
    }
    if (toggleAlt.isSelected()) {
        drawAltitud();
    }
    if (toggleVario.isSelected()) {
        drawVSI();
    }
    if (toggleGS.isSelected()) {
        drawGSpeed();
    }
    if (toggleQNH.isSelected()) {
        drawQnh();
    }
    if (toggleTemp.isSelected()) {
        drawTemp();
    }
}


void comprobarValoresSensores(){
    if (isTempCorrect()) {
        isactivoTEMP = true;
    } else {
        isactivoTEMP = false;
    }

    if (isGPSCorrect()) {
        isactivoGPS = true;
    } else {
        isactivoGPS = false;
    } 
}


void imuData(){
    quaternionToEuler(q, Euler);//Esto lo agrego 4/12/18
    fused_alt = altitude + STATIONALTFT/METERS2FT;

    textFont(font16Ver, 16);

    //getYawPitchRollRad();
    //getYawPitchRoll180();
    //getEuler360degAttitude();//este es el ultimo que tenia a marzo de 2020, sin el wit motion
    //fill(#00CF00);

    angx = ypr[2];
    angy = ypr[1];

    //addItemsToHistoryBuffers(radians(heading));//originalmente funcionaba
    addItemsToHistoryBuffers(radians(angles[0]));
    calculateMathematicalAverageOfHistory();
    calculateYamartinoAverageOfHistory(); 

    corr_heading = degrees(yamartinoAverageAngle);

    position();
}

void actualizarValores(){

    //lat = 42.083384;
    //longt = -8.500278; 
    //cog = heading+2.1;
    //sog = airspeed+2; 
    //gpsalt = altin;
    //gpsdbl = 45.5;
    //gpsnum = 20.0;

    if (valoresDemo) {
        q[0] = 1.0;
        q[1] = 0.0;
        q[2] = 0.0;
        q[3] = 0.0;
        acc[0] = 0.0;
        acc[1] = 0.0;
        acc[2] = 9.8;
        gyro[0] = 0.0;
        gyro[1] = 0.0;
        gyro[2] = 0.0;
        temp = 25.0;
        heading = 0.0;
        altitude = 22312;
        presionQNH = 1013.25;
        valorVSI = 1400.0;
        gForce = 1.0;
        ias = 270.0;
        tempOAT = 48.0;
        lat = 42.083384;
        longt = -8.500278; 
        cog = heading+2.1;
        sog = ias+2; 
        gpsalt = altitude/3.28084;
        gpsdbl = 45.5;
        gpsnum = 20.0;
        satsAzimuth[0] = 315;
        satsElevation[0] = 0;
        satsSignals[0] = 10;
    }
}

void drawOtrasUnidades(){
    myOtrasUnidades.draw();
}

float decodeFloat(String inString) {
    byte [] inData = new byte[4];
  
    if(inString.length() == 8) {
        inData[0] = (byte) unhex(inString.substring(0, 2));
        inData[1] = (byte) unhex(inString.substring(2, 4));
        inData[2] = (byte) unhex(inString.substring(4, 6));
        inData[3] = (byte) unhex(inString.substring(6, 8));
    }
      
    int intbits = (inData[3] << 24) | ((inData[2] & 0xff) << 16) | ((inData[1] & 0xff) << 8) | (inData[0] & 0xff);
    
    return Float.intBitsToFloat(intbits);
}
/*
void serialEvent(Serial p) {
    if (p == puerto10DofOatIas) {
        if(puerto10DofOatIas.available() >= 18) {
            String inputString = puerto10DofOatIas.readStringUntil('\n');
            if (inputString != null && inputString.length() > 0) {
                String [] inputStringArr = split(inputString, ",");
                if(inputStringArr.length >= 18) {
                    q[0] = decodeFloat(inputStringArr[0]);
                    q[1] = decodeFloat(inputStringArr[1]);
                    q[2] = decodeFloat(inputStringArr[2]);
                    q[3] = decodeFloat(inputStringArr[3]);
                    acc[0] = decodeFloat(inputStringArr[4]);
                    acc[1] = decodeFloat(inputStringArr[5]);
                    acc[2] = decodeFloat(inputStringArr[6]);
                    gyro[0] = decodeFloat(inputStringArr[7]);
                    gyro[1] = decodeFloat(inputStringArr[8]);
                    gyro[2] = decodeFloat(inputStringArr[9]);
                    magn[0] = decodeFloat(inputStringArr[10]);
                    magn[1] = decodeFloat(inputStringArr[11]);      
                    magn[2] = decodeFloat(inputStringArr[12]);
                    temp = decodeFloat(inputStringArr[13]);
                    press = decodeFloat(inputStringArr[14]);
                    dt = (1./decodeFloat(inputStringArr[15]));
                    heading = decodeFloat(inputStringArr[16]);
                    if(heading < -9990) {
                        heading = 0;
                    }
                    altitude = decodeFloat(inputStringArr[17]);
                    motionDetect = decodeFloat(inputStringArr[18]);
                    presionQNH = decodeFloat(inputStringArr[20]);
                    valorVSI = decodeFloat(inputStringArr[21]);
                    gForce = decodeFloat(inputStringArr[22]);
                    ias = decodeFloat(inputStringArr[23]);
                    tas = calcularTAS();
                    //valorPruebaSerial = ias;
                    if (decodeFloat(inputStringArr[24]) != 99.99) {//no se por que a veces falla la lectura de la temperatura, debe ser por lo que tarda en leerse el sensor, y no le tengo gestionado
                        tempOAT = decodeFloat(inputStringArr[24]);
                    } else {
                        tempOAT = tempOAT;
                    }
                    
                    //angles[0] = decodeFloat(inputStringArr[25]);
                    //angles[1] = decodeFloat(inputStringArr[26]);
                    //angles[2] = decodeFloat(inputStringArr[27]);
                    
                    //read GPS
                    if(HAS_GPS == 1){
                        hdop = decodeFloat(inputStringArr[25]);
                        hdop_val = decodeFloat(inputStringArr[26]);
                        lat = decodeFloat(inputStringArr[27]);
                        longt = decodeFloat(inputStringArr[28]);
                        loc_val = decodeFloat(inputStringArr[29]);
                        gpsalt = decodeFloat(inputStringArr[30]);
                        gpsalt_val = decodeFloat(inputStringArr[31]);
                        cog = decodeFloat(inputStringArr[32]);
                        cog_val = decodeFloat(inputStringArr[33]);
                        sog = decodeFloat(inputStringArr[34]);
                        sog_val = decodeFloat(inputStringArr[35]);
                        gpschars = decodeFloat(inputStringArr[36]);
                        gpsnum = decodeFloat(inputStringArr[37]);
                        
                        satHH = decodeFloat(inputStringArr[38]);
                        satMM = decodeFloat(inputStringArr[39]);
                        satSS = decodeFloat(inputStringArr[40]);
                        for (int i = 0; i < MAX_SATELLITES; ++i) {
                            satsElevation[i] = decodeFloat(inputStringArr[40+i]);
                            //println("satsElevation: "+satsElevation[i]);
                        }
                        for (int i = 0; i < MAX_SATELLITES; ++i) {
                            satsAzimuth[i] = decodeFloat(inputStringArr[80+i]);
                            //println("satsAzimuth: "+satsAzimuth[i]);
                        }
                        for (int i = 0; i < MAX_SATELLITES; ++i) {
                            satsSignals[i] = decodeFloat(inputStringArr[120+i]);
                            //println("satsSignals: "+satsSignals[i]);
                        }
                        
                        //println("satHH: "+satHH);
                        //println("satMM: "+satMM);
                        //println("satSS: "+satSS);
                    }        
                }
            }

            count = count + 1;
            
            if(burst == count) {
                myDelay(100);
                puerto10DofOatIas.write("a");
                count = 0;
            }
        }
    }
}
*/
void comprobarConexionSerial(){
    if (puerto10DofOatIas == null) {
        try {
            puerto10DofOatIas.stop();
            puerto10DofOatIas = null;
            puerto10DofOatIas = new Serial(this, serialPort, BaudRate);
            println("reconectando...");
        } catch (RuntimeException e) {
            println("port in use, trying again later...");
        }
    }
}

void serialEvent(Serial p) {
/*    
    if (p == puerto10DofOatIas) {    
        if(p.available() > 0) {
            String inputString = p.readStringUntil('\n');
            if (inputString != null && inputString.length() > 0 && saltePrimera) {
                String [] inputStringArr = split(inputString, "\t");
                if((inputStringArr.length >= 4) && (inputStringArr[0].equals("Quat:"))) { // q1,q2,q3,q4,\r\n so we have 5 elements ???
                    q[0] = Float.parseFloat(inputStringArr[1]);
                    q[1] = Float.parseFloat(inputStringArr[2]);
                    q[2] = Float.parseFloat(inputStringArr[3]);
                    q[3] = Float.parseFloat(inputStringArr[4]);
                    heading = Float.parseFloat(inputStringArr[5]);
                    //heading += 90; //Porque el sensor esta "girado" // lo quito porque quiero corregirlo en el propio arduino
                    acc[1] = Float.parseFloat(inputStringArr[6]);
                }
            }
            saltePrimera = true;
        }
    }
*/
    
    if (p == puerto10DofOatIas) {    
        if(p.available() > 0) {
            String inputString = p.readStringUntil('\n');
            if (inputString != null && inputString.length() > 0 && saltePrimera) {
                String [] inputStringArr = split(inputString, " ");
                if((inputStringArr.length >= 4) && (inputStringArr[0].equals("Orientation:"))) { // q1,q2,q3,q4,\r\n so we have 5 elements ???
                    heading = float(inputStringArr[3]) - 90.0; // convert to float yaw // los 90 son para alinear rumbo y pitch ya que deben de estar mal puestos en la placa
                    if( heading < 0 ) heading += 360.0; // convert negative to positive angles
                    angles[1] = float(inputStringArr[2]); // convert to float pitch
                    angles[2] = float(inputStringArr[1]); // convert to float roll
                    
                    acc[1] = float(inputStringArr[4]);
                    
                    //presionQNH = float(inputStringArr[5]); // convert to float press (press)
                    //altitude = float(inputStringArr[6]); // convert to float alt (altitude)//esperamos ft
                    //valorVSI = float(inputStringArr[7]); // convert to float alt (altitude)//esperamos ft/min

                }
            }
            saltePrimera = true;
        }
    }

    if (p == puerto10DofOatIas2) {
        if(puerto10DofOatIas2.available() >= 18) {
            String inputString = puerto10DofOatIas2.readStringUntil('\n');
            if (inputString != null && inputString.length() > 0) {
                String [] inputStringArr = split(inputString, ",");
                if(inputStringArr.length >= 18) {
                    float[] q1 = new float[4];
                    q1[0] = decodeFloat(inputStringArr[0]);
                    q1[1] = decodeFloat(inputStringArr[1]);
                    q1[2] = decodeFloat(inputStringArr[2]);
                    q1[3] = decodeFloat(inputStringArr[3]);
                    float[] acc1 = new float[3];
                    acc1[0] = decodeFloat(inputStringArr[4]);
                    acc1[1] = decodeFloat(inputStringArr[5]);
                    acc1[2] = decodeFloat(inputStringArr[6]);
                    float[] gyro1 = new float[3];
                    gyro1[0] = decodeFloat(inputStringArr[7]);
                    gyro1[1] = decodeFloat(inputStringArr[8]);
                    gyro1[2] = decodeFloat(inputStringArr[9]);
                    float[] magn1 = new float[3];
                    magn1[0] = decodeFloat(inputStringArr[10]);
                    magn1[1] = decodeFloat(inputStringArr[11]);      
                    magn1[2] = decodeFloat(inputStringArr[12]);
                    temp = decodeFloat(inputStringArr[13]);
                    press = decodeFloat(inputStringArr[14]);
                    dt = (1./decodeFloat(inputStringArr[15]));
                    heading1 = decodeFloat(inputStringArr[16]);
                    if(heading1 < -9990) {
                        heading1 = 0;
                    }
                    altitude = decodeFloat(inputStringArr[17]);
                    motionDetect = decodeFloat(inputStringArr[18]);
                    presionQNH = decodeFloat(inputStringArr[20]);
                    valorVSI = decodeFloat(inputStringArr[21]);
                    gForce = decodeFloat(inputStringArr[22]);
                    ias = decodeFloat(inputStringArr[23]);
                    tas = calcularTAS();
                    //valorPruebaSerial = ias;
                    if (decodeFloat(inputStringArr[24]) != 99.99) {//no se por que a veces falla la lectura de la temperatura, debe ser por lo que tarda en leerse el sensor, y no le tengo gestionado
                        tempOAT = decodeFloat(inputStringArr[24]);
                    } else {
                        tempOAT = tempOAT;
                    }
                    
                    //angles[0] = decodeFloat(inputStringArr[25]);
                    //angles[1] = decodeFloat(inputStringArr[26]);
                    //angles[2] = decodeFloat(inputStringArr[27]);
                    
                    //read GPS
                    if(HAS_GPS == 1){
                        hdop = decodeFloat(inputStringArr[25]);
                        hdop_val = decodeFloat(inputStringArr[26]);
                        lat = decodeFloat(inputStringArr[27]);
                        longt = decodeFloat(inputStringArr[28]);
                        loc_val = decodeFloat(inputStringArr[29]);
                        gpsalt = decodeFloat(inputStringArr[30]);
                        gpsalt_val = decodeFloat(inputStringArr[31]);
                        cog = decodeFloat(inputStringArr[32]);
                        cog_val = decodeFloat(inputStringArr[33]);
                        sog = decodeFloat(inputStringArr[34]);
                        sog_val = decodeFloat(inputStringArr[35]);
                        gpschars = decodeFloat(inputStringArr[36]);
                        gpsnum = decodeFloat(inputStringArr[37]);
                        
                        satHH = decodeFloat(inputStringArr[38]);
                        satMM = decodeFloat(inputStringArr[39]);
                        satSS = decodeFloat(inputStringArr[40]);
                        for (int i = 0; i < MAX_SATELLITES; ++i) {
                            satsElevation[i] = decodeFloat(inputStringArr[40+i]);
                            //println("satsElevation: "+satsElevation[i]);
                        }
                        for (int i = 0; i < MAX_SATELLITES; ++i) {
                            satsAzimuth[i] = decodeFloat(inputStringArr[80+i]);
                            //println("satsAzimuth: "+satsAzimuth[i]);
                        }
                        for (int i = 0; i < MAX_SATELLITES; ++i) {
                            satsSignals[i] = decodeFloat(inputStringArr[120+i]);
                            //println("satsSignals: "+satsSignals[i]);
                        }
                        
                        //println("satHH: "+satHH);
                        //println("satMM: "+satMM);
                        //println("satSS: "+satSS);
                    }        
                }
            }

            count = count + 1;
            
            if(burst == count) {
                myDelay(100);
                puerto10DofOatIas2.write("z");
                count = 0;
            }
        }
    }
}

void drawHorizonte(){
    //myArtificialHorizon.draw(angy, -angx); //pitch y -roll
    //myArtificialHorizon.draw(degrees(angy), degrees(-angx)); //pitch y -roll
    //myArtificialHorizon.draw(Euler[2], -Euler[1]); //pitch y -roll
    //myArtificialHorizon.draw(angles[1], -angles[2]); //pitch y -roll original
    myArtificialHorizon.draw(-angles[1], -angles[2]); //pitch y -roll pruebas
    //magnetron();
    //flci();
    //compass();
    //drawArtificialHorizon(370, 285);
    //movingHorizon();
}

void drawRumbo(){
    /*translate(myAnemometro.anemo.width, 0);  // top left corner of speed scale
    myBrujula.draw(corr_heading);
    myBrujula.drawRect(corr_heading);
    translate(-myAnemometro.anemo.width, 0);  // top left corner of speed scale*/
    
    //translate(0, 0);  // top left corner of speed scale
    //myRumbo.draw(corr_heading);
    //myRumbo.drawRect(corr_heading);
    //myRumbo.draw(angles[0]);
    //myRumbo.drawRect(angles[0]);
    myRumbo.draw(heading);
    myRumbo.drawRect(heading);
    //translate(0, 0);  // top left corner of speed scale
    
    /*
    hdg.smooth();
    hdg.beginDraw();
    hdg.pushMatrix();   // decalage heading
    hdg.translate(0, 0);  //  top left corner heading
    if (isactivo10DOF) {
        rumbo();
    } else {
        hdg.noStroke();
        hdg.fill(colorNegro);
        hdg.rect(0, 0, hdg.width, hdg.height);
        hdg.stroke(colorRojo);
        hdg.strokeWeight(2);
        hdg.line(0, 0, hdg.width, hdg.height);
        hdg.line(0, hdg.height, hdg.width, 0);
        hdg.fill(colorAmarillo);
        hdg.textFont(font14VerBold);
        hdg.textAlign(CENTER, CENTER);
        hdg.text("RUMBO", hdg.width/2, hdg.height/2);
    } 
    image(hdg, 80, 0, 300, 50);
    hdg.popMatrix();
    hdg.endDraw(); 
    */
}

void drawFecha(){
    
    translate(0, 0);  // top left corner of speed scale
    
    myFecha.drawHora();
    myFecha.drawFecha();

    translate(0, 0); //  top left corner
}

void drawVelocidad(){
    
    translate(0, 0);  // top left corner of speed scale
    myAnemometro.draw(ias);

    translate(0, 0); //  top left corner
    myTopLbar.draw();
}

void drawAltitud(){
    translate(width-120, 50);
    myAltimeter0.draw(fused_alt);
    myAltimeter0.drawRect(fused_alt);
    translate(-width+120, -50);

    translate(width-120, 0); 
    myTopRbar.draw(valorVSI);
    translate(-width+120, 0);
}

void drawVSI(){
    translate(width-100, 50);
    myVerticalSpeed0.draw(valorVSI);
    translate(-width+100, -50);

    translate(width-120, 0);
    myTopRbar.draw(valorVSI);
    translate(-width+120, 0);
}

void drawBola(){
    translate(width/2-60, height-30);
    myBola.draw();
    translate(-(width/2-60), -(height-30));
}

void drawQnh(){
    translate(width-120, height-50);
    myQnh.draw(presionQNH);
    translate(-(width-120), -(height-50));
}

void drawTemp(){
    translate(width/2-35, height-50-10);
    myTemp.draw(tempOAT);
    translate(-(width/2-35), -(height-50-10));
}

void drawGSpeed(){
    translate(0, height-50);
    myGSpeed.draw(sog);//es la velocidad que nos da el gps: sog
    translate(0, -(height-50));
}

void drawGPS(){
    myGPS.draw(sog, cog);
}

/*
void drawOpciones(){
    myOpciones.draw();
}
*/

boolean isTempCorrect(){
    if (tempOAT>-100 && tempOAT<100) {
        return true;
    } else {
        return false;
    }
}

boolean isGPSCorrect(){
    if (gpsnum > 4) {
        return true;
    } else {
        return false;
    }
}

void cargarUnidades(){
    if (opcionKmh.isSelected()) {
        unidadesVel = 1;
    } else if (opcionKt.isSelected()) {
        unidadesVel = 2;
    } else if (opcionMph.isSelected()) {
        unidadesVel = 3;
    }

    if (opcionMetros.isSelected()) {
        unidadesAlt = 1;
    } else if (opcionFt.isSelected()) {
        unidadesAlt = 2;
    }

    if (opcionMxS.isSelected()) {
        unidadesVsi = 1;
    } else if (opcionFtxMin.isSelected()) {
        unidadesVsi = 2;
    }

    if (opcionMMhg.isSelected()) {
        unidadesQnh = 1;
    } else if (opcionINhg.isSelected()) {
        unidadesQnh = 2;
    }

    if (opcionGc.isSelected()) {
        unidadesTemp = 1;
    } else if (opcionGf.isSelected()) {
        unidadesTemp = 2;
    }
}

void unidades2string(){
    cargarUnidades();

    if (unidadesVel == 1) {
        unidadesVelocidad = "Km/h";
        otrasUdVel1 = "Kt";
        otrasUdVel2 = "Mph";
        otrasIAS1 = round(ias/1.852);//(km/h)/conversion da kt
        otrasIAS2 = round(otrasIAS1*1.1507794480235);//kt*conversion da mph
        otrasGS1 = round(sog/1.852);//(km/h)/conversion da kt
        otrasGS2 = round(otrasGS1*1.1507794480235);//kt*conversion da mph
    } else if (unidadesVel == 2) {
        unidadesVelocidad = "Kt";
        otrasUdVel1 = "Km/h";
        otrasUdVel2 = "Mph";
        otrasIAS1 = round(ias);//(km/h) ya que la airspeed ya viene en km/h
        otrasIAS2 = round(otrasIAS1/1.852*1.1507794480235);//kt*conversion da mph
        otrasGS1 = round(sog);//(km/h) ya que la gs ya viene en km/h
        otrasGS2 = round(otrasGS1/1.852*1.1507794480235);//kt*conversion da mph
    } else {
        unidadesVelocidad = "Mph";
        otrasUdVel1 = "Km/h";
        otrasUdVel2 = "Kt";
        otrasIAS1 = round(ias);//(km/h) ya que la airspeed ya viene en km/h
        otrasIAS2 = round(ias/1.852);//(km/h)/conversion da Kt
        otrasGS1 = round(sog);//(km/h) ya que la gs ya viene en km/h
        otrasGS2 = round(sog/1.852);//(km/h)/conversion da Kt
    }

    if (unidadesAlt == 1) {
        unidadesALT = textMetrosUni;
        otrasUdALT1 = textPiesUni;
        otrasALT1 = round(fused_alt);//porque lo que me viene, altin, ya son pies
    } else {
        unidadesALT = textPiesUni;
        otrasUdALT1 = textMetrosUni;
        otrasALT1 = round(fused_alt / 3.2808399);
    }

    if (unidadesVsi == 1) {
        unidadesVSI = "m/s";
        otrasUdVSI1 = "ft/min";
        otrasVSI1 = valorVSI;//porque lo que viene ya esta en ft/min
    } else {
        unidadesVSI = "ft/min";
        otrasUdVSI1 = "m/s";
        otrasVSI1 = valorVSI * 0.00508;
    }

    if (unidadesQnh == 1) {
        unidadesQNH = "mmHg";
        otrasUdQNH1 = "inHg";
        otrasQNH1 = nf(presionQNH * 0.02952998751, 2, 2);//mmHg*conversion
    } else {
        unidadesQNH = "inHg";
        otrasUdQNH1 = "mmHg";
        otrasQNH2 = round(presionQNH);//porque el qnh nos viene en mmHg
    }

    if (unidadesTemp == 1) {
        unidadesTEMP = "ºC";
        otrasUdTEMP1 = "ºF";
        //otrasTEMP1 = nf(tempOAT*1.8+32, 2, 2);//conversion de C a F
    } else {
        unidadesTEMP = "ºF";
        otrasUdTEMP1 = "ºC";
        //otrasTEMP2 = round(tempOAT);//porque el qnh nos viene en ºC
    }
}

/*
void mouseWheel(MouseEvent event) {
    int e = event.getCount();
    e=e*5+(int)pow(5, abs(e))*e/(abs(e));
    altin+=e;
    altin=constrain(altin, 0, altLimit);
}
*/
/*
void mousePressed(){
    mostrarOpciones = !mostrarOpciones;
    if(cp5.getGroup("menu") != null) {
        cp5.getGroup("menu").setVisible(mostrarOpciones);
    }
}
*/
void keyPressed(){
    switch (key) {
         case '<':
            showDebugValues = !showDebugValues;
            break;
        case '0':
            maxPitchUp = 0.0;
            maxPitchDn = 0.0;
            maxRollRgt = 0.0;
            maxRollLft = 0.0;
            break;
        case 'b':
            colorTextoFormas = colorNegro;
            textoNegrita = true;
            break;
        case 'w':
            colorTextoFormas = colorBlanco;
            textoNegrita = false;
            break;
        case 'o':
            break;
        case '+':
            idPantalla++;
            if (idPantalla == 3) {
                groupMenu.setVisible(true);
                if (activeGroup == 1) {
                    groupUnidades.setVisible(true);
                    groupVisual.setVisible(false);
                    groupInfo.setVisible(false);
                    groupConfig.setVisible(false);
                    groupPower.setVisible(false);
                } else if (activeGroup == 2) {
                    groupUnidades.setVisible(false);
                    groupVisual.setVisible(true);
                    groupInfo.setVisible(false);
                    groupConfig.setVisible(false);
                    groupPower.setVisible(false);
                } else if (activeGroup == 3) {
                    groupUnidades.setVisible(false);
                    groupVisual.setVisible(false);
                    groupInfo.setVisible(true);
                    groupConfig.setVisible(false);
                    groupPower.setVisible(false);
                } else if (activeGroup == 4) {
                    groupUnidades.setVisible(false);
                    groupVisual.setVisible(false);
                    groupInfo.setVisible(false);
                    groupConfig.setVisible(true);
                    groupPower.setVisible(false);
                } else {
                    groupUnidades.setVisible(false);
                    groupVisual.setVisible(false);
                    groupInfo.setVisible(false);
                    groupConfig.setVisible(false);
                    groupPower.setVisible(true);
                }
            } else if ((idPantalla == 1) || (idPantalla == 2)) {
                groupMenu.setVisible(false);
            } else if (idPantalla > 3) {
                idPantalla = 1;
                groupMenu.setVisible(false);
            }
            println("idPantalla: " + idPantalla);
            break;
        case 'u':
            println(unidadesVel);
            println(unidadesAlt);
            println(unidadesVsi);
            println(unidadesQnh);
            println(unidadesTemp);
            println();
            break;
        case 'p':
            valoresDemo = !valoresDemo;
            isactivoVEL = !isactivoVEL;
            isactivoALT = !isactivoALT;
            isactivo10DOF = !isactivo10DOF;
            isactivoGPS = !isactivoGPS;
            isactivoTEMP = !isactivoTEMP;
            println("Valores de prueba puestos a:" + valoresDemo);
            break;
        case 'a':
            ias = 65.0;
            println(ias);
            break;
        case 's':
            ias = 120.0;
            println(ias);
            break;
        case 'd':
            ias = 260.0;
            println(ias);
            break;
        case 'f':
            ias = 285.0;
            println(ias);
            break;
        case 'g':
            ias = 80.0;
            println(ias);
            break;
        case 'h':
            ias = 220.0;
            println(ias);
            break;
        case 'j':
            ias = 0.0;
            println(ias);
            break;
        case 'k':
            ias = 340.0;
            println(ias);
            break;
        case 'm':
            println(tempOAT);
            break;
        case 'v':
            println("lat: " + lat);
            println("longt: " + longt);
            println("gpsalt: " + gpsalt);
            println("sog: " + sog);
            println("cog: " + cog);
            for (int i = 0; i < MAX_SATELLITES; ++i) {
                print(i+1);
                print(" ");
                print((int)satsAzimuth[i]);
                print(" ");
                print((int)satsElevation[i]);
                print(" ");
                print((int)satsSignals[i]);
                println();
            }
            break;
        case 'c' :
            try {
                puerto10DofOatIas.stop();
                puerto10DofOatIas = null;
                puerto10DofOatIas = new Serial(this, serialPort, BaudRate);
                println("reconectando...");
            } catch (RuntimeException e) {
                println("port in use, trying again later...");
            }
            break;
        case 'C' :
            try {
                puerto10DofOatIas2.stop();
                puerto10DofOatIas2 = null;
                puerto10DofOatIas2 = new Serial(this, serialPort2, BaudRate);
                println("reconectando...");
            } catch (RuntimeException e) {
                println("port in use, trying again later...");
            }
            break;
        case 'x' :
            println("Serial disponible? "+ puerto10DofOatIas);  
        case 'H' :
            println("Heading Placa: "+ heading1); 
        default :
            colorTextoFormas = colorBlanco;
            textoNegrita = false;
            break;   
    }
}


void reconect10DofOatIas(){
    try {
        puerto10DofOatIas.stop();
        puerto10DofOatIas = null;
            puerto10DofOatIas = new Serial(this, serialPort, BaudRate);
            println("reconectando...");
    } catch (RuntimeException e) {
        println("port in use, trying again later...");
    }
}

void mousePressed() {
    /*
    if ((idPantalla == 1) && (mouseX > 0) && (mouseX < 100) && (mouseY > height/2-50) && (mouseY < height/2+50)) {
        println("rectagulo spd tocado");
        fill(colorNegro);
        stroke(colorNegro);
        rect(10, height/2-50, 100, 100);
    }
    */
    if (idPantalla == 1) {
        println("mostrar otras unidades");
        mostrarOtrasUni = !mostrarOtrasUni;
    }
}

float calcularTAS(){
    float tas_local = 0.0;

    float delta = 0.0;

    delta = (288.15*press)/((tempOAT+273.15)*1013.25);
    
    if (delta > 0)
        tas_local = ias/sqrt(delta);

    return tas_local;
}

void getYawPitchRollRad() {
    float gx, gy, gz;
  
    gx = 2 * (q[1]*q[3] - q[0]*q[2]);
    gy = 2 * (q[0]*q[1] + q[2]*q[3]);
    gz = q[0]*q[0] - q[1]*q[1] - q[2]*q[2] + q[3]*q[3];
  
    ypr[0] = atan2(2 * q[1] * q[2] - 2 * q[0] * q[3], 2 * q[0]*q[0] + 2 * q[1] * q[1] - 1);
    ypr[1] = atan(gx / sqrt(gy*gy + gz*gz));
    ypr[2] = atan(gy / sqrt(gx*gx + gz*gz));
}

/**
 * Returns the yaw pitch and roll angles, respectively defined as the angles in degrees between
 * the Earth North and the IMU X axis (yaw), the Earth ground plane and the IMU X axis (pitch)
 * and the Earth ground plane and the IMU Y axis. 
 * 
 * Roll and Pitch angle ranges 0-360 degrees instead of 0-90 degrees
 * 
 * @note This is not an Euler representation: the rotations aren't consecutive rotations but only
 * angles from Earth and the IMU. For Euler representation Yaw, Pitch and Roll see FreeIMU::getEuler
 * 
 * @param ypr three floats array which will be populated by Yaw, Pitch and Roll angles in degrees
*/
void getYawPitchRoll180() {
    float gx, gy, gz;       // estimated gravity direction

    gx = 2 * (q[1]*q[3] - q[0]*q[2]);
    gy = 2 * (q[0]*q[1] + q[2]*q[3]);
    gz = q[0]*q[0] - q[1]*q[1] - q[2]*q[2] + q[3]*q[3];

    //calculating yaw
    ypr[0] = atan2(2 * q[1] * q[2] - 2 * q[0] * q[3], 2 * q[0]*q[0] + 2 * q[1] * q[1] - 1);   //este estaba comentado pero lo puse yo
    //ypr[0] = val[9];//este era el original
    //ypr[0] = gyro[2];//este lo use tomando las correspondencias
    if(ypr[0] > 180.) ypr[0] = ypr[0] - 360.;
    ypr[0] = ypr[0] * 0.0174532925;
    
    //calculating Pitch1
    //Serial.print(gx); Serial.print("       "); Serial.print(gz); Serial.print("       ");
    if(gx > 0 && gz > 0) {
        ypr[1] = atan(gx / sqrt(gy*gy + gz*gz));
    } else if(gx > 0 && gz <= 0) {
        ypr[1] = PI - atan(gx / sqrt(gy*gy + gz*gz));
    } else if(gx < 0 && gz < 0) {
        ypr[1] = (-PI - atan(gx / sqrt(gy*gy + gz*gz)));
    } else  {
        ypr[1] =  atan(gx / sqrt(gy*gy + gz*gz));
    }
    
    //Calculating Roll1
    ypr[2] = atan(gy / sqrt(gx*gx + gz*gz));
}


void gravityCompensateDynAcc() {
  float[] g = new float[3];
  
  // get expected direction of gravity in the sensor frame
  g[0] = 2 * (q[1] * q[3] - q[0] * q[2]);
  g[1] = 2 * (q[0] * q[1] + q[2] * q[3]);
  g[2] = q[0] * q[0] - q[1] * q[1] - q[2] * q[2] + q[3] * q[3];
  
  // compensate accelerometer readings with the expected direction of gravity
  dyn_acc[0] = acc[0] - g[0];
  dyn_acc[1] = acc[1] - g[1];
  dyn_acc[2] = acc[2] - g[2];
}

public void dibujarAviso() {
    noFill();
    noStroke();
    coloresParpadeo(255, 0, 0, 0, 0, 0);
    noStroke();
    rect(width/2-90, height/2-60, 180, 50);
    //texto con el valor
    fill(colorBlanco);
    textSize(13);
    textFont(font18Ver, 18);
    textAlign(CENTER, CENTER);
    text(textAvisoPruebas1, width/2, height/2-45);
    text(textAvisoPruebas2, width/2, height/2-25);
}

//Funcion que rellena con los colores de parpadeo pasados por parametro y un delay no bloqueante
void coloresParpadeo (int r1, int g1, int b1, int r2, int g2, int b2){
    if (millis() - now > delay) { 
            cambiar = !cambiar;//change flag
            now = millis();//reset counter
    }
    if (cambiar)
        fill(r1, g1, b1);
    else
        fill(r2, g2, b2);
}

void portada(){
    if (millis() < 15000) {
        fill(colorNegro);
        rect(0, 0, width, height);
        imageMode(CENTER);
        //image(uranoImage, width/2+136, height/2-56, 155, 155);
        image(uranoImageCompleto, width/2, height/2, 365, 153);
        imageMode(CORNER);
        fill(colorBlanco);
        textAlign(CENTER);
        if (millis() > 5000) {
            textSize(20);
            textFont(font20Ver, 22);
            text(textPortada1, width/2, height-90);
        }
        if (millis() > 10000) {
            textSize(20);
            textFont(font20Ver, 22);
            text(textPortada2, width/2, height-60);
            
            reconect10DofOatIas();
        }
        textAlign(CORNER); 
    }
}

void debugValuesWindow(){
    translate(100, 60);
    
    fill(colorNegro);
    rect(0, 0, 140, 260);
    fill(colorBlanco);
    text("Yaw: " + nfs(360-heading, 3, 2), 5, 20);
    text("Pitch: " + nfs(-angles[1], 3, 2), 5, 20*2);
    text("Roll: " + nfs(-angles[2], 3, 2), 5, 20*3);
    text("Alt(ft): " + nfs(altitude, 4, 2), 5, 20*4);
    text("Press: " + nfs(presionQNH, 3, 2), 5, 20*5);
    text("Acc Y: " + nfs(acc[1], 3, 2), 5, 20*6);

    text("Max Up: " + nfs(maxPitchUp, 3, 2), 5, 20*8);
    text("Max Dn: " + nfs(maxPitchDn, 3, 2), 5, 20*9);
    text("Max Lft: " + nfs(maxRollLft, 3, 2), 5, 20*10);
    text("Max Rgt: " + nfs(maxRollRgt, 3, 2), 5, 20*11);

    translate(-100, -60);
}

void maxPitchRoll(){
    if (maxPitchUp < -angles[1]) {
        maxPitchUp = -angles[1];
    }
    if (maxPitchDn > -angles[1]) {
        maxPitchDn = -angles[1];
    }

    if (maxRollLft < -angles[2]) {
        maxRollLft = -angles[2];
    }
    if (maxRollRgt > -angles[2]) {
        maxRollRgt = -angles[2];
    }
}

void yprDebug(){
    fill(colorNegro);
    rect(0, 0, 100, 200);
    fill(colorBlanco);
    
    text("Yd: " + nfs(degrees(ypr[0]), 2, 2), 20, 200/10);
    text("Pd: " + nfs(degrees(ypr[1]), 2, 2), 20, 200/10*2);
    text("Rd: " + nfs(degrees(ypr[2]), 2, 2), 20, 200/10*3);
    text("Y: " + nfs(angles[0], 2, 2), 20, 200/10*4);
    text("P: " + nfs(angles[1], 2, 2), 20, 200/10*5);
    text("R: " + nfs(angles[2], 2, 2), 20, 200/10*6);
    
}

void drawCube() {  
    pushMatrix();
    translate(width/2, height/2);
    scale(2,2,2);
    // a demonstration of the following is at 
    // http://www.varesano.net/blog/fabio/ahrs-sensor-fusion-orientation-filter-3d-graphical-rotating-cube
    rotateZ(-Euler[2]);
    rotateX(-Euler[1]);
    rotateY(-Euler[0]);
    
    buildBoxShape();
    
  popMatrix();
}

//////////////////////////////////////////////////////////////////////////
void buildBoxShape() {
  //box(60, 10, 40);
  //noStroke();
  
  //Z+ (to the drawing area)   FRONT
  beginShape(QUADS);
  fill(#00ff00);
  //texture(Top);
  vertex(-30, -5, 20);
  vertex(30, -5, 20);
  vertex(30, 5, 20);
  vertex(-30, 5, 20);
  endShape();
  
  beginShape(QUADS);  
  //Z-
  fill(#0000ff);
  vertex(-30, -5, -20);
  vertex(30, -5, -20);
  vertex(30, 5, -20);
  vertex(-30, 5, -20);
  endShape();
  
  beginShape(QUADS);  
  //X-
  fill(#ff0000);
  vertex(-30, -5, -20);
  vertex(-30, -5, 20);
  vertex(-30, 5, 20);
  vertex(-30, 5, -20);
  endShape();
  
  beginShape(QUADS);  
  //X+ RIGHT SIDE
  fill(#ffff00);
  vertex(30, -5, -20);
  vertex(30, -5, 20);
  vertex(30, 5, 20);
  vertex(30, 5, -20);
  endShape();
    
  beginShape(QUADS);  
  //Y-
  //fill(#ff00ff);
  texture(Top);
  vertex(-30, -5, -20, 0, 0);
  vertex(30, -5, -20, 1, 0);
  vertex(30, -5, 20, 1, 1);
  vertex(-30, -5, 20, 0, 1);
  endShape();
  
  beginShape(QUADS);  
  //Y+ Bottom
  //fill(#00ffff);
  texture(Bottom);
  vertex(-30, 5, -20, 0, 0);
  vertex(30, 5, -20, 1, 0);
  vertex(30, 5, 20, 1, 1);
  vertex(-30, 5, 20, 0, 1);
  endShape();
}

/////////////////////////////////////////////////////////////////////////////
// See Sebastian O.H. Madwick report 
// "An efficient orientation filter for inertial and intertial/magnetic sensor arrays" Chapter 2 Quaternion representation
void quaternionToEuler(float [] q, float [] euler) {
  euler[0] = atan2(2 * q[1] * q[2] - 2 * q[0] * q[3], 2 * q[0]*q[0] + 2 * q[1] * q[1] - 1); // psi
  euler[1] = -asin(2 * q[1] * q[3] + 2 * q[0] * q[2]); // theta
  euler[2] = atan2(2 * q[2] * q[3] - 2 * q[0] * q[1], 2 * q[0] * q[0] + 2 * q[3] * q[3] - 1); // phi
}

void getEuler360degAttitude() {
  float m11, m12, m21, m31, m32;
  float gx, gy, gz; // estimated gravity direction
  //float q[4]; // quaternion
  //float val[13];
  
  //getQ(q, val);
  
  gx = 2 * (q[1]*q[3] - q[0]*q[2]);
  gy = 2 * (q[0]*q[1] + q[2]*q[3]);
  gz = q[0]*q[0] - q[1]*q[1] - q[2]*q[2] + q[3]*q[3];
    
  m11 = 2.*(q[1]*q[2] + q[0]*q[3]);
  m12 = q[0]*q[0] + q[1]*q[1] - q[2]*q[2] - q[3]*q[3];
  m21 = -2.*(q[1]*q[3] - q[0]*q[2]);               
  m31 = 2.*(q[2]*q[3] + q[0]*q[1]);              
  m32 = q[0]*q[0] - q[1]*q[1] - q[2]*q[2] + q[3]*q[3];

  // find angles for rotations about X, Y, and Z axes
  angles[0] = -atan2( m11, m12 ) * 57.2957795;
  angles[1] = -asin( m21 ) * 57.2957795;
  angles[2] = -atan2( m31, m32 ) * 57.2957795;
    
  //    Gx  gy  gz
  //0-90    "+"     "+"
  //90-180  "+"     "-"
  //180-270 "-"     "-"
  //270-360 "-"     "+"
    
  if(gx >= 0 && gz < 0)
      angles[1] = 180. - angles[1];
     else if(gx < 0 && gz < 0)
       angles[1] = 180. - angles[1];
      else if(gx < 0 && gz >=0)
        angles[1] = 360. + angles[1];
        
  if(angles[0] < 0) angles[0] = 360. + angles[0];
  if(angles[2] < 0) angles[2] = 360. + angles[2];
  
  angles[0] = 360 - angles[0];
  
}
