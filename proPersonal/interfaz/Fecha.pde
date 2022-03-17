class Fecha {
    PGraphics hora;
    PGraphics fecha;
    String horaUTC;
    String horaLT;
    String fechaUTC;
    String fechaLT;

    Fecha() {
        hora = createGraphics(150, 50);
        hora.beginDraw();
        hora.background(90, 90, 90, 191);
        //hora.background(0, 0, 0);
        this.hora_utc();
        this.hora_local();
        hora.endDraw();

        fecha = createGraphics(130, 50);
        fecha.beginDraw();
        fecha.background(90, 90, 90, 191);
        //hora.background(0, 0, 0);
        this.fecha_utc();
        this.fecha_local();
        fecha.endDraw();
    }

    void drawHora() {
        hora.beginDraw();
        //hora.background(90, 90, 90, 191);
        hora.background(0, 0, 0);
        
        if (!toggleFecha.isSelected()) {
            hora.background(90, 90, 90, 191);
        } else {
            //hora.background(90, 90, 90, 127);
            hora.background(90, 90, 90);
        }

        if (!isactivo10DOF) {
            hora.background(colorNegro);
        } else {
            this.hora_utc();
            this.hora_local();

            hora.fill(colorAzulCielo);
            hora.textFont(font16Ver, 16);
            hora.textAlign(CENTER, CENTER);
            hora.text(horaLT + " L", hora.width/2, hora.height/4);
            hora.text(horaUTC + " U", hora.width/2, hora.height/4*3);
        }

        
        hora.endDraw();
        image(hora, 100, 0, 150, 50);
    }

    void drawFecha() {
        fecha.beginDraw();
        //hora.background(90, 90, 90, 191);
        fecha.background(0, 0, 0);
        
        if (!toggleFecha.isSelected()) {
            fecha.background(90, 90, 90, 191);
        } else {
            //fecha.background(90, 90, 90, 127);
            fecha.background(90, 90, 90);
        }

        if (!isactivo10DOF) {
            fecha.background(colorNegro);
        } else {
            this.fecha_utc();
            this.fecha_local();

            fecha.fill(colorAzulCielo);
            fecha.textFont(font16Ver, 16);
            fecha.textAlign(CENTER, CENTER);
            fecha.text(fechaLT + " L", fecha.width/2, fecha.height/4);
            fecha.text(fechaUTC + " U", fecha.width/2, fecha.height/4*3);
        }

        
        fecha.endDraw();
        image(fecha, 550, 0, 130, 50);
    }

    private void hora_utc(){
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        Date fechaDate = new Date();
        horaUTC = formateador.format(fechaDate);
    }

    private void hora_local(){
        SimpleDateFormat formateador;
        if (opcionHora24.isSelected()) {
            formateador = new SimpleDateFormat("HH:mm:ss");
        } else {
            formateador = new SimpleDateFormat("hh:mm:ss a");
        }

        Date fechaDate = new Date();
        horaLT = formateador.format(fechaDate);
    }

    private void fecha_utc(){
        SimpleDateFormat formateador;
        if (opcionFecha1.isSelected()) {
            formateador = new SimpleDateFormat("dd/MM/yyyy");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        } else {
            formateador = new SimpleDateFormat("MM/dd/yyyy");
            formateador.setTimeZone(TimeZone.getTimeZone("GMT"));
        }

        Date fechaDate = new Date();
        fechaUTC = formateador.format(fechaDate);
    }

    private void fecha_local(){
        SimpleDateFormat formateador;
        if (opcionFecha1.isSelected()) {
            formateador = new SimpleDateFormat("dd/MM/yyyy");
        } else {
            formateador = new SimpleDateFormat("MM/dd/yyyy");
        }

        Date fechaDate = new Date();
        fechaLT = formateador.format(fechaDate);
    }
}