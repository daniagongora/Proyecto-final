package modelado.fciencias.programa;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Clase para generar una contraseña segura
 * a partir de la dada por el usuario con 
 * SHA-256
 */
public class Contrasenia{

	/**
	 * Método que crea una contraseña segura en base a la
	 * que dio el usuario
	 * https://howtodoinjava.com/java/java-security/how
	 * -to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	 */
	public static String crearContrasenia(String conUsuario) throws Exception{
		String conRequerida = null;

		MessageDigest proceso = MessageDigest.getInstance("SHA-256");
        proceso.update(conUsuario.getBytes());
        conRequerida = String.format("%064x", new BigInteger(1, proceso.digest()).abs() ); 
        
        return conRequerida;
	}

}