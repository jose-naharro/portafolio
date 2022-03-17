PrintWriter outputTime;
BufferedReader reader;
String efisTimeString;
long millisAnteriores = 0;
long tiempoEfisEncendido;
long efisTimeCargadoLong;
long efisTimeGuardarLong;

void tiempoEfisEncendido(){
    if (millis() - millisAnteriores > 60000) {//cada 60 segundos
        tiempoEfisEncendido = tiempoEfisEncendido + millis() - millisAnteriores;
        //println(tiempoEfisEncendido);
        efisTimeGuardarLong = efisTimeCargadoLong + tiempoEfisEncendido;
        //println(efisTimeGuardarLong);
        outputTime = createWriter("data/efis-hours");
        outputTime.println(efisTimeGuardarLong);
        outputTime.flush();
        outputTime.close();

        //efisTimeGuardarLong = efisTimeCargadoLong + tiempoEfisEncendido;

        efisHOURS();

        millisAnteriores = millis();
    }
}

void efisHOURS(){
    long num; 
    float horas;
    String shoras;

    num = efisTimeGuardarLong/1000;
    horas = ((float)num)/3600;
    shoras = nf(horas, 1, 2);

    jsonInfoSistema.setString("floatHorasFuncionamiento", shoras);
    saveJSONObject(jsonInfoSistema, "data/infoSistema.json");

    textLabelIhoras.setText(textHorasFuncionamiento+" "+jsonInfoSistema.getString("floatHorasFuncionamiento"));

    print(num);print("\t");println(jsonInfoSistema.getString("floatHorasFuncionamiento"));

    cargarInfoSistema();
}