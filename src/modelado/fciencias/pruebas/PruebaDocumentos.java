package modelado.fciencias.pruebas;
import modelado.fciencias.programa.Documentos;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.*;

/**
 * Clase para probar la clase Documentos
 */
public class PruebaDocumentos{
	
	/**
	 * Prueba para el método leerDocumentos
	 * de Documentos
	 */
	public static void pruebaLeerDocumentos(){
		
		List<String> verificar = new LinkedList<>();
		verificar.add("Hola Mundo :)");
		List<String> leido = new LinkedList<>();
		
		try{
			
			leido = Documentos.leerDocumento("prueba2.txt");
			
			if(leido.equals(verificar)){
				System.out.println("paso la prueba de leer el documento");
			}
		
		}catch(Exception e){
			
			System.out.println("error al leer el documento");
		}

	}
	/**
	 * Prueba para el método guardarDocumentos
	 * de Documentos
	 */
	public static void pruebaGuardar(){
		
		List<String> leido = new LinkedList<>();
    	leido.add("hola");
    	leido.add("esto es");
    	leido.add("una");
    	leido.add("prueba");
    	
    	try{
    		
    		Documentos.guardarDocumento("prueba.txt", leido);
    		Path ruta = Paths.get("prueba.txt");
    		boolean existe = Files.exists(ruta);
    		
    		if(existe)
    			System.out.println("paso la prueba de guardar el documento");
    	
    	}catch(Exception e){
    		
    		System.out.println("error al guardar el documento");
    	}
    	
    	
	}
}