package ejercicio3;

/**
 *
 *@author natalia.iglesiast
 *@author jose.naharro
 */
public class UtilidadesString {

    /**
     * Dado un String, devuelve el numero de caracteres que estan en mayuscula.
     * Tiene en cuenta vocales con tilde y similares caracteres especiales.
     *
     * @param cadena cadena a analizar .
     * @return numero de caracteres que estan en mayuscula .
     */
    
    public static int cuentaMayusculas(String cadena) {
        int n = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isUpperCase(cadena.charAt(i))) {
                n++;
            }
        }
        return n;
    }

    /**
     * Dada una cadena, devuelve el numero de caracteres que estan en minu
     * scula. Tiene en cuenta vocales con tilde y similares caracteres
     * especiales .
     *
     * @param cadena cadena a analizar .
     * @return numero de caracteres que estan en minuscula .
     */
    public static int cuentaMinusculas(String cadena) {
        int n = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLowerCase(cadena.charAt(i))) {
                n++;
            }
        }
        return n;

    }

    /**
     * Dado un String, devuelve una lista que cuenta, para cada una de las
     * vocales, cuantas de ellas tienen tilde ( ortografica castellana ). Trata
     * por igual mayusculas y minusculas .
     *
     * @param cadena cadena a analizar .
     * @return un array de 5 enteros que indican, respectivamente, 3 el numero
     * de as con tilde , el numero de es con tilde , etc .
     */
    public static int[] cuentaTildes(String cadena) {
        int[] tildes = {0, 0, 0, 0, 0};
        int i;
        
        for (i = 0; i < cadena.length(); i++) {
            switch (cadena.charAt(i)) {
                case 'á': case 'Á':
                    tildes[0]++;
                    break;
                case 'é': case 'É':
                    tildes[1]++;
                    break;
                case 'í': case 'Í':
                    tildes[2]++;
                    break;
                case 'ó': case 'Ó':
                    tildes[3]++;
                    break;
                case 'ú': case 'Ú':
                    tildes[4]++;
                    break;
            }
        }

        return tildes;
    }
}
