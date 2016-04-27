package search;

import java.util.ArrayList;
import java.util.List;

import graphDatabase.BookNode;
import hashTables.HashEntryAuthor;



/**
 * @author Fernanda Lopez
 * @author Samuel Osuna
 * HashMapAuthor.java
 * Mar 30, 2016
 * Version 1.0
 * In this class we are searching for the match of our input string.
 * We use HashMap, to set our table size and created a list, in which
 * we will be storing all our data, in this case, referenced to the Authors
 * later we are going to make all the searching in just one class
 */

public class HashMapAuthor {
	private final static int TABLE_SIZE = 300;
	private List<HashEntryAuthor>[] table;
	public HashMapAuthor(){
		table = new List[TABLE_SIZE];
		for(int i=0;i < TABLE_SIZE; i++){
			table[i] = new ArrayList<HashEntryAuthor>();
		}
	}
	/**
	 * 
	 * @param author, this when we are searching for the autor that we wrote. 
	 * @return the integer corresponding to the first letter of the string
	 */
	public int getAuthorKey(String author){
		return (int)author.charAt(0);
		
	}
	
	/**
	 * Returns the books form an author
	 * @param author the author to get the books
	 * @return a list of the books
	 */
	public List<BookNode> get(String author){
		List<BookNode> list = new ArrayList<BookNode>();
		int hash= (getAuthorKey(author) % TABLE_SIZE);
		if(table[hash] != null){
			for(int i=0;i<table[hash].size(); i++){
				if(table[hash].get(i).getBookNode().getAuthor().equals(author)){
					list.add(table[hash].get(i).getBookNode());
				}
				
			}
		}
		return list;
		
	}
	/**
	 * 
	 * @param booknode once we have the correct node, we put it in our hashtable to storage
	 */
	public void put(BookNode booknode){
		HashEntryAuthor temp = new HashEntryAuthor(booknode);
		int hash = (temp.getKey() % TABLE_SIZE);
		table[hash].add(temp);
		
	}
}
