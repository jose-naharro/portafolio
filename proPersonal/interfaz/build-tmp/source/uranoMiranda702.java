import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.*; 
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
import processing.serial.*; 

import org.openstreetmap.gui.jmapviewer.*; 
import org.openstreetmap.gui.jmapviewer.checkBoxTree.*; 
import org.openstreetmap.gui.jmapviewer.events.*; 
import org.openstreetmap.gui.jmapviewer.interfaces.*; 
import org.openstreetmap.gui.jmapviewer.tilesources.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class uranoMiranda702 extends PApplet {


//import java.time.*;
/*
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
*/














//import vsync.*;

//import processing.io.*;

//Robot robot;

float M_PI = 3.14159265359f;

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

/*
float pitch = 0;        // pitch en degres ,positif pour nose up
float roll = 0;         //  roll positif en virage a droite
float sideslip = 0;     // sideslip positif a droite, valeur a calibrer (bille)
float airspeed = 200;     //  vitesse en Km/H
float speed10s = 0;     // acceleration, vitesse qui sera horizonteeinte dans 10 secondes
float altin = 20000;        // altitude recue en pieds
float qnh = 1013.25 ;      // qnh recu
//float heading;        //  magnetic heading in degrees
float vertspeed;      //  vertical speed in ft/min 
float vertaff;        // affichage digital valeur v/s
*/

////////////////////////////////  Valores para la velocidad  ////////////////////////////////////////
float vmo = 272;        // Velocity max en palier
float vne = 252;        // vitesse a ne pas depasser
float vman = 161;         // vitesse de manoeuvre
float greendot = 130;  // Vitesse de meilleure finesse
float vf = 128;         // vitesse de deploiement des volets
float stallFspeed = 63;  // vitesse de decrochage avec volets
float stallspeed = 82; // vitesse de decrochage en lisse
float althi = 0;        //  partie gauche alt (millier de pieds)
int   althi1 = 0;        //  partie gauche alt au format integer, pour l affichage
float altlo = 0;        //  partie droite (dizaines) de l altitude
float calc1 = 0;        //  utilise pour calcul echelle altitude
float calc2 = 0;        //  utilise aussi pour calcul echelle altitude
float calc3 = 0;        // pour l affichage du cap
float calc4 = 0;        // dans l affichage des barres de cap
float calc5 = 0;        // dans l affichage des barres de cap

float vario;          // utilise pour l affichage du vario - aiguille v/s

float accel = 0;        //  variable utilisee uniquement dans le programme pour afficher la fleche
float deltaspeed = 0;   //  variable utilisee uniquement dans le programme pour afficher la fleche
float vmo4 =-(vmo*4);   //  pour calcul position vmo
/////////////////////////////////// Fin Velocidad  //////////////////////////////////////////////////

////////////////////////////////  Colores  ////////////////////////////////////////
int colorRojo = color(255, 0, 0);
int colorNaranja = color(255, 127, 0);
int colorVerde = color(70, 140, 0);
int colorVerdeFosfo = color(0, 247, 0);
int colorBlanco = color(255);
int colorNegro = color(0);
int colorNegroTransp = color(0, 0, 0, 127);
int colorTransp1 = color(0, 0, 0, 1);
int colorTransp2 = color(255, 255, 255, 1);
int colorGris = color(83, 83, 98);
int colorGrisClaro = color(153, 153, 153);
int colorAzul = color(41, 128, 185);
int colorAzulCielo = color(0, 243, 235);
int colorAzulHorizonte = color(57, 117, 187);
int colorVioleta = color(102, 0, 153);
int colorAmarillo = color(255, 217, 15);
int colorMarron = color(81, 66, 52);
int colorMarronHorizonte = color(80, 50, 20);
int colorFucsia = color(255,0,255);//el del garmin usa 253...

int colorMaterialGrisOscuro = color(51, 51, 51);
int colorBotonActivo = color(72, 175, 242);
int colorBotonInactivo = color(23, 102, 166);

int colorAzulBotonNoSelec = color(0, 102, 204);

int c1 = color(255, 255); //blanco transparente
int c2 = color(255, 0); //blanco solido
/////////////////////////////////// Fin Colores  //////////////////////////////////////////////////


int idPantalla;
int colorTextoFormas = colorBlanco;
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
float otrasVSI1 = 0.0f;
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
float filterFactor = 0.05f;
float heading_f = 0.f;

//Moving average Heading
float corr_heading;
float heading_avg;
float heading = 0;
float oldHeading = 0.0f;
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
float satHH = 12.0f;
float satMM = 00.0f;
float satSS = 00.0f;
final int MAX_SATELLITES = 40;
float satsElevation[] = new float[40];
float satsAzimuth[] = new float[40];
float satsSignals[] = new float[40];
/*Esto debe ser para el horizonte que traia...
// Sphere Variables
float R = 150;
int xDetail = 40;
int yDetail = 30;
float[] xGrid = new float[xDetail+1];
float[] yGrid = new float[yDetail+1];
float[][][] allPoints = new float[xDetail+1][yDetail+1][3];

// Rotation Variables
float camDistance = -90;
float rotationX = 100;
float rotationY = 170;
float velocityX = 0;
float velocityY = 0;

// Texture
PImage texmap, Aimage, cmpRng, cmpAP, attCentro, attRing;
*/
float angx, angy, angz, angyLevelControl;

float S;
float A;

float sea_press = 1013.25f;           //Input local sea level pressure
String seapresscmd = "99";
float declinationAngle = 2.5f;
float STATIONALTFT = 385.f;           //adjust altitude here
float SEA_PRESS  = 1013.25f;          //default sea level pressure level in mb
float KNOWNALT   = 65.0f;             //default known altitude, 
float INHG       = 0.02952998751f;    //convert mb to in/Hg constant
float MB         = 33.8638815f;       //convert in/Hg to mb constant
float FTMETERS   = 0.3048f;
float METERS2FT  = 3.2808399f;
float KMH2KT     = 0.539957f;
float PI         = 3.14159f;
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
/*Esto debe ser para diibujar imagenes que salian en su sketch
final int VIEW_SIZE_X = 1000, VIEW_SIZE_Y = 600;
int xCompass    = 865;        int yCompass    = 365;
int xLevelObj   = 723;        int yLevelObj   = 90+0;
*/
final int burst = 32;
int count = 0;

String skpath;

public void myDelay(int time) {
    try {
        Thread.sleep(time);
    } catch (InterruptedException e) { }
}

float presionQNH = -9999.0f;
float valorVSI = -9999.0f;
float ias = -9999.0f;
float tas = -9999.0f;
float gForce = -9999.0f;
float tempOAT = -9999.0f;
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
float size = 38.0f;
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
float maxPitchUp, maxPitchDn, maxRollLft, maxRollRgt = 0.0f;

public void setup(){
    textureMode(NORMAL);
    Top = loadImage("Top.png");
    Bottom = loadImage("Bottom.png");
    loadTextureAttitudeIndicator();
    //fullScreen();//Esto lo pondremos cuando la aplicación se lance sola al iniciar la Raspberry Pi,
    //size(480, 320);//vamos a probar sin el p2d a ver que pasa
    
    //size(1024, 600, P2D);
    //noCursor();
    
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
    gpsdbl = 0.0f;
    gpsnum = 5.0f;

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

public void draw() {
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


public void comprobarValoresSensores(){
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


public void imuData(){
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

public void actualizarValores(){

    //lat = 42.083384;
    //longt = -8.500278; 
    //cog = heading+2.1;
    //sog = airspeed+2; 
    //gpsalt = altin;
    //gpsdbl = 45.5;
    //gpsnum = 20.0;

    if (valoresDemo) {
        q[0] = 1.0f;
        q[1] = 0.0f;
        q[2] = 0.0f;
        q[3] = 0.0f;
        acc[0] = 0.0f;
        acc[1] = 0.0f;
        acc[2] = 9.8f;
        gyro[0] = 0.0f;
        gyro[1] = 0.0f;
        gyro[2] = 0.0f;
        temp = 25.0f;
        heading = 0.0f;
        altitude = 22312;
        presionQNH = 1013.25f;
        valorVSI = 1400.0f;
        gForce = 1.0f;
        ias = 270.0f;
        tempOAT = 48.0f;
        lat = 42.083384f;
        longt = -8.500278f; 
        cog = heading+2.1f;
        sog = ias+2; 
        gpsalt = altitude/3.28084f;
        gpsdbl = 45.5f;
        gpsnum = 20.0f;
        satsAzimuth[0] = 315;
        satsElevation[0] = 0;
        satsSignals[0] = 10;
    }
}

public void drawOtrasUnidades(){
    myOtrasUnidades.draw();
}

public float decodeFloat(String inString) {
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
public void comprobarConexionSerial(){
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

public void serialEvent(Serial p) {
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
                    heading = PApplet.parseFloat(inputStringArr[3]) - 90.0f; // convert to float yaw // los 90 son para alinear rumbo y pitch ya que deben de estar mal puestos en la placa
                    if( heading < 0 ) heading += 360.0f; // convert negative to positive angles
                    angles[1] = PApplet.parseFloat(inputStringArr[2]); // convert to float pitch
                    angles[2] = PApplet.parseFloat(inputStringArr[1]); // convert to float roll
                    
                    acc[1] = PApplet.parseFloat(inputStringArr[4]);
                    
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
                    dt = (1.f/decodeFloat(inputStringArr[15]));
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
                    if (decodeFloat(inputStringArr[24]) != 99.99f) {//no se por que a veces falla la lectura de la temperatura, debe ser por lo que tarda en leerse el sensor, y no le tengo gestionado
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

public void drawHorizonte(){
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

public void drawRumbo(){
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

public void drawFecha(){
    
    translate(0, 0);  // top left corner of speed scale
    
    myFecha.drawHora();
    myFecha.drawFecha();

    translate(0, 0); //  top left corner
}

public void drawVelocidad(){
    
    translate(0, 0);  // top left corner of speed scale
    myAnemometro.draw(ias);

    translate(0, 0); //  top left corner
    myTopLbar.draw();
}

public void drawAltitud(){
    translate(width-120, 50);
    myAltimeter0.draw(fused_alt);
    myAltimeter0.drawRect(fused_alt);
    translate(-width+120, -50);

    translate(width-120, 0); 
    myTopRbar.draw(valorVSI);
    translate(-width+120, 0);
}

public void drawVSI(){
    translate(width-100, 50);
    myVerticalSpeed0.draw(valorVSI);
    translate(-width+100, -50);

    translate(width-120, 0);
    myTopRbar.draw(valorVSI);
    translate(-width+120, 0);
}

public void drawBola(){
    translate(width/2-60, height-30);
    myBola.draw();
    translate(-(width/2-60), -(height-30));
}

public void drawQnh(){
    translate(width-120, height-50);
    myQnh.draw(presionQNH);
    translate(-(width-120), -(height-50));
}

public void drawTemp(){
    translate(width/2-35, height-50-10);
    myTemp.draw(tempOAT);
    translate(-(width/2-35), -(height-50-10));
}

public void drawGSpeed(){
    translate(0, height-50);
    myGSpeed.draw(sog);//es la velocidad que nos da el gps: sog
    translate(0, -(height-50));
}

public void drawGPS(){
    myGPS.draw(sog, cog);
}

/*
void drawOpciones(){
    myOpciones.draw();
}
*/

public boolean isTempCorrect(){
    if (tempOAT>-100 && tempOAT<100) {
        return true;
    } else {
        return false;
    }
}

public boolean isGPSCorrect(){
    if (gpsnum > 4) {
        return true;
    } else {
        return false;
    }
}

public void cargarUnidades(){
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

public void unidades2string(){
    cargarUnidades();

    if (unidadesVel == 1) {
        unidadesVelocidad = "Km/h";
        otrasUdVel1 = "Kt";
        otrasUdVel2 = "Mph";
        otrasIAS1 = round(ias/1.852f);//(km/h)/conversion da kt
        otrasIAS2 = round(otrasIAS1*1.1507794480235f);//kt*conversion da mph
        otrasGS1 = round(sog/1.852f);//(km/h)/conversion da kt
        otrasGS2 = round(otrasGS1*1.1507794480235f);//kt*conversion da mph
    } else if (unidadesVel == 2) {
        unidadesVelocidad = "Kt";
        otrasUdVel1 = "Km/h";
        otrasUdVel2 = "Mph";
        otrasIAS1 = round(ias);//(km/h) ya que la airspeed ya viene en km/h
        otrasIAS2 = round(otrasIAS1/1.852f*1.1507794480235f);//kt*conversion da mph
        otrasGS1 = round(sog);//(km/h) ya que la gs ya viene en km/h
        otrasGS2 = round(otrasGS1/1.852f*1.1507794480235f);//kt*conversion da mph
    } else {
        unidadesVelocidad = "Mph";
        otrasUdVel1 = "Km/h";
        otrasUdVel2 = "Kt";
        otrasIAS1 = round(ias);//(km/h) ya que la airspeed ya viene en km/h
        otrasIAS2 = round(ias/1.852f);//(km/h)/conversion da Kt
        otrasGS1 = round(sog);//(km/h) ya que la gs ya viene en km/h
        otrasGS2 = round(sog/1.852f);//(km/h)/conversion da Kt
    }

    if (unidadesAlt == 1) {
        unidadesALT = textMetrosUni;
        otrasUdALT1 = textPiesUni;
        otrasALT1 = round(fused_alt);//porque lo que me viene, altin, ya son pies
    } else {
        unidadesALT = textPiesUni;
        otrasUdALT1 = textMetrosUni;
        otrasALT1 = round(fused_alt / 3.2808399f);
    }

    if (unidadesVsi == 1) {
        unidadesVSI = "m/s";
        otrasUdVSI1 = "ft/min";
        otrasVSI1 = valorVSI;//porque lo que viene ya esta en ft/min
    } else {
        unidadesVSI = "ft/min";
        otrasUdVSI1 = "m/s";
        otrasVSI1 = valorVSI * 0.00508f;
    }

    if (unidadesQnh == 1) {
        unidadesQNH = "mmHg";
        otrasUdQNH1 = "inHg";
        otrasQNH1 = nf(presionQNH * 0.02952998751f, 2, 2);//mmHg*conversion
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
public void keyPressed(){
    switch (key) {
         case '<':
            showDebugValues = !showDebugValues;
            break;
        case '0':
            maxPitchUp = 0.0f;
            maxPitchDn = 0.0f;
            maxRollRgt = 0.0f;
            maxRollLft = 0.0f;
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
            ias = 65.0f;
            println(ias);
            break;
        case 's':
            ias = 120.0f;
            println(ias);
            break;
        case 'd':
            ias = 260.0f;
            println(ias);
            break;
        case 'f':
            ias = 285.0f;
            println(ias);
            break;
        case 'g':
            ias = 80.0f;
            println(ias);
            break;
        case 'h':
            ias = 220.0f;
            println(ias);
            break;
        case 'j':
            ias = 0.0f;
            println(ias);
            break;
        case 'k':
            ias = 340.0f;
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


public void reconect10DofOatIas(){
    try {
        puerto10DofOatIas.stop();
        puerto10DofOatIas = null;
            puerto10DofOatIas = new Serial(this, serialPort, BaudRate);
            println("reconectando...");
    } catch (RuntimeException e) {
        println("port in use, trying again later...");
    }
}

public void mousePressed() {
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

public float calcularTAS(){
    float tas_local = 0.0f;

    float delta = 0.0f;

    delta = (288.15f*press)/((tempOAT+273.15f)*1013.25f);
    
    if (delta > 0)
        tas_local = ias/sqrt(delta);

    return tas_local;
}

public void getYawPitchRollRad() {
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
public void getYawPitchRoll180() {
    float gx, gy, gz;       // estimated gravity direction

    gx = 2 * (q[1]*q[3] - q[0]*q[2]);
    gy = 2 * (q[0]*q[1] + q[2]*q[3]);
    gz = q[0]*q[0] - q[1]*q[1] - q[2]*q[2] + q[3]*q[3];

    //calculating yaw
    ypr[0] = atan2(2 * q[1] * q[2] - 2 * q[0] * q[3], 2 * q[0]*q[0] + 2 * q[1] * q[1] - 1);   //este estaba comentado pero lo puse yo
    //ypr[0] = val[9];//este era el original
    //ypr[0] = gyro[2];//este lo use tomando las correspondencias
    if(ypr[0] > 180.f) ypr[0] = ypr[0] - 360.f;
    ypr[0] = ypr[0] * 0.0174532925f;
    
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


public void gravityCompensateDynAcc() {
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
public void coloresParpadeo (int r1, int g1, int b1, int r2, int g2, int b2){
    if (millis() - now > delay) { 
            cambiar = !cambiar;//change flag
            now = millis();//reset counter
    }
    if (cambiar)
        fill(r1, g1, b1);
    else
        fill(r2, g2, b2);
}

public void portada(){
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

public void debugValuesWindow(){
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

public void maxPitchRoll(){
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

public void yprDebug(){
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

public void drawCube() {  
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
public void buildBoxShape() {
  //box(60, 10, 40);
  //noStroke();
  
  //Z+ (to the drawing area)   FRONT
  beginShape(QUADS);
  fill(0xff00ff00);
  //texture(Top);
  vertex(-30, -5, 20);
  vertex(30, -5, 20);
  vertex(30, 5, 20);
  vertex(-30, 5, 20);
  endShape();
  
  beginShape(QUADS);  
  //Z-
  fill(0xff0000ff);
  vertex(-30, -5, -20);
  vertex(30, -5, -20);
  vertex(30, 5, -20);
  vertex(-30, 5, -20);
  endShape();
  
  beginShape(QUADS);  
  //X-
  fill(0xffff0000);
  vertex(-30, -5, -20);
  vertex(-30, -5, 20);
  vertex(-30, 5, 20);
  vertex(-30, 5, -20);
  endShape();
  
  beginShape(QUADS);  
  //X+ RIGHT SIDE
  fill(0xffffff00);
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
public void quaternionToEuler(float [] q, float [] euler) {
  euler[0] = atan2(2 * q[1] * q[2] - 2 * q[0] * q[3], 2 * q[0]*q[0] + 2 * q[1] * q[1] - 1); // psi
  euler[1] = -asin(2 * q[1] * q[3] + 2 * q[0] * q[2]); // theta
  euler[2] = atan2(2 * q[2] * q[3] - 2 * q[0] * q[1], 2 * q[0] * q[0] + 2 * q[3] * q[3] - 1); // phi
}

public void getEuler360degAttitude() {
  float m11, m12, m21, m31, m32;
  float gx, gy, gz; // estimated gravity direction
  //float q[4]; // quaternion
  //float val[13];
  
  //getQ(q, val);
  
  gx = 2 * (q[1]*q[3] - q[0]*q[2]);
  gy = 2 * (q[0]*q[1] + q[2]*q[3]);
  gz = q[0]*q[0] - q[1]*q[1] - q[2]*q[2] + q[3]*q[3];
    
  m11 = 2.f*(q[1]*q[2] + q[0]*q[3]);
  m12 = q[0]*q[0] + q[1]*q[1] - q[2]*q[2] - q[3]*q[3];
  m21 = -2.f*(q[1]*q[3] - q[0]*q[2]);               
  m31 = 2.f*(q[2]*q[3] + q[0]*q[1]);              
  m32 = q[0]*q[0] - q[1]*q[1] - q[2]*q[2] + q[3]*q[3];

  // find angles for rotations about X, Y, and Z axes
  angles[0] = -atan2( m11, m12 ) * 57.2957795f;
  angles[1] = -asin( m21 ) * 57.2957795f;
  angles[2] = -atan2( m31, m32 ) * 57.2957795f;
    
  //    Gx  gy  gz
  //0-90    "+"     "+"
  //90-180  "+"     "-"
  //180-270 "-"     "-"
  //270-360 "-"     "+"
    
  if(gx >= 0 && gz < 0)
      angles[1] = 180.f - angles[1];
     else if(gx < 0 && gz < 0)
       angles[1] = 180.f - angles[1];
      else if(gx < 0 && gz >=0)
        angles[1] = 360.f + angles[1];
        
  if(angles[0] < 0) angles[0] = 360.f + angles[0];
  if(angles[2] < 0) angles[2] = 360.f + angles[2];
  
  angles[0] = 360 - angles[0];
  
}
class Altimeter {
    PGraphics pg;
    PGraphics alt;
    float altMetros = 0.0f;
    float altPies = altMetros * 3.2808399f;
    Altimeter() {
        pg = createGraphics(120, 380);
        pg.beginDraw();
        pg.background(0);
        pg.noStroke();
        pg.endDraw();

        alt = createGraphics(120, 380);
        alt.beginDraw();
        alt.background(0, 0);
        alt.noStroke();
        alt.endDraw();
    }

    public void draw(float value) {
        float newValue = value;
        if (unidadesAlt == 1) {
            newValue = value / 3.2808399f;// ya lo tenemos en metros
        }
        //si no es que las unidades estan en pies por lo que no transformamos nada

        pg.beginDraw();
        pg.background(90, 90, 90, 127);
        
        if (!isactivo10DOF) {
            pg.background(colorNegro);
            pg.stroke(colorRojo);
            pg.strokeWeight(2);
            pg.line(0, 0, pg.width-50, pg.height);//estos -50 son el ancho de la VS
            pg.line(0, pg.height, pg.width-50, 0);//estos -50 son el ancho de la VS
            pg.fill(colorAmarillo);
            pg.textFont(font14VerBold);
            pg.textAlign(CENTER, CENTER);
            pg.text(textErrorALT, pg.width/2-25, pg.height/2);//estos -50 son el ancho de la VS
        } else {
            pg.translate(0, -pg.height/2);
            pg.textAlign(RIGHT, CENTER);
            pg.stroke(colorTextoFormas);
            pg.fill(colorTextoFormas);
            pg.strokeWeight(2);
            pg.strokeCap(SQUARE);
            if (textoNegrita) {
                pg.textFont(font18VerBold, 18);
            } else {
                pg.textFont(font20Ver, 18);
            }
            //float valueK = newValue/100;
            for (int i = -380; i < 380; i++) {
                if (PApplet.parseInt(i+newValue)%20 == 0 && PApplet.parseInt(i+newValue)%100 != 0) {
                    pg.line( 0, 380-i, 5, 380-i);
                }
                if (PApplet.parseInt(i+newValue)%100 == 0) {
                    pg.line( 0, 380-i, 10, 380-i);
                    pg.text( PApplet.parseInt(i+newValue), 70, 380-i); //ojo tenia int(i+newValue)/100 porque no cabian todos los numeros en 3.5"
                }
            }
        }

        pg.endDraw();
        image(pg, 0, 0);
    }

    public void drawRect(float value) {
        float newValue = value;
        if (unidadesAlt == 1) {
            newValue = value / 3.2808399f;// ya lo tenemos en metros
        }
        //si no es que las unidades estan en pies por lo que no transformamos nada

        alt.beginDraw();
        
        if (isactivo10DOF) {
            alt.stroke(colorBlanco);
            alt.strokeWeight(1);
            alt.strokeCap(SQUARE);

            actualizarValoresAlt(value);

            alt.translate(0, alt.height/2);

            alt.fill(colorNegro);
            alt.beginShape();
            alt.vertex(5, 0);
            alt.vertex(12,-18);
            alt.vertex(75,-18);
            alt.vertex(75, 18);
            alt.vertex(12, 18);
            alt.endShape(CLOSE);
            alt.textFont(font18Ver, 18);
            alt.fill(colorBlanco);
            alt.textAlign(RIGHT, CENTER);
            if (opcionMetros.isSelected()) {
                alt.text(round(altMetros/10)*10, alt.width-55, 0);
            } else {
                alt.text(round(altPies/10)*10, alt.width-50, 0);
            }
            alt.translate(0, -alt.height/2);
        }

        
        

        alt.endDraw();
        image(alt, 0, 0, alt.width, alt.height);
    }

    private void actualizarValoresAlt(float value) {
        altPies = value;
        altMetros = altPies/3.2808399f;
    }
}
/**
- Vne: Red line, Top of yellow arc. Never exceed speed. 
- Vno: Top of the green arc. Maximum structural cruise speed.
- Vfe: Top of the white arc. Maximum flap extended speed.
- Vs: Bottom of the green arc. The stall speed or the minimum steady flight speed clean configuration.
- Vy: Best rate of climb speed.
- Vso: Bottom of the white arc. The stall speed or the minimum steady flight speed in the landing configuration.
**/
class Anemometro {
    PGraphics anemo;
    float valorAnemoLocalKmh = 0.0f;
    float valorAnemoLocalKt = 0.0f;
    float valorAnemoLocalMph = 0.0f;

    float vsiLimitLocalkmh = vLimit;
    float vsiLimitLocalkt = vsiLimitLocalkmh / 1.852f;
    float vsiLimitLocalmph = vsiLimitLocalkt * 1.1507794480235f;

    //float limiteBlancoKmh = 65;
    float limiteBlancoKmh = jLimiteBlanco;
    float limiteBlancoKt = limiteBlancoKmh / 1.852f;
    float limiteBlancoMph = limiteBlancoKt * 1.1507794480235f;
    //float limiteVerdeKmh = 120;
    float limiteVerdeKmh = jLimiteVerde;
    float limiteVerdeKt = limiteVerdeKmh / 1.852f;
    float limiteVerdeMph = limiteVerdeKt * 1.1507794480235f;
    //float limiteAmarilloKmh = 260;
    float limiteAmarilloKmh = jLimiteAmarillo;
    float limiteAmarilloKt = limiteAmarilloKmh / 1.852f;
    float limiteAmarilloMph = limiteAmarilloKt * 1.1507794480235f;
    //float limiteRojoKmh = 285;
    float limiteRojoKmh = jLimiteRojo;
    float limiteRojoKt = limiteRojoKmh / 1.852f;
    float limiteRojoMph = limiteRojoKt * 1.1507794480235f;
    //float limiteVBKmh = 80;
    float limiteVBKmh = jLimiteVB;
    float limiteVBKt = limiteVBKmh / 1.852f;
    float limiteVBMph = limiteVBKt * 1.1507794480235f;
    
    //float limiteBVKmh = vsiLimitLocalkmh - limiteVerdeKmh;//que en este caso es 120 el limiteVerdeKmh, pero asi podemos modificarlo a gusto
    //float limiteBVKt = limiteBVKmh / 1.852;
    //float limiteBVMph = limiteBVKt * 1.1507794480235;

    float valorVyKmh = jValorVy;
    float valorVyKt = valorVyKmh / 1.852f;
    float valorVyMph = valorVyKt * 1.1507794480235f;

    float valorIASLocal = 0.f;
    float valorLimitLocal = 0.f;
    float limiteBlanco = 0.f;
    float limiteVerde = 0.f;
    float limiteAmarillo = 0.f;
    float limiteRojo = 0.f;
    float limiteVB = 0.0f;
    //float limiteBV = 0.0;

    float valorVy = 0.f;

    Anemometro() {
        anemo = createGraphics(100, 380);
        anemo.beginDraw();
        anemo.background(0, 0, 0);
        anemo.endDraw();
    }
    public void setvalorAnemoLocalKmh(float anemoKmh){
        this.valorAnemoLocalKmh = anemoKmh;
    }
    public float getvalorAnemoLocalKmh(){
        return this.valorAnemoLocalKmh;
    }
    public void setvalorAnemoLocalKt(float anemoKmh){
        this.valorAnemoLocalKt = anemoKmh/1.852f;
    }
    public float getvalorAnemoLocalKt(){
        return this.valorAnemoLocalKt;
    }
    public void setvalorAnemoLocalMph(float anemoKmh){
        this.valorAnemoLocalMph = anemoKmh * 0.6213711922f;
    }
    public float getvalorAnemoLocalMph(){
        return this.valorAnemoLocalMph;
    }

    public void setLimiteBlancoKmh(float theBlancoValue){
        this.limiteBlancoKmh = theBlancoValue;
    }
    public float getLimiteBlancoKmh(){
        return this.limiteBlancoKmh;
    }

    public void setLimiteVerdeKmh(float theVerdeValue){
        this.limiteVerdeKmh = theVerdeValue;
    }
    public float getLimiteVerdeKmh(){
        return this.limiteVerdeKmh;
    }
    public void setLimiteAmarilloKmh(float theAmarilloValue){
        this.limiteAmarilloKmh = theAmarilloValue;
    }
    public float getLimiteAmarilloKmh(){
        return this.limiteAmarilloKmh;
    }

    public void setLimiteRojoKmh(float theRedValue){
        this.limiteRojoKmh = theRedValue;
    }
    public float getLimiteRojoKmh(){
        return this.limiteRojoKmh;
    }
    public void setLimiteVBKmh(float theVBValue){
        this.limiteVBKmh = theVBValue;
    }
    public float getLimiteVBKmh(){
        return this.limiteVBKmh;
    }
    /*
    public void setLimiteBVKmh(float theBVValue){
        this.limiteBVKmh = theBVValue;
    }
    public float getLimiteBVKmh(){
        return this.limiteBVKmh;
    }
    */
    public void setValorVyKmh(float theVyValue){
        this.valorVyKmh = theVyValue;
    }
    public float getValorVyKmh(){
        return this.valorVyKmh;
    }

    public void draw(float anemoKmh) {
        anemo.beginDraw();

        if (!isactivo10DOF) {
            anemo.noStroke();
            anemo.fill(colorNegro);
            anemo.rect(0, 0, anemo.width, anemo.height);
            anemo.stroke(colorRojo);
            anemo.strokeWeight(2);
            anemo.line(0, 0, anemo.width, anemo.height);
            anemo.line(0, anemo.height, anemo.width, 0);
            anemo.fill(colorAmarillo);
            anemo.textFont(font14VerBold);
            anemo.textAlign(CENTER, CENTER);
            anemo.text(textErrorANEMO, anemo.width/2, anemo.height/2);
        } else {
            actualizarValoresLimites();
            actualizarValoresAnemoLocal(anemoKmh);
            
            anemo.background(90, 90, 90, 127); // fond gris

            //calculos para pruebas
            //airspeed = airspeed + ((-mouseY+180)/50) ;
            
            if (valorIASLocal > valorLimitLocal) {valorIASLocal = valorLimitLocal;}  //airspeed max 300 km/h
            if (valorIASLocal < 0 ||  Float.isNaN(valorIASLocal)) {valorIASLocal = 0;}  //airspeed min 0 km/h
            //speed10s = valorIASLocal + 10 - ((-mouseX+400)/10);  //aceleracion

            //color y peso de las rayas y numeros
            anemo.stroke(colorTextoFormas);
            anemo.strokeWeight(2);
            anemo.strokeCap(SQUARE);

            anemo.translate(0, anemo.height/2);

            anemo.pushMatrix();
            anemo.translate(0, (4*valorIASLocal));

            anemo.noStroke();
            anemo.fill(colorNegro);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10) , 12, (valorLimitLocal*40/10));
            anemo.fill(colorBlanco);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteBlanco*40/10));
            anemo.fill(colorVerde);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteVB*40/10));
            anemo.fill(colorBlanco);
            //anemo.rect(anemo.width-4, -(valorLimitLocal*40/10) + (limiteBV*40/10), 4, (valorLimitLocal*40/10) - (limiteRojo*40/10));
            anemo.rect(anemo.width-6, -(valorLimitLocal*40/10), 6, (valorLimitLocal*40/10) - (limiteVB*40/10));
            anemo.fill(colorVerde);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteVerde*40/10));
            anemo.fill(colorNaranja);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteAmarillo*40/10));
            anemo.fill(colorRojo);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteRojo*40/10));
            
            if (textoNegrita) {
                anemo.textFont(font18VerBold, 18);
            } else {
                anemo.textFont(font20Ver, 18);
            }
            anemo.stroke(colorTextoFormas);
            anemo.fill(colorTextoFormas);
            anemo.textAlign(RIGHT, CENTER);
            int k = round(valorLimitLocal-valorLimitLocal%10);
            //println(k);
            //println(round(-(round(valorLimitLocal)*4-round(valorLimitLocal)*4%10)));
            for (int i=round(-k*4); i <= 0; i += 40){//rayas largas
                anemo.line(anemo.width-12, i, anemo.width-28, i);
                anemo.text(k, anemo.width-30, i);
                k -= 10;
            }


            int l = round(valorLimitLocal-valorLimitLocal%10);
            for(int j = round(-l*4-20);j<0;j+=40)//rayas cortas
                anemo.line(anemo.width-12, j, anemo.width-22, j);
            
            //Circulito con la Vy
            anemo.noStroke();
            anemo.fill(colorAzulCielo);
            anemo.ellipse(anemo.width-8, -(valorVy*40/10), 16, 16);
            anemo.textFont(font11Ver);
            anemo.stroke(colorTextoFormas);
            anemo.fill(colorNegro);
            anemo.text("Vy", anemo.width, -(valorVy*40/10));


        /*
            int k = round(valorLimitLocal);
            println(valorLimitLocal*4-valorLimitLocal*4%10);
            for(int i=round(-(valorLimitLocal*40/10)); i <= 0; i += 40) {//rayas largas
                anemo.line(anemo.width-8, i, anemo.width-24, i);
                anemo.text(k, anemo.width-30, i);
                k -= 10;
            }

            for(int j=round(-(valorLimitLocal*40/10-20)); j < 0; j += 40) {//rayas cortas
                anemo.line(anemo.width-8, j, anemo.width-18, j);
            }
        */
            anemo.popMatrix();
            //partes fijas
            //anemo.strokeWeight(2);
            if (valorIASLocal <= limiteBlanco) {
                anemo.strokeWeight(1.5f);
                anemo.stroke(colorBlanco);
            } else if (valorIASLocal > limiteBlanco && valorIASLocal <= limiteVerde) {
                anemo.strokeWeight(3);
                anemo.stroke(colorBlanco);
            } else if (valorIASLocal > limiteVerde && valorIASLocal <= limiteAmarillo) {
                anemo.strokeWeight(3);
                anemo.stroke(colorVerde);
            } else if (valorIASLocal > limiteAmarillo && valorIASLocal <= limiteRojo) {
                anemo.strokeWeight(3);
                anemo.stroke(colorNaranja);
            } else if (valorIASLocal > limiteRojo) {
                anemo.strokeWeight(3);
                anemo.stroke(colorRojo);
            }
            
            if (textoNegrita) {
                anemo.textFont(font18VerBold, 18);
            } else {
                anemo.textFont(font20Ver, 18);
            }

            anemo.fill(colorNegro);
            anemo.beginShape();
            anemo.vertex(anemo.width-18, 0);
            anemo.vertex(anemo.width-30,-18);
            anemo.vertex(4,-18);
            anemo.vertex(4, 18);
            anemo.vertex(anemo.width-30, 18);
            anemo.endShape(CLOSE);
            anemo.fill(colorBlanco);
            anemo.text(round(valorIASLocal), anemo.width-30, 0);
            anemo.translate(0 , -anemo.height/2);

            if (!toggleRumbo.isSelected()) {
                anemo.fill(90, 90, 90, 127);
                anemo.rect(380, 0, 100, 50);
            }
        }
        
        anemo.endDraw();
        image(anemo, 0, 50, 100, 380);
    }

    public void actualizarValoresLimites() {
        //limiteBlancoKmh = 65;
        limiteBlancoKt = limiteBlancoKmh / 1.852f;
        limiteBlancoMph = limiteBlancoKt * 1.1507794480235f;
        //limiteVerdeKmh = 120;
        limiteVerdeKt = limiteVerdeKmh / 1.852f;
        limiteVerdeMph = limiteVerdeKt * 1.1507794480235f;
        //limiteAmarilloKmh = 260;
        limiteAmarilloKt = limiteAmarilloKmh / 1.852f;
        limiteAmarilloMph = limiteAmarilloKt * 1.1507794480235f;
        //limiteRojoKmh = 285;
        limiteRojoKt = limiteRojoKmh / 1.852f;
        limiteRojoMph = limiteRojoKt * 1.1507794480235f;
        //limiteVBKmh = 80;
        limiteVBKt = limiteVBKmh / 1.852f;
        limiteVBMph = limiteVBKt * 1.1507794480235f;
        
        //limiteBVKmh = vsiLimitLocalkmh - limiteVerdeKmh;//que en este caso es 120, pero asi podemos modificarlo a gusto
        //limiteBVKt = limiteBVKmh / 1.852;
        //limiteBVMph = limiteBVKt * 1.1507794480235;

        valorVyKt = valorVyKmh / 1.852f;
        valorVyMph = valorVyKt * 1.1507794480235f;
    }

    public void actualizarValoresAnemoLocal(float anemoKmh){
        setvalorAnemoLocalKmh(anemoKmh);
        setvalorAnemoLocalKt(anemoKmh);
        setvalorAnemoLocalMph(anemoKmh);
        if (unidadesVel == 1) {//unidades en km/h
            valorIASLocal = valorAnemoLocalKmh;
            valorLimitLocal = vsiLimitLocalkmh;
            limiteBlanco = limiteBlancoKmh;
            limiteVerde = limiteVerdeKmh;
            limiteAmarillo = limiteAmarilloKmh;
            limiteRojo = limiteRojoKmh;
            limiteVB = limiteVBKmh;
            //limiteBV = limiteBVKmh;
            valorVy = valorVyKmh;
        } else if (unidadesVel == 2) {//unidades en kt
            valorIASLocal = valorAnemoLocalKt;
            valorLimitLocal = vsiLimitLocalkt;
            limiteBlanco = limiteBlancoKt;
            limiteVerde = limiteVerdeKt;
            limiteAmarillo = limiteAmarilloKt;
            limiteRojo = limiteRojoKt;
            limiteVB = limiteVBKt;
            //limiteBV = limiteBVKt;
            valorVy = valorVyKt;
        } else {//unidades en mph
            valorIASLocal = valorAnemoLocalMph;
            valorLimitLocal = vsiLimitLocalmph;
            limiteBlanco = limiteBlancoMph;
            limiteVerde = limiteVerdeMph;
            limiteAmarillo = limiteAmarilloMph;
            limiteRojo = limiteRojoMph;
            limiteVB = limiteVBMph;
            //limiteBV = limiteBVMph;
            valorVy = valorVyMph;
        }
    }
}
class ArtificialHorizon {
    PGraphics horizonte;
    float ahPitch;
    float ahRoll;
    
    ArtificialHorizon(){
        horizonte = createGraphics(width, height);//tamaño de la pantalla, toda irá en azul
        horizonte.beginDraw();
        horizonte.background(colorAzulHorizonte);
        horizonte.noStroke();
        horizonte.endDraw();

        this.ahPitch = 0.f;
        this.ahRoll = 0.f;
    }

    public void draw(float valuePitch, float valueRoll) {
        horizonte.beginDraw();
        horizonte.background(colorAzulHorizonte);

        if (!isactivo10DOF) {
            horizonte.background(colorNegro);
            horizonte.stroke(colorRojo);
            horizonte.strokeWeight(2);
            horizonte.line(0, 0, horizonte.width, horizonte.height);
            horizonte.line(0, horizonte.height, horizonte.width, 0);
            horizonte.fill(colorAmarillo);
            horizonte.textFont(font16Ver);
            horizonte.textAlign(CENTER, CENTER);
            horizonte.text(textErrorHA, horizonte.width/2, horizonte.height/2);
        } else {
            horizonte.textSize(12);
            horizonte.strokeCap(SQUARE);

            if (textoNegrita) {
                horizonte.textFont(font20Ver, 20);
            } else {
                horizonte.textFont(font20Ver, 20);
            }

            actualizarValores(valuePitch, valueRoll);

            horizonte.pushMatrix();
                horizonte.translate(width/2+0.5f, height/2+0.5f);
                drawAttitudeIndicatorHorizonBrownPart(this.ahRoll, this.ahPitch);
            horizonte.popMatrix();


            setGradient(0, 0, width, height/2, colorNegroTransp, colorTransp1);
            setGradient(0, height/2, width, height/2, colorTransp1, colorNegroTransp);

            horizonte.pushMatrix();
                horizonte.translate(width/2+0.5f, height/2+0.5f);
                drawAttitudeIndicatorHorizonLines(this.ahRoll, this.ahPitch);
            horizonte.popMatrix();

            horizonte.pushMatrix();
                horizonte.translate(width/2+0.5f, height/2+0.5f);
                drawAttitudeIndicatorHorizonFixElements(this.ahRoll);
            horizonte.popMatrix();
        }

        

        horizonte.endDraw();

        image(horizonte, 0, 0);
    }

    public void actualizarValores(float actValuePitch, float actValueRoll) {
        this.ahPitch =  actValuePitch;
        this.ahRoll = actValueRoll;

        if (actValuePitch > 90 &&  actValuePitch <270) {
            this.ahPitch = 180 - actValuePitch;
        }

        if (actValuePitch >= 270 && actValuePitch <360) {
            this.ahPitch = actValuePitch - 360;
        }
    }

    public void drawAttitudeIndicatorHorizonFixElements(float fixValueRoll){
        //Esto es el triandulito amarillo
        horizonte.pushMatrix();
        horizonte.translate(0, -80);

        horizonte.stroke(255, 255, 0);    //  jaune     //  roll+sideslip indicator
        horizonte.fill(255, 255, 0);
        horizonte.strokeWeight(1);
        horizonte.triangle(0, -88, -10, -78, 10, -78);

        horizonte.translate(0, 80);
        horizonte.popMatrix();
        //Fin del triandulito amarillo

        horizonte.stroke(255, 255, 0);    //  amarillo
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.rect (-5,-5,10,10);  //  cuadradito central
        
        horizonte.fill(colorNegro);
        
        //Avion 
        horizonte.beginShape();   // ala izquierda
        horizonte.vertex (-90,-4);
        horizonte.vertex (-45,-4);
        horizonte.vertex (-45,23);
        horizonte.vertex (-53,23);
        horizonte.vertex (-53,4);
        horizonte.vertex (-90,4);
        horizonte.vertex (-90,-4);
        horizonte.endShape(CLOSE);

        horizonte.beginShape();   // ala derecha
        horizonte.vertex (90,-4);
        horizonte.vertex (45,-4);
        horizonte.vertex (45,23);
        horizonte.vertex (53,23);
        horizonte.vertex (53,4);
        horizonte.vertex (90,4);
        horizonte.vertex (90,-4);
        horizonte.endShape(CLOSE);

        //a partir de aqui tambien se mueven las cosas
        horizonte.rotate(radians(fixValueRoll));//horizonte.rotate(radians(roll));

        horizonte.pushMatrix();
        horizonte.translate(0, -80);
        //Triangulito amarillo 
        horizonte.strokeWeight(1);
        horizonte.fill(255, 255, 0);
        horizonte.triangle(0, -92, -10, -103, 10, -103);
        horizonte.translate(0, 80);
        horizonte.popMatrix();

        horizonte.stroke(colorTextoFormas);
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.arc(0, 0, 340, 340, (-2*PI/3), (-PI/3));


        horizonte.pushMatrix();
        horizonte.rotate(radians(10));
        horizonte.rect(-2,-170,4,-8);

        horizonte.rotate(radians(10));  // a 20 degres
        horizonte.rect(-2,-170,4,-8);
         
        horizonte.rotate(radians(10));  // a 30 degres
        horizonte.rect(-2,-170,4,-12);

        horizonte.rotate(radians(15));  // a 45 degres
        horizonte.line (0,-170,0,-182); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(15));  // a 67 degres 
        horizonte.line (0, -170, 0, -185);
        horizonte.line (4, -170, 4, -185);



        horizonte.stroke(colorTextoFormas);    
        horizonte.rotate(radians(-70));
        horizonte.rect(-2,-170,4,-8);

        horizonte.rotate(radians(-10));  // a 20 degres
        horizonte.rect(-2,-170,4,-8);
         
        horizonte.rotate(radians(-10));  // a 30 degres
        horizonte.rect(-2,-170,4,-12);

        horizonte.rotate(radians(-15));  // a 45 degres
        horizonte.line (0,-170,0,-182); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(-15));  // a 60 degres 
        horizonte.line (0, -170, 0, -185);
        horizonte.line (4, -170, 4, -185);

        horizonte.popMatrix();
    }

    /*
    public void drawAttitudeIndicatorHorizonFixElements(float fixValueRoll){
        horizonte.pushMatrix();
        horizonte.translate(0, -80);

        horizonte.stroke(255, 255, 0);    //  jaune     //  roll+sideslip indicator
        horizonte.fill(255, 255, 0);
        horizonte.strokeWeight(1);
        horizonte.triangle(0, -88, -10, -78, 10, -78);

        horizonte.translate(0, 80);
        horizonte.popMatrix();


        horizonte.stroke(255, 255, 0);    //  amarillo
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.rect (-5,-5,10,10);  //  cuadradito central
        
        horizonte.fill(colorNegro);
        
        //Avion 
        horizonte.beginShape();   // ala izquierda
        horizonte.vertex (-90,-4);
        horizonte.vertex (-45,-4);
        horizonte.vertex (-45,23);
        horizonte.vertex (-53,23);
        horizonte.vertex (-53,4);
        horizonte.vertex (-90,4);
        horizonte.vertex (-90,-4);
        horizonte.endShape(CLOSE);

        horizonte.beginShape();   // ala derecha
        horizonte.vertex (90,-4);
        horizonte.vertex (45,-4);
        horizonte.vertex (45,23);
        horizonte.vertex (53,23);
        horizonte.vertex (53,4);
        horizonte.vertex (90,4);
        horizonte.vertex (90,-4);
        horizonte.endShape(CLOSE);

        //a partir de aqui tambien se mueven las cosas
        horizonte.rotate(radians(fixValueRoll));//horizonte.rotate(radians(roll));

        horizonte.pushMatrix();
        horizonte.translate(0, -80);//Esto hace ampliar el arco con los cuadraditos hacia arriba en pantalla anchas
        

        horizonte.strokeWeight(1);
        horizonte.fill(255, 255, 0);
        horizonte.triangle(0, -92, -10, -103, 10, -103);

        horizonte.stroke(colorTextoFormas);
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.arc(0, 0, 180, 180, (-2*PI/3), (-PI/3));


        horizonte.pushMatrix();
        horizonte.rotate(radians(10));
        horizonte.rect(-2,-90,4,-8);

        horizonte.rotate(radians(10));  // a 20 degres
        horizonte.rect(-2,-90,4,-8);
         
        horizonte.rotate(radians(10));  // a 30 degres
        horizonte.rect(-2,-90,4,-12);

        horizonte.rotate(radians(15));  // a 45 degres
        horizonte.line (0,-90,0,-102); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(15));  // a 67 degres 
        horizonte.line (0, -90, 0, -105);
        horizonte.line (4, -90, 4, -105);



        horizonte.stroke(colorTextoFormas);    
        horizonte.rotate(radians(-70));
        horizonte.rect(-2,-90,4,-8);

        horizonte.rotate(radians(-10));  // a 20 degres
        horizonte.rect(-2,-90,4,-8);
         
        horizonte.rotate(radians(-10));  // a 30 degres
        horizonte.rect(-2,-90,4,-12);

        horizonte.rotate(radians(-15));  // a 45 degres
        horizonte.line (0,-90,0,-102); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(-15));  // a 60 degres 
        horizonte.line (0, -90, 0, -105);
        horizonte.line (4, -90, 4, -105);

        horizonte.translate(0, 80);
        horizonte.popMatrix();// Esto hace ampliar el arco con los cuadraditos hacia arriba en pantalla anchas

        horizonte.popMatrix();
    }
    */

    public void drawAttitudeIndicatorHorizonBrownPart(float linesValueRoll, float linesValuePitch){
        float linelong = sqrt(sq(width/2)+sq(height/2));
        //horizonte.rotate(-ypr[0]);
        horizonte.rotate(radians(linesValueRoll));
        horizonte.translate(0, linesValuePitch*4);
        

        
        horizonte.fill(colorMarronHorizonte);
        horizonte.rect(-linelong, 0, linelong*2, height*4);
    }

    public void drawAttitudeIndicatorHorizonLines(float linesValueRoll, float linesValuePitch){
        float linelong = sqrt(sq(width/2)+sq(height/2));
        //horizonte.rotate(-ypr[0]);
        horizonte.rotate(radians(linesValueRoll));
        horizonte.translate(0, linesValuePitch*4);
        

        
        horizonte.strokeWeight (2);
        horizonte.stroke(colorTextoFormas);
        horizonte.fill(colorTextoFormas);

        horizonte.line(-50, -80, 50, -80);  //  ______  20
        horizonte.line(-30, -60, 30, -60);  //   ____   
        horizonte.line(-50, -40, 50, -40);  //  ______  10
        horizonte.line(-30, -20, 30, -20);  //   ____

        horizonte.line(-linelong, 0, linelong, 0);  //linea central
        horizonte.line(-2, -2, -2, 2);  //cuadrado concentrico central
        horizonte.rect(-2, -2, 4, 4) ; //cuadrado concentrico central

        horizonte.line(-30, 20, 30, 20);  //  ____
        horizonte.line(-50, 40, 50, 40);  // ______  10
        horizonte.line(-30, 60, 30, 60);  //  ____
        horizonte.line(-50, 80, 50, 80);  // ______  20

        horizonte.text("20", 67, -73); 
        horizonte.text("10", 67, -33); 
        horizonte.text("10", 67, 48);
        horizonte.text("20", 67, 88);
    }

    public void setGradient(int x, int y, float w, float h, int c1, int c2) {

        noFill();
        horizonte.strokeWeight(1);
        for (int i = y; i <= y+h; i++) {
            float inter = map(i, y, y+h, 0, 1);
            int c = horizonte.lerpColor(c1, c2, inter);
            horizonte.stroke(c);
            horizonte.line(x, i, x+w, i);
        }
    }
}
/*
 * Copyright (c) 2014 Ovcharenko Alexander <ferrocentos[dot]gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/*
 * This is a copy of Electric Attitude Indicator by Mid-Continent Instrument Co, Model: 4300.
 */

PImage TextureAttitudeIndicatorRollDial;
PImage TextureAttitudeIndicatorFacePanel;
PImage TextureAttitudeIndicatorHorizonBar;
PImage TextureAttitudeIndicatorHorizonFlat;

public void loadTextureAttitudeIndicator() {
  TextureAttitudeIndicatorRollDial = loadImage("attitude_indicator_roll_dial.png");
  TextureAttitudeIndicatorFacePanel = loadImage("attitude_indicator_face_panel.png");
  TextureAttitudeIndicatorHorizonBar= loadImage("attitude_indicator_horizon_bar.png");
  TextureAttitudeIndicatorHorizonFlat = loadImage("attitude_indicator_horizon_flat.png");
}

public void drawAttitudeIndicator(float rotateZ, float moveX) {
  int cDetail =  10;  // Circle detalization
    
  // draw textured Attitude Indicator's Horizon Flat
  pushMatrix();
    drawAttitudeIndicatorHorizonFlat();
  popMatrix();

  // draw textured Attitude Indicator's Horizon Bar
  pushMatrix();
    drawAttitudeIndicatorHorizonBar(rotateZ, moveX);
  popMatrix();
    
  // draw textured Attitude Indicator's Rotating Roll Dial
  pushMatrix();
    drawAttitudeIndicatorRollDial(80, 330, rotateZ);
  popMatrix();
    
  // draw textured Attitude Indicator's Panel
  pushMatrix();
    drawAttitudeIndicatorFacePanel();
  popMatrix();
}

public void drawAttitudeIndicatorRollDial(int cDetail, float r, float rotateDial) {
  beginShape();
    noStroke();
    textureMode(NORMAL);
    rotateZ(rotateDial);
    texture(TextureAttitudeIndicatorRollDial);
    float theta = TWO_PI / cDetail;
    for (int i=0; i<cDetail; i++) {
      float angle = theta * i;
      float x = cos(angle);
      float y = sin(angle);
      vertex(x * r, y * r, (x+1)/2, (y+1)/2);
    }
  endShape();
}

public void drawAttitudeIndicatorFacePanel() {
  beginShape();
    noStroke();
    textureMode(IMAGE);
    texture(TextureAttitudeIndicatorFacePanel);
    vertex(-394, -394, 0, 0);
    vertex(394, -394, 788, 0);
    vertex(394, 394, 788, 788);
    vertex(-394, 394, 0, 788);
  endShape();
}

public void drawAttitudeIndicatorHorizonFlat() {
  beginShape();
    noStroke();
    textureMode(IMAGE);
    texture(TextureAttitudeIndicatorHorizonFlat);
    vertex(-330, -330, 0, 0);
    vertex(330, -330, 660, 0);
    vertex(330, 330, 660, 660);
    vertex(-330, 330, 0, 660);
  endShape();
}

public void drawAttitudeIndicatorHorizonBar(float rotateBar, float moveBar) {
  float newX = map(moveBar, -1, 1, -150, 150);
  
  beginShape();
    noStroke();
    textureMode(IMAGE);  
    rotateZ(rotateBar);
    translate(0, newX);
    texture(TextureAttitudeIndicatorHorizonBar);
    vertex(-264, -164, 0, 0);
    vertex(264, -164, 528, 0);
    vertex(264, 164, 528, 328);
    vertex(-264, 164, 0, 328);
  endShape();
}
/*==============================================================================
 
 Copyright (c) 2010-2013 Christopher Baker <http://christopherbaker.net>
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 
 https://github.com/SAIC-ATS/Algorithms.git
  
 ==============================================================================*/


int historyLength = 20;

float[] history = new float[historyLength]; // keep our history values

float[] historyX = new float[historyLength];
float[] historyY = new float[historyLength];

float averageAngle = 0;
float yamartinoAverageAngle = 0;

float currentAngle = 0;

public void calculateMathematicalAverageOfHistory() {
    float sum = 0;
    float sq_sum = 0;
    for(int i = 0; i < history.length; ++i) {
       sum += history[i];
       sq_sum += history[i] * history[i];
    }
    
    averageAngle = sum / history.length;

}

public void calculateYamartinoAverageOfHistory() {

  float sumX = 0;
  float sumY = 0;
  
  for (int i = 0; i < history.length; i++) {
    sumX += historyX[i];
    sumY += historyY[i];
  }

  float meanX = sumX / history.length;
  float meanY = sumY / history.length;

  yamartinoAverageAngle = myAtan2(sumY, sumX);
}

public void addItemsToHistoryBuffers(float input) {
  addToHistory(history,input);
  addToHistory(historyX,cos(input));
  addToHistory(historyY,sin(input));
}

public void addToHistory(float[] buffer, float input) {
  // delete the oldest value from the history
  // add one value to the history (the input)
  // take the average of the history and return it;

  // shift the values to the left in the array
  for (int i = buffer.length - 1; i >= 0; i--) {
    if (i == 0) {
      buffer[0] = input;
    } 
    else {
      buffer[i] = buffer[i-1];
    }
  }
}


public float myAtan2(float y, float x) {
  float t = atan2(y, x);
  return t > 0 ? t : 2 * PI + t;
}
class Bola {
    PGraphics bola;

    //float ejeXbola = acc[1]*50/9.8;
    float ejeXbola = acc[1]*50;
    //float ejeXbola = 0.0;

    Bola() {
        bola = createGraphics(120, 20);
        bola.beginDraw();
        bola.background(0);
        bola.endDraw();
    }

    public void draw() {

        actualizarValoresBola();

        if (ejeXbola > 50) ejeXbola = 50;
        if (ejeXbola < -50) ejeXbola = -50;

        bola.beginDraw();
        bola.background(0, 127);
        /*if (!isactivo10DOF) {
            bola.stroke(colorRojo);
            bola.strokeWeight(2);
            bola.line(0, 0, bola.width, bola.height);
            bola.line(0, bola.height, bola.width, 0);
            bola.fill(colorAmarillo);
            bola.textFont(font14VerBold);
            bola.textAlign(CENTER, CENTER);
            bola.text(textErrorBOLA, bola.width/2, bola.height/2);
        } else {*/
            bola.strokeCap(PROJECT);
            bola.noFill();
            bola.strokeWeight(2);
            bola.rectMode(CENTER);
            bola.rect(0, 0, width, height);
            bola.stroke(colorBlanco);
            bola.line(48, 0, 48, 20);//raya izq
            bola.line(72, 0, 72, 20);//raya dch
            bola.noStroke();
            bola.fill(colorBlanco);
            bola.ellipse(ejeXbola+60, 10, 20, 20);//circulito. Hay que ver si la bola se calcula con la aceleracion del eje Y, la que pongo aqui ya esta filtrada
        //}
        
        
        bola.endDraw();
        image(bola, 0, 0, 120, 20);
    }

    private void actualizarValoresBola() {
        //ejeXbola = acc[1]*50/9.8;
        ejeXbola = acc[1]*50;
    }
}
class Brujula {
    PGraphics hdg;
    PGraphics hdgDisp;

    float hdgValue = 0.0f;

    Brujula() {
        hdg = createGraphics(580, 50);
        hdg.beginDraw();
        hdg.background(colorNegro);
        hdg.noStroke();
        hdg.endDraw();

        hdgDisp = createGraphics(580, 50);
        hdgDisp.beginDraw();
        hdgDisp.background(colorNegro, 0);
        hdgDisp.noStroke();
        hdgDisp.endDraw();
    }

    public void setHDGvalue(float theHDGvalue){
        this.hdgValue = theHDGvalue;
    }
    public float getHDGvalue(){
        return this.hdgValue;
    }

    public void draw(float theHdgValue) {

        hdg.beginDraw();
        hdg.background(90, 90, 90, 127);
        hdg.strokeWeight(2);
        hdg.strokeCap(SQUARE);

        actualizarValorHDG(theHdgValue);
        
        if (!isactivo10DOF) {
            hdg.background(colorNegro);
            hdg.stroke(colorRojo);
            hdg.strokeWeight(2);
            hdg.line(0, 0, hdg.width, hdg.height);
            hdg.line(0, hdg.height, hdg.width, 0);
            hdg.fill(colorAmarillo);
            hdg.textFont(font14VerBold);
            hdg.textAlign(CENTER, CENTER);
            hdg.text(textErrorHDG, hdg.width/2, hdg.height/2);
        } else {
            
            //hdg.translate(hdg.width/2, 0);
            hdg.textAlign(CENTER, CENTER);
            hdg.stroke(colorTextoFormas);
            hdg.fill(colorTextoFormas);
            hdg.strokeWeight(2);
            hdg.strokeCap(SQUARE);
            if (textoNegrita) {
                hdg.textFont(font18VerBold, 18);
            } else {
                hdg.textFont(font18Ver, 18);
            }

            hdg.translate(hdg.width/2, 0);
            heading();
            hdg.translate(-hdg.width/2, 0);
        }

        hdg.endDraw();
        image(hdg, 0, 0, hdg.width, hdg.height);
    }

    public void heading(){
        float hdgLocal = getHDGvalue();
        if (hdgLocal == 360)
            hdgLocal = 0;

        int j = 0;
        //hdg.stroke(255, 0, 0);
        hdg.textAlign(CENTER, CENTER);
        hdg.translate(-3600, 0);
        hdg.translate(-hdgLocal*50/5, 0);
        //rayas grandes
        for(int i = -360; i < 0 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-20);
            hdg.text(nf(360+i, 3), j, hdg.height/3);
            j+=50;
        }
        for(int i = 0; i < 360 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-20);
            hdg.text(nf(i, 3), j, hdg.height/3);
            j+=50;
        }
        
        for(int i = 360; i < 720 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-20);
            hdg.text(nf(i-360, 3), j, hdg.height/3);
            j+=50;
        }
        
        //rayas pequeñas
        j = 0;
        for(int i = -360; i < 0 ; i+=1){
            //hago una rayita vertical cada 10px
            hdg.line(j, hdg.height, j, hdg.height-10);
            j+=10;
        }

        for(int i = 0; i < 360 ; i+=1){
            //hago una rayita vertical cada 10px
            hdg.line(j, hdg.height, j, hdg.height-10);
            j+=10;
        }
        for(int i = 360; i < 720 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-10);
            j+=10;
        }


        hdg.translate(-(hdgLocal*50/5), 0);
        hdg.translate(-3600, 0);
    }

    public void drawRect(float theHdgValue) {

        hdgDisp.beginDraw();
        
        if (isactivo10DOF) {
            hdgDisp.stroke(colorBlanco);
            hdgDisp.strokeWeight(1);
            hdgDisp.strokeCap(SQUARE);

            actualizarValorHDG(theHdgValue);

            hdgDisp.translate(0, 0);

            hdgDisp.fill(colorNegro);
            hdgDisp.beginShape();
            hdgDisp.vertex(hdgDisp.width/2, hdgDisp.height-10);
            hdgDisp.vertex(hdgDisp.width/2-5, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2-25, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2-25, 1);
            hdgDisp.vertex(hdgDisp.width/2+25, 1);
            hdgDisp.vertex(hdgDisp.width/2+25, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2+5, hdgDisp.height-20);
            hdgDisp.endShape(CLOSE);
            hdgDisp.textFont(font18Ver, 18);
            hdgDisp.fill(colorBlanco);
            hdgDisp.textAlign(CENTER, CENTER);
            hdgDisp.text(nf(round(getHDGvalue()), 3), hdgDisp.width/2, 15);
        }

        hdgDisp.endDraw();
        image(hdgDisp, 0, 0, hdgDisp.width, hdgDisp.height);
    }

    private void actualizarValorHDG(float theHDGvalue) {
        setHDGvalue(theHDGvalue);
    }
}
class Brujula2 {
    PGraphics hdg;
    PGraphics hdgDisp;

    float hdgValue = 0.0f;

    Brujula2() {
        hdg = createGraphics(300, 50);
        hdg.beginDraw();
        hdg.background(colorNegro);
        hdg.noStroke();
        hdg.endDraw();

        hdgDisp = createGraphics(300, 50);
        hdgDisp.beginDraw();
        hdgDisp.background(colorNegro, 0);
        hdgDisp.noStroke();
        hdgDisp.endDraw();
    }

    public void setHDGvalue(float theHDGvalue){
        this.hdgValue = theHDGvalue;
    }
    public float getHDGvalue(){
        return this.hdgValue;
    }

    public void draw(float theHdgValue) {

        hdg.beginDraw();
        hdg.background(90, 90, 90, 127);
        hdg.strokeWeight(2);
        hdg.strokeCap(SQUARE);

        actualizarValorHDG(theHdgValue);
        
        if (!isactivo10DOF) {
            hdg.background(colorNegro);
            hdg.stroke(colorRojo);
            hdg.strokeWeight(2);
            hdg.line(0, 0, hdg.width, hdg.height);
            hdg.line(0, hdg.height, hdg.width, 0);
            hdg.fill(colorAmarillo);
            hdg.textFont(font14VerBold);
            hdg.textAlign(CENTER, CENTER);
            hdg.text("HDG", hdg.width/2, hdg.height/2);
        } else {
            
            //hdg.translate(hdg.width/2, 0);
            hdg.textAlign(CENTER, CENTER);
            hdg.stroke(colorTextoFormas);
            hdg.fill(colorTextoFormas);
            hdg.strokeWeight(2);
            hdg.strokeCap(SQUARE);
            if (textoNegrita) {
                hdg.textFont(font18VerBold, 18);
            } else {
                hdg.textFont(font18Ver, 18);
            }

            /*for (int i = -300; i < 300; i++) {
                if (int(i+getHDGvalue())%50 == 0) {
                    hdg.line(hdg.width+i, hdg.height, hdg.width+i, hdg.height-10);
                    hdg.text(int(i+getHDGvalue())+285, hdg.width+i, 15);
                }
            }*/
            for (int i = -hdg.width; i < hdg.width; i++) {
                //Long lines every 50px are 5 degrees (340 345 350 355 360 005 010 , by instante)
                if (PApplet.parseInt(i+getHDGvalue())%50 == 0) {
                    hdg.line(hdg.width+i, hdg.height, hdg.width+i, hdg.height-20);
                    hdg.text(PApplet.parseInt(i+getHDGvalue()), hdg.width+i, 15);
                }
                //Sort lines every 10px are 1 degrees and they haven't  the number.
                if ((PApplet.parseInt(i+getHDGvalue())%50 != 0) && (PApplet.parseInt(i+getHDGvalue())%10 == 0)) {
                    hdg.line(hdg.width+i, hdg.height, hdg.width+i, hdg.height-10);
                }
            }
            //println("hdgValue: "+nf(getHDGvalue(), 1, 1));
            //hdg.translate(-hdg.width/2, 0);
        }

        hdg.endDraw();
        image(hdg, 0, 0, hdg.width, hdg.height);
    }

    public void drawRect(float theHdgValue) {

        hdgDisp.beginDraw();
        
        if (isactivo10DOF) {
            hdgDisp.stroke(colorBlanco);
            hdgDisp.strokeWeight(1);
            hdgDisp.strokeCap(SQUARE);

            actualizarValorHDG(theHdgValue);

            hdgDisp.translate(0, 0);

            hdgDisp.fill(colorNegro);
            hdgDisp.beginShape();
            hdgDisp.vertex(hdgDisp.width/2, hdgDisp.height-10);
            hdgDisp.vertex(hdgDisp.width/2-5, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2-25, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2-25, 1);
            hdgDisp.vertex(hdgDisp.width/2+25, 1);
            hdgDisp.vertex(hdgDisp.width/2+25, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2+5, hdgDisp.height-20);
            hdgDisp.endShape(CLOSE);
            hdgDisp.textFont(font18Ver, 18);
            hdgDisp.fill(colorBlanco);
            hdgDisp.textAlign(CENTER, CENTER);
            hdgDisp.text(nf(round(getHDGvalue()), 3), hdgDisp.width/2, 15);
        }

        hdgDisp.endDraw();
        image(hdgDisp, 0, 0, hdgDisp.width, hdgDisp.height);
    }

    private void actualizarValorHDG(float theHDGvalue) {
        setHDGvalue(theHDGvalue);
    }
}
class Fecha {
    PGraphics hora;
    PGraphics fecha;
    String horaUTC;
    String horaLT;
    String fechaUTC;
    String fechaLT;

    Fecha() {
        hora = createGraphics(150, 50);
        hora.beginDraw();
        hora.background(90, 90, 90, 191);
        //hora.background(0, 0, 0);
        this.hora_utc();
        this.hora_local();
        hora.endDraw();

        fecha = createGraphics(130, 50);
        fecha.beginDraw();
        fecha.background(90, 90, 90, 191);
        //hora.background(0, 0, 0);
        this.fecha_utc();
        this.fecha_local();
        fecha.endDraw();
    }

    public void drawHora() {
        hora.beginDraw();
        //hora.background(90, 90, 90, 191);
        hora.background(0, 0, 0);
        
        if (!toggleFecha.isSelected()) {
            hora.background(90, 90, 90, 191);
        } else {
            //hora.background(90, 90, 90, 127);
            hora.background(90, 90, 90);
        }

        if (!isactivo10DOF) {
            hora.background(colorNegro);
        } else {
            this.hora_utc();
            this.hora_local();

            hora.fill(colorAzulCielo);
            hora.textFont(font16Ver, 16);
            hora.textAlign(CENTER, CENTER);
            hora.text(horaLT + " L", hora.width/2, hora.height/4);
            hora.text(horaUTC + " U", hora.width/2, hora.height/4*3);
        }

        
        hora.endDraw();
        image(hora, 100, 0, 150, 50);
    }

    public void drawFecha() {
        fecha.beginDraw();
        //hora.background(90, 90, 90, 191);
        fecha.background(0, 0, 0);
        
        if (!toggleFecha.isSelected()) {
            fecha.background(90, 90, 90, 191);
        } else {
            //fecha.background(90, 90, 90, 127);
            fecha.background(90, 90, 90);
        }

        if (!isactivo10DOF) {
            fecha.background(colorNegro);
        } else {
            this.fecha_utc();
            this.fecha_local();

            fecha.fill(colorAzulCielo);
            fecha.textFont(font16Ver, 16);
            fecha.textAlign(CENTER, CENTER);
            fecha.text(fechaLT + " L", fecha.width/2, fecha.height/4);
            fecha.text(fechaUTC + " U", fecha.width/2, fecha.height/4*3);
        }

        
        fecha.endDraw();
        image(fecha, 550, 0, 130, 50);
    }

    private void hora_utc(){
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        Date fechaDate = new Date();
        horaUTC = formateador.format(fechaDate);
    }

    private void hora_local(){
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
        }

        Date fechaDate = new Date();
        horaLT = formateador.format(fechaDate);
    }

    private void fecha_utc(){
        SimpleDateFormat formateador;
        if (opcionFecha1.isSelected()) {
            formateador = new SimpleDateFormat("dd/MM/yyyy");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        } else {
            formateador = new SimpleDateFormat("MM/dd/yyyy");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        Date fechaDate = new Date();
        fechaUTC = formateador.format(fechaDate);
    }

    private void fecha_local(){
        SimpleDateFormat formateador;
        if (opcionFecha1.isSelected()) {
            formateador = new SimpleDateFormat("dd/MM/yyyy");
        } else {
            formateador = new SimpleDateFormat("MM/dd/yyyy");
        }

        Date fechaDate = new Date();
        fechaLT = formateador.format(fechaDate);
    }
}
class GSpeed {
    PGraphics gs;
    float gsKt = 0.0f;//aqui habrá que poner la del gps...bueno, ya veremos
    float gsKmh = gsKt*1.852f;
    float gsMph = gsKt*1.1507794480235f;
    GSpeed() {
        gs = createGraphics(100, 50);
        gs.beginDraw();
        //gs.background(90, 90, 90, 191);
        gs.background(0, 0, 0);
        gs.endDraw();
    }

    public void draw(float value) {
        float gsLocal;

        if (unidadesVel == 1) {
            gsLocal = value;
        } else if ( unidadesVel == 2) {
            gsLocal = value/1.852f;
        } else {
            gsLocal = value/1.852f*1.1507794480235f;
        }

        gs.beginDraw();
        //gs.background(90, 90, 90, 191);
        gs.background(0, 0, 0);
        //gsKt = value;

        if (!isactivoGPS) {
            gs.stroke(colorRojo);
            gs.strokeWeight(2);
            gs.line(0, 0, gs.width, gs.height);
            gs.line(0, gs.height, gs.width, 0);
            gs.fill(colorAmarillo);
            gs.textFont(font16Ver);
            gs.textAlign(CENTER, CENTER);
            gs.text(textErrorGS, gs.width/2, gs.height/2);
        } else {
            gs.noStroke();
            gs.rectMode(CENTER);
            gs.fill(colorNegro);
            gs.rect(gs.width/2, gs.height/3*2, gs.width-3, 30);
            
            gs.textAlign(CENTER, CENTER);
            //gs.fill(colorBlanco);
            gs.fill(colorFucsia);
            gs.textFont(font20Ver);
            gs.text(round(gsLocal), gs.width/2, gs.height/3*2);
            
            //gs.fill(colorAzulCielo);
            gs.fill(colorFucsia);
            gs.textFont(font16Ver);
            gs.text("GS", gs.width/4, gs.height/6);
            gs.textFont(font16Ver);
            gs.text(unidadesVelocidad, gs.width/4*3, gs.height/6);
        }

        

        gs.endDraw();
        image(gs, 0, 0, 100, 50);
    }   
}
class Gps {
    PGraphics gps;
    String fechaUTC;
    String fechaLT;
    float senyalMedia;
    float gsGpsKmh;
    float gsGpsKt;
    float gsGpsMph;
    float gsGpsMostrar;
    float altGpsFt;
    float altGpsM;
    float altGpsMostrar;
    //String fechaUTCgps;
    //String fechaLTgps;

    Gps() {
        gps = createGraphics(width, height);
        gps.beginDraw();
        gps.background(0);
        gps.endDraw();
        this.senyalMedia = 0.0f;
        this.fecha_utc();
        this.fecha_local();
        //this.fecha_utc_gps();
        //this.fecha_local_gps();
    }

    public float getSenyalMedia(){
        return this.senyalMedia;
    }

    public void setSenyalMedia(float mySenyalMedia){
        this.senyalMedia = mySenyalMedia;
    }
    public void calcularSenyalMedia(float[] senyalesGPS){
        int j = 0;
        float acumuladoSenyal = 0;

        for (int i = 0; i < MAX_SATELLITES; ++i) {
            if (senyalesGPS[i] != 0 && senyalesGPS[i] <= 100) {
                j +=1;
                acumuladoSenyal = acumuladoSenyal + senyalesGPS[i];
            }
        }
        setSenyalMedia(acumuladoSenyal/j);
        //println("acumuladoSenyal: "+acumuladoSenyal);
        //println("j: "+j);
    }
    public void draw(float gpsGSvalue, float gpsCRSvalue) {
        this.fecha_utc();
        this.fecha_local();
        //this.fecha_utc_gps();
        //this.fecha_local_gps();
        gps.beginDraw();
        gps.background(0);
        
        if (!isactivoGPS) {
            gps.stroke(colorRojo);
            gps.strokeWeight(2);
            gps.line(0, 0, gps.width, gps.height);
            gps.line(0, gps.height, gps.width, 0);
            gps.fill(colorAmarillo);
            gps.textFont(font16Ver);
            gps.textAlign(CENTER, CENTER);
            gps.text("GPS", gps.width/2, gps.height/2);
        } else {

            calcularSenyalMedia(satsSignals);

            calcularVelocidades();

            calcularAltitudes();

            gps.textAlign(LEFT, CENTER);
            gps.fill(colorNaranja);
            gps.stroke(colorNaranja);
            gps.textFont(font25VerBold);

            gps.text(textInfoGPS, 50, 20);
            //gps.line(50, 35, 185, 35);

            gps.textFont(font20Ver);
            gps.text(textLocalGPS, 50, 80);
            gps.text(textUTCGPS, 50, 110);
            gps.text(textLatGPS, 50, 140);
            gps.text(textLonGPS, 50, 170);
            gps.text(textGSGPS, 50, 200);
            gps.text(textCursoGPS, 50, 230);
            gps.text(textAltGPS, 50, 260);

            gps.fill(colorBlanco);
            gps.textFont(font20Ver);
            gps.textAlign(RIGHT, CENTER);
            gps.text(fechaLT, 300, 80);
            gps.text(fechaUTC, 300, 110);
            gps.text(nfs(lat, 2, 6), 300, 140);
            gps.text(nfs(longt, 3, 6), 300, 170);
            gps.text(nf((int)gsGpsMostrar, 3), 300, 200);//sog
            gps.text(nf((int)cog, 3), 300, 230);//cog
            gps.text(nf(round(altGpsMostrar), 5), 300, 260);//gpsalt

            gps.textAlign(LEFT, CENTER);
            gps.fill(colorNaranja);
            gps.text(unidadesVelocidad, 305, 200);//sog unidadesVelocidad  
            gps.text(unidadesALT, 305, 260);//gpsalt unidadesALT 

            this.dibujarRadar();

            gps.popMatrix();

            gps.textAlign(LEFT, CENTER);
            gps.textFont(font20Ver);
            gps.fill(colorNaranja);
            gps.text(textSatActivosGPS, 50, gps.height-90);
            gps.text(textSenalGPS, 50, gps.height-60);

            gps.textAlign(RIGHT, CENTER);
            gps.fill(colorBlanco);
            gps.text(PApplet.parseInt(gpsnum), 50+10+180, gps.height-90);
            gps.text(nfs(this.senyalMedia, 0, 1), 50+10+180, gps.height-60);
            gps.fill(colorNaranja);
            gps.text("dBs", 50+10+220, gps.height-60);
        }

        gps.endDraw();
        image(gps, 0, 0, width, height);
    }
    
    private void dibujarRadar(){
        gps.stroke(colorBlanco);
        gps.noFill();
        gps.ellipseMode(CENTER);
        gps.ellipse(gps.width/4*3, gps.height/2*1, (gps.width/4-30)*2, (gps.width/4-30)*2);//exterior
        gps.ellipse(gps.width/4*3, gps.height/2*1, (gps.width/4-30)*2/3*2, (gps.width/4-30)*2/3*2);//medio
        gps.ellipse(gps.width/4*3, gps.height/2*1, (gps.width/4-30)*2/3, (gps.width/4-30)*2/3);//interior

        for(float i = 0; i < 360; i +=30.0f) {
            gps.pushMatrix();
            gps.translate(gps.width/4*3, gps.height/2*1);
            gps.rotate(radians(i));
            gps.strokeWeight(1);
            gps.line(0, 0, 0, 170);
            gps.popMatrix();
        }
        
        gps.pushMatrix();
        gps.textFont(font22Ver);
        gps.textAlign(CENTER, CENTER);
        gps.translate(gps.width/4*3, gps.height/2*1);
        gps.text("N", 1, -190);
        gps.text("S", 1, 190);
        gps.text("E", 190, 0);
        gps.text("W", -190, 0);
        gps.textFont(font14Ver);
        //Revisar esto para mandar solo satélites validos, por ejemplo desde arduino, que mande 1º cuantos va a mandar y asi trasladalo a una variable de limite en processing (no MAX_SATELLITES)
        for (int i = 0; i < MAX_SATELLITES; ++i) {
            if (satsSignals[i]>0 && satsSignals[i]<100 && satsElevation[i]>=0 && satsAzimuth[i]>=0) {
                gps.fill(colorVerdeFosfo);
                gps.noStroke();
                gps.ellipseMode(CENTER);
                gps.ellipse(satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])), 20, 20);
                gps.fill(colorNegro);
                gps.text(i+1, satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])));
            }
            if (satsSignals[i] == 0 && (satsElevation[i]>0 || satsAzimuth[i]>0)) {
                gps.fill(colorRojo);
                gps.noStroke();
                gps.ellipseMode(CENTER);
                gps.ellipse(satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])), 20, 20);
                gps.fill(colorNegro);
                gps.text(i+1, satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])));
            }
        }
