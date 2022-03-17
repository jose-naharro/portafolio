package ejercicio1;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class Elipse extends Curvas implements Figura2D {
    
    private double radio2;

    public Elipse(String nombre, int x, int y, double radio, double radio2) {
        super(nombre, x, y, radio);
        
          if (radio2 == 0) {
            throw new IllegalArgumentException("radio = 0");
        } else {
            this.radio2 = Math.abs(radio2);
        }

    }
    public double getRadio2(){
        return radio2;
    }
    @Override
    public double area() {
        double a;
        a = PI * radio * radio2;
        return a;
     
    }
    
    @Override
    public double perimetro() {
        double p;
        p = 2 * PI * (double)pow (((double)pow(radio,2)+(double)pow(radio2,2)),0.5);
        return p;
    }

}