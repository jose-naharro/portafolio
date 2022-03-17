package ejercicio1;

import static java.lang.Math.PI;

public class Circulo extends Curvas implements Figura2D {

    public Circulo(String nombre, int x, int y, double radio) {
        super(nombre, x, y, radio);
         
    }

    @Override
    public double area() {
        double a;
        a = PI * (double) Math.pow(radio, 2);
        return a;
     
    }
    
    @Override
    public double perimetro() {
        double p;
        p = 2 * PI * radio;
        return p;
    }

}
