class GSpeed {
    PGraphics gs;
    float gsKt = 0.0;//aqui habrá que poner la del gps...bueno, ya veremos
    float gsKmh = gsKt*1.852;
    float gsMph = gsKt*1.1507794480235;
    GSpeed() {
        gs = createGraphics(100, 50);
        gs.beginDraw();
        //gs.background(90, 90, 90, 191);
        gs.background(0, 0, 0);
        gs.endDraw();
    }

    void draw(float value) {
        float gsLocal;

        if (unidadesVel == 1) {
            gsLocal = value;
        } else if ( unidadesVel == 2) {
            gsLocal = value/1.852;
        } else {
            gsLocal = value/1.852*1.1507794480235;
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