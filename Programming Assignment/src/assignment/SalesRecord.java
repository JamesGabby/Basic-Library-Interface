package assignment;

/**
 * This class models a sales record
 * 
 * @author James Gabbitus
 *
 */
public class SalesRecord {
	
	private int customer_id;
	private int product_no;
	private String order_date;
	private String order_time;

	/**
	 * 
	 * @param customer_id The unique ID of the customer
	 * @param product_no The unique no. of the product
	 * @param order_date The date the product was ordered
	 * @param order_time The time the product was ordered
	 */
	public SalesRecord(int customer_id, int product_no, String order_date, String order_time) {
	
		this.customer_id = customer_id;
		this.product_no = product_no;
		this.order_date = order_date;
		this.order_time = order_time;

		}
	
	/**
	 * 
	 * @return The ID of the customer
	 */
	public int getCustomerID() {
		return this.customer_id;
	}
	
	/**
	 * 
	 * @param customer_id Sets the ID of the customer
	 */
	public void setCustomerID(int customer_id) {
		this.customer_id = customer_id;
	}
	
	/**
	 * 
	 * @return The date the product was ordered
	 */
	public String getOrderDate() {
		return this.order_date;
	}
	
	/**
	 * 
	 * @param order_date Sets the order date of the purchase
	 */
	public void setOrderDate(String order_date) {
		this.order_date = order_date;
	}
	
	/**
	 * 
	 * @return The time the product was ordered
	 */
	public String getOrderTime() {
		return this.order_time;
	}
	
	/**
	 * 
	 * @param order_time Sets the order timr of the purchase
	 */
	public void setOrderTime(String order_time) {
		this.order_time = order_time;
	}
	
	/**
	 * 
	 * @return The number ID of the product
	 */
	public int getProductNo() {
		return product_no;
	}
	
	/**
	 * 
	 * @param product_no Sets the number ID of the product
	 */
	public void setProductNo(int product_no) {
		this.product_no = product_no;
	}
	
	/**
	 * Return an instance of a sales record
	 */
	@Override
	public String toString() {
		return "Customer ID = " + this.customer_id + "Product No. = " + this.product_no + "\nOrder_date = " + this.order_date + "\nOrder_time = " + this.order_time;
	}

}
