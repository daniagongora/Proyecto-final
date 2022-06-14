package modelado.fciencias.programa;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase para descifrar el documento previamente cifrado
 */
public class Descifrar {
    
    /**
     * Metodo para desencriptar un documento, dadas sus "share"
     * @param shares las share necesarias para desencriptar
     * @param encriptado el archivo encriptado 
     * @throws Exception en caso de que no se pueda desencriptar el archivo
     */
    public void desencriptaDoc(String shares, String encriptado) throws Exception {
        
        List<String> archivoDescifrado =  new LinkedList<>();
        try{
        List<String> cifrado = Documentos.leerDocumento(encriptado);  
        String ruta = cifrado.get(0);
        String clave = RecuperaContrasenia.recuperaContrasenia(shares).toString();
        
        for(int i = 1; i < cifrado.size();i++){
            String linDes = desencriptar(clave, cifrado.get(i));
            archivoDescifrado.add(linDes);
        }
        
        Documentos.guardarDocumento(ruta, archivoDescifrado);
        
        }catch (IOException e){
            
            System.out.println("Error al desencriptar el archivo");
            
        }
    }
    /**
     * Metodo para desencriptar una linea del archivo
     * @param llave la llave de encripatacion
     * @param encriptado el archivo encriptado
     * @return la linea del archivo desencriptado
     * @throws Exception en caso de que no se pueda desencriptar la linea
     */   
    private static String desencriptar(String llave, String encriptado) throws Exception {

        byte[] clave = new byte[llave.length() / 2];
        
        for (int i = 0; i < clave.length; i++) {
            
            int numAByte = Integer.parseInt(llave.substring(2 * i, 2 * i + 2), 16);
            clave[i] = (byte) numAByte;   
            
        }
        
        Cipher proceso = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec secreto = new SecretKeySpec(clave, "AES");
        proceso.init(Cipher.DECRYPT_MODE, secreto);
        byte[] info = Base64.getDecoder().decode(encriptado.getBytes(StandardCharsets.UTF_8));
        byte[] bytesTexto = proceso.doFinal(info);

        return new String(bytesTexto, StandardCharsets.UTF_8);
    }
    
    /**
     * Metodo main para probar la desencriptacion
     * @param args argumentos de consola 
     * @throws Exception en caso de algun error 
     */
    public static void main(String[] args) throws Exception{
        
        Descifrar p = new Descifrar();
        p.desencriptaDoc("archic.frg", "archic.aes");
    }
}
