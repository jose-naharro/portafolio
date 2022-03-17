
package ejercicio2;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class Mensaje {

    private String de;
    private ArrayList<String> para;
    private ArrayList<String> cc;
    private String asunto;
    private String mensaje;
    private Calendar fecha;
    private ArrayList<Adjunto> adjuntos;
    private int tamanoAdjuntos;
    private int tamanoTexto;
    
    public Mensaje(String de, ArrayList<String>  para, ArrayList<String>  cc, String asunto,
            String mensaje, Calendar fecha, ArrayList<Adjunto> adjuntos) {
        this.de = de;
        
        this.para = new ArrayList<>();
        this.para.addAll(para);
        
        this.cc = new ArrayList<>();
        this.cc.addAll(cc);
        
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.fecha = fecha;

        this.adjuntos = new ArrayList<>(); //hace falta???
        this.adjuntos.addAll(adjuntos);
        for (Adjunto adjunto : this.adjuntos) {//Calculamos el tamaño
            this.tamanoAdjuntos += adjunto.getTamano();
        }
        this.tamanoTexto = this.mensaje.getBytes().length;

        this.tamanoAdjuntos = tamanoAdjuntos;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }
    
    public ArrayList<String>  getPara() {
        return para;
    }

    public void addPara(String para) {
        this.para.add(para);
    }
    
    public ArrayList<String>  getCc() {
        return cc;
    }

    public void addCc(String cc) {
        this.cc.add(cc);
    }
    
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
        this.tamanoTexto = mensaje.getBytes().length;
    }
    
    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }
    
    public ArrayList<Adjunto> getAdjuntos() {
        return this.adjuntos;
    }

    public void agregarAdjunto(Adjunto adjunto) {
        this.adjuntos.add(adjunto);
        this.tamanoAdjuntos += adjunto.getTamano();
    }
    
    public int getTamano() {
        return tamanoTexto + tamanoAdjuntos;
    }


    
    
    
    
    
}
