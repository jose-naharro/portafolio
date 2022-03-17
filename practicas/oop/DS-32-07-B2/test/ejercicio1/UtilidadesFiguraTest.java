package ejercicio1;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class UtilidadesFiguraTest{

    public UtilidadesFiguraTest() {
    }

    @Test
    public void testAreaMedia() {

        List<DimensionFigura> Lista = new ArrayList<>();

        Lista.add(new Rectangulo("R1", 9, 7, 3, 4));
        Lista.add(new Esfera("Esfera2", 1, -45, 3, 5.6));
        Lista.add(new Elipse("Elipse1", 13, 3, 3.2, 2.7));
        Lista.add(new Rectangulo("R2", 32, 0, 4, 1.5));
        Lista.add(new Cubo("Cubo", 1, 0, -4, -5));

        double result = UtilidadesFigura.areaMedia(Lista);
        assertEquals(117.8, result, 0.1);

    }

    @Test
    public void testPerimetroMedia() {
         List<Figura2D> Lista = new ArrayList<>();

        Lista.add(new Rectangulo("R1", 9, 7, 3, 4));
        Lista.add(new Rectangulo("R2", 32, 0, 4, 1.5));
        Lista.add(new TrianguloEqui("Triangulo2", 1, 0, -4.6));
        Lista.add(new Elipse("Elipse1", 13, 3, 3.2, 2.7));
        Lista.add(new Rectangulo("R2", 32, 0, 4, 1.5));
        Lista.add(new Circulo("Circulo",4,4,6.3));

        double result = UtilidadesFigura.perimetroMedia(Lista);
        assertEquals(19.28, result, 0.1);
    }

    @Test
    public void testNumSegMedia() {
        List<Poligonos> Lista = new ArrayList<>();

        Lista.add(new Rectangulo("R1", 9, 7, 3, 4));
        Lista.add(new TrianguloEqui("Triangulo", 1, -45, 3.4));
        Lista.add(new Rectangulo("R1", 9, 7, 3, 4));
        Lista.add(new Rectangulo("R2", 32, 0, 4, 1.5));
        Lista.add(new TrianguloEqui("Triangulo2", 1, 0, -4));

        double result = UtilidadesFigura.numSegMedia(Lista);
        assertEquals(3.6, result, 0.1);
    }

    @Test
    public void testVolumenMedia() {
         List<Figura3D> Lista = new ArrayList<>();

        Lista.add(new PiramideCuadr("Piramide", 3, 3, 2, 3.2, 2.7));
        Lista.add(new Esfera("Esfera1", 1, -45, 3, 5.6));
        Lista.add(new Cubo("Cubo2", 13, 3, 7, 2.7));
        Lista.add(new Cilindro("Cilindro2", 32, 0, 4, 1.5, 11.7));
        Lista.add(new Cubo("Cubo2", 1, 0, -4, -5));

        double result = UtilidadesFigura.volumenMedia(Lista);
        assertEquals(194.4, result, 0.1);
    }

    @Test
    public void testNumCarasMedia() {
         List<Poliedros> Lista = new ArrayList<>();

        Lista.add(new Cubo("Cubo", 9, 7, 3, -4));
        Lista.add(new Cubo("Cubo2", 1, -4, 3, 6));
        Lista.add(new PiramideCuadr("Piramide", 3, 3, 2, 3.2, 2.7));
        Lista.add(new PiramideCuadr("Piramide2", 1, 4, 6, 3.2, 10.7)); 
        Lista.add(new Cubo("Cubo3", 1, 0, -4, -5));

        double result = UtilidadesFigura.numCarasMedia(Lista);
        assertEquals(5.6, result, 0.1);
    }

    @Test
    public void testRadioMedia() {
         List<Curvas> Lista = new ArrayList<>();

        Lista.add(new Circulo("C1", 9, 7, 3.4));
        Lista.add(new Esfera("Esfera2", 1, -45, 3, 5.6));
        Lista.add(new Elipse("Elipse1", 13, 3, 3.2, 2.7));
        Lista.add(new Circulo("C2", 32, 0, 1.5));
        Lista.add(new Cilindro("Cilindro", 1, 0, 5, -2.5, -5));

        double result = UtilidadesFigura.radioMedia(Lista);
        assertEquals(3.23, result, 0.1);
    }

}
