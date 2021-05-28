package assignment;

import java.io.OutputStream;
import java.io.*;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class processes the form of the SignUpHandler class, inserting the entered data
 * into the admins database table and hashing the text password
 * 
 * @author James Gabbitus
 *
 */
public class SignUpProcessHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
		String data = "Hello";
		String algorithm = "MD5";
		byte[] salt = createSalt();
		try {
			System.out.println("MD5 hash: " + generateHash(data, algorithm, salt));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
	    System.out.println("In FormProcessHandler");
	    BufferedReader in = new BufferedReader( new InputStreamReader(he.getRequestBody()) );
	    String line;
	    String request = "";
	    while( (line = in.readLine()) != null ) {
	        request = request + line;
	    }
	    System.out.println("request is " + request);
	    HashMap<String,String> map = Util.requestStringToMap(request);
	    System.out.println(map);    
	    
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody() ));
	    he.sendResponseHeaders(200,0);
	    BookDao books = new BookDao(); 
   	    String user = map.get("username");
   	    String pass = map.get("password");
   	    
   	    try {
	   	    try {
	   	    	String hp = PasswordHasher.hashPassword(pass.toString());
	   	    	Admin rec = new Admin((books.maxID(0)+1), user.toString(), hp);
				books.insertRecordIntoAdmins(rec);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	    
		System.out.println("Username: "+user+"\nPassword: "+pass);
		System.out.println("Signed up succesfully");
		out.write(
			  "<html>" +
				  "<head> <title> Sign Up Data </title> "+
				  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
				  "</head>" +
				  "<body>" +
				  	  "<button onclick=\"toMenu()\">Menu</button>"+
				      "<h4> Welcome "  + map.get("username") + "!</h4>" +	  
					  "<script>"+
						  "function toMenu() {"+
					          "location.assign(\"/menu\");"+
					          "return false;"+
						  "}"+  
					  "</script>");
		out.close();
	}
	
	/**
	 * Hashes a String of text with a chosen algorithm and salts the hash for extra security
	 * 
	 * @param data the text to be hashed
	 * @param algorithm the algorithm used to hash the text
	 * @param salt to seed of the hash
	 * @return the hashed text
	 * @throws NoSuchAlgorithmException
	 */
	private String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.reset();
		digest.update(salt);
		byte[] hash = digest.digest(data.getBytes());
		return bytesToStringHex(hash);
	}
	
	/**
	 * Length of hexArray
	 */
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	/**
	 * Convert the bytes to a String of hex characters
	 * 
	 * @param bytes The bytes of the hash
	 * @return the hashed text
	 */
	private String bytesToStringHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	/**
	 * Seed the hash with a random chain of bytes
	 * 
	 * @return a random chain of bytes
	 */
	private byte[] createSalt() {
		byte[] bytes = new byte[20];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}	
}