/*
        gps.fill(colorVerdeFosfo);
        gps.noStroke();
        gps.ellipseMode(CENTER);
        gps.ellipse(0, 10, 10, 10);
        gps.ellipse(-10, 30, 10, 10);
        gps.ellipse(25, -45, 10, 10);
        gps.ellipse(90, 0, 10, 10);
        gps.ellipse(70, -35, 10, 10);
        gps.fill(colorRojo);
        gps.ellipse(0, -10, 10, 10);
        gps.ellipse(10, -30, 10, 10);
        gps.ellipse(-25, 45, 10, 10);
        gps.ellipse(-90, 0, 10, 10);
        gps.ellipse(-70, 35, 10, 10);
*/
    }

    public void calcularVelocidades(){
        gsGpsKmh = sog;
        gsGpsKt = sog/1.852f;
        gsGpsMph = sog*0.621371f;

        if (unidadesVel == 1) {
            gsGpsMostrar = gsGpsKmh;
        } else if (unidadesVel == 2) {
            gsGpsMostrar = gsGpsKt;
        } else {
            gsGpsMostrar = gsGpsMph;
        }
    }
    
    public void calcularAltitudes(){
        altGpsFt = gpsalt*3.28084f;
        altGpsM = gpsalt;
        if (unidadesAlt == 1) {
            altGpsMostrar = altGpsM;
        } else {
            altGpsMostrar = altGpsFt;
        }
    }

    private void fecha_utc(){ 
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        Date fechaDate = new Date();
        fechaUTC = formateador.format(fechaDate);
    }

    private void fecha_local(){
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
        }

        Date fechaDate = new Date();
        fechaLT = formateador.format(fechaDate);

        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

        //Date fechaDate = new Date();
        //fechaLT = formateador.format(fechaDate);
    }
