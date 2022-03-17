package ejercicio1;

import static java.lang.Math.PI;

public class Esfera extends Curvas implements Figura3D {
    
    private int z;

    public Esfera(String nombre, int x, int y, int z, double radio) {
        super(nombre, x, y, radio);
        
        this.z = z;
    }

    public int getZ(){
        return z;
    }
    
    @Override
    public double area() {
        double s;
        s = 4 * PI * (double)Math.pow(radio,2);
        return s;
    }

    @Override
    public double volumen() {
        double v;
        
        v = 4 * PI * (double)Math.pow(radio,3) / 3;
        return v;
    }
}
