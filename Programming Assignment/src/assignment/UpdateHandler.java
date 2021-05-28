package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

/**
 * This class allows the user to update a book based on the books ID
 * @author James Gabbitus
 *
 */
public class UpdateHandler implements HttpHandler {
	
    public void handle(HttpExchange he) throws IOException {
   
	    System.out.println("UpdateHandler Called");
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody() ));
	    
	    // Get param from URL
	    Map <String,String> map = Util.requestStringToMap(he.getRequestURI().getQuery());
	
	    // print the params for debugging 
	    System.out.println(map);
	
	    //get ID number
	    int ID = Integer.parseInt(map.get("id"));
	
	    BookDao books = new BookDao();

	    try {
		     Book editBook = books.getBook(ID);
		     out.write(
		      "<html>" +
		      "<head> <title>Update Book</title> "+
		         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
		      "</head>" +
		      "<body>" +
		      "<h3>Update Book</h3>"+
		      "<table>" +
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
			  "    <th>Price</th>" +
			  "    <th>Notes</th>" +
			  "  </tr>" +
		      "</thead>" +
		      "<tbody>");
		
		        out.write(
		      "  <tr>"       +
		      "    <td>"+ editBook.getID() + "</td>" +
		      "    <td>"+ editBook.getTitle() + "</td>" +
		      "    <td>"+ editBook.getAuthor() + "</td>" +
		      "    <td>"+ editBook.getYear() + "</td>" +
		      "    <td>"+ editBook.getEdition() + "</td>" +
		      "    <td>"+ editBook.getPublisher() + "</td>" +
		      "    <td>"+ editBook.getIsbn() + "</td>" +
		      "    <td>"+ editBook.getCover() + "</td>" +
		      "    <td>"+ editBook.getCondition() + "</td>" +
		      "    <td>"+ editBook.getPrice() + "</td>" +
		      "    <td>"+ editBook.getNotes() + "</td>" +
		      "  </tr>");
		        
		        out.write(
  		      "  <tr>"  +
  		      "    <form method = \"Post\" action = \"/updateaction\">" +
	  		      "    <td>"+ "<label> </label>" +	  
	  		      "    <input name = \"id\" required>" + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "	   <input name = \"title\"required> " + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "	   <input name = \"author\"required> " + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "    <input name = \"year\"required>" + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "    <input name = \"edition\"required> " + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "    <input name = \"publisher\"required> " + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "    <input name = \"isbn\"required> " + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "    <input name = \"cover\"required> " + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "    <input name = \"condition\"required> " + "</td>" +
	  		      "    <td>"+ "<label> </label>" +
				  "    <input name = \"price\"required> " + "</td>" +
				  "    <td>"+ "<label> </label>" +
				  "    <input name = \"notes\"required> " + "</td>" +
				  "    <td>"+ "<label> </label>" +
				  "	   <td><input type = \"submit\" value = \"Submit\"> </td> "+
			  "  </form>" +
  		      "  </tr>");     
		      out.write(
			      "</tbody>" +
			      "</table>" +
			      "<button onclick=\"goBack()\">Return to Library Editor</button>\r\n" + 
			      "<script>\r\n" + 
				      "function goBack() {\r\n" + 
				      "  window.history.go(-1);\r\n" + 
				      "}\r\n" + 
			      "</script>"+ 
			      "</body>" +
			      "</html>");
		     
	     } catch(SQLException se) {
	    	 System.out.println(se.getMessage());
	     }
	    out.close();
	}
}
