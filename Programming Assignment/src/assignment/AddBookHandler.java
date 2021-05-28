package assignment;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;
/**
 * This class handles a form for the client to enter the details of a new book
 * @author James Gabbitus
 *
 */
public class AddBookHandler implements HttpHandler {

	/**
	 * @param he Encapsulates a HTTP request received and a response to be generated in one exchange
	 */
	public void handle(HttpExchange he) throws IOException {

		he.sendResponseHeaders(200,0);
		BufferedWriter out = new BufferedWriter( new OutputStreamWriter(he.getResponseBody()) );

		out.write(
				"<html>" +
					"<head> <title> Add Book </title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
					"</head>" +
					"<body>" +
						"<button onclick=\"toMenu()\">Menu</button>"+
						"<h1>Add a book</h1>" +
						"<button onclick=\"goBack()\">Back</button>\r\n" + 
						"<form method = \"Post\" action = \"/addbookaction\">" +
							"<label> Enter ID </label>" +
							"<input name = \"id\"> <br> "+ 
							"<label> Enter title </label>" +
							"<input name = \"title\"> <br> "+ 
							"<label> Enter author </label>" +
							"<input name = \"author\"> <br> "+ 
							"<label> Enter year </label>" +
							"<input name = \"year\"> <br> "+ 
							"<label> Enter edition </label>" +
							"<input name = \"edition\"> <br> "+ 
							"<label> Enter publisher </label>" +
							"<input name = \"publisher\"> <br> "+ 
							"<label> Enter ISBN </label>" +
							"<input name = \"isbn\"> <br> "+ 
							"<label> Enter cover </label>" +
							"<input name = \"cover\"> <br> "+ 
							"<label> Enter condition </label>" +
							"<input name = \"condition\"> <br> "+ 
							"<label> Enter price </label>" +
							"<input name = \"price\"> <br> "+  
							"<label> Enter notes </label>" +
							"<input name = \"notes\"> <br> "+ 
							"<input type = \"submit\" value = \"Submit\">  "+
						"</form>" + 
						"<script>"+
							"function toMenu() {"+
							"location.assign(\"/menu\");"+
							"}"+
							"function goBack() {\r\n" + 
							"    window.history.go(-1);\r\n" + 
							"}\r\n" + 
						"</script>"+
						 "</body>" +
				"</html>");
		out.close();
	}
}