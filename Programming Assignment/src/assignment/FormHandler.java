package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;
/**
 * This class allows a user to search for a book in the database by its title
 * 
 * @author James Gabbitus
 *
 */
public class FormHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    
	    out.write(
		  "<html>" +
		  "<head> <title> Search by Title </title> "+
		  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
		  "</head>" +
		  "<body>" +
			  "<h1>Search for a book</h1>" +
			  "<button onclick=\"toMenu()\">Menu</button>"+
		      "<form method = \"Post\" action = \"/formaction\">" +
			      "<label> Enter book title </label>" +
				  "<input name = \"title\">"+ 
				  "<input type = \"submit\" value = \"Submit\">  "+
			  "</form>" + 
			  "<button onclick=\"goBack()\">Back</button>\r\n" + 
			  "<script>"+
				  "function toMenu() {"+
			          "location.assign(\"/menu\");"+
				  "}"+
				 "function goBack() {\r\n" + 
				 "  window.history.go(-1);\r\n" + 
				 "}\r\n" + 
			  "</script>"+
		  "</body>" +
		  "</html>");
	    out.close();
	  }
}