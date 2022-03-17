
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
public class MensajeTest {
    
    public MensajeTest() {
    }

    /**
     * Test of Mensaje method, of class Mensaje.
     */
    @Test
    public void testMensaje() {
        
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
        
        Mensaje mensaje1 = new Mensaje(de,para,cc,asunto,mensaje,fecha,adjuntos);

        assertEquals(de, mensaje1.getDe());
        assertEquals(para, mensaje1.getPara());
        assertEquals(cc, mensaje1.getCc());
        assertEquals(asunto, mensaje1.getAsunto());
        assertEquals(mensaje, mensaje1.getMensaje());
        assertEquals(fecha, mensaje1.getFecha());
        assertEquals(adjunto1, mensaje1.getAdjuntos().get(0));
        assertEquals(adjunto2, mensaje1.getAdjuntos().get(1));
        assertEquals(351 , mensaje1.getTamano());
        
        fecha.set(2015, 1, 2, 10, 00, 00);
        mensaje1.setAsunto("Hola");
        mensaje1.addCc("javier@javier.es");
        cc.add("javier@javier.es");
        mensaje1.setFecha(fecha);
        mensaje1.setMensaje("Mensaje");
        mensaje1.addPara("pedro@gmail.com");
        para.add("pedro@gmail.com");
        mensaje1.agregarAdjunto(adjunto2);
        mensaje1.setDe("jose@jose.it");
        
        assertEquals("jose@jose.it", mensaje1.getDe());
        assertEquals(para, mensaje1.getPara());
        assertEquals(cc, mensaje1.getCc());
        assertEquals("Hola", mensaje1.getAsunto());
        assertEquals("Mensaje", mensaje1.getMensaje());
        assertEquals(fecha, mensaje1.getFecha());
        assertEquals(adjunto1, mensaje1.getAdjuntos().get(0));
        assertEquals(adjunto2, mensaje1.getAdjuntos().get(1));
        assertEquals(adjunto2, mensaje1.getAdjuntos().get(2));
        assertEquals(507 , mensaje1.getTamano());
    }
    
    
}
