package Utils;

import java.util.Base64;

/*this is simple encrimtion and decryption tool for passwords**/

public class Encryption {
	

	
	public static String encrypt(String input){
		    return Base64.getEncoder().withoutPadding().encodeToString(input.getBytes());
		}
	
	public static String decrypt(String cipherText) {
		    return new String(Base64.getDecoder().decode(cipherText));
		}
}
