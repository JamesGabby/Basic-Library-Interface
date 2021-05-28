package assignment;

import java.io.OutputStream;
import java.io.*;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.HashMap;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;
/**
 * This class processes the form of the FormHandler class, retrieving the specified book based on
 * its title and writing it out to the server
 * 
 * @author James Gabbitus
 *
 */
public class FormProcessHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
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
	    Book book;
	    
		try {
			out.write(
			    "<html>" +
			    "<head> <title>Form Data</title> "+
			    	"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
			    "</head>" +
			    "<body>" +
			    "<h1>Search for a book</h1>" +
			    "<button onclick=\"goBack()\">Back</button>\r\n" +
			    "<form method = \"Post\" action = \"/formaction\">" +
				    "<label> Enter book title </label>" +
				    "<input name = \"username\"> <br\\> "+ 
				    "<input type = \"submit\" value = \"Submit\">  "+
			  "</form>" + 
			    "<table class=\"table\">" +
			    "<thead>" +
				  "  <tr>" +
				  "    <th>ID</th>" +
				  "    <th>Title</th>" +
				  "    <th>Author</th>" +
				  "    <th>Year</th>" +
				  "    <th>Edition</th>" +
				  "    <th>Publisher</th>" +
				  "    <th>ISBN</th>" +
				  "    <th>Cover</th>" +
				  "    <th>Condition</th>" +
				  "    <th>Price(£)</th>" +
				  "    <th>Notes</th>" +
				  "  </tr>" +
				  "</thead>" +
				  "<tbody>"
			);
			
			 //iterate the HashMap, get the input value, retrieve and write out the book of that value
			 for (String key: map.keySet()) {
				 String result = map.get(key);
				 book = books.getBookByTitle(result);
					      
			     out.write(
			      "  <tr>"+
			      "    <td>"+ book.getID() + "</td>" +
			      "    <td>"+ book.getTitle() + "</td>" +
			      "    <td>"+ book.getAuthor() + "</td>" +
			      "    <td>"+ book.getYear() + "</td>" +
			      "    <td>"+ book.getEdition() + "</td>" +
			      "    <td>"+ book.getPublisher() + "</td>" +
			      "    <td>"+ book.getIsbn() + "</td>" +
			      "    <td>"+ book.getCover() + "</td>" +
			      "    <td>"+ book.getCondition() + "</td>" +
			      "    <td>"+ book.getPrice() + "</td>" +
			      "    <td>"+ book.getNotes() + "</td>" +
			      "  </tr>"+
			      "</script>"+
			      "</tbody>"+
			      "</table>"+
			      "</div>"+
			      "</body>"+
			      "</html>");
		  	 }
			  
			  out.write(
				    "</table>" +
				    "<script>"+
					    "function goBack() {\r\n" + 
					     "  window.history.go(-1);\r\n" + 
					     "}\r\n" + 
				    "</script>"+
				    "</body>" +
				    "</html>");
			  
			  out.close();
		  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
	}
}