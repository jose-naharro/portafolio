class Gps {
    PGraphics gps;
    String fechaUTC;
    String fechaLT;
    float senyalMedia;
    float gsGpsKmh;
    float gsGpsKt;
    float gsGpsMph;
    float gsGpsMostrar;
    float altGpsFt;
    float altGpsM;
    float altGpsMostrar;
    //String fechaUTCgps;
    //String fechaLTgps;

    Gps() {
        gps = createGraphics(width, height);
        gps.beginDraw();
        gps.background(0);
        gps.endDraw();
        this.senyalMedia = 0.0;
        this.fecha_utc();
        this.fecha_local();
        //this.fecha_utc_gps();
        //this.fecha_local_gps();
    }

    public float getSenyalMedia(){
        return this.senyalMedia;
    }

    public void setSenyalMedia(float mySenyalMedia){
        this.senyalMedia = mySenyalMedia;
    }
    public void calcularSenyalMedia(float[] senyalesGPS){
        int j = 0;
        float acumuladoSenyal = 0;

        for (int i = 0; i < MAX_SATELLITES; ++i) {
            if (senyalesGPS[i] != 0 && senyalesGPS[i] <= 100) {
                j +=1;
                acumuladoSenyal = acumuladoSenyal + senyalesGPS[i];
            }
        }
        setSenyalMedia(acumuladoSenyal/j);
        //println("acumuladoSenyal: "+acumuladoSenyal);
        //println("j: "+j);
    }
    void draw(float gpsGSvalue, float gpsCRSvalue) {
        this.fecha_utc();
        this.fecha_local();
        //this.fecha_utc_gps();
        //this.fecha_local_gps();
        gps.beginDraw();
        gps.background(0);
        
        if (!isactivoGPS) {
            gps.stroke(colorRojo);
            gps.strokeWeight(2);
            gps.line(0, 0, gps.width, gps.height);
            gps.line(0, gps.height, gps.width, 0);
            gps.fill(colorAmarillo);
            gps.textFont(font16Ver);
            gps.textAlign(CENTER, CENTER);
            gps.text("GPS", gps.width/2, gps.height/2);
        } else {

            calcularSenyalMedia(satsSignals);

            calcularVelocidades();

            calcularAltitudes();

            gps.textAlign(LEFT, CENTER);
            gps.fill(colorNaranja);
            gps.stroke(colorNaranja);
            gps.textFont(font25VerBold);

            gps.text(textInfoGPS, 50, 20);
            //gps.line(50, 35, 185, 35);

            gps.textFont(font20Ver);
            gps.text(textLocalGPS, 50, 80);
            gps.text(textUTCGPS, 50, 110);
            gps.text(textLatGPS, 50, 140);
            gps.text(textLonGPS, 50, 170);
            gps.text(textGSGPS, 50, 200);
            gps.text(textCursoGPS, 50, 230);
            gps.text(textAltGPS, 50, 260);

            gps.fill(colorBlanco);
            gps.textFont(font20Ver);
            gps.textAlign(RIGHT, CENTER);
            gps.text(fechaLT, 300, 80);
            gps.text(fechaUTC, 300, 110);
            gps.text(nfs(lat, 2, 6), 300, 140);
            gps.text(nfs(longt, 3, 6), 300, 170);
            gps.text(nf((int)gsGpsMostrar, 3), 300, 200);//sog
            gps.text(nf((int)cog, 3), 300, 230);//cog
            gps.text(nf(round(altGpsMostrar), 5), 300, 260);//gpsalt

            gps.textAlign(LEFT, CENTER);
            gps.fill(colorNaranja);
            gps.text(unidadesVelocidad, 305, 200);//sog unidadesVelocidad  
            gps.text(unidadesALT, 305, 260);//gpsalt unidadesALT 

            this.dibujarRadar();

            gps.popMatrix();

            gps.textAlign(LEFT, CENTER);
            gps.textFont(font20Ver);
            gps.fill(colorNaranja);
            gps.text(textSatActivosGPS, 50, gps.height-90);
            gps.text(textSenalGPS, 50, gps.height-60);

            gps.textAlign(RIGHT, CENTER);
            gps.fill(colorBlanco);
            gps.text(int(gpsnum), 50+10+180, gps.height-90);
            gps.text(nfs(this.senyalMedia, 0, 1), 50+10+180, gps.height-60);
            gps.fill(colorNaranja);
            gps.text("dBs", 50+10+220, gps.height-60);
        }

        gps.endDraw();
        image(gps, 0, 0, width, height);
    }
    
    private void dibujarRadar(){
        gps.stroke(colorBlanco);
        gps.noFill();
        gps.ellipseMode(CENTER);
        gps.ellipse(gps.width/4*3, gps.height/2*1, (gps.width/4-30)*2, (gps.width/4-30)*2);//exterior
        gps.ellipse(gps.width/4*3, gps.height/2*1, (gps.width/4-30)*2/3*2, (gps.width/4-30)*2/3*2);//medio
        gps.ellipse(gps.width/4*3, gps.height/2*1, (gps.width/4-30)*2/3, (gps.width/4-30)*2/3);//interior

        for(float i = 0; i < 360; i +=30.0) {
            gps.pushMatrix();
            gps.translate(gps.width/4*3, gps.height/2*1);
            gps.rotate(radians(i));
            gps.strokeWeight(1);
            gps.line(0, 0, 0, 170);
            gps.popMatrix();
        }
        
        gps.pushMatrix();
        gps.textFont(font22Ver);
        gps.textAlign(CENTER, CENTER);
        gps.translate(gps.width/4*3, gps.height/2*1);
        gps.text("N", 1, -190);
        gps.text("S", 1, 190);
        gps.text("E", 190, 0);
        gps.text("W", -190, 0);
        gps.textFont(font14Ver);
        //Revisar esto para mandar solo satélites validos, por ejemplo desde arduino, que mande 1º cuantos va a mandar y asi trasladalo a una variable de limite en processing (no MAX_SATELLITES)
        for (int i = 0; i < MAX_SATELLITES; ++i) {
            if (satsSignals[i]>0 && satsSignals[i]<100 && satsElevation[i]>=0 && satsAzimuth[i]>=0) {
                gps.fill(colorVerdeFosfo);
                gps.noStroke();
                gps.ellipseMode(CENTER);
                gps.ellipse(satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])), 20, 20);
                gps.fill(colorNegro);
                gps.text(i+1, satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])));
            }
            if (satsSignals[i] == 0 && (satsElevation[i]>0 || satsAzimuth[i]>0)) {
                gps.fill(colorRojo);
                gps.noStroke();
                gps.ellipseMode(CENTER);
                gps.ellipse(satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])), 20, 20);
                gps.fill(colorNegro);
                gps.text(i+1, satsElevation[i]*sin(radians(satsAzimuth[i])), -satsElevation[i]*cos(radians(satsAzimuth[i])));
            }
        }
