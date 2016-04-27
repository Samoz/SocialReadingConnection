/**
 * 
 * @param author is the string that has the name of the author , and title of the book, which is the information that
 * we are currently storing in the node
 * @param title
 */

package graphDatabase;

import interfaces.BookNodeTInterface;

public class BookNodeT implements BookNodeTInterface{

	private String author,title;

	public BookNodeT(String author, String title){
		this.author = author;
		this.title = title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setAuthor(String author){
		this.author = author;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String toString(){
		return "Book title:" + this.title + "\nAuthor:" + this.author;
	}
}
