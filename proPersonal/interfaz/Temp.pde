class Temp {
    PGraphics temp;

    float localTempC = 0.0; //Este en ºC
    float localTempF = 0.0; //Este en ºF

    Temp() {
        temp = createGraphics(70, 20);
        temp.beginDraw();
        temp.background(0);
        temp.endDraw();
    }
    
    public void setTempC(float newTemp) {
        this.localTempC = newTemp;
    }
    public float getTempC() {
        return this.localTempC;
    }

    public void setTempF(float newTemp) {
        this.localTempF = newTemp*1.8+32;
    }
    public float getTempF() {
        return this.localTempF;
    }
    
    void draw(float theTemp) {

        actualizarValoresTemp(theTemp);//Ojo, la theTemp viene en ºC

        temp.beginDraw();
        temp.background(0);
        if (isactivoTEMP) {
            temp.fill(colorBlanco);
            temp.textFont(font14Ver);
            temp.textAlign(CENTER, CENTER);
            if (unidadesTemp == 1) {
                temp.text(nfs(getTempC(), 2, 1) + " " + unidadesTEMP, temp.width/2, temp.height/2);
            } else {
                temp.text(nfs(getTempF(), 2, 1) + " " + unidadesTEMP, temp.width/2, temp.height/2);
            }
        } else {
            temp.stroke(colorRojo);
            temp.strokeWeight(2);
            temp.line(0, 0, temp.width, temp.height);
            temp.line(0, temp.height, temp.width, 0);
            temp.fill(colorAmarillo);
            temp.textFont(font14VerBold);
            temp.textAlign(CENTER, CENTER);
            temp.text(textErrorTEMP, temp.width/2, temp.height/2);
        }
        
        
        temp.endDraw();
        image(temp, 0, 0, 70, 20);
    }

    private void actualizarValoresTemp(float newTemp) {
        setTempC(newTemp);
        setTempF(newTemp);
    }
}