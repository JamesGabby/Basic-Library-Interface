package assignment;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class acts as a search menu for three different ways to search for a book: by ID, by title and by the max price 
 * 
 * @author James Gabbitus
 *
 */
public class SearchHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
			
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    
	    out.write(
			  "<html>" +
			  "<head> <title>Search Books</title> "+
			      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
			  "</head>" +
			  "<body>" +
			      "<h1>Search Rare Books</h1>"+
			   	  "<ul>"+
					   "<li><button onclick=\"viewBookByTitle()\">Search by title</button></li>"+
					   "<li><button onclick=\"viewBookById()\">Search by ID</button></li>"+
					   "<li><button onclick=\"viewBookByPrice()\">Search by price-range</button></li>"+
				  "</ul>"+
				  "<button onclick=\"goBack()\">Menu</button>\r\n" + 
		      "<script>"+
			      "function goBack() {\r\n" + 
			          "location.assign(\"/menu\");"+ 
			      "}\r\n" + 
				  "function viewBookByTitle() {"+
					  "location.assign(\"/form\");"+
		          "}"+
				  "function viewBookById() {"+
				      "location.assign(\"/idform\");"+
				  "}"+
				  "function viewBookByPrice() {"+
				      "location.assign(\"/price\");"+
				  "}"+
		      "</script>"+
		      "</body>"+
		      "</html>");
	    out.close();
	  }
}