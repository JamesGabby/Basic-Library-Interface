package assignment;
/**
 * This class models the attributes of a book
 * 
 * @author James Gabbitus
 *
 */
public class Book {

	private int book_id;
	private String title;
	private String author;
	private int year;
	private int edition;
	private String publisher;
	private String isbn;
	private String cover;
	private String condition;
	private int price;
	private String notes;
	/**
	 * 
	 * @param book_id The ID of this book
	 * @param title The named title of this book
	 * @param author This book author's name
	 * @param year The year in which this book was published
	 * @param edition The edition of this book
	 * @param publisher The name of this book's publisher
	 * @param isbn This book's ISBN code
	 * @param cover The type of cover this book has
	 * @param condition The condition of this book
	 * @param price The cost of this book
	 * @param notes Any relevant details regarding this book
	 */
	public Book(int book_id, String title, String author, int year, 
			int edition, String publisher, String isbn, String cover, 
			String condition, int price, String notes) {

		this.book_id = book_id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.edition = edition;
		this.publisher = publisher;
		this.isbn = isbn;
		this.cover = cover;
		this.condition = condition;
		this.price = price;
		this.notes = notes;
	}
	
	/**
	 * 
	 * @return The ID of the book
	 */
	public int getID() {
		return this.book_id;
	}
	
	/**
	 * 
	 * @param book_id Sets the ID of this book
	 */
	public void setID(int book_id) {
		this.book_id = book_id;
	}
	
	/**
	 * 
	 * @return The title of this book
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 
	 * @param title Sets the title of this book
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @return The author of this book
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * 
	 * @param author Sets the author of the book
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * 
	 * @return The year this book was published
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * 
	 * @param year Sets the year this book was published
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * 
	 * @return The edition of this book
	 */
	public int getEdition() {
		return this.edition;
	}
	
	/**
	 * 
	 * @param edition Sets the edition of this book
	 */
	public void setEdition(int edition) {
		this.edition = edition;
	}
	
	/**
	 * 
	 * @return The publisher of this book
	 */
	public String getPublisher() {
		return this.publisher;
	}
	
	/**
	 * 
	 * @param publisher Sets the publisher of this book
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * 
	 * @return The ISBN of this book
	 */
	public String getIsbn() {
		return this.isbn;
	}
	
	/**
	 * 
	 * @param isbn Sets the ISBN of this book
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	/**
	 * 
	 * @return The cover of this book
	 */
	public String getCover() {
		return this.cover;
	}
	
	/**
	 * 
	 * @param cover Sets the cover of this book
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	/**
	 * 
	 * @return The condition of this book
	 */
	public String getCondition() {
		return this.condition;
	}
	
	/**
	 * 
	 * @param condition Sets the condition of this book
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	/**
	 * 
	 * @return The price of this book
	 */
	public int getPrice() {
		return this.price;
	}
	
	/**
	 * 
	 * @param price Sets the price of this book
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return The notes of this book
	 */
	public String getNotes() {
		return this.notes;
	}
	
	/**
	 * 
	 * @param notes Sets the notes of this book
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Returns an instance of this book
	 */
	@Override
	public String toString() {
		return "Book ID = " + this.book_id + "\nTitle = " + this.title + "\nAuthor = " + this.author 
				+ "\nYear = " + this.year + "\nEdition = " + this.edition + "\nPublisher = " + this.publisher 
				+ "\nISBN = " + this.isbn + "\nCover = " + this.cover + "\nCondition = " + this.condition 
				+ "\nPrice = " + this.price + "\nNotes = " + this.notes +"\n--------------------";
	}
}
