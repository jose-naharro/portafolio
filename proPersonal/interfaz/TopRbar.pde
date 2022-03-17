class TopRbar {
    PGraphics tRb;
    float valorVSILocalFtMin = 0.0;//vsi en ft/min
    float valorVSILocalMS = 0.0;//vsi en m/s

    TopRbar() {
        tRb = createGraphics(120, 50);
        tRb.beginDraw();
        tRb.background(0, 0, 0);
        tRb.endDraw();
    }
    public void setVSIlocalFtMin(float vsiFtMin){
        this.valorVSILocalFtMin = vsiFtMin;
    }
    public float getVSIlocalFtMin(){
        return this.valorVSILocalFtMin;
    }
    public void setVSIlocalMS(float vsiFtMin){
        this.valorVSILocalMS = vsiFtMin * 0.00508;
    }
    public float getVSIlocalMS(){
        return this.valorVSILocalMS;
    }


    void draw(float vsiFtMin) {
        tRb.beginDraw();

        if ((!toggleAlt.isSelected()) || (!toggleVario.isSelected())) {
            tRb.background(90, 90, 90, 191);
        } else {
            tRb.background(90, 90, 90, 127);
        }
        
        if (!isactivo10DOF) {
            tRb.background(colorNegro);
        } else {
            actualizarValoresVSIlocal(vsiFtMin);
            tRb.fill(colorAzulCielo);
            tRb.textFont(font14Ver, 14);
            tRb.textAlign(CENTER, CENTER);
            tRb.text(unidadesALT, tRb.width/4, tRb.height-10);

            tRb.text(unidadesVSI, tRb.width/4*3, tRb.height-30);
            tRb.fill(colorBlanco);

            if (unidadesVsi == 1) {
                tRb.text(nfs(getVSIlocalMS(), 0, 2), tRb.width/4*3, tRb.height-10);
            } else {
                tRb.text(round(getVSIlocalFtMin()/10)*10, tRb.width/4*3, tRb.height-10); // El /10)*10 lo pongo para redondear a multiplos de 10
            }
        }

        tRb.endDraw();
        image(tRb, 0, 0, 120, 50);
    }
    public void actualizarValoresVSIlocal(float vsiFtMin){
        setVSIlocalFtMin(vsiFtMin);
        setVSIlocalMS(vsiFtMin);
    }
}


/*
void topRbar(){
    float valorVSILocal = valorVSI;//primero en ft/min

    if ((toggleVerALT.getValue() == 0.0) || (toggleVerVSI.getValue() == 0.0)) {
        topRbar.background(90, 90, 90, 191);
    } else {
        topRbar.background(90, 90, 90, 127);
    }

    if (unidadesVsi == 1) {
        valorVSILocal = valorVSI * 0.00508; //si esta en m/s lo pasamos a estoas unidades.
    }

    topRbar.fill(colorAzulCielo);
    topRbar.textFont(font14VerBold, 14);
    topRbar.textAlign(CENTER, CENTER);
    topRbar.text(unidadesALT, topRbar.width/4, topRbar.height-10);

    topRbar.text(unidadesVSI, topRbar.width/4*3, topRbar.height-30);
    topRbar.fill(colorBlanco);

    if (unidadesVsi == 1) {
        valorVSILocal = valorVSI * 0.00508; //si esta en m/s lo pasamos a estoas unidades.
        topRbar.text(nfs(valorVSILocal, 0, 2), topRbar.width/4*3, topRbar.height-10);
    } else {
        topRbar.text(round(valorVSILocal), topRbar.width/4*3, topRbar.height-10);
    }
}
*/