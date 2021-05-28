package assignment;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class retrieves all the books from the database and writes them out to the client
 * 
 * @author James Gabbitus
 *
 */
public class RetrieveAllBooksHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    BookDao books = new BookDao();
	    try {
		    ArrayList<Book> allBooks = books.getAllBooks();
		    out.write(
				  "<html>" +
				  "<head> <title>All Books</title> "+
				  	  "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />" +
				  "</head>" +
				  "<body>" +
					  "<h1>Collectors Books</h1>"+
					  "<button onclick=\"goHome()\">Home</button>"+
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
				      "    <td><a href=\"/buybook?id=" + d.getID() + "\"> Buy </a></td>" +
				      "  </tr>" 
			      );
		      }
		      
		      out.write(
			      "</tbody>"+
			      "</table>"+
			      "</div>"+
			      "<script>"+
				      "function goHome() {"+
				          "location.assign(\"/menu\");"+
				      "}"+
			     " </script>"+
			      "</body>"+
			      "</html>");
	      
	     } catch(SQLException se) {
	    	 System.out.println(se.getMessage());
	    }
	    out.close();
	  }
}