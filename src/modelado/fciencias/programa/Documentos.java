package modelado.fciencias.programa;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.*;
/**
 * Clase para leer y guardar el documento
 * con la información a esconder
 */
public class Documentos{

	/**
	 * Método para leer el documento reibido
	 * @param ruta la ruta dada del documento
	 * @return la lista documento, que contiene las lineas de texto.
	 */
	public static List<String> leerDocumento(String ruta) throws IOException{
		
		List<String> leido = new LinkedList<>();
    	Path rutaRecuperada = Paths.get(ruta);
    	
    	for (String cadena : Files.readAllLines(rutaRecuperada)) {
      		leido.add(cadena);
    	}
   		
   		return leido;
	}
	
	/**
	 * Método para guardar un documento dado
	 * @param ruta la ruta en donde guardar el documento
	 * @param leido la lista que contiene la información del documento
	 */
	public static void guardarDocumento(String ruta, List<String> leido) throws IOException{
		
		String guardado = "";
    	Path rutaGuardar = Paths.get(ruta);
    	
    	for (String cadena : leido) {
    		guardado += cadena + "\n";
       	}
    	
    	Files.write(rutaGuardar, guardado.getBytes());
	}
}