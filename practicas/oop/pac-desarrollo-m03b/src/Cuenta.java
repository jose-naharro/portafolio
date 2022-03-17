import java.util.ArrayList;
import java.util.List;

public class Cuenta {
	
	private double saldo;
	private Usuario usuario;
	private List<Gasto> gastos;
	private List<Ingreso> ingresos;
	
	/* Constructor de la clase */
	public Cuenta(Usuario usuario) {
		//Inicializamos todos los atributos.
		this.usuario = usuario;
		this.saldo= 0.0;
		this.gastos= new ArrayList <Gasto>();
		this.ingresos= new ArrayList <Ingreso>();
	}

	/* Getters y Setters */
	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Ingreso> getIngresos() {
		return this.ingresos;
	}
	
	public List<Gasto> getGastos() {
		return this.gastos;
	}
	
	public double addIngresos(String description, double cantidad) {
		//Añadimos el ingreso a la lista de ingresos
		this.ingresos.add(new Ingreso(cantidad, description)); 
		//Aumentamos el saldo
		this.saldo += cantidad;
		
		//Devolvemos el saldo
		return this.saldo;
	}
	
	public double addGastos(String description, double cantidad) {
		try {
			//Si existe saldo suficiente, anotaremos el gasto, si no lanzaremos una excepción
			//Devuelve el saldo según la declaración del método.
			if (this.getSaldo() >= cantidad) {
				this.gastos.add(new Gasto (cantidad, description));
				this.saldo -= cantidad;
				return this.saldo;
			} else {
				throw new GastoException();
			}
		} catch (GastoException e) {
			System.err.println(e.getMessage()); 
			return this.saldo;
		}
	}
	
	/* Métodos */
	@Override
	public String toString() {
		return "Usuario: " + getUsuario().toString() + ", saldo: " + this.saldo + " €";
	}
}
