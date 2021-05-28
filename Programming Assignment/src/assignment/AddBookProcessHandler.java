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
 * This class processes the form from the class AddBookHandler and adds a book to the database
 * @author James Gabbitus
 *
 */
public class AddBookProcessHandler implements HttpHandler {
	/**
	 * @param he Encapsulates a HTTP request received and a response to be generated in one exchange
	 */
	public void handle(HttpExchange he) throws IOException {
   
		BookDao books = new BookDao();
	    System.out.println("In AddBookProcessHandler");
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
		
		String s_id = map.get("id"); int id = Integer.parseInt(s_id);
   	    String title = map.get("title");
 	    String author = map.get("author");
 	    String s_year = map.get("year"); int year = Integer.parseInt(s_year);
	 	String s_edition = map.get("edition"); int edition = Integer.parseInt(s_edition);
	 	String publisher = map.get("publisher");
	 	String isbn = map.get("isbn");
	 	System.out.println(isbn);
	 	String cover = map.get("cover");
	 	String condition = map.get("condition");
	 	String s_price = map.get("price"); int price = Integer.parseInt(s_price);
	 	String notes = map.get("notes");
	 	
	 	Book newBook = new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);
	 	try {
			books.insertRecordIntoCollectionTable(newBook);
			out.write(
					  "<html>" +
						  "<head> <title>Add Book PH/title> "+
						      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						  "</head>" +
						  "<body>" +
						      "<h1>Book Added</h1>"+
						      "<button onclick=\"goBack()\">Return to Library Editor</button>\r\n" + 
						      "\r\n" + 
						      "<script>\r\n" + 
							      "function goBack() {\r\n" + 
							      "  window.history.go(-2);\r\n" + 
							      "}\r\n" + 
						      "</script>"+
					      "</body>"+
				      "</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	out.close();
	}
}