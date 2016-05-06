package search;

import java.util.ArrayList;
import java.util.List;


import hashTables.HashEntryTitle;
import graphDatabase.BookNode;


/**
 * @author Fernanda Lopez
 * @author Samuel Osuna
 * HashMapTitle.java
 * Mar 30, 2016
 * Version 1.0
 * This class has the same purposes as the one with the Author, in this class we are searching for the matching 
 * Title that the user wrote and the one in our database. 
 */
public class HashMapTitle {
	private final static int TABLE_SIZE = 300;
	private List<HashEntryTitle>[] table;
	public HashMapTitle(){
		table = new List[TABLE_SIZE];
		for(int i=0;i < TABLE_SIZE; i++){
			table[i] = new ArrayList<HashEntryTitle>();
		}
	}
	/**
	 * 
	 * @param title, the string we are going to search, based on the tile of the book
	 * @return the integer that corresponds to the first letter of the string
	 */
	public int getTitleKey(String title){
		return (int)title.charAt(0);
		
	}
	/**
	 * 
	 * @param title once we have the string, we get the title key and use the modular operation to have the 
	 * corresponding code, once it matches we stored it in our hash table and add it to our book list
	 * @return a list that has the matching strings, giving all the books the user was/is searching 
	 */
	public BookNode get(String title){
		BookNode bookNode = null;
		int hash = (getTitleKey(title) % TABLE_SIZE);
		if(table[hash] != null){
			for(int i=0;i<table[hash].size(); i++){
				if(table[hash].get(i).getBookNode().getBookTitle().equals(title)){
					bookNode = table[hash].get(i).getBookNode();
					break;
					//list.add(table[hash].get(i).getBookNode());
				}
			}
		}
		return bookNode;
		
	}
	/**
	 * 
	 * @param booknode once we have all the matches we put our key in our hashtable
	 */
	public void put(BookNode booknode){
		HashEntryTitle temp = new HashEntryTitle(booknode);
		int hash = (temp.getKey() % TABLE_SIZE);
		table[hash].add(temp);
		
	}

}
