package assignment;

import java.io.OutputStream;
import java.io.*;
import java.io.OutputStreamWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class processes the form of the SaleHandler class, altering the stock based on
 * the book ID and the new stock amount, writing the new amount out to the server
 * 
 * @author James Gabbitus
 *
 */
public class SaleProcessHandler implements HttpHandler {
	
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
   	    int book_id = Integer.parseInt(map.get("bookid"));
   	    int count = Integer.parseInt(map.get("sold"));
	   	try {
			books.updateRecord(book_id, count);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block 
			e1.printStackTrace();
		}
   	    Book book;
   	    try {
	   	    out.write(
	   	    		
				  "<html>" +
				  "<head> <title> Stock Count </title> "+
				  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
				  "</head>" +
				  "<body>" +
				  	  "<button onclick=\"toMenu()\">Menu</button>"+
				      
				      
					  "<h2>Sales Record</h2>" +
					  "<h2>Book #" + book_id + "'s sale record has been updated with a copy of " + count + "</h2>"+
					  
				      "<table class=\"table\">" +
					  "<thead>"+
						 "<tr>"+
						  	"<th> Title </th>"+
						  	"<th> Stock </th>"+
					  	 "</tr>");
	   	    
	   	    book = books.getBook(book_id);
	   	    out.write(
						"<tr>"+
							"<td>"+ book.getTitle() + "</td>"+
							"<td>"+ count + "</td>"+
						"</tr>"+
					"</thead>"+
					"</table>"+
					"<button onclick=\"goBack()\">Back</button>\r\n" + 
					  
			      "<script>"+
	
				      "function toMenu() {"+
				          "location.assign(\"/menu\");"+
				      "}"+
				     
					 "function goBack() {\r\n" + 
					 "  window.history.go(-1);\r\n" + 
					 "}\r\n" + 
			      "</script>"+
			      "</body>"+
			      "</html>");
		
			out.close();
		
   	 } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}