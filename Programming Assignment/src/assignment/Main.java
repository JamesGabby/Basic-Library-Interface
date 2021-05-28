package assignment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

/**
 * This class is where the main program is run for two primary methods. In the main method 
 * there is a method to call on and a class to call on. The method (connectToServer()) connects
 * to the server and allows for the use of the web application and the class (BookLogin.main(args))
 * calls on BookLogin's main method where the book library's menu can be accessed through the console
 * 
 * @author James Gabbitus, 16006751
 *
 */
class Main {
	
	static final private int PORT = 8076;
	
	public static void main(String[] args) throws SQLException, IOException {
		connectToServer();
		//BookLogin.main(args);
	}
	
	/**
	 * Connects to the server and creates all the URIs with the addresses for the web application
	 * 
	 * @throws IOException
	 */
	public static void connectToServer() throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
		server.createContext("/login", new LoginHandler());
		server.createContext("/loginaction", new LoginProcessHandler());
		server.createContext("/allbooks", new RetrieveAllBooksHandler());
		server.createContext("/addbookaction", new AddBookProcessHandler());
		server.createContext("/addbook", new AddBookHandler());
		server.createContext("/menu", new MenuHandler());
	    server.createContext("/form", new FormHandler());
	    server.createContext("/formaction", new FormProcessHandler());
	    server.createContext("/deletebook", new DeleteHandler());
	    server.createContext("/updatebook", new UpdateHandler());
	    server.createContext("/updateaction", new UpdateProcessHandler());
	    server.createContext("/idform", new IDFormHandler());
	    server.createContext("/idformaction", new IDFormProcessHandler());
	    server.createContext("/validateisbn", new ValidateISBNHandler());
	    server.createContext("/signup", new SignUpHandler());
	    server.createContext("/signupaction", new SignUpProcessHandler());
	    server.createContext("/price", new PriceRangeHandler());
	    server.createContext("/priceaction", new PriceRangeProcessHandler());
	    server.createContext("/search", new SearchHandler());
	    server.createContext("/sales", new SaleHandler());
	    server.createContext("/salesaction", new SaleProcessHandler());
	    server.createContext("/buybook", new PurchaseHandler());
	    server.createContext("/record", new SalesRecordHandler());
	    server.setExecutor(null);
	    server.start();
	    System.out.println("The server is listening on port " + PORT);
	}
	
	/**
	 * Supplies the console menu for admin access only
	 * 
	 * @throws SQLException
	 */
	public static void adminMenu() throws SQLException {
		Scanner in = new Scanner(System.in);
		BookDao books = new BookDao();
		String selection;
			do {
			      System.out.println("--------------------");
			      System.out.println("Book Inventory (Admin)");
			      System.out.println("Choose from these options");
			      System.out.println("--------------------");
			      System.out.println("[1] List all Books");
			      System.out.println("[2] Search Book by ID");
			      System.out.println("[3] Add a new Book");
			      System.out.println("[4] Update a Book by ID");
			      System.out.println("[5] Delete a Book by ID");
			      System.out.println("[6] Exit");
			      System.out.println();

			      selection = in.nextLine();

			      switch (selection) {
			      case "1":
			          ArrayList<Book> allBooks = books.getAllBooks();
			          for (int i = 0; i < allBooks.size(); i++) {
			        	  System.out.println(allBooks.get(i));
			          }
			          System.out.println();
			          break;

			      case "2":
			          System.out.println("\nEnter Book ID to get details  ");
			          int book_id = Integer.parseInt(in.nextLine());
			          System.out.println(books.getBook(book_id));
			          System.out.println();
			          break;

			      case "3":
			          System.out.println("Add a new Book");
			          Book book = createBook();
			          books.insertRecordIntoCollectionTable(book);
			          System.out.println();
			          break;

			      case "4":
			    	  System.out.println("Update a book");
				      System.out.println("\nEnter book ID to update ");
				      int uID = Integer.parseInt(in.nextLine());
				      System.out.println(books.getBook(uID));
				      Book updatedBook = updateHelper(books.getBook(uID));
				      books.updateBook(updatedBook);
				      break;

			      case "5":
			          System.out.println("Delete book");
			          System.out.println("Enter ID of book to delete");
			          int dID = Integer.parseInt(in.nextLine());
			          books.deleteBook(dID);
			          break;

			      case "6":
			          System.out.println("Menu exited.");
			          break;
			          default:
			          System.out.println("Invalid Selection");
			      }
		    } while (!selection.equals("6"));
	}
	
	/**
	 * Supplies the console menu for users
	 * 
	 * @throws SQLException
	 */
	public static void userMenu() throws SQLException {
		Scanner in = new Scanner(System.in);
		BookDao books = new BookDao();
		String selection;
			do {
			      System.out.println("--------------------");
			      System.out.println("Book Inventory (User)");
			      System.out.println("Choose from these options");
			      System.out.println("--------------------");
			      System.out.println("[1] List all Books");
			      System.out.println("[2] Search Book by ID");
			      System.out.println("[3] Exit");
			      System.out.println();

			      selection = in.nextLine();

			      switch (selection) {
			      case "1":
			          ArrayList<Book> allBooks = books.getAllBooks();
			          for (int i = 0; i < allBooks.size(); i++) {
			        	  System.out.println(allBooks.get(i));
			          }
			        System.out.println();
			        break;

			      case "2":
			          System.out.println("\nEnter Book ID to get details  ");
			          int book_id = Integer.parseInt(in.nextLine());
			          System.out.println(books.getBook(book_id));
			          System.out.println();
			          break;

			      case "3":
			          System.out.println("Menu exited.");
			          break;
			          default:
			          System.out.println("Invalid Selection");
			      }
			} while (!selection.equals("3"));
	}
	
	/**
	 * Helper method for the menu to create a book
	 * 
	 * @return the book containing the parameter values of the user input
	 */
	private static Book createBook() {
		int book_id;
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

	    Scanner in = new Scanner(System.in);
	    System.out.println("Please enter ID");
	    book_id = Integer.parseInt(in.nextLine());
	    
	    System.out.println("Please enter the title");
	    title = in.nextLine();

	    System.out.println("Please enter the author");
	    author = in.nextLine();

	    System.out.println("Please enter the year");
	    year = Integer.parseInt(in.nextLine());
	    
	    System.out.println("Please enter the edition");
	    edition = Integer.parseInt(in.nextLine());
	    
	    System.out.println("Please enter the publisher");
	    publisher = in.nextLine();
	    
	    System.out.println("Please enter the ISBN");
	    isbn = in.nextLine();
	    
	    System.out.println("Please enter the cover");
	    cover = in.nextLine();
	    
	    System.out.println("Please enter the condition");
	    condition = in.nextLine();
	    
	    System.out.println("Please enter the price");
	    price = Integer.parseInt(in.nextLine());
	    
	    System.out.println("Please enter the notes");
	    notes = in.nextLine();

	    return new Book(book_id, title, author, year, 
				edition, publisher, isbn, cover, 
				condition, price, notes);
	  }
	
	 /**
	  * Helper method for the menu to update a book
	  * @param book the book to be updated
	  * @return the book containing the parameter values of the user input
	  */
	 private static Book updateHelper(Book book) {
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

		    Scanner in = new Scanner(System.in);
		    System.out.println("Updating book with ID: " + book.getID());
		    System.out.println("\nPlease enter title");
		    title = in.nextLine();
		    if (title.equals(""))
		    	title = book.getTitle();

		    System.out.println("Please enter new author");
		    author = in.nextLine();
		    if (author.equals(""))
		    	author = book.getAuthor();

		    System.out.println("Please enter year");
		    String strYear = in.nextLine();
		    if (strYear.equals(""))
		    	year = book.getYear();
		    else
		    	year = Integer.parseInt(strYear);

		    System.out.println("Please enter edition");
		    String strEdition = in.nextLine();
		    if (strEdition.equals(""))
		    	edition = book.getEdition();
		    else
		    	edition = Integer.parseInt(strEdition);
		    
		    System.out.println("Please enter new publisher");
		    publisher = in.nextLine();
		    if (publisher.equals(""))
		    	publisher = book.getPublisher();
		    
		    System.out.println("Please enter new ISBN");
		    isbn = in.nextLine();
		    if (isbn.equals(""))
		    	isbn = book.getIsbn();
		    
		    System.out.println("Please enter new cover");
		    cover = in.nextLine();
		    if (cover.equals(""))
		    	cover = book.getCover();
		    
		    System.out.println("Please enter new condition");
		    condition = in.nextLine();
		    if (condition.equals(""))
		    	condition = book.getCondition();
		    
		    System.out.println("Please enter price");
		    String strPrice = in.nextLine();
		    if (strPrice.equals(""))
		    	price = book.getPrice();
		    else
		    	price = Integer.parseInt(strPrice);
		    
		    System.out.println("Please enter new notes");
		    notes = in.nextLine();
		    if (notes.equals(""))
		    	notes = book.getNotes();

		    return new Book(book.getID(), title, author, year, edition, publisher, isbn, cover, condition, price, notes);
		  }
}
//James Gabbitus, 16006751