/*
    private void fecha_utc_gps(){
        
        String input = "2016-11-09 "+nf(int(satHH), 2)+":"+nf(int(satMM), 2)+":"+nf(int(satSS), 2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime formatDateTime = LocalDateTime.parse(input, formatter);
        
        fechaUTCgps = formatDateTime.getHour() +":"+ formatDateTime.getMinute() +":"+ formatDateTime.getSecond();

        //println(fechaUTCgps);
    }

    private void fecha_local_gps(){
        String input = "2016-11-09 "+nf(int(satHH), 2)+":"+nf(int(satMM), 2)+":"+nf(int(satSS), 2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime formatDateTime = LocalDateTime.parse(input, formatter);

        
        fechaLTgps = formatDateTime.atZone(ZoneId.systemDefault()).getHour() +":"+ formatDateTime.atZone(ZoneId.systemDefault()).getMinute() +":"+ formatDateTime.atZone(ZoneId.systemDefault()).getSecond();
    }
*/
}
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


public void cargarIdiomaInicial(){
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

public void cargarIdioma(){
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

public void position(){
  
  float g0 = 1;
  gravityCompensateDynAcc();
  
  motionDetect_transition = motionDetect - motionDetect_old;
  
  if(motionDetect < 1){
    statex = statex + dyn_acc[0];
    statey = statey + dyn_acc[1];
    statez = statez + dyn_acc[2];
    state_cnt = state_cnt + 1;
  }
  
  if(motionDetect_transition == 1){
    statex_avg = statex / state_cnt;
    statey_avg = statey / state_cnt;
    statez_avg = statez / state_cnt;
    state_cnt = 0;
    statex = 0;
    statey = 0;
    statez = 0;
  } 
  
  motionDetect_old = motionDetect;
   
  accelerationx[1] = (dyn_acc[0]-statex_avg)*g0;
  accelerationy[1] = (dyn_acc[1]-statey_avg)*g0;
  accelerationz[1] = (dyn_acc[2]-statex_avg)*g0;
  
  dts = dt;
  
  //first ntegration:
  velocityx[1] = velocityx[0] + ((accelerationx[0]+accelerationx[1])/2)*dts;
  velocityy[1] = velocityy[0] + ((accelerationy[0]+accelerationy[1])/2)*dts;
  velocityz[1] = velocityz[0] + ((accelerationz[0]+accelerationz[1])/2)*dts;
  
  //if no motion is detected set velocity to zero to reduce errors propagating to position
  if(motionDetect < 1) {
    velocityx[1] = 0.0f;
    velocityy[1] = 0.0f;
    velocityz[1] = 0.0f;
  }

  //second  integration:
  positionX[1]= positionX[0] + ((velocityx[0]+velocityx[1])/2)*dts;
  positionY[1]= positionY[0] + ((velocityy[0]+velocityy[1])/2)*dts;
  positionZ[1]= positionZ[0] + ((velocityz[1]+velocityz[1])/2)*dts;
  
  accelerationx[0] = accelerationx[1];   //The current acceleration value must be sent 
                                         //to the previous acceleration 
  accelerationy[0] = accelerationy[1];   //variable in order to introduce the new 
                                         //acceleration value.
  accelerationz[0] = accelerationz[1];                   
    
  velocityx[0] = velocityx[1];           //Same done for the velocity variable
  velocityy[0] = velocityy[1];
  velocityz[0] = velocityz[1];
  
  positionX[0]= positionX[1];
  positionY[0]= positionY[1];
  positionZ[0]= positionZ[1];
  
  
  
}

  
class OtrasUnidades {
    PGraphics otrasUnidadesIzq;
    PGraphics otrasUnidadesDch;

    OtrasUnidades() {
        otrasUnidadesIzq = createGraphics(100, 100);
        otrasUnidadesIzq.beginDraw();
        otrasUnidadesIzq.background(0);
        otrasUnidadesIzq.endDraw();

        otrasUnidadesDch = createGraphics(100, 100);
        otrasUnidadesDch.beginDraw();
        otrasUnidadesDch.background(0);
        otrasUnidadesDch.endDraw();
    }

    public void draw() {
        //Inicio parte izquierda ///////////////////////////////////////////
        otrasUnidadesIzq.beginDraw();
        otrasUnidadesIzq.background(0);

        otrasUnidadesIzq.stroke(colorBlanco);
        otrasUnidadesIzq.line(0, otrasUnidadesIzq.height/2, otrasUnidadesIzq.width, otrasUnidadesIzq.height/2);

        otrasUnidadesIzq.fill(colorAzulCielo);
        otrasUnidadesIzq.stroke(colorNaranja);
        otrasUnidadesIzq.textFont(font14Ver);
        otrasUnidadesIzq.textAlign(LEFT, CENTER);
        otrasUnidadesIzq.text("IAS", 5, otrasUnidadesIzq.height/10);
        otrasUnidadesIzq.text("GS", 5, (otrasUnidadesIzq.height/10)*6);

        otrasUnidadesIzq.fill(colorNaranja);
        otrasUnidadesIzq.stroke(colorNaranja);
        otrasUnidadesIzq.textFont(font14Ver);
        otrasUnidadesIzq.textAlign(LEFT, CENTER);
        otrasUnidadesIzq.text(otrasUdVel1, otrasUnidadesIzq.width-45, otrasUnidadesIzq.height/4);
        otrasUnidadesIzq.text(otrasUdVel2, otrasUnidadesIzq.width-45, (otrasUnidadesIzq.height/4)*1.5f);
        otrasUnidadesIzq.text(otrasUdVel1, otrasUnidadesIzq.width-45, (otrasUnidadesIzq.height/4)*3);
        otrasUnidadesIzq.text(otrasUdVel2, otrasUnidadesIzq.width-45, (otrasUnidadesIzq.height/4)*3.5f);
        
        otrasUnidadesIzq.textAlign(RIGHT, CENTER);
        if (isactivo10DOF) {
            otrasUnidadesIzq.text(otrasIAS1, otrasUnidadesIzq.width-60, otrasUnidadesIzq.height/4);
            otrasUnidadesIzq.text(otrasIAS2, otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*1.5f);
        } else {
            otrasUnidadesIzq.fill(colorRojo);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, otrasUnidadesIzq.height/4);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*1.5f);
        }
        if (isactivoGPS) {
            otrasUnidadesIzq.text(otrasGS1, otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3);
            otrasUnidadesIzq.text(otrasGS2, otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3.5f);
        } else {
            otrasUnidadesIzq.fill(colorRojo);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3.5f);
        }

        

        otrasUnidadesIzq.endDraw();
        //Fin parte izquierda //////////////////////////////////////////////
        
        //Inicio parte derecha /////////////////////////////////////////////
        otrasUnidadesDch.beginDraw();
        otrasUnidadesDch.background(0);

        otrasUnidadesDch.stroke(colorBlanco);
        otrasUnidadesDch.line(0, otrasUnidadesDch.height/3, otrasUnidadesDch.width, otrasUnidadesDch.height/3);
        otrasUnidadesDch.line(0, otrasUnidadesDch.height*2/3, otrasUnidadesDch.width, otrasUnidadesDch.height*2/3);

        otrasUnidadesDch.fill(colorAzulCielo);
        otrasUnidadesDch.stroke(colorNaranja);
        otrasUnidadesDch.textFont(font14Ver);
        otrasUnidadesDch.textAlign(LEFT, CENTER);
        otrasUnidadesDch.text("ALT", 5, otrasUnidadesDch.height/10);
        otrasUnidadesDch.text("VS", 5, (otrasUnidadesDch.height/10)*4.25f);
        otrasUnidadesDch.text("QNH", 5, (otrasUnidadesDch.height/10)*7.5f);

        otrasUnidadesDch.fill(colorNaranja);
        otrasUnidadesDch.stroke(colorNaranja);
        otrasUnidadesDch.textFont(font10Ver);
        otrasUnidadesDch.textAlign(LEFT, CENTER);


        otrasUnidadesDch.text(otrasUdALT1, otrasUnidadesDch.width-45, otrasUnidadesDch.height/4);
        otrasUnidadesDch.text(otrasUdVSI1, otrasUnidadesDch.width-45, (otrasUnidadesDch.height/4)*2.25f);
        otrasUnidadesDch.text(otrasUdQNH1, otrasUnidadesDch.width-45, (otrasUnidadesDch.height/4)*3.75f);

        otrasUnidadesDch.textFont(font12Ver);
        otrasUnidadesDch.textAlign(RIGHT, CENTER);

        if (!isactivo10DOF) {
            otrasUnidadesDch.fill(colorRojo);
            otrasUnidadesDch.text("XXXX", otrasUnidadesDch.width-50, otrasUnidadesDch.height/4);
            otrasUnidadesDch.text("XXXX", otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*2.25f);
            otrasUnidadesDch.text("XXXX", otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*3.75f);
        } else {
            otrasUnidadesDch.text(otrasALT1, otrasUnidadesDch.width-50, otrasUnidadesDch.height/4);
        
            if (opcionMxS.isSelected()) {
                otrasUnidadesDch.text(round(otrasVSI1), otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*2.25f);
            } else {
                otrasUnidadesDch.text(nfs(otrasVSI1, 0, 1), otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*2.25f);
            }
            if (opcionMMhg.isSelected()) {
                otrasUnidadesDch.text(otrasQNH1, otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*3.75f);
            } else {
                otrasUnidadesDch.text(otrasQNH2, otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*3.75f);
            }
        }

        
        

        otrasUnidadesDch.endDraw();
        //Fin parte derecha ////////////////////////////////////////////////

        image(otrasUnidadesIzq, myAnemometro.anemo.width, height-100, 100, 100);
        image(otrasUnidadesDch, width-myAltimeter0.alt.width-otrasUnidadesDch.width, height-100, 100, 100);
    }
}
class Qnh {
    PGraphics qnh;
    float qnhHPa = 1023.0f;
    float qnhIn = qnhHPa*0.00029529983071445f;
    Qnh() {
        qnh = createGraphics(120, 50);
        qnh.beginDraw();
        //qnh.background(90, 90, 90, 191);
        qnh.background(0, 0, 0);
        qnh.endDraw();
    }

