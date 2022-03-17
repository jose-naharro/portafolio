class Brujula {
    PGraphics hdg;
    PGraphics hdgDisp;

    float hdgValue = 0.0;

    Brujula() {
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

            hdg.translate(hdg.width/2, 0);
            heading();
            hdg.translate(-hdg.width/2, 0);
        }

        hdg.endDraw();
        image(hdg, 0, 0, hdg.width, hdg.height);
    }

    void heading(){
        float hdgLocal = getHDGvalue();
        if (hdgLocal == 360)
            hdgLocal = 0;

        int j = 0;
        //hdg.stroke(255, 0, 0);
        hdg.textAlign(CENTER, CENTER);
        hdg.translate(-3600, 0);
        hdg.translate(-hdgLocal*50/5, 0);
        //rayas grandes
        for(int i = -360; i < 0 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-20);
            hdg.text(nf(360+i, 3), j, hdg.height/3);
            j+=50;
        }
        for(int i = 0; i < 360 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-20);
            hdg.text(nf(i, 3), j, hdg.height/3);
            j+=50;
        }
        
        for(int i = 360; i < 720 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-20);
            hdg.text(nf(i-360, 3), j, hdg.height/3);
            j+=50;
        }
        
        //rayas pequeñas
        j = 0;
        for(int i = -360; i < 0 ; i+=1){
            //hago una rayita vertical cada 10px
            hdg.line(j, hdg.height, j, hdg.height-10);
            j+=10;
        }

        for(int i = 0; i < 360 ; i+=1){
            //hago una rayita vertical cada 10px
            hdg.line(j, hdg.height, j, hdg.height-10);
            j+=10;
        }
        for(int i = 360; i < 720 ; i+=5){
            //hago una rayita vertical cada 50px
            hdg.line(j, hdg.height, j, hdg.height-10);
            j+=10;
        }


        hdg.translate(-(hdgLocal*50/5), 0);
        hdg.translate(-3600, 0);
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
