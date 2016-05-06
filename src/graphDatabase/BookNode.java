/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * BookNode.java
 * March 30, 2016
 * Version 1.0
 */

package graphDatabase;

import java.util.ArrayList;
import java.util.List;

import interfaces.BookNodeInterface;

public class BookNode implements BookNodeInterface {
	
	private String bookTitle;
	private String authorFName, authorLName;
	private String[] keyWords;
	private List<BookNode> similarBooks;
	
	public BookNode(String bookTitle, String authorFName, String authorLName) {
		this.similarBooks = new ArrayList<BookNode>();
		this.bookTitle = bookTitle;
		this.authorFName = authorFName;
		this.authorLName = authorLName;
		this.keyWords = new String[3];
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
	public void addKeywords(String keyWord1, String keyWord2, String keyWord3) {
		this.keyWords[0] = keyWord1;
		this.keyWords[1] = keyWord2;
		this.keyWords[2] = keyWord3;
	}

	@Override
	public String[] getKeywords() {
		return this.keyWords;
	}
	
	public void setSimilarBooks(List<BookNode> similarBooks) {
		this.similarBooks = similarBooks;
	}

	public String toString() {
		String s = "";
		s+= "Book: " + this.bookTitle + "\n" +
				" Author: " + this.authorFName + " " + this.authorLName + "\n" +
				" Genres: " + this.keyWords[0] + ", " + this.keyWords[1] + ", " + this.keyWords[2] +
				"\n";
		return s;
	}
	
	public String getAKeyword(int index) {
		return this.keyWords[index];
	}
	
	public boolean addSimilarNode(BookNode similarNode) {
		int counterKW = 0;
		for (int i = 0; i < keyWords.length; i++) {
			for (int j = 0; j < keyWords.length; j++) {
				if (similarNode.getKeywords()[i].equals(this.getKeywords()[j])) {
					counterKW++;
				}
			}
		}
		if (counterKW >= 2) {
			this.similarBooks.add(similarNode);
			similarNode.getSimilarBooks().add(this);
			return true;
		}
		return false;
	}
	
	public List<BookNode> getSimilarBooks() {
		return this.similarBooks;
	}

	@Override
	public String getAuthor() {
		return getAuthorFirstName() + " " + getAuthorLastName();
	}
}