package hashTables;

import graphDatabase.BookNodeT;

/**
 * @author Fernanda Lopez
 * @author Samuel Osuna
 * HashEntryTitle.java
 * Mar 30, 2016
 * Version 1.0
 * hashEntryTitle.java
 * Right now we are using separated hashtables, because since is the beggining of our project we wanted to make sure
 * that we are doing is correct. But, we are planning to have all the hashtables in one. But this one has the same
 * purposes as the HashEntryAuthor, but only in this one we are writing the Title of the book.
 */

public class HashEntryTitle {
		private int key;
		private BookNodeT booknode;
		public HashEntryTitle(BookNodeT booknode){
			this.key = createKey(booknode);
			this.booknode = booknode;
		}
		/**
		 * 
		 * @return the key of the title of the book
		 */
		public int getKey(){
			return key;
		}
		/**
		 * 
		 * @return the node that has the information stored about the book
		 */
		public BookNodeT getBookNodeT(){
			return booknode;	
		}
		/**
		 * 
		 * @param booknode, from the information that, right now we write in the node but it will be a database
		 * @return an integer made from the first letter of the String
		 */
		private int createKey(BookNodeT booknode){
			return (int)booknode.getTitle().charAt(0);
		}
		/**
		 * User for testing purposes, just to make sure that the program is printing the correct information
		 */
		public String toString(){
			return "hashEntry" + "key" + this.key + "Book title" + this.booknode;
		}
		
	}



