#include <Arduino.h>
#include <Bounce2.h>//Libreria efecto rebote de botones. Parece ser que esta es la que va.
#include <DebouncedInput.h>//Libreria efecto rebote de botones
#include <Servo.h>
#include <AccelStepper.h>

#define HALFSTEP 8 // Definimos HALFSTEP 8 para poder usar el motor por pasos/steeper motor 28BYJ-48
// Definimos los pines del driver ULN2003
#define motorPin1 8 // Pin 1 en el pin IN1 del driver ULN2003
#define motorPin2 9 // Pin 2 en el pin IN2 del driver ULN2003
#define motorPin3 10 // Pin 3 en el pin IN3 del driver ULN2003
#define motorPin4 11 // Pin 4 en el pin IN4 del driver ULN2003
// Damos nombre al motor "steeper" y le adjudicamos las caracteristicas anteriores
AccelStepper stepper(HALFSTEP, motorPin1, motorPin3, motorPin2, motorPin4);

char val; // Data received from the serial port

byte pinBotonFlapsUp = 2;
byte pinBotonFlapsDn = 3;
DebouncedInput debouncerBotonFlapsUp(pinBotonFlapsUp, 20, false);
DebouncedInput debouncerBotonFlapsDn(pinBotonFlapsDn, 20, false);
byte pinBotonFdC = 4;
DebouncedInput debouncerBotonFdC(pinBotonFdC, 20, false);

byte pinBotonTrimUp = 6;
byte pinBotonTrimDn = 7;
DebouncedInput debouncerBotonTrimUp(pinBotonTrimUp, 20, false);
DebouncedInput debouncerBotonTrimDn(pinBotonTrimDn, 20, false);
Servo trimServo;
byte pinServo = 5;


byte pinLedPrueba = 13;

void setup(){
    int target = 1;
    
    pinMode(pinLedPrueba, OUTPUT);

    pinMode(pinBotonTrimUp, INPUT);
    pinMode(pinBotonTrimDn, INPUT);
    debouncerBotonTrimUp.begin();
    debouncerBotonTrimDn.begin();
    trimServo.attach(pinServo);
    trimServo.write(90);

    pinMode(pinBotonFlapsUp, INPUT);
    pinMode(pinBotonFlapsDn, INPUT);
    debouncerBotonFlapsUp.begin();
    debouncerBotonFlapsDn.begin();
    
    pinMode(pinBotonFdC, INPUT);
    debouncerBotonFdC.begin();

    stepper.setMaxSpeed(1000.0);
    stepper.setAcceleration(500.0);
    
    while(debouncerBotonFdC.read() == HIGH){
        stepper.moveTo(target++);
        stepper.run();
        digitalWrite(pinLedPrueba, LOW); //indicates it's doing something
    }//Cambiar el sentido segun nos convenga con el - o +

    digitalWrite(pinLedPrueba, HIGH); //indicates it's doing something
    stepper.setCurrentPosition(0); // once homePin == LOW, reset currentPosition to 0

    stepper.setMaxSpeed(1000.0);
    stepper.setAcceleration(500.0);
    
    //stepper.runToNewPosition(-30000); // Lo llevamos ahasta flaps 0...
    stepper.moveTo(-3000);
    stepper.runToPosition();

    stepper.setMaxSpeed(1000.0);
    stepper.setAcceleration(500.0);
    
    Serial.begin(115200);

    establishContact();  // send a byte to establish contact until receiver responds 
}

void loop(){

    if (Serial.available() > 0) { // If data is available to read,
        val = Serial.read(); // read it and store it in val

        trim_control();

        flaps_control();
        
        Serial.println();//Marca de salto de linea para el buffer de Processing en la lectura de este serial.
    }  
}

void trim_control(){
    static int pos = 90;

    if (debouncerBotonTrimDn.changedTo(HIGH)){
        if (pos > 56){
            pos--;
            trimServo.write(pos);
            //if(Serial.availableForWrite()>60){
            	Serial.print("trimMenos");
            	Serial.print(",");
        	//}
        }
    } else if (debouncerBotonTrimUp.changedTo(HIGH)){
        if(pos < 126){
            pos++;
            trimServo.write(pos);
            //if(Serial.availableForWrite()>60){
            	Serial.print("trimMas");
            	Serial.print(",");
            //}
        }
    } else {
    	//if(Serial.availableForWrite()>60){
    		  Serial.print("trimVacio"); //Este else es parte nueva para mandar todos los datos por el serial de cada vez.
        	Serial.print(",");
        //}
    }
}

void flaps_control(){
    static int pos = 0; // Las posiciones van a ir ser -1, 0, 1, 2 y 3, correspondientes a -3º, 0º, 10º, 25º y 40º

    if (debouncerBotonFlapsDn.changedTo(HIGH)){
        if (pos < 3){
            pos++;
            //if(Serial.availableForWrite()>60){
            	Serial.print("flapsDn");
            	Serial.print(",");
            //}
        }
    } else if (debouncerBotonFlapsUp.changedTo(HIGH)){
        if(pos > -1){
            pos--;
            //if(Serial.availableForWrite()>60){
            	Serial.print("flapsUp");
            	Serial.print(",");
            //}
        }
    } else {
        //if(Serial.availableForWrite()>60){
        	Serial.print("flapsVacio"); //Este else es parte nueva para mandar todos los datos por el serial de cada vez.
        	Serial.print(",");
        //}
    }

    switch (pos) {
        case -1:
          //stepper.runToNewPosition(0);
          stepper.moveTo(0);
          stepper.run();
          //stepper.setMaxSpeed(1000.0);
          //stepper.setAcceleration(500.0);
          break;
        case 0:
          //stepper.runToNewPosition(-30000);
          stepper.moveTo(-3000);
          stepper.run();
          //stepper.setMaxSpeed(1000.0);
          //stepper.setAcceleration(500.0);
          break;
        case 1:
          //stepper.runToNewPosition(-60000);
          stepper.moveTo(-6000);
          stepper.run();
          //stepper.setMaxSpeed(1000.0);
          //stepper.setAcceleration(500.0);
        break;
        case 2:
          //stepper.runToNewPosition(-120000);
          stepper.moveTo(-12000);
          stepper.run();
          //stepper.setMaxSpeed(1000.0);
          //stepper.setAcceleration(500.0);
        break;
        case 3:
          //stepper.runToNewPosition(-180000);
          stepper.moveTo(-18000);
          stepper.run();
          //stepper.setMaxSpeed(1000.0);
          //stepper.setAcceleration(500.0);
        break;
    }

}

void establishContact() {
    while (Serial.available() <= 0) {
        Serial.println("A");   // send a capital A
        delay(300);
    }
}
