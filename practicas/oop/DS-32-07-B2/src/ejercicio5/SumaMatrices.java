
package ejercicio5;

/**
 *
 * @author natalia.iglesiast
 * @author jose.naharro
 */
public class SumaMatrices {
    
    public SumaMatrices(){
        
    }
    public Matriz sumaMatrices(Matriz a, Matriz b){
        int i, j;
        
        if (b.getFilas() != a.getFilas() || b.getColumnas() != a.getColumnas())
            throw new RuntimeException("Dimensiones no compatibles");
        
        Matriz c = new Matriz(a.getFilas(), a.getColumnas());
        
        for (i = 0; i < a.getFilas(); i++)
            for (j = 0; j < a.getColumnas(); j++)
                c.getElementos()[i][j] = a.getElementos()[i][j] + b.getElementos()[i][j];
        
        return c;
    }
}
