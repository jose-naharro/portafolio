
package ejercicio1;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class Rectangulo {

    /* Atributos con las dimensiones de este rectangulo (valores entero). */
    private int base;
    private int altura;

    /*
     * Inicializa un nuevo rectangulo con los valores pasados por parametro.
     * Si se intenta dar valor 0 a alguna de las dimensiones, lanza
     * la excepcion IllegalArgumentException.
     * Si se intenta dar valor negativo a alguna de las dimensiones,
     * lo correge al valor positivo.
     * @param base base.
     * @param altura altura.
     */
    public Rectangulo(int base, int altura) {

        if ((base == 0) || (altura == 0)) {
            throw new IllegalArgumentException("base o altura = 0");
        } else {
            this.base = Math.abs(base);
            this.altura = Math.abs(altura);
        }

    }

    /**
     * Inicializa un nuevo rectangulo con base 2 y altura 1.
     */
    public Rectangulo() {
        this.base = 2;
        this.altura = 1;

    }

    /*
     * Setters y getters para los atributos.
     * Si se intenta dar valor 0 a alguna de las dimensiones, lanza 
     * la excepcion IllegalArgumentExcepcion.
     * Si se intenta dar valor negativo a alguna de las dimensiones.
     * lo corrige al valor positivo.
     */
    public void setBase(int base) {
        if (base == 0) {
            throw new IllegalArgumentException("base = 0");
        } else {
            this.base = Math.abs(base);
        }
    }

    public void setAltura(int altura) {
        if (altura == 0) {
            throw new IllegalArgumentException("altura = 0");
        } else {
            this.altura = Math.abs(altura);
        }
    }

    public int getBase() {
        return base;
    }

    public int getAltura() {
        return altura;
    }

    /**
     * Indica si este rectangulo en un cuadrado.
     *
     * @return true si y solo si la base y la altura son iguales.
     */
    public boolean esCuadrado() {
        if (base == altura) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Calcula el area de este rectangulo.
     *
     * @return area de este rectangulo.
     */
    public int area() {
        int area;
        area = this.base * this.altura;
        return area;
    }

    /**
     * Calcula el perimetro de este rectangulo.
     *
     * @return perimetro de este rectangulo.
     */
    public int perimetro() {
        int perimetro;
        perimetro = 2 * (base + altura);
        return perimetro;
    }

    /**
     * Gira 90 grados este rectangulo .
     */
    public void gira() {
        int x;
        x = base;
        base = altura;
        altura = x;
    }

    /**
     * Garantiza que este rectangulo este orientado de forma que la base no sea
     * menor que la altura .
     */
    public void ponHorizontal() {
        if (base < altura) {
            this.gira();
        }
    }

    /**
     * Garantiza que este rectangulo este orientado de forma que la altura no
     * sea menor que la base .
     */
    public void ponVertical() {
        if (altura < base) {
            this.gira();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        final Rectangulo rectangulo = (Rectangulo) obj;
//        Dos rectangulos iguales si poniendolos horizontales (o verticales)
//        su base y altura son iguales.
        rectangulo.ponHorizontal();
        this.ponHorizontal();

        if ((this.getBase() == rectangulo.getBase()) && (this.getAltura() == rectangulo.getAltura())) {
            return true;
        } else {
            return false;
        }
//        int[] datos1 = {this.base, this.altura};
//        int[] datos2 = {rectangulo.base, rectangulo.altura};
//
//        Arrays.sort(datos1);
//        Arrays.sort(datos2);
//
//        if (Arrays.equals(datos1, datos2)) {
//            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 5 * hash + this.altura + this.base;
        return hash;
    }
}
