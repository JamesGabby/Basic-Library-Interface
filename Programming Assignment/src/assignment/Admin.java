package assignment;
/**
 * 
 * @author James Gabbitus
 *
 */
public class Admin {
	private int admin_id;
	private String username;
	private String password;

	/**
	 * 
	 * @param admin_id This admin's ID
	 * @param username The username of this admin
	 * @param password The password of this admin
	 */
	public Admin(int admin_id, String username, String password) {
		this.admin_id = admin_id;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 
	 * @return The admin ID of this admin
	 */
	public int getAdminID() {
		return this.admin_id;
	}
	
	/**
	 * 
	 * @param admin_id Sets the admin ID of this admin
	 */
	public void setAdminID(int admin_id) {
		this.admin_id = admin_id;
	}
	
	/**
	 * 
	 * @return The username of this admin
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * 
	 * @param username Sets the username of this admin
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * 
	 * @return The password of this admin
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * 
	 * @param password Sets the password of this admin
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns an instance of this admin
	 */
	@Override
	public String toString() {
		return "Admin ID = " + this.admin_id + "\nUsername = " + this.username + "\nPassword = " + this.password +"\n--------------------";
	}
}
