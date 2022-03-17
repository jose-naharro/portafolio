
class Rumbo {
    PGraphics hdg;
    PGraphics hdgDisp;

    float hdgValue = 0.0;

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

    void draw(float theHdgValue) {

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

    void drawRect(float theHdgValue) {

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