package ejercicio5;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class PeliculaTest {

    public PeliculaTest() {

        //Pelicula sin valoraciones
        Pelicula peli1 = new Pelicula("Conan");
        ArrayList<Valoracion> listaValoracionesVacia = new ArrayList<>();
        assertEquals("Conan", peli1.getTitulo());
        assertEquals(listaValoracionesVacia, peli1.getValoraciones());
    }

    /**
     * Test of getTitulo method, of class Pelicula.
     */
    @Test
    public void testGetTitulo() {
        Pelicula peli1 = new Pelicula("Conan");
        assertEquals("Conan", peli1.getTitulo());
    }

    /**
     * Test of setTitulo method, of class Pelicula.
     */
    @Test
    public void testSetTitulo() {
        Pelicula peli1 = new Pelicula("Conan");
        peli1.setTitulo("Conan el barbaro");
        assertEquals("Conan el barbaro", peli1.getTitulo());
    }

    /**
     * Test of getValoraciones method, of class Pelicula.
     */
    @Test
    public void testGetValoraciones() {
        //Pelicula sin valoraciones
        Pelicula peli1 = new Pelicula("Conan");
        ArrayList<Valoracion> listaValoracionesVacia = new ArrayList<>();
        assertEquals("Conan", peli1.getTitulo());
        assertEquals(listaValoracionesVacia, peli1.getValoraciones());

        //Insertamos valoraciones
        peli1.insertaValoracion(Valoracion.EXCELENTE);
        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.BUENA);
        peli1.insertaValoracion(Valoracion.PESIMA);
        ArrayList<Valoracion> listaValoraciones = new ArrayList<>();
        listaValoraciones.add(Valoracion.EXCELENTE);
        listaValoraciones.add(Valoracion.NO_VALORADA);
        listaValoraciones.add(Valoracion.NO_VALORADA);
        listaValoraciones.add(Valoracion.BUENA);
        listaValoraciones.add(Valoracion.PESIMA);
        assertEquals(listaValoraciones, peli1.getValoraciones());
    }

    /**
     * Test of setValoraciones method, of class Pelicula.
     */
    @Test
    public void testSetValoraciones() {
        Pelicula peli1 = new Pelicula("Conan");

        ArrayList<Valoracion> listaValoraciones = new ArrayList<>();
        listaValoraciones.add(Valoracion.EXCELENTE);
        listaValoraciones.add(Valoracion.NO_VALORADA);
        listaValoraciones.add(Valoracion.NO_VALORADA);
        listaValoraciones.add(Valoracion.BUENA);
        listaValoraciones.add(Valoracion.PESIMA);
        peli1.setValoraciones(listaValoraciones);
        assertEquals(listaValoraciones, peli1.getValoraciones());
    }

    /**
     * Test of insertaValoracion method, of class Pelicula.
     */
    @Test
    public void testInsertaValoracion() {

        Pelicula peli1 = new Pelicula("Conan");
        peli1.insertaValoracion(Valoracion.EXCELENTE);
        assertEquals(Valoracion.EXCELENTE, peli1.getValoraciones().get(0));

        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.BUENA);
        assertEquals(Valoracion.NO_VALORADA, peli1.getValoraciones().get(1));
        assertEquals(Valoracion.BUENA, peli1.getValoraciones().get(2));
    }

    /**
     * Test of valoracionMaxima method, of class Pelicula.
     */
    @Test
    public void testValoracionMaxima() {

        //Pelicula no valorada
        Pelicula peli1 = new Pelicula("Conan");
        assertEquals(Valoracion.NO_VALORADA, peli1.valoracionMaxima());

        //Valoramos, de manera desordenada
        peli1.insertaValoracion(Valoracion.EXCELENTE);
        peli1.insertaValoracion(Valoracion.OBRA_MAESTRA);
        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.PESIMA);
        peli1.insertaValoracion(Valoracion.EXCELENTE);
        peli1.insertaValoracion(Valoracion.BUENA);
        peli1.insertaValoracion(Valoracion.OBRA_MAESTRA);
        peli1.insertaValoracion(Valoracion.MEDIOCRE);
        peli1.insertaValoracion(Valoracion.MALA);
        assertEquals(Valoracion.OBRA_MAESTRA, peli1.valoracionMaxima());

        Pelicula peli2 = new Pelicula("Avatar");
        peli2.insertaValoracion(Valoracion.NO_VALORADA);
        peli2.insertaValoracion(Valoracion.NO_VALORADA);
        peli2.insertaValoracion(Valoracion.NO_VALORADA);
        assertEquals(Valoracion.NO_VALORADA, peli2.valoracionMaxima());
    }

    /**
     * Test of valorNumericoMedio method, of class Pelicula.
     *
     * @throws ejercicio5.ValoracionNoValidaException
     */
    @Test
    public void testValorNumericoMedio() throws ValoracionNoValidaException {
        double delta = 0.001;

        Pelicula peli1 = new Pelicula("Conan");

        //Sin no valoradas
        peli1.insertaValoracion(Valoracion.EXCELENTE);
        peli1.insertaValoracion(Valoracion.OBRA_MAESTRA);
        assertEquals(9.0, peli1.valorNumericoMedio(), delta);

        //con varias no valoradas
        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.PESIMA);
        peli1.insertaValoracion(Valoracion.EXCELENTE);
        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.BUENA);
        peli1.insertaValoracion(Valoracion.OBRA_MAESTRA);
        peli1.insertaValoracion(Valoracion.MEDIOCRE);
        peli1.insertaValoracion(Valoracion.MALA);
        double result = (8 + 10 + 8 + 6 + 10 + 4 + 2) / 8;
        assertEquals(result, peli1.valorNumericoMedio(), delta);

    }

    /**
     * Test of valorNumericoMedio method, of class Pelicula.
     *
     */
    @Test
    public void testValorNumericoMedioMal1() {
        double delta = 0.001;

        Pelicula peli1 = new Pelicula("Conan");

        try {
            //Lista vacia de valoraciones
            peli1.valorNumericoMedio();
        } catch (ValoracionNoValidaException ex) {
            System.out.println("Problem: " + ex.getMessage());
        }

        //con varias no valoradas
        peli1.insertaValoracion(Valoracion.NO_VALORADA);
        peli1.insertaValoracion(Valoracion.NO_VALORADA);

        try {
            //Lista vacia de valoraciones
            peli1.valorNumericoMedio();
        } catch (ValoracionNoValidaException ex) {
            System.out.println("Problem: " + ex.getMessage());
        }

    }

}
