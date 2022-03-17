class VerticalSpeed {
    PGraphics vsi;

    VerticalSpeed() {
        vsi = createGraphics(100, 380);
        vsi.beginDraw();
        vsi.background(0);
        vsi.noStroke();
        vsi.endDraw();    
    }

    void draw(float myVS){
        float vsiMap;
        float myVSLocal = myVS;
        float v = vsiLimit/1000;
        float vsiLimitLocal = vsiLimit;

        if (unidadesVsi == 1) {
            myVSLocal = myVS  * 0.00508;// ya tenemos la vsi en metros/s
            v = vsiLimitLocal * 0.00508;// ya tenemos el limite de las rayitas en metros/s
            vsiLimitLocal *= 0.00508;// ya tenemos el limite de la vsi en metros/s
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
                    v -= 2.54;
                }

                for(int i = 30+8; i < vsi.height-30; i += 8) {//short lines vsi
                    vsi.line(vsi.width-6, i, vsi.width, i);
                }
            } else {//si es en ft/s
                //float v = vsiLimit/1000;
                for(int i = 30; i <= vsi.height-30; i += 40) {//long lines vsi
                    vsi.line(vsi.width-12, i, vsi.width, i);
                    vsi.text(nf(v, 1, 1), vsi.width-13, i-1);// el -1 es para centrarlo mejor
                    v -= 0.5;
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