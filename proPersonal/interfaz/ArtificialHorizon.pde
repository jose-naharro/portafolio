class ArtificialHorizon {
    PGraphics horizonte;
    float ahPitch;
    float ahRoll;
    
    ArtificialHorizon(){
        horizonte = createGraphics(width, height);//tamaño de la pantalla, toda irá en azul
        horizonte.beginDraw();
        horizonte.background(colorAzulHorizonte);
        horizonte.noStroke();
        horizonte.endDraw();

        this.ahPitch = 0.;
        this.ahRoll = 0.;
    }

    public void draw(float valuePitch, float valueRoll) {
        horizonte.beginDraw();
        horizonte.background(colorAzulHorizonte);

        if (!isactivo10DOF) {
            horizonte.background(colorNegro);
            horizonte.stroke(colorRojo);
            horizonte.strokeWeight(2);
            horizonte.line(0, 0, horizonte.width, horizonte.height);
            horizonte.line(0, horizonte.height, horizonte.width, 0);
            horizonte.fill(colorAmarillo);
            horizonte.textFont(font16Ver);
            horizonte.textAlign(CENTER, CENTER);
            horizonte.text(textErrorHA, horizonte.width/2, horizonte.height/2);
        } else {
            horizonte.textSize(12);
            horizonte.strokeCap(SQUARE);

            if (textoNegrita) {
                horizonte.textFont(font20Ver, 20);
            } else {
                horizonte.textFont(font20Ver, 20);
            }

            actualizarValores(valuePitch, valueRoll);

            horizonte.pushMatrix();
                horizonte.translate(width/2+0.5, height/2+0.5);
                drawAttitudeIndicatorHorizonBrownPart(this.ahRoll, this.ahPitch);
            horizonte.popMatrix();


            setGradient(0, 0, width, height/2, colorNegroTransp, colorTransp1);
            setGradient(0, height/2, width, height/2, colorTransp1, colorNegroTransp);

            horizonte.pushMatrix();
                horizonte.translate(width/2+0.5, height/2+0.5);
                drawAttitudeIndicatorHorizonLines(this.ahRoll, this.ahPitch);
            horizonte.popMatrix();

            horizonte.pushMatrix();
                horizonte.translate(width/2+0.5, height/2+0.5);
                drawAttitudeIndicatorHorizonFixElements(this.ahRoll);
            horizonte.popMatrix();
        }

        

        horizonte.endDraw();

        image(horizonte, 0, 0);
    }

    public void actualizarValores(float actValuePitch, float actValueRoll) {
        this.ahPitch =  actValuePitch;
        this.ahRoll = actValueRoll;

        if (actValuePitch > 90 &&  actValuePitch <270) {
            this.ahPitch = 180 - actValuePitch;
        }

        if (actValuePitch >= 270 && actValuePitch <360) {
            this.ahPitch = actValuePitch - 360;
        }
    }

    public void drawAttitudeIndicatorHorizonFixElements(float fixValueRoll){
        //Esto es el triandulito amarillo
        horizonte.pushMatrix();
        horizonte.translate(0, -80);

        horizonte.stroke(255, 255, 0);    //  jaune     //  roll+sideslip indicator
        horizonte.fill(255, 255, 0);
        horizonte.strokeWeight(1);
        horizonte.triangle(0, -88, -10, -78, 10, -78);

        horizonte.translate(0, 80);
        horizonte.popMatrix();
        //Fin del triandulito amarillo

        horizonte.stroke(255, 255, 0);    //  amarillo
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.rect (-5,-5,10,10);  //  cuadradito central
        
        horizonte.fill(colorNegro);
        
        //Avion 
        horizonte.beginShape();   // ala izquierda
        horizonte.vertex (-90,-4);
        horizonte.vertex (-45,-4);
        horizonte.vertex (-45,23);
        horizonte.vertex (-53,23);
        horizonte.vertex (-53,4);
        horizonte.vertex (-90,4);
        horizonte.vertex (-90,-4);
        horizonte.endShape(CLOSE);

        horizonte.beginShape();   // ala derecha
        horizonte.vertex (90,-4);
        horizonte.vertex (45,-4);
        horizonte.vertex (45,23);
        horizonte.vertex (53,23);
        horizonte.vertex (53,4);
        horizonte.vertex (90,4);
        horizonte.vertex (90,-4);
        horizonte.endShape(CLOSE);

        //a partir de aqui tambien se mueven las cosas
        horizonte.rotate(radians(fixValueRoll));//horizonte.rotate(radians(roll));

        horizonte.pushMatrix();
        horizonte.translate(0, -80);
        //Triangulito amarillo 
        horizonte.strokeWeight(1);
        horizonte.fill(255, 255, 0);
        horizonte.triangle(0, -92, -10, -103, 10, -103);
        horizonte.translate(0, 80);
        horizonte.popMatrix();

        horizonte.stroke(colorTextoFormas);
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.arc(0, 0, 340, 340, (-2*PI/3), (-PI/3));


        horizonte.pushMatrix();
        horizonte.rotate(radians(10));
        horizonte.rect(-2,-170,4,-8);

        horizonte.rotate(radians(10));  // a 20 degres
        horizonte.rect(-2,-170,4,-8);
         
        horizonte.rotate(radians(10));  // a 30 degres
        horizonte.rect(-2,-170,4,-12);

        horizonte.rotate(radians(15));  // a 45 degres
        horizonte.line (0,-170,0,-182); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(15));  // a 67 degres 
        horizonte.line (0, -170, 0, -185);
        horizonte.line (4, -170, 4, -185);



        horizonte.stroke(colorTextoFormas);    
        horizonte.rotate(radians(-70));
        horizonte.rect(-2,-170,4,-8);

        horizonte.rotate(radians(-10));  // a 20 degres
        horizonte.rect(-2,-170,4,-8);
         
        horizonte.rotate(radians(-10));  // a 30 degres
        horizonte.rect(-2,-170,4,-12);

        horizonte.rotate(radians(-15));  // a 45 degres
        horizonte.line (0,-170,0,-182); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(-15));  // a 60 degres 
        horizonte.line (0, -170, 0, -185);
        horizonte.line (4, -170, 4, -185);

        horizonte.popMatrix();
    }

    /*
    public void drawAttitudeIndicatorHorizonFixElements(float fixValueRoll){
        horizonte.pushMatrix();
        horizonte.translate(0, -80);

        horizonte.stroke(255, 255, 0);    //  jaune     //  roll+sideslip indicator
        horizonte.fill(255, 255, 0);
        horizonte.strokeWeight(1);
        horizonte.triangle(0, -88, -10, -78, 10, -78);

        horizonte.translate(0, 80);
        horizonte.popMatrix();


        horizonte.stroke(255, 255, 0);    //  amarillo
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.rect (-5,-5,10,10);  //  cuadradito central
        
        horizonte.fill(colorNegro);
        
        //Avion 
        horizonte.beginShape();   // ala izquierda
        horizonte.vertex (-90,-4);
        horizonte.vertex (-45,-4);
        horizonte.vertex (-45,23);
        horizonte.vertex (-53,23);
        horizonte.vertex (-53,4);
        horizonte.vertex (-90,4);
        horizonte.vertex (-90,-4);
        horizonte.endShape(CLOSE);

        horizonte.beginShape();   // ala derecha
        horizonte.vertex (90,-4);
        horizonte.vertex (45,-4);
        horizonte.vertex (45,23);
        horizonte.vertex (53,23);
        horizonte.vertex (53,4);
        horizonte.vertex (90,4);
        horizonte.vertex (90,-4);
        horizonte.endShape(CLOSE);

        //a partir de aqui tambien se mueven las cosas
        horizonte.rotate(radians(fixValueRoll));//horizonte.rotate(radians(roll));

        horizonte.pushMatrix();
        horizonte.translate(0, -80);//Esto hace ampliar el arco con los cuadraditos hacia arriba en pantalla anchas
        

        horizonte.strokeWeight(1);
        horizonte.fill(255, 255, 0);
        horizonte.triangle(0, -92, -10, -103, 10, -103);

        horizonte.stroke(colorTextoFormas);
        horizonte.strokeWeight(2);
        horizonte.noFill();
        horizonte.arc(0, 0, 180, 180, (-2*PI/3), (-PI/3));


        horizonte.pushMatrix();
        horizonte.rotate(radians(10));
        horizonte.rect(-2,-90,4,-8);

        horizonte.rotate(radians(10));  // a 20 degres
        horizonte.rect(-2,-90,4,-8);
         
        horizonte.rotate(radians(10));  // a 30 degres
        horizonte.rect(-2,-90,4,-12);

        horizonte.rotate(radians(15));  // a 45 degres
        horizonte.line (0,-90,0,-102); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(15));  // a 67 degres 
        horizonte.line (0, -90, 0, -105);
        horizonte.line (4, -90, 4, -105);



        horizonte.stroke(colorTextoFormas);    
        horizonte.rotate(radians(-70));
        horizonte.rect(-2,-90,4,-8);

        horizonte.rotate(radians(-10));  // a 20 degres
        horizonte.rect(-2,-90,4,-8);
         
        horizonte.rotate(radians(-10));  // a 30 degres
        horizonte.rect(-2,-90,4,-12);

        horizonte.rotate(radians(-15));  // a 45 degres
        horizonte.line (0,-90,0,-102); 
         
        //horizonte.stroke(0, 255, 0);    //  2 barres vertes 
        horizonte.rotate(radians(-15));  // a 60 degres 
        horizonte.line (0, -90, 0, -105);
        horizonte.line (4, -90, 4, -105);

        horizonte.translate(0, 80);
        horizonte.popMatrix();// Esto hace ampliar el arco con los cuadraditos hacia arriba en pantalla anchas

        horizonte.popMatrix();
    }
    */

    public void drawAttitudeIndicatorHorizonBrownPart(float linesValueRoll, float linesValuePitch){
        float linelong = sqrt(sq(width/2)+sq(height/2));
        //horizonte.rotate(-ypr[0]);
        horizonte.rotate(radians(linesValueRoll));
        horizonte.translate(0, linesValuePitch*4);
        

        
        horizonte.fill(colorMarronHorizonte);
        horizonte.rect(-linelong, 0, linelong*2, height*4);
    }

    public void drawAttitudeIndicatorHorizonLines(float linesValueRoll, float linesValuePitch){
        float linelong = sqrt(sq(width/2)+sq(height/2));
        //horizonte.rotate(-ypr[0]);
        horizonte.rotate(radians(linesValueRoll));
        horizonte.translate(0, linesValuePitch*4);
        

        
        horizonte.strokeWeight (2);
        horizonte.stroke(colorTextoFormas);
        horizonte.fill(colorTextoFormas);

        horizonte.line(-50, -80, 50, -80);  //  ______  20
        horizonte.line(-30, -60, 30, -60);  //   ____   
        horizonte.line(-50, -40, 50, -40);  //  ______  10
        horizonte.line(-30, -20, 30, -20);  //   ____

        horizonte.line(-linelong, 0, linelong, 0);  //linea central
        horizonte.line(-2, -2, -2, 2);  //cuadrado concentrico central
        horizonte.rect(-2, -2, 4, 4) ; //cuadrado concentrico central

        horizonte.line(-30, 20, 30, 20);  //  ____
        horizonte.line(-50, 40, 50, 40);  // ______  10
        horizonte.line(-30, 60, 30, 60);  //  ____
        horizonte.line(-50, 80, 50, 80);  // ______  20

        horizonte.text("20", 67, -73); 
        horizonte.text("10", 67, -33); 
        horizonte.text("10", 67, 48);
        horizonte.text("20", 67, 88);
    }

    public void setGradient(int x, int y, float w, float h, color c1, color c2) {

        noFill();
        horizonte.strokeWeight(1);
        for (int i = y; i <= y+h; i++) {
            float inter = map(i, y, y+h, 0, 1);
            color c = horizonte.lerpColor(c1, c2, inter);
            horizonte.stroke(c);
            horizonte.line(x, i, x+w, i);
        }
    }
}