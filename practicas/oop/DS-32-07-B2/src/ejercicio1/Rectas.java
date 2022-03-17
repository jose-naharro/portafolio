package ejercicio1;

public abstract class Rectas extends FormaFigura {

    protected double lado;
    

    public Rectas(String nombre, int x, int y, double lado) {
        super(nombre, x, y);

        if (lado == 0) {
            throw new IllegalArgumentException("lado = 0");
        } else {
            this.lado = Math.abs(lado);
        }
    }

    public double getLado() {
        return lado;
    }

}
