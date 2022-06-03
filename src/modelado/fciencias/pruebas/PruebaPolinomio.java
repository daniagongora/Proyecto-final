package modelado.fciencias.pruebas;
import java.math.BigInteger;
import modelado.fciencias.programa.PolinomioAleatorio;
import modelado.fciencias.programa.Contrasenia;

/**
 * Clase para probar métodos de la clase
 * PolinomioAleatorio
 */
public class PruebaPolinomio{

	/**
	 * Prueba para el método generaEnteros de
	 * PolinomioAleatorio
	 */
	public static void pruebaGeneraEnteros(){
		
		try{
			
			String clave = Contrasenia.crearContrasenia("prueba123");
			BigInteger llave = new BigInteger(clave, 16);

			PolinomioAleatorio polinomioAleatorio = new PolinomioAleatorio(5, 3, llave);
			BigInteger[] polinomio = polinomioAleatorio.generaEnteros(llave);
		
			if(polinomio[polinomio.length-1].equals(llave)){
				System.out.println("Paso la prueba de crear el polinomio");
			}
		
		}catch(Exception e){
			System.out.println("ocurrio un error al generar la contraseña");
		}
		
	}
}