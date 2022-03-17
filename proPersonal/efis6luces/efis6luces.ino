#include <Arduino.h>
#include <Bounce2.h>//Libreria efecto rebote de botones, creo que esta no va, es la de debajo.
#include <DebouncedInput.h>//Libreria efecto rebote de botones

char val; // Data received from the serial port

byte pinBotonNav = 2;
byte pinLedNav = 6;
Bounce debouncerBotonNav = Bounce();

byte pinBotonBcn = 3;
byte pinLedBcn = 7;
Bounce debouncerBotonBcn = Bounce();

byte pinBotonStb = 4;
byte pinLedStb = 8;
Bounce debouncerBotonStb = Bounce();

byte pinBotonLand = 5;
byte pinLedLand = 9;
Bounce debouncerBotonLand = Bounce();

byte pinLedPrueba = 13;

void setup(){

    pinMode(pinLedPrueba, OUTPUT);

    pinMode(pinBotonNav, INPUT);
    pinMode(pinLedNav, OUTPUT);
    debouncerBotonNav.attach(pinBotonNav);
    debouncerBotonNav.interval(20); // intervalo en ms

    pinMode(pinBotonStb, INPUT);
    pinMode(pinLedStb, OUTPUT);
    debouncerBotonStb.attach(pinBotonStb);
    debouncerBotonStb.interval(20); // intervalo en ms

    pinMode(pinBotonBcn, INPUT);
    pinMode(pinLedBcn, OUTPUT);
    debouncerBotonBcn.attach(pinBotonBcn);
    debouncerBotonBcn.interval(20); // intervalo en ms

    pinMode(pinBotonLand, INPUT);
    pinMode(pinLedLand, OUTPUT);
    debouncerBotonLand.attach(pinBotonLand);
    debouncerBotonLand.interval(20); // intervalo en ms

    Serial.begin(9600);

    establishContact();  // send a byte to establish contact until receiver responds 
}

void loop(){

    if (Serial.available() > 0) { // If data is available to read,
        val = Serial.read(); // read it and store it in val
            
        monitor_estado_pulsador_nav();

        monitor_estado_pulsador_stb();

        monitor_estado_pulsador_bcn();

        monitor_estado_pulsador_land();
        
        Serial.println();//Marca de salto de linea para el buffer de Processing en la lectura de este serial.
    }
}

void monitor_estado_pulsador_nav(){
    static boolean estadoUltimo = 0;
    boolean estadoActual;

    debouncerBotonNav.update();

    estadoActual = debouncerBotonNav.read();//digitalRead(pinBotonNav);

    if (estadoActual == HIGH){
        //digitalWrite(pinLedNav, HIGH);
        analogWrite(pinLedNav, 10);
    } else {
        digitalWrite(pinLedNav, LOW);
    }

    if(estadoActual != estadoUltimo){
        if(estadoActual == HIGH){
            Serial.print("navOn");
            Serial.print(",");
        } else {
            Serial.print("navOff");
            Serial.print(",");
        }
    } else {
    	  Serial.print("navVacio"); //Este else es parte nueva para mandar todos los datos por el serial de cada vez.
        Serial.print(",");
    }

    estadoUltimo = estadoActual;
}

void monitor_estado_pulsador_stb(){
    static boolean estadoUltimo = 0;
    boolean estadoActual;

    debouncerBotonStb.update();

    estadoActual = debouncerBotonStb.read();//digitalRead(pinBotonStb);

    if (estadoActual == HIGH){
        strobe();
    } else {
        digitalWrite(pinLedStb, LOW);
    }

    if(estadoActual != estadoUltimo){
        if(estadoActual == HIGH){
            Serial.print("stbOn");
            Serial.print(",");
        } else {
            Serial.print("stbOff");
            Serial.print(",");
        }
    } else {
    	  Serial.print("stbVacio"); //Este else es parte nueva para mandar todos los datos por el serial de cada vez.
        Serial.print(",");
    }

    estadoUltimo = estadoActual;
}

void monitor_estado_pulsador_bcn(){
    static boolean estadoUltimo = 0;
    boolean estadoActual;

    debouncerBotonBcn.update();

    estadoActual = debouncerBotonBcn.read();//digitalRead(pinBotonBcn);

    if (estadoActual == HIGH){
        beacon();
    } else {
        digitalWrite(pinLedBcn, LOW);
    }

    if(estadoActual != estadoUltimo){
        if(estadoActual == HIGH){
            Serial.print("bcnOn");
            Serial.print(",");
        } else {
            Serial.print("bcnOff");
            Serial.print(",");
        }
    } else {
    	  Serial.print("bcnVacio"); //Este else es parte nueva para mandar todos los datos por el serial de cada vez.
        Serial.print(",");
    }

    estadoUltimo = estadoActual;
}

void monitor_estado_pulsador_land(){
    static boolean estadoUltimo = 0;
    boolean estadoActual;

    debouncerBotonLand.update();

    estadoActual = debouncerBotonLand.read();//digitalRead(pinBotonNav);

    if (estadoActual == HIGH){
        //digitalWrite(pinLedLand, HIGH);
        analogWrite(pinLedLand, 10);
    } else {
        digitalWrite(pinLedLand, LOW);
    }

    if(estadoActual != estadoUltimo){
        if(estadoActual == HIGH){
            Serial.print("lndOn");
            Serial.print(",");
        } else {
            Serial.print("lndOff");
            Serial.print(",");
        }
    } else {
          Serial.print("lndVacio"); //Este else es parte nueva para mandar todos los datos por el serial de cada vez.
        Serial.print(",");
    }

    estadoUltimo = estadoActual;
}

void strobe(){
    static boolean estadoLed = LOW;
    static unsigned long tUCambioLed = 0;
    static int contador = 1;

    unsigned long intervalo = 10;
    unsigned long tActual = millis();

    if((tActual - tUCambioLed >= intervalo)&&(contador<=3)){
        tUCambioLed = tActual;

        if(estadoLed == LOW){
            estadoLed = HIGH;
            contador++; //Suma del contador para saber que ha entrado
        } else {
            estadoLed = LOW;
        }
        digitalWrite(pinLedStb, estadoLed);
    } else if((tActual - tUCambioLed >= intervalo)&&(contador>3)&&(contador<=13)){
        tUCambioLed = tActual;

        if(estadoLed == LOW){
            estadoLed = HIGH;
            contador++; //Suma del contador para saber que ha entrado
        } else {
            estadoLed = LOW;
        }
        digitalWrite(pinLedStb, LOW);
    } else if (contador>13){
        contador = 1;
    }
}


void beacon(){
    int valor;
    int periodo = 1000;
    long time = 0;

    time = millis();
    valor = 128 + 127 * cos(2*PI / periodo*time);
    analogWrite(pinLedBcn, valor); // 0 a 255
}

void establishContact() {
    while (Serial.available() <= 0) {
        Serial.println("A");   // send a capital A
        delay(300);
    }
}
