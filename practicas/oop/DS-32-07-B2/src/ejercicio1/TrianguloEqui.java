package ejercicio1;

import static java.lang.Math.pow;

public class TrianguloEqui extends Poligonos implements Figura2D {

    public TrianguloEqui(String nombre, int x, int y, double lado) {
        super(nombre, x, y, lado);
        this.numSegmentos =3;
   
    }
     
    public double apotema() {
        double h, a;
        a = lado/ 2;
        h = (double) pow((double) (pow(lado, 2) + (double) pow(a, 2)), 0.5);
        return h;
    }
    
    @Override
    public double area() {
        return lado* apotema() / 2; 
    }

}
