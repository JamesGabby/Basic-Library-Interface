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
 * This class checks whether an ISBN is valid
 * @author James Gabbitus
 *
 */
public class ValidateISBNHandler implements HttpHandler {
	
    public void handle(HttpExchange he) throws IOException {
   
	    System.out.println("ISBNHandler Called");
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody() ));
	    
	    // Get param from URL
	    Map <String,String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());
	
	    // print the params for debugging 
	    System.out.println(parms);
	
	    // get ID number
	    int ID = Integer.parseInt(parms.get("id"));
	    BookDao books = new BookDao();
	    try { 
		     Book book = books.getBook(ID);
		     // get isbn 
		     String isbn = book.getIsbn();
		     
		     // remove spaces and dashes
		     isbn = isbn.replaceAll("( |-)", "");
		         
		     // check depending on length
		     boolean isValid = false;
		     if (isbn.length() == 10) {
		    	 isValid = checkIsbn10(isbn);
		     } else if (isbn.length() == 13) {
		    	 isValid = checkIsbn13(isbn);
		     } else {
		    	 isValid = false;
		     }
		     
		     out.write(
			      "<html>" +
			      "<head> <title>Validate ISBN</title> "+
			         "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
			      "</head>" +
			      "<body>" +
			      "<h3>Validate ISBN</h3>"+
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
		        
		      if (isValid) {
		    	  System.out.println(isbn + " is valid\n");
		    	  out.write(
				      "</tbody>" +
				      "</table>" +
				      "<h3>ISBN '" + isbn +"' is valid</h3>");
		      } else {
		    	  System.out.println(isbn + " is NOT valid\n");
		    	  out.write(
		    		  "</tbody>" +
		    		  "</table>" +
		    		  "<h3>ISBN '" + isbn +"' is NOT valid</h3>");
		     }
		       
		      out.write(
			      "<button onclick=\"goBack()\">Back</button>\r\n" + 
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
    
	/**
	 * Check a 10-digit ISBN
	 * 
	 * @param isbn the ISBN digits String, with the last digit possible 'X'
	 * @return whether the ISBN is valid in the 10-digit scheme
	 */
	private boolean checkIsbn10(String isbn) {
		// sum the digits times 10, 9, ..., 1
		int sum = 0;
		String dStr;
		for (int d = 0; d < 10; d++) {
			dStr = isbn.substring(d, d+1);
			if (d < 9 || dStr != "X") {
				sum += Integer.parseInt(dStr) * (10-d);
			} else {
				sum += 10;
			}
		}
		return (sum % 11 == 0);
	}
	
	/**
	 * Check a 13-digit ISBN
	 * 
	 * @param isbn the ISBN digits String, where all values are 0-9
	 * @return whether the ISBN is valid in the 13-digit scheme
	 */
	private boolean checkIsbn13(String isbn) {
		// sum the digits times 1, 2, 1, 3, ..., 1
		int sum = 0;
		int dVal;
		for (int d = 0; d < 13; d++) {
			dVal = Integer.parseInt(isbn.substring(d, d+1));
			if (d % 2 == 0) {
				sum += dVal * 1;
			} else {
				sum += dVal * 3;
			}
		}
		return (sum % 10 == 0);
	}
	
}
