package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

/**
 * This class handles the purchasing of a book where one stock of that book will be removed from the database
 * and its order date and time as well as product number will be recorded and stored in another table
 * 
 * @author James Gabbitus
 *
 */
public class PurchaseHandler implements HttpHandler {
	
    public void handle(HttpExchange he) throws IOException {
	    System.out.println("PurchaseHandler Called");
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody() ));
	    
	    // Get param from URL
	    Map <String,String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());
	
	    // print the params for debugging 
	    System.out.println(parms);
	
	    //get ID number
	    int ID = Integer.parseInt(parms.get("id"));
	    
	    BookDao books = new BookDao();
	    LocalDate date = LocalDate.now(); 
	    LocalTime time = LocalTime.now(); 
	    String dateOfPurchase = date.toString();
	    String timeOfPurchase = time.toString();
	    System.out.println(dateOfPurchase); 
	    System.out.println(timeOfPurchase);

	    try {
		     Book book = books.getBook(ID);
		     books.insertIntoSalesRecord(dateOfPurchase, timeOfPurchase, ID);
		     out.write(
		      "<html>" +
			      "<head> <title>Buy Book</title> "+
			         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
			      "</head>" +
			      "<body>" +
				      "<h2> " + book.getTitle() + " has been ordered!</h2>"+
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
		
		        out.write(
		      "  <tr>"       +
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
			      "<p>" + dateOfPurchase + "/" + timeOfPurchase + "</p>"+
			      "</body>" +
		      "</html>");
		      
		      int updateStock = books.stockCount(ID)-1;
			  books.updateRecord(ID, updateStock);
			    
	     } catch(SQLException se) {
	    	 System.out.println(se.getMessage());
	     }
	    out.close();
	}
}