/*
        gps.fill(colorVerdeFosfo);
        gps.noStroke();
        gps.ellipseMode(CENTER);
        gps.ellipse(0, 10, 10, 10);
        gps.ellipse(-10, 30, 10, 10);
        gps.ellipse(25, -45, 10, 10);
        gps.ellipse(90, 0, 10, 10);
        gps.ellipse(70, -35, 10, 10);
        gps.fill(colorRojo);
        gps.ellipse(0, -10, 10, 10);
        gps.ellipse(10, -30, 10, 10);
        gps.ellipse(-25, 45, 10, 10);
        gps.ellipse(-90, 0, 10, 10);
        gps.ellipse(-70, 35, 10, 10);
*/
    }

    void calcularVelocidades(){
        gsGpsKmh = sog;
        gsGpsKt = sog/1.852;
        gsGpsMph = sog*0.621371;

        if (unidadesVel == 1) {
            gsGpsMostrar = gsGpsKmh;
        } else if (unidadesVel == 2) {
            gsGpsMostrar = gsGpsKt;
        } else {
            gsGpsMostrar = gsGpsMph;
        }
    }
    
    void calcularAltitudes(){
        altGpsFt = gpsalt*3.28084;
        altGpsM = gpsalt;
        if (unidadesAlt == 1) {
            altGpsMostrar = altGpsM;
        } else {
            altGpsMostrar = altGpsFt;
        }
    }

    private void fecha_utc(){ 
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        Date fechaDate = new Date();
        fechaUTC = formateador.format(fechaDate);
    }

    private void fecha_local(){
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
        }

        Date fechaDate = new Date();
        fechaLT = formateador.format(fechaDate);

        //SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

        //Date fechaDate = new Date();
        //fechaLT = formateador.format(fechaDate);
    }
/*
    private void fecha_utc_gps(){
        
        String input = "2016-11-09 "+nf(int(satHH), 2)+":"+nf(int(satMM), 2)+":"+nf(int(satSS), 2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime formatDateTime = LocalDateTime.parse(input, formatter);
        
        fechaUTCgps = formatDateTime.getHour() +":"+ formatDateTime.getMinute() +":"+ formatDateTime.getSecond();

        //println(fechaUTCgps);
    }

    private void fecha_local_gps(){
        String input = "2016-11-09 "+nf(int(satHH), 2)+":"+nf(int(satMM), 2)+":"+nf(int(satSS), 2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime formatDateTime = LocalDateTime.parse(input, formatter);

        
        fechaLTgps = formatDateTime.atZone(ZoneId.systemDefault()).getHour() +":"+ formatDateTime.atZone(ZoneId.systemDefault()).getMinute() +":"+ formatDateTime.atZone(ZoneId.systemDefault()).getSecond();
    }
*/
}
