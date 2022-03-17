import java.util.regex.Pattern;

public class Usuario {
	private String nombre;
	private int edad;
	private String DNI;
	
	
	/* Constructor de la clase */
	public Usuario() {
		
	}
	
	/* Getters y Setters */
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return this.edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getDNI() {
		return this.DNI;
	}
	
	public boolean setDNI(String DNI){
		String regExpA= "\\d{8}[a-zA-Z]";
		String regExpB= "\\d{8}[-][a-zA-Z]"; 
		
		// Comprobamos si la cadena pasada coincide con uno de los dos patrones
		//con o sin guion entre los numeros y la letra
		if (Pattern.matches(regExpA, DNI) || Pattern.matches(regExpB, DNI)){
			this.DNI= DNI;
			return true;	
		} else {
			return false;
		}
    }
	
	/* Métodos */
	
	@Override
	public String toString() {
		return "Usuario: " + this.getNombre() + ", edad: " + this.getEdad() + ", DNI: " + this.getDNI();
	}
}
