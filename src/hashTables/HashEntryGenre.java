package hashTables;

import graphDatabase.BookNode;

/**
 * @author Fernanda Lopez
 * @author Samuel Osuna
 * HashEntryGenre.java
 * Mar 30, 2016
 * Version 1.0
 *
 * HashEntryGenre.java
 * This the most complicated one since our whole project is based in this one. We are going to use three lists
 * to store the different genres in the books, and then we are going to store this genres in the BookNodes. 
 * And after that , we are going to make the matching with the graph, and give the recommendation to the user.
 * It is not yet implemented , but it is going to be. And yes, we will have duplicated values.
 */
public class HashEntryGenre {
	
	private int theKey0;
	private int theKey1;
	private int theKey2;
	
	private BookNode bookNode;
	
	public HashEntryGenre(BookNode bookNode, int index){
		this.bookNode = bookNode;
		this.theKey0 = createKey0(bookNode, index);
		this.theKey1 = createKey1(bookNode, index);
		this.theKey2 = createKey2(bookNode, index);
	}
	
	public int createKey0(BookNode bookNode, int index) {
		return (int) bookNode.getAKeyword(index).charAt(0);
	}
	
	
	public int createKey1(BookNode bookNode, int index) {
		return (int) bookNode.getAKeyword(index).charAt(0);
	}
	
	public int createKey2(BookNode bookNode, int index) {
		return (int) bookNode.getAKeyword(index).charAt(1);
	}
	
	public int getKey0(BookNode bookNode, int index) {
		return this.theKey0;
	}
	
	public int getKey1(BookNode bookNode) {
		return this.theKey1;
	}
	
	public int getKey3(BookNode bookNode) {
		return this.theKey2;
	}
	
	public BookNode getBookNode() {
		return this.bookNode;
	}
	
	public String toString(){
		return "hash entry" + "key0" + this.theKey0 + "author" + this.bookNode + 
				"hash entry" + "key1" + this.theKey0 + "author" + this.bookNode +
				"hash entry" + "key2" + this.theKey0 + "author" + this.bookNode;
	}

}
