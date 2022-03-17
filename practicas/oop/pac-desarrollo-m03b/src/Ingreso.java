
public class Ingreso extends Dinero {
	
	/* Constructor de la clase */
	public Ingreso(double ingreso, String description) {
		this.setDinero(ingreso);
		this.setDescription(description);
	}

	/* M�todos */
	@Override
	public String toString() {
		return "Ingreso: " + this.getDescription() + ", cantidad:" + this.getDinero() + "�";
	}
}
