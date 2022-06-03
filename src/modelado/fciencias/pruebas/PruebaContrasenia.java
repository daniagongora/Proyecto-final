package modelado.fciencias.pruebas;
import modelado.fciencias.programa.Contrasenia;
import java.math.BigInteger;


/**
 * Clase para probar la generación de 
 * contraseñas con SHA-256
 */
public class PruebaContrasenia{

	/**
	 * Prueba 
	 * @Test
	 */
	 
	public static void pruebaCrearContrasenia(){
		
		String generado = null;

		try{
			generado = Contrasenia.crearContrasenia("prueba123");
		
		}catch(Exception e){
			System.out.println("ocurrio un error al generar la contraseña");
		}
		
		BigInteger numero = new BigInteger(generado, 16);
    	assert (256 == numero.bitLength());
		assert (generado != "prueba123");

		if(256 == numero.bitLength() && generado != "prueba123"){
			System.out.println("paso la prueba de la contraseña");
			
		}else{
			System.out.println("no paso la prueba de la contraseña");
			
		}
		
	}
}