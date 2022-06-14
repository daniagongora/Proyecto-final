package modelado.fciencias.pruebas;
import modelado.fciencias.programa.Cifrar;
import modelado.fciencias.programa.Contrasenia;
import java.util.List;
import java.util.*;
import java.math.BigInteger;

/**
 * Clase para probar los métodos de la
 * clase cifrar
 */
public class PruebaCifrador{
	
	public static Cifrar cifrador=new Cifrar();
	
	/**
	 * Método para probar que quitarRuta()
	 * de Cifrar
	 **/
	public static void pruebaQuitarRuta(){
		
		String ruta = "/home/dania/Documentos/modelado/hola.txt";
		String prueba = cifrador.quitarRuta(ruta);
		
		if(prueba != ruta && prueba.indexOf('/')==-1){
			System.out.println("paso la prueba de quitar la ruta a los documentos");
		
		}else{
			System.out.println("no paso la prueba");
		}
	}

	/**
	 * Método para probar encriptaDoc() de  Cifrar
	 */
	public static void pruebaEncriptaDoc(){
		
		List<String> texto = new LinkedList<>();
		texto.add("hola esto es una prueba");
		String contrasenia = null;
		
		try{
			contrasenia = Contrasenia.crearContrasenia("prueba123");
			
			if(cifrador.encriptaDoc("prueba.txt", texto, contrasenia).contains("prueba.txt")){
				System.out.println("paso la prueba de encriptar el documento original");
			}
		
		}catch(Exception e){
			System.out.println("algo salio mal");
		}
	}
	
	/**
	 * Método para probar encriptar() de Cifrar
	 */
	public static void pruebaEncriptar(){

		String contrasenia = null;
		
		try{
			contrasenia = Contrasenia.crearContrasenia("prueba123");
			String prueba = "Hola soy una prueba";
			String generado = cifrador.encriptar(contrasenia, prueba);
						
			if(generado != prueba && generado != null && generado.length() > 19) {
				System.out.println("paso la prueba al encriptar");
			}
		
		}catch(Exception e){
			System.out.println("ocurrio un error ");
		}
	}
}