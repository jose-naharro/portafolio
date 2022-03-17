/**
- Vne: Red line, Top of yellow arc. Never exceed speed. 
- Vno: Top of the green arc. Maximum structural cruise speed.
- Vfe: Top of the white arc. Maximum flap extended speed.
- Vs: Bottom of the green arc. The stall speed or the minimum steady flight speed clean configuration.
- Vy: Best rate of climb speed.
- Vso: Bottom of the white arc. The stall speed or the minimum steady flight speed in the landing configuration.
**/
class Anemometro {
    PGraphics anemo;
    float valorAnemoLocalKmh = 0.0;
    float valorAnemoLocalKt = 0.0;
    float valorAnemoLocalMph = 0.0;

    float vsiLimitLocalkmh = vLimit;
    float vsiLimitLocalkt = vsiLimitLocalkmh / 1.852;
    float vsiLimitLocalmph = vsiLimitLocalkt * 1.1507794480235;

    //float limiteBlancoKmh = 65;
    float limiteBlancoKmh = jLimiteBlanco;
    float limiteBlancoKt = limiteBlancoKmh / 1.852;
    float limiteBlancoMph = limiteBlancoKt * 1.1507794480235;
    //float limiteVerdeKmh = 120;
    float limiteVerdeKmh = jLimiteVerde;
    float limiteVerdeKt = limiteVerdeKmh / 1.852;
    float limiteVerdeMph = limiteVerdeKt * 1.1507794480235;
    //float limiteAmarilloKmh = 260;
    float limiteAmarilloKmh = jLimiteAmarillo;
    float limiteAmarilloKt = limiteAmarilloKmh / 1.852;
    float limiteAmarilloMph = limiteAmarilloKt * 1.1507794480235;
    //float limiteRojoKmh = 285;
    float limiteRojoKmh = jLimiteRojo;
    float limiteRojoKt = limiteRojoKmh / 1.852;
    float limiteRojoMph = limiteRojoKt * 1.1507794480235;
    //float limiteVBKmh = 80;
    float limiteVBKmh = jLimiteVB;
    float limiteVBKt = limiteVBKmh / 1.852;
    float limiteVBMph = limiteVBKt * 1.1507794480235;
    
    //float limiteBVKmh = vsiLimitLocalkmh - limiteVerdeKmh;//que en este caso es 120 el limiteVerdeKmh, pero asi podemos modificarlo a gusto
    //float limiteBVKt = limiteBVKmh / 1.852;
    //float limiteBVMph = limiteBVKt * 1.1507794480235;

    float valorVyKmh = jValorVy;
    float valorVyKt = valorVyKmh / 1.852;
    float valorVyMph = valorVyKt * 1.1507794480235;

    float valorIASLocal = 0.;
    float valorLimitLocal = 0.;
    float limiteBlanco = 0.;
    float limiteVerde = 0.;
    float limiteAmarillo = 0.;
    float limiteRojo = 0.;
    float limiteVB = 0.0;
    //float limiteBV = 0.0;

    float valorVy = 0.;

    Anemometro() {
        anemo = createGraphics(100, 380);
        anemo.beginDraw();
        anemo.background(0, 0, 0);
        anemo.endDraw();
    }
    public void setvalorAnemoLocalKmh(float anemoKmh){
        this.valorAnemoLocalKmh = anemoKmh;
    }
    public float getvalorAnemoLocalKmh(){
        return this.valorAnemoLocalKmh;
    }
    public void setvalorAnemoLocalKt(float anemoKmh){
        this.valorAnemoLocalKt = anemoKmh/1.852;
    }
    public float getvalorAnemoLocalKt(){
        return this.valorAnemoLocalKt;
    }
    public void setvalorAnemoLocalMph(float anemoKmh){
        this.valorAnemoLocalMph = anemoKmh * 0.6213711922;
    }
    public float getvalorAnemoLocalMph(){
        return this.valorAnemoLocalMph;
    }

    public void setLimiteBlancoKmh(float theBlancoValue){
        this.limiteBlancoKmh = theBlancoValue;
    }
    public float getLimiteBlancoKmh(){
        return this.limiteBlancoKmh;
    }

    public void setLimiteVerdeKmh(float theVerdeValue){
        this.limiteVerdeKmh = theVerdeValue;
    }
    public float getLimiteVerdeKmh(){
        return this.limiteVerdeKmh;
    }
    public void setLimiteAmarilloKmh(float theAmarilloValue){
        this.limiteAmarilloKmh = theAmarilloValue;
    }
    public float getLimiteAmarilloKmh(){
        return this.limiteAmarilloKmh;
    }

