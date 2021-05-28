package assignment;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class allows the user to select an option from the menu
 * 
 * @author James Gabbitus
 *
 */
public class MenuHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    
	    out.write(
			  "<html>" +
			  "<head> <title>Menu</title> "+
			      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
			  "</head>" +
			  "<body>" +
			      "<h1>Rare Books Library</h1>"+
			   	  "<ul>"+
			   	       "<li><button onclick=\"login()\">Admin Priviledges</button></li>"+
					   "<li><button onclick=\"viewAllBooks()\">View All Books</button></li>"+
					   "<li><button onclick=\"Search()\">Search books</button></li>"+
					   "<li><button onclick=\"signup()\">Sign up</button></li>"+
				  "</ul>"+
		      "<script>"+
		          "function viewAllBooks() {"+
		     		  "location.assign(\"/allbooks\");"+
		     	  "}"+
				  "function Search() {"+
					  "location.assign(\"/search\");"+
		          "}"+
				  "function login() {"+
				      "location.assign(\"/login\");"+
				  "}"+ 
				  "function signup() {"+
				  	  "location.assign(\"/signup\");"+
				  "}"+
		      "</script>"+
		      "</body>"+
		      "</html>");
	    out.close();
	  }
}