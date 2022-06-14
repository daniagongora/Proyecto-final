package modelado.fciencias.programa;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.*;
import java.io.Console;


/**
 * Clase para cifrar el documento con 
 * una contraseña dada por la clase contraseña
 */
public class Cifrar{


    /**
     * Método para quitar la ruta de un doumento
     * @param documento el documento de quitarle la ruta
     */
    public String quitarRuta(String documento){
        
        String rutaDoc = documento;
       
        for (int i = 0; i < documento.length(); i++) {
            
            char simbolo = documento.charAt(i);
            String quitar = "/";
            
            if (simbolo == quitar.charAt(0)) {
                rutaDoc = documento.substring(i+1);
            }
        }
        
        return rutaDoc;  
    }

    /**
     * Recibe la contraseña del usuario de forma que no 
     * hace eco en la terminal 
     * @return la contraseña dada por el usuario
     */
    public String obtenerContrasenia() throws Exception{
        
        char[] contrasenaUsr = null;
        Console entrada;
        String contrasena = null;
        entrada = System.console();
            
            if (entrada != null) {
                contrasenaUsr = entrada.readPassword("Ingrese una contraseña: ");
            }
        
        contrasena = String.valueOf(contrasenaUsr);
        return contrasena;
    }
    
    /**
     * Verifica que los puntos minimos y de evaluación sean correctos
     * @param args la entrada en consola
     */
    public void verificarArgs(String args[]) throws Exception{
        
        int nPuntos = Integer.parseInt(args[2]);
        int minPuntos = Integer.parseInt(args[3]);

        if (nPuntos <= 2) {
            System.out.println("Se requieren al menos 2 puntos");
            System.exit(1);
        }
        else if (minPuntos > nPuntos) {
            System.out.println("los puntos minimos deben ser menores a los totales");
          System.exit(1);
        }

    }
    /**
     * Encripta cada linea de texto de un documento
     * @param ruta el documento
     * @param archivo el archivo de origen
     * @param contrasenia la contraseña para encriptar
     */
    public List<String> encriptaDoc(String ruta, List<String> archivo, String contrasenia){
        
        List<String> archivoCifrado = new LinkedList<>();
        archivoCifrado.add(ruta);
        
        try {
            for (String linEncp : archivo) {
          
                String linCifrada = encriptar(contrasenia, linEncp);
                archivoCifrado.add(linCifrada);
            }
        
        }catch(Exception e) {
            System.out.println("Error al encriptar el archivo.");
        }
        
        return archivoCifrado;
    }
    
    /**
     * Método para encriptar el texto que se reciba.
     * @param llave la llave para encriptar (la contrasenia)
     * @param texto el texto a encriptar
     * @return el texto encriptado
     * 
    */ 
    public String encriptar(String llave, String texto) throws Exception {
        
        byte[] clave = new byte[llave.length() / 2];
        
        for (int i = 0; i < clave.length; i++) {
            int numAByte = Integer.parseInt(llave.substring(2 * i, 2 * i + 2), 16);
            clave[i] = (byte) numAByte;
        }
        
        Cipher proceso = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec esconder = new SecretKeySpec(clave, "AES");
        proceso.init(Cipher.ENCRYPT_MODE, esconder);
        byte[] bytesTexto = proceso.doFinal(texto.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(bytesTexto);
    }
    
    /**
     * Método para ordenar los puntos de las evaluaciones
     * @param horner las lista de paejas de las evaluaciones
     */
    public List<String> acomodaPuntos(List<BigInteger> horner){
        
        List<String> parejasOrdena = new LinkedList<>();
        String encontrar = "(";
        for (BigInteger cordenada : horner) {
            if (horner.indexOf(cordenada) % 2 == 0) {
                encontrar += cordenada.toString() + ", ";
            } else {
                encontrar += cordenada.toString() + ")";
                parejasOrdena.add(encontrar);
                encontrar = "(";
            }
        }
        return parejasOrdena;
    }
    
    /**
    * Método para cifrar el documento 
    * @param args la entrada de consola
    */
    public void cifrar(String args[]) throws Exception{
        
        /*Obtener los valores de consola*/
        String archivoEvaluaciones = args[1];
        String docOriginal = args[4];
        int evaluaciones = 0;
        int minimos = 0;
        evaluaciones = Integer.parseInt(args[2]);
        minimos = Integer.parseInt(args[3]);

        try {
            verificarArgs(args);

        }catch(Exception e) {
            System.err.println("El dato ingresado como parámetro no es correcto");
            System.exit(1);
        }

        String rutaDoc = quitarRuta(docOriginal);
      
        String contrasenia="";
        try {
            contrasenia = obtenerContrasenia();
            
        } catch(Exception e) {
            System.out.println("Formato inválido de contraseña.");
        }

        String contraseniaSegura = "";
        try {
            contraseniaSegura = Contrasenia.crearContrasenia(contrasenia);
        } catch(Exception e) {
            System.out.println("Error al generar contraseña.");
        }
        
        /* Creamos el polinomio con los valores de la consola con PolinomioAleatorio*/
        BigInteger realContrasenia = new BigInteger(contraseniaSegura, 16);
        PolinomioAleatorio polinomio = new PolinomioAleatorio(minimos, evaluaciones, realContrasenia);
        List<BigInteger> parejasnEval = polinomio.horner();
        List<String> parejasOrdenadas = acomodaPuntos(parejasnEval);

        /* Leemos y obtenemos el texto del documento original */
        List<String> textoOriginal = new LinkedList<>();
        try {
            textoOriginal = Documentos.leerDocumento(docOriginal);
        } catch(IOException e) {
            System.out.println("Archivo" + e.getMessage() + "no encontrado");
        }

        List<String> archivoAES = encriptaDoc(rutaDoc, textoOriginal, contraseniaSegura);

        try {
           
            String docCifrado = archivoEvaluaciones + ".aes";
            Documentos.guardarDocumento(docCifrado, archivoAES);
            String parejasCompletas = archivoEvaluaciones + ".frg";
            Documentos.guardarDocumento(parejasCompletas, parejasOrdenadas);
       
        } catch(IOException e) {
            e.getMessage();
        }
        System.out.println("Tu documento se cifro exitosamente");
    
        return;
    }
}