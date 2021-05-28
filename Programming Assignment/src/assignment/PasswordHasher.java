package assignment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class hashes a piece of text
 * 
 * @author James Gabbitus
 *
 */
public class PasswordHasher {
	
	/**
	 * Hashes a textual password
	 * 
	 * @param password The password-text String to be hashed
	 * @return the hashed password
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] result = md.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : result) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();	
	}
}