    public void draw(float value) {
        qnh.beginDraw();
        //qnh.background(90, 90, 90, 191);
        qnh.background(0, 0, 0);
        
        if (!isactivo10DOF) {
            qnh.stroke(colorRojo);
            qnh.strokeWeight(2);
            qnh.line(0, 0, qnh.width, qnh.height);
            qnh.line(0, qnh.height, qnh.width, 0);
            qnh.fill(colorAmarillo);
            qnh.textFont(font16Ver);
            qnh.textAlign(CENTER, CENTER);
            qnh.text(textErrorQNH, qnh.width/2, qnh.height/2);
        } else {
            setQnhs(value);

            qnh.rectMode(CENTER);
            qnh.fill(colorNegro);
            qnh.noStroke();
            qnh.rect(qnh.width/2, qnh.height/3*2, qnh.width-3, 30);

            qnh.textAlign(CENTER, CENTER);
            qnh.fill(colorBlanco);
            qnh.textFont(font20Ver);
            if (opcionMMhg.isSelected()) {
                qnh.text(round(qnhHPa), qnh.width/2, qnh.height/3*2);
            } else {
                qnh.text(nf(qnhIn, 2, 2), qnh.width/2, qnh.height/3*2);
            }
            
            qnh.fill(colorAzulCielo);
            qnh.textFont(font16Ver);
            qnh.text("QNH", qnh.width/4, qnh.height/6);
            qnh.textFont(font16Ver);
            qnh.text(unidadesQNH, qnh.width/4*3, qnh.height/6);
        }

        
        
        qnh.endDraw();
        image(qnh, 0, 0, 120, 50);
    }

