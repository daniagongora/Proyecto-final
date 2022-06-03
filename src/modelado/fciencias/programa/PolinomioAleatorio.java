package modelado.fciencias.programa;
import java.math.BigInteger;
import java.util.Random;
import java.util.ArrayList;

/**
 * Clase para generar el polinomio
 * con numeros enteros aleatorios
 *
 **/
public class PolinomioAleatorio{

//número primo para el modulo
private BigInteger primo = new BigInteger("208351617316091241234326746312124448251235562226470491514186331217050270460481");

//la llave del polinomio la cual será la que sea creada en contraseña
private BigInteger llave;

//puntos del polinomio  
private int puntos;

//coeficientes enteros del polinomio
private BigInteger[] enteros;

//los puntos necesarios para evualuar el polinomio
private int nPuntos;
    
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
}