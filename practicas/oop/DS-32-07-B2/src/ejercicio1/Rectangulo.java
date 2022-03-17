package ejercicio1;

public class Rectangulo extends Poligonos implements Figura2D{
    private double altura;
    
    public Rectangulo(String nombre, int x, int y , double lado, double altura){
       super(nombre, x, y,lado);
       if (altura == 0)  {
            throw new IllegalArgumentException("altura = 0");
        } else {
            this.altura = Math.abs(altura);
        }
       this.numSegmentos = 4;
       
    }
    
    
     public double getAltura() {
        return altura;
    }
       
    @Override
    public double area(){
        return lado*altura;
    }
    
    @Override
    public double perimetro() {
        double p;
        p = lado * 2 + altura *2;
        return p;
    }
}