    public void setLimiteRojoKmh(float theRedValue){
        this.limiteRojoKmh = theRedValue;
    }
    public float getLimiteRojoKmh(){
        return this.limiteRojoKmh;
    }
    public void setLimiteVBKmh(float theVBValue){
        this.limiteVBKmh = theVBValue;
    }
    public float getLimiteVBKmh(){
        return this.limiteVBKmh;
    }
    /*
    public void setLimiteBVKmh(float theBVValue){
        this.limiteBVKmh = theBVValue;
    }
    public float getLimiteBVKmh(){
        return this.limiteBVKmh;
    }
    */
    public void setValorVyKmh(float theVyValue){
        this.valorVyKmh = theVyValue;
    }
    public float getValorVyKmh(){
        return this.valorVyKmh;
    }

    void draw(float anemoKmh) {
        anemo.beginDraw();

        if (!isactivo10DOF) {
            anemo.noStroke();
            anemo.fill(colorNegro);
            anemo.rect(0, 0, anemo.width, anemo.height);
            anemo.stroke(colorRojo);
            anemo.strokeWeight(2);
            anemo.line(0, 0, anemo.width, anemo.height);
            anemo.line(0, anemo.height, anemo.width, 0);
            anemo.fill(colorAmarillo);
            anemo.textFont(font14VerBold);
            anemo.textAlign(CENTER, CENTER);
            anemo.text(textErrorANEMO, anemo.width/2, anemo.height/2);
        } else {
            actualizarValoresLimites();
            actualizarValoresAnemoLocal(anemoKmh);
            
            anemo.background(90, 90, 90, 127); // fond gris

            //calculos para pruebas
            //airspeed = airspeed + ((-mouseY+180)/50) ;
            
            if (valorIASLocal > valorLimitLocal) {valorIASLocal = valorLimitLocal;}  //airspeed max 300 km/h
            if (valorIASLocal < 0 ||  Float.isNaN(valorIASLocal)) {valorIASLocal = 0;}  //airspeed min 0 km/h
            //speed10s = valorIASLocal + 10 - ((-mouseX+400)/10);  //aceleracion

            //color y peso de las rayas y numeros
            anemo.stroke(colorTextoFormas);
            anemo.strokeWeight(2);
            anemo.strokeCap(SQUARE);

            anemo.translate(0, anemo.height/2);

            anemo.pushMatrix();
            anemo.translate(0, (4*valorIASLocal));

            anemo.noStroke();
            anemo.fill(colorNegro);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10) , 12, (valorLimitLocal*40/10));
            anemo.fill(colorBlanco);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteBlanco*40/10));
            anemo.fill(colorVerde);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteVB*40/10));
            anemo.fill(colorBlanco);
            //anemo.rect(anemo.width-4, -(valorLimitLocal*40/10) + (limiteBV*40/10), 4, (valorLimitLocal*40/10) - (limiteRojo*40/10));
            anemo.rect(anemo.width-6, -(valorLimitLocal*40/10), 6, (valorLimitLocal*40/10) - (limiteVB*40/10));
            anemo.fill(colorVerde);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteVerde*40/10));
            anemo.fill(colorNaranja);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteAmarillo*40/10));
            anemo.fill(colorRojo);
            anemo.rect(anemo.width-12, -(valorLimitLocal*40/10), 12, (valorLimitLocal*40/10) - (limiteRojo*40/10));
            
            if (textoNegrita) {
                anemo.textFont(font18VerBold, 18);
            } else {
                anemo.textFont(font20Ver, 18);
            }
            anemo.stroke(colorTextoFormas);
            anemo.fill(colorTextoFormas);
            anemo.textAlign(RIGHT, CENTER);
            int k = round(valorLimitLocal-valorLimitLocal%10);
            //println(k);
            //println(round(-(round(valorLimitLocal)*4-round(valorLimitLocal)*4%10)));
            for (int i=round(-k*4); i <= 0; i += 40){//rayas largas
                anemo.line(anemo.width-12, i, anemo.width-28, i);
                anemo.text(k, anemo.width-30, i);
                k -= 10;
            }


            int l = round(valorLimitLocal-valorLimitLocal%10);
            for(int j = round(-l*4-20);j<0;j+=40)//rayas cortas
                anemo.line(anemo.width-12, j, anemo.width-22, j);
            
            //Circulito con la Vy
            anemo.noStroke();
            anemo.fill(colorAzulCielo);
            anemo.ellipse(anemo.width-8, -(valorVy*40/10), 16, 16);
            anemo.textFont(font11Ver);
            anemo.stroke(colorTextoFormas);
            anemo.fill(colorNegro);
            anemo.text("Vy", anemo.width, -(valorVy*40/10));


        /*
            int k = round(valorLimitLocal);
            println(valorLimitLocal*4-valorLimitLocal*4%10);
            for(int i=round(-(valorLimitLocal*40/10)); i <= 0; i += 40) {//rayas largas
                anemo.line(anemo.width-8, i, anemo.width-24, i);
                anemo.text(k, anemo.width-30, i);
                k -= 10;
            }

            for(int j=round(-(valorLimitLocal*40/10-20)); j < 0; j += 40) {//rayas cortas
                anemo.line(anemo.width-8, j, anemo.width-18, j);
            }
        */
            anemo.popMatrix();
            //partes fijas
            //anemo.strokeWeight(2);
            if (valorIASLocal <= limiteBlanco) {
                anemo.strokeWeight(1.5);
                anemo.stroke(colorBlanco);
            } else if (valorIASLocal > limiteBlanco && valorIASLocal <= limiteVerde) {
                anemo.strokeWeight(3);
                anemo.stroke(colorBlanco);
            } else if (valorIASLocal > limiteVerde && valorIASLocal <= limiteAmarillo) {
                anemo.strokeWeight(3);
                anemo.stroke(colorVerde);
            } else if (valorIASLocal > limiteAmarillo && valorIASLocal <= limiteRojo) {
                anemo.strokeWeight(3);
                anemo.stroke(colorNaranja);
            } else if (valorIASLocal > limiteRojo) {
                anemo.strokeWeight(3);
                anemo.stroke(colorRojo);
            }
            
            if (textoNegrita) {
                anemo.textFont(font18VerBold, 18);
            } else {
                anemo.textFont(font20Ver, 18);
            }

            anemo.fill(colorNegro);
            anemo.beginShape();
            anemo.vertex(anemo.width-18, 0);
            anemo.vertex(anemo.width-30,-18);
            anemo.vertex(4,-18);
            anemo.vertex(4, 18);
            anemo.vertex(anemo.width-30, 18);
            anemo.endShape(CLOSE);
            anemo.fill(colorBlanco);
            anemo.text(round(valorIASLocal), anemo.width-30, 0);
            anemo.translate(0 , -anemo.height/2);

            if (!toggleRumbo.isSelected()) {
                anemo.fill(90, 90, 90, 127);
                anemo.rect(380, 0, 100, 50);
            }
        }
        
        anemo.endDraw();
        image(anemo, 0, 50, 100, 380);
    }

    public void actualizarValoresLimites() {
        //limiteBlancoKmh = 65;
        limiteBlancoKt = limiteBlancoKmh / 1.852;
        limiteBlancoMph = limiteBlancoKt * 1.1507794480235;
        //limiteVerdeKmh = 120;
        limiteVerdeKt = limiteVerdeKmh / 1.852;
        limiteVerdeMph = limiteVerdeKt * 1.1507794480235;
        //limiteAmarilloKmh = 260;
        limiteAmarilloKt = limiteAmarilloKmh / 1.852;
        limiteAmarilloMph = limiteAmarilloKt * 1.1507794480235;
        //limiteRojoKmh = 285;
        limiteRojoKt = limiteRojoKmh / 1.852;
        limiteRojoMph = limiteRojoKt * 1.1507794480235;
        //limiteVBKmh = 80;
        limiteVBKt = limiteVBKmh / 1.852;
        limiteVBMph = limiteVBKt * 1.1507794480235;
        
        //limiteBVKmh = vsiLimitLocalkmh - limiteVerdeKmh;//que en este caso es 120, pero asi podemos modificarlo a gusto
        //limiteBVKt = limiteBVKmh / 1.852;
        //limiteBVMph = limiteBVKt * 1.1507794480235;

        valorVyKt = valorVyKmh / 1.852;
        valorVyMph = valorVyKt * 1.1507794480235;
    }

    public void actualizarValoresAnemoLocal(float anemoKmh){
        setvalorAnemoLocalKmh(anemoKmh);
        setvalorAnemoLocalKt(anemoKmh);
        setvalorAnemoLocalMph(anemoKmh);
        if (unidadesVel == 1) {//unidades en km/h
            valorIASLocal = valorAnemoLocalKmh;
            valorLimitLocal = vsiLimitLocalkmh;
            limiteBlanco = limiteBlancoKmh;
            limiteVerde = limiteVerdeKmh;
            limiteAmarillo = limiteAmarilloKmh;
            limiteRojo = limiteRojoKmh;
            limiteVB = limiteVBKmh;
            //limiteBV = limiteBVKmh;
            valorVy = valorVyKmh;
        } else if (unidadesVel == 2) {//unidades en kt
            valorIASLocal = valorAnemoLocalKt;
            valorLimitLocal = vsiLimitLocalkt;
            limiteBlanco = limiteBlancoKt;
            limiteVerde = limiteVerdeKt;
            limiteAmarillo = limiteAmarilloKt;
            limiteRojo = limiteRojoKt;
            limiteVB = limiteVBKt;
            //limiteBV = limiteBVKt;
            valorVy = valorVyKt;
        } else {//unidades en mph
            valorIASLocal = valorAnemoLocalMph;
            valorLimitLocal = vsiLimitLocalmph;
            limiteBlanco = limiteBlancoMph;
            limiteVerde = limiteVerdeMph;
            limiteAmarillo = limiteAmarilloMph;
            limiteRojo = limiteRojoMph;
            limiteVB = limiteVBMph;
            //limiteBV = limiteBVMph;
            valorVy = valorVyMph;
        }
    }
}
