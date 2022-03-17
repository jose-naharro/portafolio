class TopLbar {
    PGraphics tLb;

    TopLbar() {
        tLb = createGraphics(100, 50);
        tLb.beginDraw();
        tLb.background(0, 0, 0);
        tLb.endDraw();
    }

    void draw() {
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