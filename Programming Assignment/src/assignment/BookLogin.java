package assignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 * This class generates a login GUI for access to the admin menu
 *  
 * @author James Gabbitus
 *
 */
public class BookLogin implements ActionListener {
	
	private static String userInput;
	private static String passInput;
	
	private static JFrame frame;
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel message;

	public static void main(String[] args) throws SQLException {
		frame = new JFrame();
		JPanel panel = new JPanel();
		frame.setSize(300, 170);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField();
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);	
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 50, 165, 25);
		panel.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(10, 80, 80, 25);
		button.addActionListener(new BookLogin());
		panel.add(button);
		
		message = new JLabel();
		message.setBounds(10, 105, 190, 25);
		panel.add(message);
		
		chooseMenu();
	}
	
	/**
	 * This method represents the initial menu of the console
	 * 
	 * @throws SQLException
	 */
	public static void chooseMenu() throws SQLException {
		
		Scanner in = new Scanner(System.in);
		String selection;
		 
		for (int i = 0; i < 1; i++) {
		      System.out.println("--------------------");
		      System.out.println("Choose your menu");
		      System.out.println("--------------------");
		      System.out.println("[1] User Menu");
		      System.out.println("[2] Admin Menu");
		      System.out.println("[3] Exit");
		      System.out.println();

		      selection = in.nextLine();

		      switch (selection) {
		      case "1":
		    	  Main.userMenu();
		          System.out.println();
		          break;

		      case "2":
		    	  frame.setVisible(true);
		          System.out.println();
		          break;

		      case "3":
		          System.out.println("Menu exited.");
		          break;
		          default:
		          System.out.println("Invalid Selection");
		      }
		    } 
		}
	
	/**
	 * This method validates the details of the login GUI and executes the admin menu if details are valid
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String user = userText.getText();
		String pass = passwordText.getText();
		
		setUserInput(user.toString());
		setPassInput(pass.toString());
		
		BookGUIDao admins = new BookGUIDao();	
		//Main menu = new Main();
		
		try {
			
			if (user.equals(admins.getUser()) && pass.equals(admins.getPass())) {
				System.out.println("~~~~~~~~~~~~~~~~~");
				System.out.println("Login successful!");
				System.out.println("~~~~~~~~~~~~~~~~~");
				Main.adminMenu();
				System.exit(0);
			} else if (user.equals("") && pass.equals("")) {
				message.setText("Please enter your details");
			} else {
				System.out.println("Access denied.\n");
				message.setText("Invalid username or password");
				userText.setText("");
				passwordText.setText("");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Gets the user input from the GUI
	 * 
	 * @return the user input
	 */
	public String getUserInput() {
		return userInput;
	}
	
	/**
	 * Sets the user input from the GUI
	 * 
	 * @param userInput the username input from the GUI
	 */
	public void setUserInput(String userInput) {
		BookLogin.userInput = userInput;
	}
	
	/**
	 * Gets the user input from password label of the GUI
	 * 
	 * @return the user input from the password label
	 */
	public String getPassInput() {
		return passInput;
	}
	
	/**
	 * Sets the password input from the GUI
	 * 
	 * @param passInput the password input from the GUI
	 */
	public void setPassInput(String passInput) {
		BookLogin.passInput = passInput;
	}		
}
