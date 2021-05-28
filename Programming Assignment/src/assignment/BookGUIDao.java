package assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * This class handles the database connection and login proces for the GUI of the BookLogin class
 * @author James Gabbitus
 *
 */
public class BookGUIDao {
	
	BookLogin code = new BookLogin();
	
	BookGUIDao() {}
	
	/**
	 * Get the database connection
	 * 
	 * @return the database connection
	 */
	static Connection getDBConnection() {
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
	 * Retrieve the username from the database based off the user input
	 * 
	 * @return the username based off the user input, if it doesn't exist return null
	 * @throws SQLException
	 */
	public String getUser() throws SQLException {

		String temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String input = code.getUserInput();

		String query = "\nSELECT username \nFROM admins_gui \nWHERE username = '" + input + "';";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			result = statement.executeQuery(query);

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
	 * Retrieve the password from the database based on the user input
	 * 
	 * @return the password based on the user input, if it doesn't exist return null
	 * @throws SQLException
	 */
	public String getPass() throws SQLException {

		String temp = null;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String input = code.getPassInput();

		String query = "\nSELECT password \nFROM admins_gui \nWHERE password = '" + input + "';";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();		
			//System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);
			//System.out.println("Result: " + result.getString("Password") + "\n");

			while (result.next()) {
				String pass = result.getString("Password");
				temp = pass;
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
}