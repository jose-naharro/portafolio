
package ejercicio2;

import java.util.ArrayList;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class BuzonTest {
    
    public BuzonTest() {
    }

    /**
     * Test of agregarMensaje method, of class Buzon.
     */
    @Test
    public void testAgregarMensaje() {
        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarMensaje(email2);
        buzon1.agregarMensaje(email1);
        
        assertEquals(email1.getDe(), buzon1.getCorreos().get(0).getDe());
        assertEquals(email2.getDe(), buzon1.getCorreos().get(1).getDe());

    }

    /**
     * Test of agregarListaMensajes method, of class Buzon.
     */
    @Test
    public void testAgregarListaMensajes() {

        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email2);
        listaMensajes.add(email1);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        assertEquals(email1.getDe(), buzon1.getCorreos().get(0).getDe());
        assertEquals(email2.getDe(), buzon1.getCorreos().get(1).getDe());
    }

    /**
     * Test of eliminarMensaje method, of class Buzon.
     */
    @Test
    public void testEliminarMensaje() {
        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email2);
        listaMensajes.add(email1);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        buzon1.eliminarMensaje(email1);
        
        assertEquals(email2.getDe(), buzon1.getCorreos().get(0).getDe());
    }

    /**
     * Test of eliminarListaMensajes method, of class Buzon.
     */
    @Test
    public void testEliminarListaMensajes() {
        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email1);
        listaMensajes.add(email2);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        buzon1.eliminarListaMensajes();
        
        assertTrue(buzon1.getCorreos().isEmpty());
    }

    /**
     * Test of getCorreos method, of class Buzon.
     */
    @Test
    public void testGetCorreos() {
        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email1);
        listaMensajes.add(email2);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        buzon1.getCorreos();
        
        assertEquals(listaMensajes, buzon1.getCorreos());
    }
    
    /**
     * Test of OrdenarPorAsunto method, of class Buzon.
     */
    @Test
    public void testOrdenarPorAsunto() {
        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba2";
        String mensaje = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba1";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email2);
        listaMensajes.add(email1);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        buzon1.ordenarPor(new OrdenarPorAsunto());
        
        assertEquals("prueba1", buzon1.getCorreos().get(0).getAsunto());
    }
    
    /**
     * Test of OrdenarPorDe method, of class Buzon.
     */
    @Test
    public void testOrdenarPorDe() {
        //Creamos un mensaje
        String de = "emisor2@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor1@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email2);
        listaMensajes.add(email1);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        buzon1.ordenarPor(new OrdenarPorDe());
        
        assertEquals("emisor1@micorreo.com", buzon1.getCorreos().get(0).getDe());
    }
    
    /**
     * Test of OrdenarPorDe method, of class Buzon.
     */
    @Test
    public void testOrdenarPorTamano() {
        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor1@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor2@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",500);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email2);
        listaMensajes.add(email1);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        buzon1.ordenarPor(new OrdenarPorTamano());
        
        assertEquals(email2.getTamano(), buzon1.getCorreos().get(0).getTamano());
    }
    
    
    /**
     * Test of OrdenarPorPara method, of class Buzon.
     */
    @Test
    public void testOrdenarPorPara() {
        //Creamos un mensaje
        String de = "emisor1@micorreo.com";
        ArrayList<String>  para = new ArrayList<>();
        para.add("receptor2@micorreo.com");
        ArrayList<String>  cc = new ArrayList<>();
        String asunto = "prueba1";
        String mensaje = "Hola esto es una prueba de a ver si va este correo1";
        Calendar fecha = Calendar.getInstance();
        fecha.set(2015, 1, 1, 10, 00, 00);
        
        Adjunto adjunto1 = new Adjunto("adjuntoA",100);
        Adjunto adjunto2 = new Adjunto("adjuntoB",200);
        ArrayList<Adjunto> adjuntos = new ArrayList<>();
        adjuntos.add(adjunto1);
        adjuntos.add(adjunto2);
        
        Mensaje email1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);
        
        
        //Creamos otro mensaje
        String de2 = "emisor2@micorreo.com";
        ArrayList<String>  para2 = new ArrayList<>();
        para2.add("receptor1@micorreo.com");
        ArrayList<String>  cc2 = new ArrayList<>();
        String asunto2 = "prueba2";
        String mensaje2 = "Hola esto es una prueba de a ver si va este correo2";
        Calendar fecha2 = Calendar.getInstance();
        fecha2.set(2015, 2, 1, 10, 00, 00);
        
        Adjunto adjunto12 = new Adjunto("adjuntoA",100);
        Adjunto adjunto22 = new Adjunto("adjuntoB",500);
        ArrayList<Adjunto> adjuntos2 = new ArrayList<>();
        adjuntos2.add(adjunto12);
        adjuntos2.add(adjunto22);
        
        Mensaje email2 = new Mensaje(de2,para2,cc2,asunto2,mensaje2,fecha2,adjuntos2);
        
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        listaMensajes.add(email2);
        listaMensajes.add(email1);
        
        //Creamos un buzon de correo
        Buzon buzon1 = new Buzon();
        buzon1.agregarListaMensajes(listaMensajes);
        
        buzon1.ordenarPor(new OrdenarPorPara());
        
        assertEquals(email2.getPara().get(0), buzon1.getCorreos().get(0).getPara().get(0));
    }
    
}
