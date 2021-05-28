package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class allows the client to see the book stock and alter the stock amount of any chosen book
 * 
 * @author James Gabbitus
 *
 */
public class SaleHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    BookDao books = new BookDao();
	    
	    try {
	    	ArrayList<Book> allBooks = books.getAllBooks();
		    out.write(
			  "<html>" +
			  "<head> <title> USR </title> "+
			  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
			  "</head>" +
			  "<body>" +
				  "<h1>Update Stock Count</h1>" +
				  "<button onclick=\"goBack()\">Back</button>\r\n" + 
				  
			      "<form method = \"Post\" action = \"/salesaction\" name = \"myForm\"> " +
				      "<label> Enter Book ID </label>" + "<input name = \"bookid\" required><br>"+ 
					  "<label> Enter new amount </label>" + "<input name = \"sold\" input type = \"text\" required><br>"+ 
					  "<input type = \"submit\" value = \"Submit\">  "+
				  "</form>" + 
					  
					"<table class=\"table\">" +
					"<thead>"+
						 "<tr>"+
						 	"<th> ID </th>"+
						  	"<th> Title </th>"+
						  	"<th> Stock </th>"+
						 "</tr>");
					
					for (Book d : allBooks) {
						out.write(
							 "<tr>"+
								"<td>"+ d.getID() + "</td>"+
								"<td>"+ d.getTitle() + "</td>"+
								"<td>"+ books.bookAmount(d.getID()) + "</td>"+
							 "</tr>");
					}
					
					out.write(
						"</thead>"+
						"</table>"+		  
					    "<script>"+					  
							"function goBack() {\r\n" + 
							"  window.history.go(-1);\r\n" + 
							"}\r\n" +     
					    "</script>"+
					"</body>" +
			    "</html>");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    out.close();
	  }

}