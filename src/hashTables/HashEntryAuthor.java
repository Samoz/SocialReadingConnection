/**
 * 
 */
package hashTables;

import graphDatabase.BookNodeT;

/**
 * @author Fernanda Lopez
 * @author Samuel Osuna
 * HashEntryAuthor.java
 * Mar 30, 2016
 * Version 1.0
 * In this class we are implementing one of our key strings for searching which is the Author, 
 * we make our constructors and the createKey method, which uses the first letter of the string written by
 * the user ( for now, we are going to use the Data Base of Google Books so we can have the most access we have
 * to the books that are currently online and in the world) and this letter has a modular method, that divides the word
 * into code and delivers it to us so we can make the match of the letter with the book that we are looking for
 */

public class HashEntryAuthor {
	private int key;
	private BookNodeT booknode;
	public HashEntryAuthor(BookNodeT booknode){
		this.key = createKey(booknode);
		this.booknode = booknode;
		
	}
	
	/**
	 * 
	 * Getter for our key, in it we simple get the key and return it
	 * @return the key of the book
	 */
	public int getKey(){
		return key;
	}
	/**
	 * 
	 * Getter for the BookNode, in which we get the information that is stored in it
	 * @return the booknode
	 */
	public BookNodeT getBookNodeT(){
		return booknode;
	}
	/**
	 * 
	 * @param booknode, made by the string the user writes as Title or Author
	 * @return it takes the first letter of the string and returns its integer
	 */
	private int createKey(BookNodeT booknode){
		return (int)booknode.getAuthor().charAt(0);
	}
	/**
	 * Simply gives us the key and the author, this is used mainly for testing purposes
	 */
	public String toString(){
		return "hash entry" + "key" + this.key + "author" + this.booknode;
	}
}
