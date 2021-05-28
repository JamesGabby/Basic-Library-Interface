package assignment;

import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/**
 * This class allows for other classes to put values into a HashMap 
 * @author James Gabbitus
 *
 */
public class Util {
	
	/**
	 * A prepared HashMap to link values from other classes
	 * @param request takes a value to be split
	 * @return the key and the value
	 */
    public static HashMap<String, String> requestStringToMap(String request) {
	    HashMap<String, String> map = new HashMap<String, String>();
	    String[] pairs = request.split("&");
	  
	    for (int i = 0; i < pairs.length; i++) {
		    String pair = pairs[i];
	        try {
	            String key = pair.split("=")[0];
	            key = URLDecoder.decode(key, "UTF-8");
	
	            String value = pair.split("=")[1];
	            value = URLDecoder.decode(value, "UTF-8");
	
	            map.put(key, value);
	          
	      } catch (UnsupportedEncodingException e) {
	    	  System.err.println(e.getMessage());
	      }
	  }
        return map;
  	} 

}
