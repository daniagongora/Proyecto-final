package modelado.fciencias.programa;
import java.math.BigInteger;
import java.util.*;

/**
 * Clase para generar el polinomio
 * con numeros enteros aleatorios
 * estructura del polinomio
 *  https://github.com/terappy/ShamirSecretSharing/blob
/master/src/main/java/scheme/SecretShare.java
 **/
public class PolinomioAleatorio{

    //número primo para el modulo
    private final BigInteger primo = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");

    //la llave del polinomio la cual será la que sea creada en contraseña
    private BigInteger llave;

    //puntos del polinomio  
    private final int puntos;

    //coeficientes enteros del polinomio
    private final BigInteger[] enteros;

    //los puntos necesarios para evualuar el polinomio
    private final int nPuntos;
    
    /**
     * Constructor de la clase que genera un polinomio
     * @param puntosDados los puntos por los que esta el polinomio
     * @param evalua los puntos por donde se evaluara
     * @param clave la llave escondida en el polinomio
     */
    public PolinomioAleatorio(int puntosDados, int evalua, BigInteger clave){
        
        this.puntos = puntosDados-1;
        this.nPuntos = evalua;
        this.llave = clave;
        this.enteros = generaEnteros(clave);
        
    }
    
    /**
     * Método que genera los números enteros
     * de los coeficientes del polinomio
     * @param llave el termino independiente del polinomio
     * @return un arreglo de estos enteros
     * https://github.com/RicoVergara94/ShamirsSecretSharingAlgo
     /blob/main/src/main/java/Polynomial.java
     **/
    public BigInteger[] generaEnteros(BigInteger llave){
        
        BigInteger[] coeficientes = new BigInteger[this.puntos+1];
        
        for (int i = 0; i<coeficientes.length-1; i++) {
            Random random = new Random();
            coeficientes[i] = new BigInteger(256, random);
        }
        
        coeficientes[coeficientes.length-1] = llave;
        return coeficientes;
    }
    /**
     * Método de evalucaiones de horner
     * para dar la n parejas ordenadas.
     * @return lista con las evaluaciones de hornes
     */
    public List<BigInteger> horner(){
        
        List<BigInteger> parejasOrdenadas = new LinkedList<>();

        for (int i = 1; i <= nPuntos; i++) {
            
            BigInteger resultado = new BigInteger(String.valueOf(i));
            parejasOrdenadas.add(resultado);           
            BigInteger resultado2 = auxiliarHorner(i);
            parejasOrdenadas.add(resultado2);
        }
        return parejasOrdenadas;
        
    }
    /*
    * Método auxiliarHorner que realiza la evaluación en el polinomio
    * del punto recibido.
    */
    private BigInteger auxiliarHorner(int evaluacion){
        
        BigInteger numero = new BigInteger(String.valueOf(evaluacion));
        BigInteger resultado = new BigInteger("0");
        for (BigInteger entero : enteros) {
            
            BigInteger multiplicacion = resultado.multiply(numero);
            BigInteger modulo = multiplicacion.mod(primo);
            BigInteger suma = modulo.add(entero);
            BigInteger sumaModulo = suma.mod(primo);
            resultado = sumaModulo;
            
        }
        return resultado;
    }
}