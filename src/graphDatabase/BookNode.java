/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * BookNode.java
 * March 30, 2016
 * Version 1.0
 */

package graphDatabase;

import java.util.ArrayList;
import interfaces.BookNodeInterface;

public class BookNode implements BookNodeInterface {
	
	private String bookTitle;
	private String authorFName, authorLName;
	private String[] keyWords;
	private int rating;
	private ArrayList<BookEdge> theConnection;
	
	public BookNode(String bookTitle, String authorFName, String authorLName) {
		this.theConnection = new ArrayList<BookEdge>();
		this.bookTitle = bookTitle;
		this.authorFName = authorFName;
		this.authorLName = authorLName;
		this.keyWords = new String[3];
	}
	
	@Override
	public void addConnection(BookEdge edge) {
		if (this.theConnection.contains(edge)) {
			return;
		}
		else {
			this.theConnection.add(edge);
		}
	}

	@Override
	public void setBookTitle(String title) {
		this.bookTitle = title;
	}

	@Override
	public String getBookTitle() {
		return this.bookTitle;
	}
	
	public void setAuthor(String firstName, String lastName) {
		this.authorFName = firstName;
		this.authorLName = lastName;
	}

	@Override
	public String getAuthorFirstName() {
		return this.authorFName;
		
	}
	
	public String getAuthorLastName() {
		return this.authorLName;
	}

	@Override
	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public int getRating() {
		return this.rating;
	}
	
	@Override
	public void addKeywords(String keyWord1, String keyWord2, String keyWord3) {
		this.keyWords[0] = keyWord1;
		this.keyWords[1] = keyWord2;
		this.keyWords[2] = keyWord3;
	}

	@Override
	public String[] getKeywords() {
		return this.keyWords;
	}
	
	public String toString() {
		return "Book: " + this.bookTitle + "\n" +
				" Author: " + this.authorFName + " " + this.authorLName + "\n" +
				" Genres: " + this.keyWords[0] + ", " + this.keyWords[1] + ", " + this.keyWords[2] + "\n" +
				" Rating: " + this.rating;
	}

	@Override
	public int getConnectionCount() {
		return this.theConnection.size();
	}

	@Override
	public boolean containsConnection(BookEdge edge) {
		return this.theConnection.contains(edge);
	}
}