    private void setQnhs(float value){
        qnhHPa = value;
        qnhIn = qnhHPa*0.029529983071445f;
    }
}

class Rumbo {
    PGraphics hdg;
    PGraphics hdgDisp;

    float hdgValue = 0.0f;

    Rumbo() {
        hdg = createGraphics(580, 50);
        hdg.beginDraw();
        hdg.background(colorNegro);
        hdg.noStroke();
        hdg.endDraw();

        hdgDisp = createGraphics(580, 50);
        hdgDisp.beginDraw();
        hdgDisp.background(colorNegro, 0);
        hdgDisp.noStroke();
        hdgDisp.endDraw();
    }

    public void setHDGvalue(float theHDGvalue){
        this.hdgValue = theHDGvalue;
    }
    public float getHDGvalue(){
        return this.hdgValue;
    }

    public void draw(float theHdgValue) {

        hdg.beginDraw();
        hdg.background(90, 90, 90, 127);
        hdg.strokeWeight(2);
        hdg.strokeCap(SQUARE);

        setHDGvalue(theHdgValue);
        
        if (!isactivo10DOF) {
            hdg.background(colorNegro);
            hdg.stroke(colorRojo);
            hdg.strokeWeight(2);
            hdg.line(0, 0, hdg.width, hdg.height);
            hdg.line(0, hdg.height, hdg.width, 0);
            hdg.fill(colorAmarillo);
            hdg.textFont(font16Ver);
            hdg.textAlign(CENTER, CENTER);
            hdg.text(textErrorHDG, hdg.width/2, hdg.height/2);
        } else {
            //hdg.translate(hdg.width/2, 0);
            hdg.textAlign(CENTER, CENTER);
            hdg.stroke(colorTextoFormas);
            hdg.fill(colorTextoFormas);
            hdg.strokeWeight(2);
            hdg.strokeCap(SQUARE);
            if (textoNegrita) {
                hdg.textFont(font18VerBold, 18);
            } else {
                hdg.textFont(font18Ver, 18);
            }

            //hdg.translate(hdg.width/2, 100);
            rosaVientos();
            //hdg.translate(-hdg.width/2, -100);
        }

        hdg.endDraw();
        image(hdg, 100, 0, hdg.width, hdg.height);
    }

