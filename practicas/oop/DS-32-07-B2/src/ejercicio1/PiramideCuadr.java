package ejercicio1;

import static java.lang.Math.pow;

public class PiramideCuadr extends Poliedros implements Figura3D {

    private double altura;

    public PiramideCuadr(String nombre, int x, int y,int z, double lado, double altura) {
        super(nombre, x, y, z, lado);
        if (altura == 0) {
            throw new IllegalArgumentException("altura = 0");
        } else {
            this.altura = Math.abs(altura);
        }
        this.numCaras = 5;
    }
    
    public double getAltura(){
        return altura;
    }

    public double apotema() {
        double h, a;
        a = lado / 2;
        h = (double) pow((double) (pow(altura, 2) + (double) pow(a, 2)), 0.5);
        return h;
    }

    public double areaCara() {
        double a;
        a = (lado * apotema()) / 2;
        return a;
    }

    public double areaBase() {
        double b;
        b = lado * lado;
        return b;
    }

    @Override
    public double area() {
        double s;
        s = areaCara() * 4 + areaBase();
        return s;
    }

    @Override
    public double volumen() {
        double v;
        v = areaBase() * altura / 3;
        return v;
    }
}
