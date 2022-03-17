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

    void draw() {

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