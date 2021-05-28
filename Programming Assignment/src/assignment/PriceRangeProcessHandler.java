package assignment;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class processes the form of the PriceRangeHandler class, retrieving the specified book based on
 * the max price and writing it out to the server
 * 
 * @author James Gabbitus
 *
 */
public class PriceRangeProcessHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
		
		System.out.println("In PriceRangeProcessHandler");
	    BufferedReader in = new BufferedReader( new InputStreamReader(he.getRequestBody()) );
	   
	    String line;
	    String request = "";
	    while( (line = in.readLine()) != null ) {
	        request = request + line;
	    }
	    System.out.println("request is " + request);
	    HashMap<String,String> map = Util.requestStringToMap(request);
	    System.out.println(map); 
	    int price = Integer.parseInt(map.get("price"));
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    BookDao books = new BookDao();
	    	    
	    try {
		    ArrayList<Book> allBooks = books.getBooksByPrice(price);
		    out.write(
		  		  "<html>" +
		  		  "<head> <title> Search by Price Data </title> "+
		  		  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
		  		  "</head>" +
		  		  "<body>" +
		  			  "<h1>Search for a book</h1>" +
		  			  "<button onclick=\"goBack()\">Back</button>\r\n" +
		  			      "<form method = \"Post\" action = \"/priceaction\">" +
		  				      "<label> Enter Max Price </label>" +
		  					  "<input name = \"price\">"+ 
		  					  "<input type = \"submit\" value = \"Submit\">  "+
		  				  "</form>" + 
		  			  "<script>"+
			  			"function goBack() {\r\n" + 
					     "  window.history.go(-2);\r\n" + 
					     "}\r\n" +       
		  			  "</script>"+
		  		  "</body>" +
		  		  "</html>");
		    
		    out.write(
				  "<html>" +
				  "<head> <title>Book Library</title> "+
				     "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
				  "</head>" +
				  "<body>" +
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
					  "<tbody>");
			
		      for (Book d : allBooks) {
			      out.write(
				      "  <tr>"+
				      "    <td>"+ d.getID() + "</td>" +
				      "    <td>"+ d.getTitle() + "</td>" +
				      "    <td>"+ d.getAuthor() + "</td>" +
				      "    <td>"+ d.getYear() + "</td>" +
				      "    <td>"+ d.getEdition() + "</td>" +
				      "    <td>"+ d.getPublisher() + "</td>" +
				      "    <td>"+ d.getIsbn() + "</td>" +
				      "    <td>"+ d.getCover() + "</td>" +
				      "    <td>"+ d.getCondition() + "</td>" +
				      "    <td>"+ d.getPrice() + "</td>" +
				      "    <td>"+ d.getNotes() + "</td>" +
				      "  </tr>" 
			      );
		      }
		      
		      out.write(
			      "</tbody>"+
			      "</table>"+
			      "</div>"+
			      "</body>"+
			      "</html>");
	      
	     } catch(SQLException se) {
	    	 System.out.println(se.getMessage());
	    }
	    out.close();
	  }
}