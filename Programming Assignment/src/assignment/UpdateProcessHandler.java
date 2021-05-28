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
 * This class processes the form of the UpdateHandler class, updating the specified book based on
 * its ID 
 * 
 * @author James Gabbitus
 *
 */
public class UpdateProcessHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
		
		BookDao books = new BookDao();
	    System.out.println("In UpdateProcessHandler");
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
	    
	    int ID = Integer.parseInt(map.get("id"));
	  
		try {
			Book book = books.getBook(ID);	
			
	        String title;
	        String author;
	        int year;
	        int edition;
	        String publisher;
	        String isbn;
	        String cover;
	        String condition;
	        int price;
	        String notes;
	        
	        title = map.get("title");
			if (title.equals("")) 
				title = book.getTitle();
			
			author = map.get("author");
			if (author.equals("")) 
				author = book.getAuthor();
			
			String strYear = map.get("year");
		    if (strYear.equals(""))
		    	year = book.getYear();
		    else
		    	year = Integer.parseInt(strYear);
		    
		    String strEdition = map.get("edition");
		    if (strEdition.equals(""))
		    	edition = book.getEdition();
		    else
		    	edition = Integer.parseInt(strEdition);
		    
		    publisher = map.get("publisher");
		    if (publisher.equals(""))
		    	publisher = book.getPublisher();
			
		    isbn = map.get("isbn");
		    if (isbn.equals(""))
		    	isbn = book.getIsbn();
			
		    cover = map.get("cover");
		    if (cover.equals(""))
		    	cover = book.getCover();

		    condition = map.get("condition");
		    if (condition.equals(""))
		    	condition = book.getCondition();
			
		    String strPrice = map.get("price");
		    if (strPrice.equals(""))
		    	price = book.getPrice();
		    else
		    	price = Integer.parseInt(strPrice);
		    
		    notes = map.get("notes");
		    if (notes.equals(""))
		    	notes = book.getNotes();
	   	    
		 	Book editedBook = new Book(book.getID(), title, author, year, edition, publisher, isbn, cover, condition, price, notes);
			books.updateBook(editedBook);
			out.write(
				  "<html>" +
				  "<head> <title>Update Book Data</title> "+
				      "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
				  "</head>" +
				  "<body>" +
				      "<h1>Book Updated</h1>"+
				      "<button onclick=\"goBack()\">Return to Library Editor</button>\r\n" + 
				      "\r\n" + 
				      "<script>\r\n" + 
					      "function goBack() {\r\n" + 
					      "    window.history.go(-2);\r\n" + 
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