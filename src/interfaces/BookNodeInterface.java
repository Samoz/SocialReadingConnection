/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * BookNodeInterface.java
 * March 30, 2016
 * Version 1.0
 */

package interfaces;

import graphDatabase.BookEdge;

public interface BookNodeInterface {
	
	
	/**
	 * Sets the name of the book
	 * @param title, the title of the book
	 */
	void setBookTitle(String title);
	
	/**
	 * 
	 * @return the title of the book
	 */
	String getBookTitle();
	
	/**
	 * Sets the author's first and last name
	 * @param firstName the first name of the author
	 * @param lastName the last name of the author
	 */
	void setAuthor(String firstName, String lastName);
	
	/**
	 * 
	 * @return the first name of the author
	 */
	String getAuthorFirstName();
	
	/**
	 * 
	 * @return the last name of the author
	 */
	String getAuthorLastName();
	
	String getAuthor();
	
	/**
	 * 
	 * @return a well formated-string of the info of the node
	 */
	String toString();
}
