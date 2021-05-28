package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class represents a form in which the user is able to enter a username and password to login
 * @author James Gabbitus
 *
 */
public class LoginHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    
	    out.write(
		  "<html>" +
		  "<head> <title> Admin Login </title> "+
		  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
		  "</head>" +
		  "<body>" +
			  "<h1>Login</h1>" +
			  "<button onclick=\"toMenu()\">Menu</button>"+
			  
		      "<form method = \"Post\" action = \"/loginaction\" name = \"myForm\"> " +
			      "<label> Username </label>" + "<input name = \"username\" required><br>"+ 
				  "<label> Password </label>" + "<input name = \"password\" input type = \"password\" required><br>"+ 
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