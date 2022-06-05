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
		
		if (args.length==0 || args.length < 4) {
     		 System.out.println("no diste argumentos suficientes");
     		 System.exit(1);
    	}
		if (args[0].equals("c")) {
    
      		Cifrar cifrador=new Cifrar();
      		
      		try{
      			cifrador.cifrar(args);
      			System.out.println("Tu documento se cifro exitosamente");
      		}catch(Exception e){
      			System.out.println("algo salio mal al cifrar");
      		}
    	}
    	if(args[0].equals("d")){
    		System.out.println("esto debe decodificar");
    	}
    	
	}
}