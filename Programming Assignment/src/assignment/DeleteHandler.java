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
 * This class retrieves the specified book and deletes it from the database
 * @author James Gabbitus
 *
 */
public class DeleteHandler implements HttpHandler {
	
    public void handle(HttpExchange he) throws IOException {
	    System.out.println("DeleteHandler Called");
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody() ));
	    
	    Map <String,String> parms = Util.requestStringToMap(he.getRequestURI().getQuery()); 
	    System.out.println(parms);
	    int ID = Integer.parseInt(parms.get("id"));
	    BookDao books = new BookDao();
	    try {
	    	//get the book to be deleted
		     Book deletedBook = books.getBook(ID);
		     out.write(
		      "<html>" +
			      "<head> <title>Delete Book</title> "+
			         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
			      "</head>" +
			      "<body>" +
				      "<h1> Book Deleted</h1>"+
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
					  "    <th>Price</th>" +
					  "    <th>Notes</th>" +
					  "  </tr>" +
				      "</thead>" +
				      "<tbody>");
			
		      //show the deleted book
		        out.write(
				      "  <tr>"       +
				      "    <td>"+ deletedBook.getID() + "</td>" +
				      "    <td>"+ deletedBook.getTitle() + "</td>" +
				      "    <td>"+ deletedBook.getAuthor() + "</td>" +
				      "    <td>"+ deletedBook.getYear() + "</td>" +
				      "    <td>"+ deletedBook.getEdition() + "</td>" +
				      "    <td>"+ deletedBook.getPublisher() + "</td>" +
				      "    <td>"+ deletedBook.getIsbn() + "</td>" +
				      "    <td>"+ deletedBook.getCover() + "</td>" +
				      "    <td>"+ deletedBook.getCondition() + "</td>" +
				      "    <td>"+ deletedBook.getPrice() + "</td>" +
				      "    <td>"+ deletedBook.getNotes() + "</td>" +
				      "  </tr>" 
				        );
		      
		      out.write(
				      "</tbody>" +
				      "</table>" +
			      "<button onclick=\"goBack()\">Back</button>\r\n" + 
			      "<script>\r\n" + 
				      "function goBack() {\r\n" + 
				      "  window.history.go(-1);\r\n" + 
				      "}\r\n" + 
			      "</script>"+
			      "</body>" +
		      "</html>");
		      //delete the book
		      books.deleteBook(ID);
		      
	     } catch(SQLException se) {
	    	 System.out.println(se.getMessage());
	     }
	    out.close();
	}
}
