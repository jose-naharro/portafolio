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

    void draw() {
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
        otrasUnidadesIzq.text(otrasUdVel2, otrasUnidadesIzq.width-45, (otrasUnidadesIzq.height/4)*1.5);
        otrasUnidadesIzq.text(otrasUdVel1, otrasUnidadesIzq.width-45, (otrasUnidadesIzq.height/4)*3);
        otrasUnidadesIzq.text(otrasUdVel2, otrasUnidadesIzq.width-45, (otrasUnidadesIzq.height/4)*3.5);
        
        otrasUnidadesIzq.textAlign(RIGHT, CENTER);
        if (isactivo10DOF) {
            otrasUnidadesIzq.text(otrasIAS1, otrasUnidadesIzq.width-60, otrasUnidadesIzq.height/4);
            otrasUnidadesIzq.text(otrasIAS2, otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*1.5);
        } else {
            otrasUnidadesIzq.fill(colorRojo);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, otrasUnidadesIzq.height/4);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*1.5);
        }
        if (isactivoGPS) {
            otrasUnidadesIzq.text(otrasGS1, otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3);
            otrasUnidadesIzq.text(otrasGS2, otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3.5);
        } else {
            otrasUnidadesIzq.fill(colorRojo);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3);
            otrasUnidadesIzq.text("XXX", otrasUnidadesIzq.width-60, (otrasUnidadesIzq.height/4)*3.5);
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
        otrasUnidadesDch.text("VS", 5, (otrasUnidadesDch.height/10)*4.25);
        otrasUnidadesDch.text("QNH", 5, (otrasUnidadesDch.height/10)*7.5);

        otrasUnidadesDch.fill(colorNaranja);
        otrasUnidadesDch.stroke(colorNaranja);
        otrasUnidadesDch.textFont(font10Ver);
        otrasUnidadesDch.textAlign(LEFT, CENTER);


        otrasUnidadesDch.text(otrasUdALT1, otrasUnidadesDch.width-45, otrasUnidadesDch.height/4);
        otrasUnidadesDch.text(otrasUdVSI1, otrasUnidadesDch.width-45, (otrasUnidadesDch.height/4)*2.25);
        otrasUnidadesDch.text(otrasUdQNH1, otrasUnidadesDch.width-45, (otrasUnidadesDch.height/4)*3.75);

        otrasUnidadesDch.textFont(font12Ver);
        otrasUnidadesDch.textAlign(RIGHT, CENTER);

        if (!isactivo10DOF) {
            otrasUnidadesDch.fill(colorRojo);
            otrasUnidadesDch.text("XXXX", otrasUnidadesDch.width-50, otrasUnidadesDch.height/4);
            otrasUnidadesDch.text("XXXX", otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*2.25);
            otrasUnidadesDch.text("XXXX", otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*3.75);
        } else {
            otrasUnidadesDch.text(otrasALT1, otrasUnidadesDch.width-50, otrasUnidadesDch.height/4);
        
            if (opcionMxS.isSelected()) {
                otrasUnidadesDch.text(round(otrasVSI1), otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*2.25);
            } else {
                otrasUnidadesDch.text(nfs(otrasVSI1, 0, 1), otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*2.25);
            }
            if (opcionMMhg.isSelected()) {
                otrasUnidadesDch.text(otrasQNH1, otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*3.75);
            } else {
                otrasUnidadesDch.text(otrasQNH2, otrasUnidadesDch.width-50, (otrasUnidadesDch.height/4)*3.75);
            }
        }

        
        

        otrasUnidadesDch.endDraw();
        //Fin parte derecha ////////////////////////////////////////////////

        image(otrasUnidadesIzq, myAnemometro.anemo.width, height-100, 100, 100);
        image(otrasUnidadesDch, width-myAltimeter0.alt.width-otrasUnidadesDch.width, height-100, 100, 100);
    }
}
