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
	private List<BookNode> similarBooks;
	private List<String> keyWords;
	
	public BookNode(String bookTitle, String authorFName, String authorLName) {
		this.similarBooks = new ArrayList<BookNode>();
		this.keyWords = new ArrayList<String>();
		this.bookTitle = bookTitle;
		this.authorFName = authorFName;
		this.authorLName = authorLName;
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
	
	public void addKeyword(String keyWord) {
		this.keyWords.add(keyWord);
	}
	
	public List<String> getKeywords() {
		return this.keyWords;
	}
	
	public void setKeywords(List<String> keyWords) {
		this.keyWords = keyWords;
	}
	
	public void setSimilarBooks(List<BookNode> similarBooks) {
		this.similarBooks = similarBooks;
	}

	public String toString() {
		String s = "";
		s+= "Book: " + this.bookTitle + "\n" +
				" Author: " + this.authorFName + " " + this.authorLName + "\n" +
				" Genres: \n";
		for (String keyWord : this.keyWords) {
			s += " - " + keyWord + "\n";
		}
		return s;
	}
	
	public boolean addSimilarNode(BookNode similarNode) {
		int counterKW = 0;
		for (String keyWordSimilarNode : similarNode.keyWords) {
			for (String keyWordThisNode : this.keyWords) {
				if (keyWordSimilarNode.equals(keyWordThisNode)) {
					counterKW++;
				}
			}
		}
		if (counterKW >= 3) {
			this.similarBooks.add(similarNode);
			similarNode.getSimilarBooks().add(this);
			return true;
		}
		return false;
	}
	
	public List<BookNode> getSimilarBooks() {
		return this.similarBooks;
	}

	public String getAuthor() {
		return getAuthorFirstName() + " " + getAuthorLastName();
	}
}