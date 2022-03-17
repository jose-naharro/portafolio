class Brujula2 {
    PGraphics hdg;
    PGraphics hdgDisp;

    float hdgValue = 0.0;

    Brujula2() {
        hdg = createGraphics(300, 50);
        hdg.beginDraw();
        hdg.background(colorNegro);
        hdg.noStroke();
        hdg.endDraw();

        hdgDisp = createGraphics(300, 50);
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

        actualizarValorHDG(theHdgValue);
        
        if (!isactivo10DOF) {
            hdg.background(colorNegro);
            hdg.stroke(colorRojo);
            hdg.strokeWeight(2);
            hdg.line(0, 0, hdg.width, hdg.height);
            hdg.line(0, hdg.height, hdg.width, 0);
            hdg.fill(colorAmarillo);
            hdg.textFont(font14VerBold);
            hdg.textAlign(CENTER, CENTER);
            hdg.text("HDG", hdg.width/2, hdg.height/2);
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

            /*for (int i = -300; i < 300; i++) {
                if (int(i+getHDGvalue())%50 == 0) {
                    hdg.line(hdg.width+i, hdg.height, hdg.width+i, hdg.height-10);
                    hdg.text(int(i+getHDGvalue())+285, hdg.width+i, 15);
                }
            }*/
            for (int i = -hdg.width; i < hdg.width; i++) {
                //Long lines every 50px are 5 degrees (340 345 350 355 360 005 010 , by instante)
                if (int(i+getHDGvalue())%50 == 0) {
                    hdg.line(hdg.width+i, hdg.height, hdg.width+i, hdg.height-20);
                    hdg.text(int(i+getHDGvalue()), hdg.width+i, 15);
                }
                //Sort lines every 10px are 1 degrees and they haven't  the number.
                if ((int(i+getHDGvalue())%50 != 0) && (int(i+getHDGvalue())%10 == 0)) {
                    hdg.line(hdg.width+i, hdg.height, hdg.width+i, hdg.height-10);
                }
            }
            //println("hdgValue: "+nf(getHDGvalue(), 1, 1));
            //hdg.translate(-hdg.width/2, 0);
        }

        hdg.endDraw();
        image(hdg, 0, 0, hdg.width, hdg.height);
    }

    void drawRect(float theHdgValue) {

        hdgDisp.beginDraw();
        
        if (isactivo10DOF) {
            hdgDisp.stroke(colorBlanco);
            hdgDisp.strokeWeight(1);
            hdgDisp.strokeCap(SQUARE);

            actualizarValorHDG(theHdgValue);

            hdgDisp.translate(0, 0);

            hdgDisp.fill(colorNegro);
            hdgDisp.beginShape();
            hdgDisp.vertex(hdgDisp.width/2, hdgDisp.height-10);
            hdgDisp.vertex(hdgDisp.width/2-5, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2-25, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2-25, 1);
            hdgDisp.vertex(hdgDisp.width/2+25, 1);
            hdgDisp.vertex(hdgDisp.width/2+25, hdgDisp.height-20);
            hdgDisp.vertex(hdgDisp.width/2+5, hdgDisp.height-20);
            hdgDisp.endShape(CLOSE);
            hdgDisp.textFont(font18Ver, 18);
            hdgDisp.fill(colorBlanco);
            hdgDisp.textAlign(CENTER, CENTER);
            hdgDisp.text(nf(round(getHDGvalue()), 3), hdgDisp.width/2, 15);
        }

        hdgDisp.endDraw();
        image(hdgDisp, 0, 0, hdgDisp.width, hdgDisp.height);
    }

    private void actualizarValorHDG(float theHDGvalue) {
        setHDGvalue(theHDGvalue);
    }
}
