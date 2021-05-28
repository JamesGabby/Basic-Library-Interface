package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * This class retrieves the books sales records and writes them out to the client
 * 
 * @author James Gabbitus
 *
 */
public class SalesRecordHandler implements HttpHandler {
	
	public void handle(HttpExchange he) throws IOException {
	    
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );
	    BookDao books = new BookDao();
	    
	    try {
	    	ArrayList<SalesRecord> allRecords = books.getAllRecords();
		    out.write(
			  "<html>" +
			  "<head> <title> Sales Record </title> "+
			  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
			  "</head>" +
			  "<body>" +
				  "<h1>Sales Record</h1>" +
				  "<button onclick=\"goBack()\">Back</button>\r\n" +
					"<table class=\"table\">" +
					"<thead>"+
						 "<tr>"+
						 	"<th> Customer ID </th>"+
						 	"<th> Product No. </th>"+
						  	"<th> Order Date </th>"+
						  	"<th> Order Time </th>"+
						 "</tr>");
					
		    for (SalesRecord d : allRecords) {
				out.write(
					"<tr>"+
						"<td>"+ d.getCustomerID() + "</td>"+
						"<td>"+ d.getProductNo() + "</td>"+
						"<td>"+ d.getOrderDate() + "</td>"+
						"<td>"+ d.getOrderTime() + "</td>"+
					"</tr>");
			}
					
					out.write(
					"</thead>"+
					"</table>"+	  
				    "<script>"+
						"function goBack() {\r\n" + 
						"  window.history.go(-1);\r\n" + 
						"}\r\n" +    
				    "</script>"+
				    "</body>" +
				    "</html>");	
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    out.close();
	  }

}