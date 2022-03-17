package ejercicio1;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class Cilindro extends Curvas implements Figura3D {

    private double altura;
    private int z;
    
    public Cilindro(String nombre, int x, int y, int z, double radio, double altura) {
        super(nombre, x, y,radio);
        
         if (altura == 0) {
            throw new IllegalArgumentException("altura = 0");
        } else {
            this.altura = Math.abs(altura);
        }
        this.z = z;

    }
     public int getZ(){
        return z;
    }
    
    public double getAltura(){
        return altura;
    }

    @Override
    public double area() {
        double s;
        s = 2 * PI * radio * (altura + radio) ;
        return s;
    }

    @Override
    public double volumen() {
        double v;
        
        v = PI * (double) pow(radio,2) * altura;
        return v;
    }
}