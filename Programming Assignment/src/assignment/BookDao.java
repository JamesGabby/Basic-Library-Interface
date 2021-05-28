package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 * This class contains all the methods for retrieving data from the database
 * @author James Gabbitus
 *
 */
public class BookDao {
	LoginProcessHandler code = 
			new LoginProcessHandler();
	
	public BookDao() {}
	
	/**
	 * This method gets the database connection
	 * 
	 * @return the database connection
	 */
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			String dbURL = "jdbc:sqlite:book.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return dbConnection;
	}
	
	/**
	 * This method retrieves all the data from each book from the table 'rare_books'
	 * 
	 * @return every book instance in the table 'rare_books'
	 * @throws SQLException
	 */
	public ArrayList<Book> getAllBooks() throws SQLException {
		System.out.println("Retrieving all books ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM rare_books;\n";
		ArrayList<Book> books = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {

				int book_id = result.getInt("Book_ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
				
				books.add(new Book(book_id, title, author, year, 
						edition, publisher, isbn, cover, 
						condition, price, notes));
			}
		} catch(Exception e) {
			System.out.println("get all books: "+e);
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return books;
	}
	
	/**
	 * This method retrieves all the data from each sale from the table 'sales_record'
	 * 
	 * @return every sale instance in the table 'sales_record'
	 * @throws SQLException
	 */
	public ArrayList<SalesRecord> getAllRecords() throws SQLException {
		System.out.println("Retrieving all records ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM sales_record;\n";
		ArrayList<SalesRecord> records = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {

				int customer_id = result.getInt("Customer_ID");
				int product_no = result.getInt("Product_No");
				String date = result.getString("Order_Date");
				String time = result.getString("Order_time");
				
				records.add(new SalesRecord(customer_id, product_no, date, time));
			}
		} catch(Exception e) {
			System.out.println("get all records: "+e);
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return records;
	}
	
	/**
	 * This method retrieves all the data from each book if they meet a specified price-range
	 * 
	 * @param priceIn The maximum price of the books that are wanted to be retrieved
	 * @return the books that are in the price-range
	 * @throws SQLException
	 */
	public ArrayList<Book> getBooksByPrice(int priceIn) throws SQLException {
		System.out.println("Retrieving all books ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM rare_books WHERE price < " + priceIn + ";\n";
		ArrayList<Book> books = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query); // Execute SQL query and record response to string
			while (result.next()) {

				int book_id = result.getInt("Book_ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
				
				books.add(new Book(book_id, title, author, year, 
						edition, publisher, isbn, cover, 
						condition, price, notes));
			}
		} catch(Exception e) {
			System.out.println("get all books: "+e);
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return books;
	}
	
	/**
	 * This method checks if the username entered from the client-end matches one in the table 'admins'
	 * 
	 * @return the username entered from the client-end if it exists
	 * @throws SQLException
	 */
	public String getUserProcess() throws SQLException {

		String temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String input = code.getUserInput();

		String query = "\nSELECT username \nFROM admins \nWHERE LOWER(username) = LOWER('" + input + "');";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			//System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);
			//System.out.println("Result: " + result.getString("Username") + "\n");

			while (result.next()) {
				String user = result.getString("Username");
				temp = user;
			}
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}

	/**
	 * This method retieves an instance of a book based on its ID
	 * 
	 * @param book_id the book ID to select the book wanted 
	 * @return the book based on the specified book ID
	 * @throws SQLException
	 */
	public Book getBook(int book_id) throws SQLException {

		Book temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "\nSELECT * \nFROM rare_books \nWHERE book_id = " + book_id + ";\n";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);

			while (result.next()) {

				int id = result.getInt("Book_ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
				
				temp = new Book(id, title, author, year, 
						edition, publisher, isbn, cover, 
						condition, price, notes);
			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}
	
	/**
	 * This method selects the password from the table 'admins' based on the input of the 'username' label
	 in 'LoginHandler'. The queried hashed password is then compared to the newly created hashed password
	 from the text input of the 'password' label
	 *
	 * @param max the password hash in the database
	 * @return the hashed password retrieved from the database
	 * @throws SQLException
	 */
	public String getHashToCompare(String max) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String input = code.getUserInput();

		String query = "SELECT password FROM admins WHERE username = '" + input.toString() + "';";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			
			while (result.next()) {
				String pass = result.getString("password");	
				max = pass;
			}
				
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		System.out.println("password to return: " + max);
		return max;
	}
	
	/**
	 * Retrieves the maximum admin ID from the database table 'admins' in order to
	 auto-increment 'admin_id' for the SignUpHandler class
	 *
	 * @param max the maximum admin ID; the largest ID number of admin
	 * @return the largest admin ID number
	 * @throws SQLException
	 */
	public int maxID(int max) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT admin_id, MAX(admin_id) FROM admins;";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			
			while (result.next()) {
				
				int ad_id = result.getInt("admin_id");
				System.out.println(ad_id);
				
				max = ad_id;
			}
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return max;
	}
	
	/**
	 * Retrieves the maximum customer no. from the database table 'sales_record' 
	 in order to auto-increment 'customer_no' for the SaleRecordHandler class
	 *
	 * @param max the maximum customer no; the largest number of the customer_no
	 * @return the largest customer_no
	 * @throws SQLException
	 */
	public int maxCusID(int max) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT customer_id, MAX(customer_id) FROM sales_record;";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			
			while (result.next()) {
				
				int ad_id = result.getInt("customer_id");
				System.out.println(ad_id);
				
				max = ad_id;
			}
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return max;
	}
	
	/**
	 * Retrieves the amount of stock for the specified book
	 * 
	 * @param max the book_id of the book 
	 * @return the stock count for that book
	 * @throws SQLException
	 */
	public int stockCount(int max) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT amount FROM book_count WHERE book_id = " + max + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			
			while (result.next()) {
				
				int ad_id = result.getInt("amount");
				System.out.println(ad_id);
				
				max = ad_id;
			}
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return max;
	}
	
	/**
	 * Retrieves the amount of stock for the specified book
	 * 
	 * @param book the book_id of the book 
	 * @return the stock count for that book
	 * @throws SQLException
	 */
	public int bookAmount(int book) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT amount FROM book_count WHERE book_id = " + book + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			result = statement.executeQuery(query);
			
			while (result.next()) {
				int ad_id = result.getInt("amount");
				book = ad_id;
			}
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return book;
	}
	
	/**
	 * Retrieves all the order dates from database table 'sales_record'
	 * 
	 * @param record the order_date of an ordered book
	 * @return the order_dates of every ordered book
	 * @throws SQLException
	 */
	public String dateRecord(String record) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT order_date FROM sales_record;";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			
			while (result.next()) {
				
				String ad_id = result.getString("order_date");
				System.out.println(ad_id);
				
				record = ad_id;
			}
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return record;
	}
	
	/**
	 * Retrieves all the order times from database table 'sales_record'
	 * 
	 * @param record the order_time of an ordered book
	 * @return the order_times of every ordered book
	 * @throws SQLException
	 */
	public String timeRecord(String record) throws SQLException {

		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT order_time FROM sales_record;";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			
			while (result.next()) {
				
				String ad_id = result.getString("order_time");
				System.out.println(ad_id);
				
				record = ad_id;
			}	
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return record;
	}
	
	/**
	 * Updates the amount of stock the book has
	 * 
	 * @param id the book_id to select the book to be updated
	 * @param count the stock of that book
	 * @return the updated stock amount of the book
	 * @throws SQLException
	 */
	public int updateRecord(int id, int count) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "UPDATE book_count SET amount = " + count + " WHERE book_id = " + id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			result = statement.executeQuery(query);
			
			while (result.next()) {
				
				int record = result.getInt("amount");
				int book_id = result.getInt("book_id");
				System.out.println(record);
				
				count = record;
				id = book_id;
			}
				
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return count;
	}
	
	/**
	 * Retrieves a book based on its title
	 * 
	 * @param title the title of the book to be retrieved
	 * @return the book of the specified title
	 * @throws SQLException
	 */
	public Book getBookByTitle(String title) throws SQLException {
		Book temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "\nSELECT * \nFROM rare_books \nWHERE LOWER(title) = LOWER('" + title + "');\n";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);

			while (result.next()) {

				int id = result.getInt("Book_ID");
				String b_title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				int price = result.getInt("Price");
				String notes = result.getString("Notes");
				
				temp = new Book(id, b_title, author, year, 
						edition, publisher, isbn, cover, 
						condition, price, notes);
			}
			
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}
	
	/**
	 * Deletes a book from the database based on a specified book_id
	 * 
	 * @param book_id the book_id of the book to be deleted
	 * @return true if result is 1, otherwise false
	 * @throws SQLException
	 */
	public Boolean deleteBook(int book_id) throws SQLException {
		System.out.println("Deleting book");
		Connection dbConnection = null;
		Statement statement = null;
		int result = 0;
		String query = "DELETE FROM rare_books WHERE book_id = " + book_id + "; \n";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			result = statement.executeUpdate(query);
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Takes in the parameters of the Book class and updates a book based off them
	 * 
	 * @param book The book to be updated
	 * @return true; update the book
	 * @throws SQLException
	 */
	public Boolean updateBook(Book book) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "UPDATE rare_books " + "SET book_id = '" + book.getID() + "'," + " title = '"
				+ book.getTitle() + "'," + " author = '" + book.getAuthor() + "'," + " year = '" 
				+ book.getYear() + "'," + " edition = '" + book.getEdition() + "', publisher = '"
				+ book.getPublisher() +"', ISBN = '" + book.getIsbn() + "'," + " cover = '" 
				+ book.getCover() + "', condition = '" + book.getCondition() + "'," 
				+ " price = '" + book.getPrice() + "', notes = '" + book.getNotes() 
				+ "' WHERE book_id = '" + book.getID() + "';";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL update
			statement.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}

	/**
	 * Takes in the parameters of the Book class and inserts a new book into the database table 'rare_books'
	 * 
	 * @param in The book to be created
	 * @return true; create the book
	 * @throws SQLException
	 */
	public boolean insertRecordIntoCollectionTable(Book in) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		
		String update = "INSERT INTO rare_books (Book_ID, Title, Author, Year, Edition, Publisher, ISBN,"
						+ "Cover, Condition, Price, Notes) \nVALUES ("+in.getID()+","+ "'"+in.getTitle()
						+"','"+in.getAuthor()+"',"+in.getYear()+","+in.getEdition()+",'"+in.getPublisher()+
						"','"+in.getIsbn()+"','"+in.getCover()+"','"+in.getCondition()+"',"+in.getPrice()+
						",'"+in.getNotes()+"');";
		boolean ok = false;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(update);
			// execute SQL query
			statement.executeUpdate(update);
			ok = true;	
		} catch (SQLException e) {
				System.out.println(e.getMessage());
				
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return ok;
	}
	
	/**
	 * Takes in the parameters of the Admin class and creates a new instance of an admin
	 * 
	 * @param in The admin to be created
	 * @return true; create the admin
	 * @throws SQLException
	 */
	public boolean insertRecordIntoAdmins(Admin in) throws SQLException {
		
		Connection dbConnection = null;
		Statement statement = null;
		
		String update = "INSERT INTO admins (Admin_ID, Username, Password) \nVALUES ("+in.getAdminID()+"," + "'"+in.getUsername()
						+ "','" + in.getPassword() + "');";
		boolean ok = false;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(update);
			// execute SQL query
			statement.executeUpdate(update);
			ok = true;
				
		} catch (SQLException e) {
				System.out.println(e.getMessage());
		} finally {
			
			if (statement != null) {
					statement.close();
			}
			if (dbConnection != null) {
					dbConnection.close();
			}
		}
		return ok;
	}

	/**
	 * Inserts a new record of sale into the database
	 * 
	 * @param dateOfPurchase The date the book was purchased
	 * @param timeOfPurchase The time the book was purchased
	 * @param productOrdered The books product number
	 * @return true; insert the sale instance
	 * @throws SQLException
	 */
	public boolean insertIntoSalesRecord(String dateOfPurchase, String timeOfPurchase, int productOrdered) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		
		String update = "INSERT INTO sales_record (customer_id, product_no, order_date, order_time) \nVALUES "
				+ "("+(maxCusID(0)+1)+"," + productOrdered + ",'" + dateOfPurchase + "','" + timeOfPurchase + "');";
		boolean ok = false;
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(update);
			// execute SQL query
			statement.executeUpdate(update);
			ok = true;
				
		} catch (SQLException e) {
				System.out.println(e.getMessage());
		} finally {
			
			if (statement != null) {
					statement.close();
			}
			if (dbConnection != null) {
					dbConnection.close();
			}
		}
		return ok;
	}
	
}