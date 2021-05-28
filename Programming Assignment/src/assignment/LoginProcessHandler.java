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
 * This class processes the LoginHandler form, comparing the username and hashed password 
 * to the data in the database for validation; if valid, display admin priviledges
 * 
 * @author James Gabbitus
 *
 */
public class LoginProcessHandler implements HttpHandler {
	private static String userInput;
	
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
	    
   	    String user = map.get("username");
   	    String pass = map.get("password");
   	    
   	    try {
   	    	// Covert the entered text password from the client to a new hashed password
			String hashedPassword = PasswordHasher.hashPassword(pass.toString());
			System.out.println("Password input converted to hash: " + hashedPassword);
			setUserInput(user.toString());
	   	    
	   	    System.out.println("\nComparing: " + hashedPassword + "\nWith: " + books.getHashToCompare(""));
		   	if (hashedPassword.equals(books.getHashToCompare(""))) {
		   		System.out.println("Match\n");
			} else {
				System.out.println("No match\n");
			}

		try {
			ArrayList<Book> allBooks = books.getAllBooks();
			/**
			 * Check whether the client-entered username exists in the database and check if the created password hash 
			 * matches the password hash of the username; if both conditions are met then grant access
			 */
			if (user.equalsIgnoreCase(books.getUserProcess()) && hashedPassword.equals(books.getHashToCompare(""))) {
				System.out.println("Login successful");
				out.write(
					  "<html>" +
						  "<head> <title> Logged in successfully! </title> "+
						  	  "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
						  "</head>" +
						  "<body>" +
						  	  "<button onclick=\"toMenu()\">Menu</button>"+
						  	  "<button onclick=\"logOut()\">Log Out</button>"+
						      "<h4>Welcome "+ map.get("username") +"!</h4>" +
							  "<h2>Library Editor</h2>" +
							  "<button onclick=\"addBook()\">New Book</button>"+
							  "<button onclick=\"Update()\">Stock count</button>"+
							  "<button onclick=\"viewSalesRecord()\">View sales record</button>"+
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
							  "    <th></th>" +
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
					      "    <td>"+ d.getIsbn() + "<br><a href=\"/validateisbn?id=" + d.getID() + "\"> Check Validity</a></td>" +
					      "    <td>"+ d.getCover() + "</td>" +
					      "    <td>"+ d.getCondition() + "</td>" +
					      "    <td>"+ d.getPrice() + "</td>" +
					      "    <td>"+ d.getNotes() + "</td>" +
					      "    <td><a href=\"/updatebook?id=" + d.getID() + "\"> Edit </a><br>"
					      +    "<a href=\"/deletebook?id=" + d.getID() + "\"> Delete </a><br>" +
					      "  </tr>" 
				      );
			      }
			      
			      out.write(
				      "</tbody>"+
				      "</table>"+
				      "</div>"+
				      "<script>"+
					      "function toMenu() {"+
					         "location.assign(\"/menu\");"+
					      "}"+
					      "function addBook() {"+
				             "location.assign(\"/addbook\");"+
				          "}"+
				          "function editBook() {"+
				         	 "location.assign(\"/editbook\");"+
			              "}"+
				          "function deleteBook() {"+
				         	 "location.assign(\"/deletebook\");"+
			              "}"+
			              "function logOut() {"+
				         	 "location.assign(\"/menu\");"+
			              "}"+
			              "function Update() {"+
					      	 "location.assign(\"/sales\");"+
						  "}"+    
					 	  "function viewSalesRecord() {"+
					 	  	 "location.assign(\"/record\");"+
						  "}"+
				      "</script>");

			      } else {	  
			    	  System.out.println("Invalid login");
			    	  out.write(  
			    			"<head>"+
			    			    "<title> Search by Title </title> "+
			    				"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +	  
			    			"</head>" +
			    			"<body>" +
							    "<h1>Login</h1>" +
							    "<button onclick=\"toMenu()\">Menu</button>"+
						    "<form method = \"Post\" action = \"/loginaction\">" +
							     "<label> Username </label>" + "<input name = \"username\" required><br>"+ 
								 "<label> Password </label>" + "<input name = \"password\" input type = \"password\" required><br>"+ 
								 "<input type = \"submit\" value = \"Submit\">  <p>Invalid login</p>"+
						    "</form>" + 
							  "<script>"+
								  "function toMenu() {"+
							          "location.assign(\"/menu\");"+
							          "return false;"+
								  "}"+
							  "</script>");
			      }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
   	 } catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
	}
	
	/**
	 * Get the username value of the client in the getHashToCompare() method which 
	 * checks if that username exists and queries the hashed password through it
	 * 
	 * @return The username input of the client
	 */
	public String getUserInput() {
		return userInput;
	}
	
	/**
	 * Setter used to set the username input of the client 
	 * 
	 * @param userInput Sets the username input of the client
	 */
	public void setUserInput(String userInput) {
		LoginProcessHandler.userInput = userInput;
	}
}