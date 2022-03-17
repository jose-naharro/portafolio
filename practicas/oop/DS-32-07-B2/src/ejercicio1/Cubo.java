package ejercicio1;

public class Cubo extends Poliedros implements Figura3D {

    
    public Cubo(String nombre, int x, int y, int z, double lado) {
        super(nombre, x, y, z , lado);

        this.numCaras = 6;
    }
     
    public double areaCara(){
        double a;
        a = lado* lado;
        return a;
    }
    @Override
    public double area() {
        double s;
        s = areaCara() * numCaras;
        return s;
    }

    @Override
    public double volumen() {
        double v;
        v = Math.pow(lado, 3);
        return v;
    }
}