    public void rosaVientos(){
        float hdgLocal = getHDGvalue();

        //hdg.noStroke();

        //hdg.pushMatrix();  
        hdg.fill(colorNegro, 127);
        hdg.stroke(colorBlanco);
        hdg.ellipse(hdg.width/2, 800, 1600, 1600);
        hdg.translate(hdg.width/2, 800);
        hdg.rotate(radians(hdgLocal));//ojo, aqui hay que poner un - dependiendo de la imu
        for(int i = 0; i < 360; i+=5) {
            hdg.pushMatrix();
            hdg.rotate(radians(i));
            hdg.stroke(colorBlanco);
            hdg.line(0, -800, 0, -790);
            hdg.textFont(font16Ver);
            hdg.fill(colorBlanco);
            hdg.textAlign(CENTER);
            hdg.text(nf(i, 3), 0, -775);
            hdg.popMatrix();
        }
        for(int i = 0; i < 360; i++) {
            hdg.pushMatrix();
            hdg.rotate(radians(i));
            hdg.stroke(colorBlanco);
            hdg.line(0, -800, 0, -795);
            hdg.popMatrix();
        }
        //hdg.rotate(-PI-radians(hdgLocal));
        hdg.translate(-hdg.width/2, -800);
        //hdg.popMatrix();

        //hdg.pushMatrix(); 

        //hdg.strokeWeight(2);

        //hdg.popMatrix();

    }

