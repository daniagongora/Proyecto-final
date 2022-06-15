package modelado.fciencias.pruebas;
import modelado.fciencias.programa.Contrasenia;
/**
 * Clase para correr todas las pruebas
 * del proyecto
 */
public class Prueba{

	/**
	 * Clase para correr todas las pruebas de cada clase
	 */
	public static void main (String[] args){

		PruebaContrasenia.pruebaCrearContrasenia();
		PruebaDocumentos.pruebaGuardar();
		PruebaDocumentos.pruebaLeerDocumentos();
		PruebaPolinomio.pruebaGeneraEnteros();
		PruebaCifrador.pruebaQuitarRuta();
		PruebaCifrador.pruebaEncriptaDoc();
		PruebaCifrador.pruebaEncriptar();
	
	}
}