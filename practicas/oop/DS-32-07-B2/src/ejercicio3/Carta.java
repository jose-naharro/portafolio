package ejercicio3;

public final class Carta {

    private final PaloCarta palo;
    private final NumeroCarta numero;
    private final double valor;
    protected boolean descubierta;

    public Carta(NumeroCarta numero, PaloCarta palo) {
        this.palo = palo;
        this.numero = numero;
        switch (numero) {
            case AS:
                this.valor = 1;
                break;
            case DOS:
                this.valor = 2;
                break;
            case TRES:
                this.valor = 3;
                break;
            case CUATRO:
                this.valor = 4;
                break;
            case CINCO:
                this.valor = 5;
                break;
            case SEIS:
                this.valor = 6;
                break;
            case SIETE:
                this.valor = 7;
                break;
             case SOTA:
                this.valor = 0.5;
                break;
            case CABALLO:
                this.valor = 0.5;
                break;
            case REY:
                this.valor = 0.5;
                break;
            default: throw new IllegalArgumentException();
        }
    }
    


    NumeroCarta getNumero() {
        return numero;
    }

    PaloCarta getPalo() {
        return palo;
    }
    
    public double getValor(){
        return valor;
    }
    
    public boolean setDescubierta(boolean b){       
        return this.descubierta = b;
    }
    
     public boolean getDescubierta(){
        return descubierta;
    }
   
     
    @Override
    public String toString() {
        return this.numero + " de " + this.palo;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Carta)) {
            return false;
        }
        
        Carta c = (Carta) obj;
        
        return numero.equals(c.numero) && palo.equals(c.palo);
    }
}