
public class Gasto extends Dinero {

	/* Constructor de la clase */
	public Gasto(double gasto, String description) {
		this.setDinero(gasto);
		this.setDescription(description);
	}

	/* M�todos */
	@Override
	public String toString() {
		return "Gasto: " + this.getDescription() + ", cantidad:" + this.getDinero() + "�";
	}

}
