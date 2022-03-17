package ejercicio1;

public abstract class Poliedros extends Rectas implements Figura3D {

    protected int numCaras;
    protected int z;

    public Poliedros(String nombre, int x, int y, int z, double lado) {
          super(nombre, x, y ,z);

        if (lado == 0) {
            throw new IllegalArgumentException(" altura = 0");
        } else {
            this.lado = Math.abs(lado);;
        }
        this.z = z;
    }
    
    public abstract double areaCara();
    
    public int getZ() {
        return z;
    }

    public int getNumCaras() {
        return numCaras;
    }

}