    public void drawRect(float theHdgValue) {

        hdgDisp.beginDraw();
        
        if (isactivo10DOF) {
            hdgDisp.stroke(colorBlanco);
            hdgDisp.strokeWeight(1);
            hdgDisp.strokeCap(SQUARE);

            setHDGvalue(theHdgValue);

            hdgDisp.translate(0, 0);

            hdgDisp.fill(colorNegro);
            hdgDisp.beginShape();
            hdgDisp.vertex(hdgDisp.width/2, hdgDisp.height-45);//Punta
            hdgDisp.vertex(hdgDisp.width/2-5, hdgDisp.height-35);//Picos arriba izq
            hdgDisp.vertex(hdgDisp.width/2-25, hdgDisp.height-35);//Picos arriba izq
            hdgDisp.vertex(hdgDisp.width/2-25, hdgDisp.height-1);//Pico abajo izq
            hdgDisp.vertex(hdgDisp.width/2+25, hdgDisp.height-1);//Pico abajo dch
            hdgDisp.vertex(hdgDisp.width/2+25, hdgDisp.height-35);//Picos arriba dcha
            hdgDisp.vertex(hdgDisp.width/2+5, hdgDisp.height-35);//Picos arriba dcha
            hdgDisp.endShape(CLOSE);
            hdgDisp.textFont(font18Ver, 18);
            hdgDisp.fill(colorBlanco);
            hdgDisp.textAlign(CENTER, CENTER);
            hdgDisp.text(nf(round(360-getHDGvalue()), 3), hdgDisp.width/2, hdgDisp.height-20);
        }

        hdgDisp.endDraw();
        image(hdgDisp, 100, 0, hdgDisp.width, hdgDisp.height);
    }
}

/*
//  note ; 7 " display is 800x480
//  zone heading est 400pix * 60 pix  pour 50 degres
//  un degre= 8 pixels
void rumbo(){
    float valorHeading = corr_heading;
    hdg.background(90, 90, 90, 127);
    hdg.strokeWeight(2);
    hdg.strokeCap(SQUARE);
    
    //hdg.stroke(colorVerde);
    //hdg.line(hdg.width/2, 0, hdg.width/2, hdg.height);
    //hdg.line(0, hdg.height/2, hdg.width, hdg.height/2);
    
    //   calcul pour la simulation / test
    //if (mouseX < 200) {heading = heading +  0.01 *((mouseX - 200));}
    //if (mouseX > 350) {heading = heading +  0.01*((mouseX - 350));}
      
    if (valorHeading>= 360) {valorHeading = 0;}  // limite droite
    if (valorHeading<0) { valorHeading = 360;}  //  limite gauche
  
    //spd.textSize(20);          // chiffres blancs ******************************

    if (textoNegrita) {
        hdg.textFont(font20VerBold, 20);
    } else {
        hdg.textFont(font20Ver, 20);
    }

    // heading=120 ;   // a effacer
    hdg.noStroke();  // pas de bord

    hdg.pushMatrix();  //    partie chiffres XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    calc3 = ((-valorHeading)*8)+192-40;   //  decalage
    hdg.translate(calc3, 0);

    hdg.fill(colorTextoFormas);
    hdg.textSize(25);  //  textes en 28
    if ((valorHeading >= 0) && (valorHeading < 90)){
        hdg.textSize(25);
        hdg.text ("0",0,50);  // espaces de 240 pix
        hdg.text ("3",240,50); 
        hdg.text ("6",480,50);
        hdg.text ("9",720,50);
        hdg.textSize(18);   // textes en 25
        hdg.text("34",-168,45);
        hdg.text("35",-88,45);
        hdg.text("1",80,45); // decale de 80 pix et espace de  240 pix
        hdg.text("2",160,45);
        hdg.text("4",320,45);
        hdg.text("5",400,45);
        hdg.text("7",560,45);
        hdg.text("8",640,45);
        hdg.text("10",792,45);
        hdg.text("11",872,45);
    }
    if ((valorHeading >= 90) && (valorHeading < 180)){  
        hdg.textSize(25);
        hdg.text ("9",720,50);
        hdg.text ("12",950,50); 
        hdg.text ("15",1190,50);
        hdg.text ("18",1430,50);
        hdg.textSize(18);
        hdg.text("7",560,45);
        hdg.text("8",640,45);
        hdg.text("10",792,45);
        hdg.text("11",872,45);
        hdg.text("13",1032,45);
        hdg.text("14",1112,45);
        hdg.text("16",1272,45);
        hdg.text("17",1352,45);
        hdg.text("19",1512,45);
        hdg.text("20",1592,45); 
    }
    if ((valorHeading >= 180) && (valorHeading < 270)){          
        hdg.textSize(25);
        hdg.text ("15",1190,50);
        hdg.text ("18",1430,50);  
        hdg.text ("21",1670,50);
        hdg.text ("24",1910,50);
        hdg.text ("27",2150,50);
        hdg.textSize(18);
        hdg.text("16",1272,45);
        hdg.text("17",1352,45);
        hdg.text("19",1512,45);
        hdg.text("20",1592,45);
        hdg.text("22",1752,45);
        hdg.text("23",1832,45);
        hdg.text("25",1992,45);
        hdg.text("26",2072,45);
        hdg.text("28",2232,45);
        hdg.text("29",2312,45);
    }
    if ((valorHeading >= 270) && (valorHeading < 360)){   
        hdg.textSize(25);
        hdg.text ("24",1910,50);
        hdg.text ("27",2150,50);
        hdg.text ("30",2390,50);
        hdg.text ("33",2630,50);
        hdg.text ("0",2880,50);
        hdg.textSize(18);
        hdg.text("23",1832,45);
        hdg.text("25",1992,45);
        hdg.text("26",2072,45);
        hdg.text("28",2232,45);
        hdg.text("29",2312,45);
        hdg.text("31",2472,45);
        hdg.text("32",2552,45);
        hdg.text("34",2712,45);
        hdg.text("35",2792,45);
        hdg.text("1",2960,45);
        hdg.text("2",3040,45);
        hdg.text("34",-168,45);
        hdg.text("35",-88,45); 
    }  

    hdg.popMatrix();  // 

    hdg.pushMatrix();  //    partie traits blancs  XXXXXXXXXXXXXXXXXXXXXXXXXXXXX

    hdg.stroke(colorTextoFormas);  //  couleur blanc du trait
    hdg.strokeWeight(2);
    for(calc4=0; calc4<=400; calc4=calc4+80){
        calc5 = (calc4 -(valorHeading % 10)*8)+0;   // modulo
        hdg.line (calc5,0,calc5,20);  //   une barre tous les 5 degres = 40 pixels
        hdg.line (calc5+40,0,calc5+40,10);  //   une barre courte
    }

    hdg.popMatrix();

    if (textoNegrita) {
        hdg.textFont(font20VerBold, 20);
    } else {
        hdg.textFont(font20Ver, 20);
    }

    hdg.translate(hdg.width/2-30, hdg.height/2);
    myAnemometro.anemo.textAlign(CENTER, CENTER);
    hdg.strokeWeight(1.5);
    hdg.stroke(colorBlanco);
    hdg.fill(colorNegro);
    hdg.beginShape();
    hdg.vertex(myAnemometro.anemo.width/2, -10);
    hdg.vertex(myAnemometro.anemo.width/2-24, 0);
    hdg.vertex(myAnemometro.anemo.width/2-24, 24);
    hdg.vertex(myAnemometro.anemo.width/2+24, 24);
    hdg.vertex(myAnemometro.anemo.width/2+24, 0);
    hdg.endShape(CLOSE);
    hdg.fill(colorBlanco);
    hdg.text(nf(round(valorHeading), 3), 20, 20);

    hdg.translate(30-hdg.width/2, -hdg.height/2);
}
*/
class Temp {
    PGraphics temp;

    float localTempC = 0.0f; //Este en ºC
    float localTempF = 0.0f; //Este en ºF

    Temp() {
        temp = createGraphics(70, 20);
        temp.beginDraw();
        temp.background(0);
        temp.endDraw();
    }
    
    public void setTempC(float newTemp) {
        this.localTempC = newTemp;
    }
    public float getTempC() {
        return this.localTempC;
    }

    public void setTempF(float newTemp) {
        this.localTempF = newTemp*1.8f+32;
    }
    public float getTempF() {
        return this.localTempF;
    }
    
    public void draw(float theTemp) {

        actualizarValoresTemp(theTemp);//Ojo, la theTemp viene en ºC

        temp.beginDraw();
        temp.background(0);
        if (isactivoTEMP) {
            temp.fill(colorBlanco);
            temp.textFont(font14Ver);
            temp.textAlign(CENTER, CENTER);
            if (unidadesTemp == 1) {
                temp.text(nfs(getTempC(), 2, 1) + " " + unidadesTEMP, temp.width/2, temp.height/2);
            } else {
                temp.text(nfs(getTempF(), 2, 1) + " " + unidadesTEMP, temp.width/2, temp.height/2);
            }
        } else {
            temp.stroke(colorRojo);
            temp.strokeWeight(2);
            temp.line(0, 0, temp.width, temp.height);
            temp.line(0, temp.height, temp.width, 0);
            temp.fill(colorAmarillo);
            temp.textFont(font14VerBold);
            temp.textAlign(CENTER, CENTER);
            temp.text(textErrorTEMP, temp.width/2, temp.height/2);
        }
        
        
        temp.endDraw();
        image(temp, 0, 0, 70, 20);
    }

    private void actualizarValoresTemp(float newTemp) {
        setTempC(newTemp);
        setTempF(newTemp);
    }
}
PrintWriter outputTime;
BufferedReader reader;
String efisTimeString;
long millisAnteriores = 0;
long tiempoEfisEncendido;
long efisTimeCargadoLong;
long efisTimeGuardarLong;

public void tiempoEfisEncendido(){
    if (millis() - millisAnteriores > 60000) {//cada 60 segundos
        tiempoEfisEncendido = tiempoEfisEncendido + millis() - millisAnteriores;
        //println(tiempoEfisEncendido);
        efisTimeGuardarLong = efisTimeCargadoLong + tiempoEfisEncendido;
        //println(efisTimeGuardarLong);
        outputTime = createWriter("data/efis-hours");
        outputTime.println(efisTimeGuardarLong);
        outputTime.flush();
        outputTime.close();

        //efisTimeGuardarLong = efisTimeCargadoLong + tiempoEfisEncendido;

        efisHOURS();

        millisAnteriores = millis();
    }
}

public void efisHOURS(){
    long num; 
    float horas;
    String shoras;

    num = efisTimeGuardarLong/1000;
    horas = ((float)num)/3600;
    shoras = nf(horas, 1, 2);

    jsonInfoSistema.setString("floatHorasFuncionamiento", shoras);
    saveJSONObject(jsonInfoSistema, "data/infoSistema.json");

    textLabelIhoras.setText(textHorasFuncionamiento+" "+jsonInfoSistema.getString("floatHorasFuncionamiento"));

    print(num);print("\t");println(jsonInfoSistema.getString("floatHorasFuncionamiento"));

    cargarInfoSistema();
}
class TopLbar {
    PGraphics tLb;

    TopLbar() {
        tLb = createGraphics(100, 50);
        tLb.beginDraw();
        tLb.background(0, 0, 0);
        tLb.endDraw();
    }

    public void draw() {
        tLb.beginDraw();

        if (!toggleIAS.isSelected()) {
            tLb.background(90, 90, 90, 191);
        } else {
            tLb.background(90, 90, 90, 127);
        }

        if (!isactivo10DOF) {
            tLb.background(colorNegro);
        } else {
            tLb.fill(colorAzulCielo);
            tLb.textFont(font14Ver, 14);
            tLb.textAlign(CENTER, CENTER);
            tLb.text(unidadesVelocidad, tLb.width/2, tLb.height-20); 
        }

        tLb.endDraw();
        image(tLb, 0, 0, 100, 50);
    }   
}
class TopRbar {
    PGraphics tRb;
    float valorVSILocalFtMin = 0.0f;//vsi en ft/min
    float valorVSILocalMS = 0.0f;//vsi en m/s

    TopRbar() {
        tRb = createGraphics(120, 50);
        tRb.beginDraw();
        tRb.background(0, 0, 0);
        tRb.endDraw();
    }
    public void setVSIlocalFtMin(float vsiFtMin){
        this.valorVSILocalFtMin = vsiFtMin;
    }
    public float getVSIlocalFtMin(){
        return this.valorVSILocalFtMin;
    }
    public void setVSIlocalMS(float vsiFtMin){
        this.valorVSILocalMS = vsiFtMin * 0.00508f;
    }
    public float getVSIlocalMS(){
        return this.valorVSILocalMS;
    }


    public void draw(float vsiFtMin) {
        tRb.beginDraw();

        if ((!toggleAlt.isSelected()) || (!toggleVario.isSelected())) {
            tRb.background(90, 90, 90, 191);
        } else {
            tRb.background(90, 90, 90, 127);
        }
        
        if (!isactivo10DOF) {
            tRb.background(colorNegro);
        } else {
            actualizarValoresVSIlocal(vsiFtMin);
            tRb.fill(colorAzulCielo);
            tRb.textFont(font14Ver, 14);
            tRb.textAlign(CENTER, CENTER);
            tRb.text(unidadesALT, tRb.width/4, tRb.height-10);

            tRb.text(unidadesVSI, tRb.width/4*3, tRb.height-30);
            tRb.fill(colorBlanco);

            if (unidadesVsi == 1) {
                tRb.text(nfs(getVSIlocalMS(), 0, 2), tRb.width/4*3, tRb.height-10);
            } else {
                tRb.text(round(getVSIlocalFtMin()/10)*10, tRb.width/4*3, tRb.height-10); // El /10)*10 lo pongo para redondear a multiplos de 10
            }
        }

        tRb.endDraw();
        image(tRb, 0, 0, 120, 50);
    }
    public void actualizarValoresVSIlocal(float vsiFtMin){
        setVSIlocalFtMin(vsiFtMin);
        setVSIlocalMS(vsiFtMin);
    }
}


