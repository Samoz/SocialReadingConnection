package interfaces;

public interface BookNodeTInterface {
	/**
	 * Sets the title of the book
	 * @param title the title of the book
	 */
	void setTitle(String title);
	
	/**
	 * Sets the author of the book
	 * @param author the author of the book
	 */
	void setAuthor(String author);
	
	/**
	 * 
	 * @return the author of the book
	 */
	String getAuthor();
	
	/**
	 * 
	 * @return the title of the book
	 */
	String getTitle();
	
	/**
	 * 
	 * @return a well-formated string of the info of the node
	 */
	String toString();
}
