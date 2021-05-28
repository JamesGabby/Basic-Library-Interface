package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class allows the user to sign up
 * 
 * @author James Gabbitus
 *
 */
public class SignUpHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    
	    out.write(
		  "<html>" +
		  "<head> <title> Sign Up </title> "+
		  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
		  "</head>" +
		  "<body>" +
			  "<h1>Sign up</h1>" +
			  "<button onclick=\"toMenu()\">Menu</button>"+
			  
		      "<form method = \"Post\" action = \"/signupaction\" name = \"myForm\"> " +
			      "<label> Enter a username </label>" + "<input name = \"username\" required><br>"+ 
				  "<label> Enter a password </label>" + "<input name = \"password\" input type = \"password\" required><br>"+ 
				  "<input type = \"submit\" value = \"Submit\">  "+
			  "</form>" + 
					  
			  "<script>"+
			  
				  "function toMenu() {"+
			          "location.assign(\"/menu\");"+
			          "return false;"+
				  "}"+
		          
			  "</script>"+
			  
		  "</body>" +
		  "</html>");
	    
	    out.close();
	  }
}