class Altimeter {
    PGraphics pg;
    PGraphics alt;
    float altMetros = 0.0;
    float altPies = altMetros * 3.2808399;
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

    void draw(float value) {
        float newValue = value;
        if (unidadesAlt == 1) {
            newValue = value / 3.2808399;// ya lo tenemos en metros
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
                if (int(i+newValue)%20 == 0 && int(i+newValue)%100 != 0) {
                    pg.line( 0, 380-i, 5, 380-i);
                }
                if (int(i+newValue)%100 == 0) {
                    pg.line( 0, 380-i, 10, 380-i);
                    pg.text( int(i+newValue), 70, 380-i); //ojo tenia int(i+newValue)/100 porque no cabian todos los numeros en 3.5"
                }
            }
        }

        pg.endDraw();
        image(pg, 0, 0);
    }

    void drawRect(float value) {
        float newValue = value;
        if (unidadesAlt == 1) {
            newValue = value / 3.2808399;// ya lo tenemos en metros
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
        altMetros = altPies/3.2808399;
    }
}
