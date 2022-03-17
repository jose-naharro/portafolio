class Qnh {
    PGraphics qnh;
    float qnhHPa = 1023.0;
    float qnhIn = qnhHPa*0.00029529983071445;
    Qnh() {
        qnh = createGraphics(120, 50);
        qnh.beginDraw();
        //qnh.background(90, 90, 90, 191);
        qnh.background(0, 0, 0);
        qnh.endDraw();
    }

    void draw(float value) {
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
        qnhIn = qnhHPa*0.029529983071445;
    }
}