package modelado.fciencias.programa;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
/**
 * CLase para recuperar la contrasenia de los polinomios en base a "x" parejas
 * ordenadas de polinimios 
 */
public class RecuperaContrasenia {
    
    /**
     * Clase auxiliar para guardar las "SecretShare"
     */
    private static class SecretShare{
        
        // corresponde al segundo valor de las share
        private final BigInteger fx;
        
        /**
         * Metodo constructor de las SecretShare, para cualguier Secret share
         * "x" corresponde al primer valor de las parejas ordenadas y "fx"
         * al segundo valor.
         * @param x el primer valor de las parejas ordenadas
         * @param fx el segundo valor de las parejas ordenadas
         */
        public SecretShare(BigInteger x,BigInteger fx){
            this.fx = fx;
        }
        
        /**
         * Metodo de acceso al valor de "fx" representacion de f(x)=y
         * @return el valor de "f(x)
         */
        public BigInteger getfx(){
            return fx; 
        }
    }
    
    //el numero primo dado para el ejercicio
    private static final BigInteger primo = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");
    
    /**
     * Metodo para recuperar la "key" que encripta el archivo en base a lagrange
     * @param shares un arreglo con las "share" de encriptacion
     * @return  la "key" para desencriptar el archivo
     */
    private static BigInteger recupera(final SecretShare[] shares) {
        //En caso de conocer el "minimo de shares"
        int k = shares.length;
        BigInteger total = BigInteger.ZERO;
        for (int i = 0; i < k; i++) {
            BigInteger numerador = BigInteger.ONE;
            BigInteger denominador = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    numerador = numerador.multiply(BigInteger.valueOf(-j - 1)).mod(primo);
                    denominador = denominador.multiply(BigInteger.valueOf(i - j)).mod(primo);
                }
            }
            
            final BigInteger value = shares[i].getfx();
            final BigInteger tmp = value.multiply(numerador).multiply(denominador.modInverse(primo)).mod(primo);
            total = total.add(primo).add(tmp).mod(primo);
        }
        return total;
    }
    /**
     * Metodo para recuperar la "key" en base a un archivo dado
     * @param ruta la ruta del archivo 
     * @return el valor de la "key" en BigInteger
     */
    public static BigInteger recuperaContrasenia(String ruta) {
        BigInteger contrasenia = null;
        
        try {
            List<String> parejas = Documentos.leerDocumento(ruta);
            SecretShare[] shares = new SecretShare[parejas.size()];
            for (int i = 0; i < parejas.size(); i++) {
                String[] pareja = parejas.get(i).split(",");
                shares[i] = new SecretShare(new BigInteger(pareja[0].substring(1)), new BigInteger(pareja[1].substring(1, pareja[1].length() - 1)));
            }
            contrasenia = recupera(shares);
        } catch (IOException ex) {
            System.out.println("Ocurrrio un error al leer el archivo\n "
                    + "Verifique si el archivo existe o la ruta es correcta");
        }
        return contrasenia;

    }                         
}
