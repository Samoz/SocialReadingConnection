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
	private int key;
	private BookNode booknode;
	public HashEntryGenre(BookNode booknode){
		this.key = createKey(booknode);
	}
	public int getKey(){
		return key;
	}
	public BookNode getBookNodeT(){
		return booknode;
	}
	private int createKey(BookNode booknode){
		return (int)booknode.getAuthor().charAt(0);
	}
	public String toString(){
		return "hash entry" + "key" + this.key + "author" + this.booknode;
	}

}
