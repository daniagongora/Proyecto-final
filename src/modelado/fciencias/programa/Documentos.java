package modelado.fciencias.programa;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.*;
import java.io.*;
/**
 * Clase para leer y guardar el documento
 * con la información a esconder
 */
public class Documentos{


	/*public static List<String> leerDocumento(String ruta) throws IOException{
		
		List<String> leido = new LinkedList<>();
    	Path rutaRecuperada = Paths.get(ruta);
    	
    	for (String cadena : Files.readAllLines(rutaRecuperada)) {
      		leido.add(cadena);
    	}
   		
   		return leido;
	}*/
	/**
	 * Método para leer el documento reibido
	 * @param ruta la ruta dada del documento
	 * @return la lista documento, que contiene las lineas de texto.
	 * https://www.logicbig.com/how-to/code-snippets/jcode-java-io-files-write.html
	 */
	public static List<String> leerDocumento(String ruta) throws IOException{
		
		List<String> leido = new LinkedList<>();
    	Path rutaRecuperada = Paths.get(ruta);
    	File documento = new File(ruta);
    	BufferedReader doc = new BufferedReader(new FileReader(documento));
    	String info;
    	while ((info = doc.readLine()) != null) {
      		leido.add(info);
    	}
   		
   		return leido;
	}
	
	/**
	 * Método para guardar un documento dado
	 * @param ruta la ruta en donde guardar el documento
	 * @param leido la lista que contiene la información del documento
	 * https://www.logicbig.com/how-to/code-snippets/jcode-java-io-files-write.html
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