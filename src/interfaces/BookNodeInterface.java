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
	 * Add an edge to the node
	 * @param edge the edge to add
	 */
	void addConnection(BookEdge edge);
	
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
	
	/**
	 * Sets the rating of the book
	 * @param rating the rating of the book
	 */
	void setRating(int rating);
	
	/**
	 * 
	 * @return the rating of the book
	 */
	int getRating();
	
	/**
	 * Sets keywords as a genres for the book
	 * they are used to relate one book to others
	 * they are stored in an array
	 * @param keyWord1	the first keyword/genre for the book
	 * @param keyWord2	the second keyword/genre for the book
	 * @param keyWord3	the third keyword/genre for the book
	 */
	void addKeywords(String keyWord1, String keyWord2, String keyWord3);
	
	/**
	 * 
	 * @return returns an array of the keywords of the book
	 */
	String[] getKeywords();
	
	/**
	 * 
	 * @return the number of edges that the node has
	 */
	int getConnectionCount();
	
	/**
	 * 
	 * @return a well formated-string of the info of the node
	 */
	String toString();
	
	/**
	 * Check if exist an edge that connects the current node to another node
	 * @param edge the edge to check
	 * @return true if there's a connection
	 */
	boolean containsConnection(BookEdge edge); 
}
