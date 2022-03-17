package ejercicio1;

public abstract class Poligonos extends Rectas implements Figura2D {

    protected int numSegmentos;

    public Poligonos(String nombre, int x, int y , double lado) {
        super(nombre, x, y, lado);
    }

     public int getNumSegmentos(){
       return numSegmentos;
    }
    
    @Override
    public double perimetro() {
        double p;
        p = lado * numSegmentos;
        return p;
    }
}
