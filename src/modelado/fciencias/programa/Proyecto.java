package modelado.fciencias.programa;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.math.BigInteger;
import java.io.IOException;
import java.util.*;

/**
 * Clase principal que implementa el esquema 
 * secreto compartido de shamir
 */

public class Proyecto{

	public static void main (String[] args){
		
		if (args.length==0) {
     		 System.out.println("no diste argumentos");
     		 System.exit(1);
    	}
		if (args[0].equals("c")) {
      		System.out.println("esto debe codificar");
      		try{
			
			String clave = Contrasenia.crearContrasenia("prueba123");
			BigInteger llave = new BigInteger(clave, 16);

			PolinomioAleatorio polinomioAleatorio = new PolinomioAleatorio(5, 3, llave);
			BigInteger[] polinomio = polinomioAleatorio.generaEnteros(llave);
		
		}catch(Exception e){
			System.out.println("ocurrio un error al generar la contraseña");
		}
    	}
    	if(args[0].equals("d")){
    		System.out.println("esto debe decodificar");
    	}
    	
	}
}