/*
void topRbar(){
    float valorVSILocal = valorVSI;//primero en ft/min

    if ((toggleVerALT.getValue() == 0.0) || (toggleVerVSI.getValue() == 0.0)) {
        topRbar.background(90, 90, 90, 191);
    } else {
        topRbar.background(90, 90, 90, 127);
    }

    if (unidadesVsi == 1) {
        valorVSILocal = valorVSI * 0.00508; //si esta en m/s lo pasamos a estoas unidades.
    }

    topRbar.fill(colorAzulCielo);
    topRbar.textFont(font14VerBold, 14);
    topRbar.textAlign(CENTER, CENTER);
    topRbar.text(unidadesALT, topRbar.width/4, topRbar.height-10);

    topRbar.text(unidadesVSI, topRbar.width/4*3, topRbar.height-30);
    topRbar.fill(colorBlanco);

    if (unidadesVsi == 1) {
        valorVSILocal = valorVSI * 0.00508; //si esta en m/s lo pasamos a estoas unidades.
        topRbar.text(nfs(valorVSILocal, 0, 2), topRbar.width/4*3, topRbar.height-10);
    } else {
        topRbar.text(round(valorVSILocal), topRbar.width/4*3, topRbar.height-10);
    }
}
*/
class VerticalSpeed {
    PGraphics vsi;

    VerticalSpeed() {
        vsi = createGraphics(100, 380);
        vsi.beginDraw();
        vsi.background(0);
        vsi.noStroke();
        vsi.endDraw();    
    }

    public void draw(float myVS){
        float vsiMap;
        float myVSLocal = myVS;
        float v = vsiLimit/1000;
        float vsiLimitLocal = vsiLimit;

        if (unidadesVsi == 1) {
            myVSLocal = myVS  * 0.00508f;// ya tenemos la vsi en metros/s
            v = vsiLimitLocal * 0.00508f;// ya tenemos el limite de las rayitas en metros/s
            vsiLimitLocal *= 0.00508f;// ya tenemos el limite de la vsi en metros/s
        }
        
        vsi.beginDraw();
        
        if (toggleAlt.isSelected()) {
            vsi.background(90, 90, 90, 0);
        } else {
            vsi.background(90, 90, 90, 127);
        }
        
        if (!isactivo10DOF) {
            //vsi.background(colorNegro);
            vsi.stroke(colorRojo);
            vsi.strokeWeight(2);
            vsi.line(vsi.width/2, 0, vsi.width, vsi.height);
            vsi.line(vsi.width/2, vsi.height, vsi.width, 0);
            vsi.fill(colorAmarillo);
            vsi.textFont(font14VerBold);
            vsi.textAlign(CENTER, CENTER);
            vsi.text(textErrorVSI, vsi.width/2+25, vsi.height/2);
        } else {
            //vertspeed = vertspeed-((-mouseY+200)/2);//valores para test
            if (myVSLocal > vsiLimitLocal)    {myVSLocal = vsiLimitLocal;}
            if (myVSLocal < (-vsiLimitLocal)) {myVSLocal =-vsiLimitLocal;}
            
            if (toggleAlt.isSelected()) {
                vsi.background(90, 90, 90, 0);
            } else {
                vsi.background(90, 90, 90, 127);
            }

            //vertspeed = 500;

            //Linea que sube o baja segun la VSI
            vsi.strokeWeight(10);
            vsi.strokeCap(SQUARE);
            vsi.stroke(colorFucsia);
            vsiMap = map(myVSLocal, -vsiLimitLocal, vsiLimitLocal, vsi.height-30, 30); //Estos 30 son el espacio entre el inicio del vsi y la primera marca por arriba y por abajo
            vsi.line(vsi.width-5, vsi.height/2, vsi.width-5, vsiMap);

            vsi.strokeWeight(1);
            vsi.stroke(colorTextoFormas);
            vsi.textAlign(RIGHT, CENTER);
            vsi.textFont(font18VerBold, 12);
            vsi.fill(colorTextoFormas);
            
            if (unidadesVsi == 1) {//si es en m/s
                //float v = vsiLimit/1000;
                for(int i = 30; i <= vsi.height-30; i += 40) {//long lines vsi
                    vsi.line(vsi.width-12, i, vsi.width, i);
                    vsi.text(nf(v, 1, 1), vsi.width-13, i-1);// el -1 es para centrarlo mejor
                    v -= 2.54f;
                }

                for(int i = 30+8; i < vsi.height-30; i += 8) {//short lines vsi
                    vsi.line(vsi.width-6, i, vsi.width, i);
                }
            } else {//si es en ft/s
                //float v = vsiLimit/1000;
                for(int i = 30; i <= vsi.height-30; i += 40) {//long lines vsi
                    vsi.line(vsi.width-12, i, vsi.width, i);
                    vsi.text(nf(v, 1, 1), vsi.width-13, i-1);// el -1 es para centrarlo mejor
                    v -= 0.5f;
                }

                for(int i = 30+8; i < vsi.height-30; i += 8) {//short lines vsi
                    vsi.line(vsi.width-6, i, vsi.width, i);
                }
            }
        }

        
        

        vsi.endDraw();
        image(vsi, 0, 0);
    }
}

/*
void verticalspeed(){
    float  vsiMap;
    float myVS = valorVSI;
    //valores para test
    //vertspeed = vertspeed-((-mouseY+200)/2);
    if (myVS > vsiLimit)    {myVS = vsiLimit;}
    if (myVS < (-vsiLimit)) {myVS =-vsiLimit;}
    
    if (toggleVerALT.getValue() == 1.0) {
        vsi.background(90, 90, 90, 0);
    } else {
        vsi.background(90, 90, 90, 127);
    }
    
    //debbug lines
    //vsi.stroke(colorAmarillo);
    //vsi.line(0, vsi.height/2, vsi.width, vsi.height/2);//linea debug horizontal
    //vsi.line(vsi.width/2, 0, vsi.width/2, vsi.height);//linea debug vertical

    //vertspeed = 500;

    //Linea que sube o baja segun la VSI
    vsi.strokeWeight(10);
    vsi.strokeCap(SQUARE);
    vsi.stroke(colorFucsia);
    vsiMap = map(myVS, -vsiLimit, vsiLimit, vsi.height-5, 5);
    vsi.line(vsi.width-5, vsi.height/2, vsi.width-5, vsiMap);

    vsi.strokeWeight(1);
    vsi.stroke(colorTextoFormas);
    vsi.textAlign(RIGHT, CENTER);
    vsi.textFont(font18VerBold, 12);
    vsi.fill(colorTextoFormas);
    float v = vsiLimit/1000;
    for(int i = 5; i <= vsi.height-5; i += 35) {//long lines vsi
        vsi.line(vsi.width-12, i, vsi.width, i);
        vsi.text(nf(v, 1, 1), vsi.width-13, i-1);// el -2 es para centrarlo mejor
        v -= 0.5;
    }

    for(int i = 5+7; i < vsi.height-5; i += 7) {//short lines vsi
        vsi.line(vsi.width-6, i, vsi.width, i);
    }
}
*/

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

public void opciones(){
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
    toggleFecha = new GCheckbox(this, width/25*9.5f, height/16*3, 200, 70);
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
    toggleTemp = new GCheckbox(this, width/25*9.5f, height/16*7, 200, 70);
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
    toggleBola = new GCheckbox(this, width/25*9.5f, height/16*11, 200, 70);
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
    textLabelIdioma = new GLabel(this, width/25-10, height/6*3.3f, 180, 25);
    textLabelIdioma.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIdioma.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelIdioma.setText(textIdiomaInfoUpdate);
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionEsp = new GOption(this, width/25-10, height/6*3.3f+50, 100, 45);
    opcionEsp.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionEsp.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionEsp.setText("Español");
    opcionEsp.setIcon(iconRadio, null, null, null);
    opcionEng = new GOption(this, width/25*4, height/6*3.3f+50, 100, 45);
    opcionEng.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionEng.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionEng.setText("English");
    opcionEng.setIcon(iconRadio, null, null, null);
    opcionFran = new GOption(this, width/25-10, height/6*3.3f+50+60, 105, 45);
    opcionFran.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFran.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFran.setText("Français");
    opcionFran.setIcon(iconRadio, null, null, null);
    opcionDeut = new GOption(this, width/25*4, height/6*3.3f+50+60, 100, 45);
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
    textLabelIdiomaCambiado = new GLabel(this, width/25-10, height/6*5.5f, 250, 25);
    textLabelIdiomaCambiado.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    textLabelIdiomaCambiado.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    groupInfo.addControls(textLabelIdiomaCambiado);

    textLabelIFormatoFH = new GLabel(this, width/25*8, height/6*3.3f, 250, 25);
    textLabelIFormatoFH.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIFormatoFH.setFont(new Font("B612 Regular", Font.PLAIN, 20));
    textLabelIFormatoFH.setText(textFormatoHora);
    opcionesIformatoFecha = new GToggleGroup();
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionFecha1 = new GOption(this, width/25*8, height/6*3.3f+50, 140, 45);
    opcionFecha1.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFecha1.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFecha1.setText(textFormatoFecha1);
    opcionFecha1.setIcon(iconRadio, null, null, null);
    opcionFecha2 = new GOption(this, width/25*12+20, height/6*3.3f+50, 140, 45);
    opcionFecha2.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionFecha2.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionFecha2.setText(textFormatoFecha2);
    opcionFecha2.setIcon(iconRadio, null, null, null);
    opcionesIformatoFecha.addControls(opcionFecha1, opcionFecha2);
    opcionesIformatoHora = new GToggleGroup();
    iconRadio = new GIcon(this, "user_radio.png", 2, 1);
    opcionHora24 = new GOption(this, width/25*8, height/6*3.3f+50+60, 120, 45);
    opcionHora24.setTextAlign(GAlign.LEFT, GAlign.MIDDLE);
    opcionHora24.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    opcionHora24.setText("24H");
    opcionHora24.setIcon(iconRadio, null, null, null);
    opcionHora12 = new GOption(this, width/25*12+20, height/6*3.3f+50+60, 120, 45);
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

    botonTimezone = new GButton(this,width/25*10, height/6*5.3f, 120, 40);
    botonTimezone.setFont(new Font("B612", Font.BOLD, 14));
    botonTimezone.setText(textoBotonTimezone);
    botonTimezone.useRoundCorners(false);
    botonTimezone.setLocalColor(4, colorBotonInactivo);
    botonTimezone.addEventHandler(this, "botonTimezoneClick");
    groupInfo.addControls(botonTimezone);

    textLabelIModelo = new GLabel(this, width/25*18, height/6*3.3f, 250, 25);
    textLabelIModelo.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIModelo.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    textLabelIModelo.setText(textModeloEfis+" "+modeloEfis);
    textLabelIVersion = new GLabel(this, width/25*18, height/6*3.6f, 250, 25);
    textLabelIVersion.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelIVersion.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    textLabelIVersion.setText(textVersionSistema+" "+versionSistema);
    textLabelInSerie = new GLabel(this, width/25*18, height/6*3.9f, 250, 25);
    textLabelInSerie.setTextAlign(GAlign.LEFT, GAlign.CENTER);
    textLabelInSerie.setFont(new Font("B612 Regular", Font.PLAIN, 14));
    textLabelInSerie.setText(textNumeroSerie+" "+numeroSerie);
    textLabelIhoras = new GLabel(this, width/25*18, height/6*4.2f, 250, 25);
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

    textLabelValProp = new GLabel(this, width/16*8.6f, height/25*4, 200, 25);
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


    textLabelAvisoValores = new GLabel(this, 0, height/25*23.5f, 800, 25);
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

public void cargarInfoSistema(){
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
    float value = PApplet.parseFloat(sliderVne.getValueS());
    value++;
    sliderVne.setValue(value);
}
public void botonMenosVneClick(GButton button, GEvent event){
    float value = PApplet.parseFloat(sliderVne.getValueS());
    value--;
    sliderVne.setValue(value);
}
public void botonEnviarVneClick(GButton button, GEvent event){
    println("BangVne!! " + sliderVne.getValueS());
    if (PApplet.parseInt(sliderVne.getValueS()) > jLimiteAmarillo) { //Si es mayor que el límite amarillo podemos escribir por pantalla y guardar en el json
        jLimiteRojo = PApplet.parseInt(sliderVne.getValueS());//asignamos el valor
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
    float value = PApplet.parseFloat(sliderVno.getValueS());
    value++;
    sliderVno.setValue(value);
}
public void botonMenosVnoClick(GButton button, GEvent event){
    float value = PApplet.parseFloat(sliderVno.getValueS());
    value--;
    sliderVno.setValue(value);
}
public void botonEnviarVnoClick(GButton button, GEvent event){
    println("BangVno!! " + sliderVno.getValueS());
    if ((PApplet.parseInt(sliderVno.getValueS()) > jLimiteVerde) && (PApplet.parseInt(sliderVno.getValueS()) < jLimiteRojo)) {
        jLimiteAmarillo = PApplet.parseInt(sliderVno.getValueS());//asignamos el valor
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
    float value = PApplet.parseFloat(sliderVfe.getValueS());
    value++;
    sliderVfe.setValue(value);
}
public void botonMenosVfeClick(GButton button, GEvent event){
    float value = PApplet.parseFloat(sliderVfe.getValueS());
    value--;
    sliderVfe.setValue(value);
}
public void botonEnviarVfeClick(GButton button, GEvent event){
    println("BangVfe!! " + sliderVfe.getValueS());
    if ((PApplet.parseInt(sliderVfe.getValueS()) > jLimiteVB) && (PApplet.parseInt(sliderVfe.getValueS()) < jLimiteAmarillo)) {
        jLimiteVerde = PApplet.parseInt(sliderVfe.getValueS());//asignamos el valor
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
    float value = PApplet.parseFloat(sliderVs.getValueS());
    value++;
    sliderVs.setValue(value);
}
public void botonMenosVsClick(GButton button, GEvent event){
    float value = PApplet.parseFloat(sliderVs.getValueS());
    value--;
    sliderVs.setValue(value);
}
public void botonEnviarVsClick(GButton button, GEvent event){
    println("BangVs!! " + sliderVs.getValueS());
    if ((PApplet.parseInt(sliderVs.getValueS()) > jLimiteBlanco) && (PApplet.parseInt(sliderVs.getValueS()) < jLimiteVerde)) {
        jLimiteVB = PApplet.parseInt(sliderVs.getValueS());//asignamos el valor
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
    float value = PApplet.parseFloat(sliderVy.getValueS());
    value++;
    sliderVy.setValue(value);
}
public void botonMenosVyClick(GButton button, GEvent event){
    float value = PApplet.parseFloat(sliderVy.getValueS());
    value--;
    sliderVy.setValue(value);
}
public void botonEnviarVyClick(GButton button, GEvent event){
    println("BangVy!! " + sliderVy.getValueS());
    if (PApplet.parseInt(sliderVy.getValueS()) > jLimiteBlanco) {
        jValorVy = PApplet.parseInt(sliderVy.getValueS());//asignamos el valor
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
    float value = PApplet.parseFloat(sliderVso.getValueS());
    value++;
    sliderVso.setValue(value);
}
public void botonMenosVsoClick(GButton button, GEvent event){
    float value = PApplet.parseFloat(sliderVso.getValueS());
    value--;
    sliderVso.setValue(value);
}
public void botonEnviarVsoClick(GButton button, GEvent event){
    println("BangVso!! " + sliderVso.getValueS());
    if ((PApplet.parseInt(sliderVso.getValueS()) > 0) && (PApplet.parseInt(sliderVso.getValueS()) < jLimiteVB)) {
        jLimiteBlanco = PApplet.parseInt(sliderVso.getValueS());//asignamos el valor
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
  public void settings() {  size(800, 480);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "uranoMiranda702" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
