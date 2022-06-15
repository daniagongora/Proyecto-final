package modelado.fciencias.programa;
/**
 * Clase principal que implementa el esquema 
 * secreto compartido de shamir
 */

public class Proyecto{
    
    public static void main (String[] args){		
        
        if (args.length==0 || args.length < 2) {
            System.out.println("no diste argumentos suficientes");
            System.exit(1);
    	}
        
        if (args[0].equals("c")) {
    
            Cifrar cifrador=new Cifrar();      		
      		try{
                 cifrador.cifrar(args);
                   
      		}catch(Exception e){
                System.out.println("algo salio mal al cifrar");
      		}
    	}
        
    	if(args[0].equals("d")){
            Descifrar descifrado=new Descifrar();
            try{
            descifrado.descifrar(args);
        }catch(Exception e){
        	System.out.println("algo salio mal al descifrar");
        }
    	}
    	
    }
}