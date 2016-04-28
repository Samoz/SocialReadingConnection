package search;

import java.util.ArrayList;
import java.util.List;

import graphDatabase.BookNode;
import hashTables.HashEntryGenre;


/**
 * @author Fernanda Lopez
 * @author Samuel Osuna
 * HashMapGenre.java
 * Mar 30, 2016
 * Version 1.0
 * In this class we will be implementing our search based in key words, the books will have from 
 * 1-3 genres each and the nodes will be connected through them. Also we will be adding the Ranking feature, by
 * using a heap our nodes will be sorted from most favorite to less.
 */
public class HashMapGenre {
	
	private final static int TABLE_SIZE = 300;
	private List<HashEntryGenre>[] table;
	public HashMapGenre(){
		table = new List[TABLE_SIZE];
		for(int i=0;i < TABLE_SIZE; i++){
			table[i] = new ArrayList<HashEntryGenre>();
		}
	}
	
	public int getGenreKey0(String genre){
		return (int)genre.charAt(0);
	}
	
	public int getGenreKey1(String genre){
		return (int)genre.charAt(0);
	}
	
	public int getGenreKey2(String genre){
		return (int)genre.charAt(0);
	}

	public List<BookNode> getGenre0(String genre){
		List<BookNode> list0 = new ArrayList<BookNode>();
		int hash= (getGenreKey0(genre) % TABLE_SIZE);
		if(table[hash] != null){
			for(int i=0;i<table[hash].size(); i++){
				if(table[hash].get(i).getBookNode().getAuthor().equals(genre)){
					list0.add(table[hash].get(i).getBookNode());
				}
			}
		}
		return list0;
	} 
	
	public List<BookNode> getGenre1(String genre){
		List<BookNode> list1 = new ArrayList<BookNode>();
		int hash= (getGenreKey0(genre) % TABLE_SIZE);
		if(table[hash] != null){
			for(int i=0;i<table[hash].size(); i++){
				if(table[hash].get(i).getBookNode().getAuthor().equals(genre)){
					list1.add(table[hash].get(i).getBookNode());
				}
			}
		}
		return list1;
	} 
	
	public List<BookNode> get2(String genre){
		List<BookNode> list2 = new ArrayList<BookNode>();
		int hash= (getGenreKey0(genre) % TABLE_SIZE);
		if(table[hash] != null){
			for(int i=0;i<table[hash].size(); i++){
				if(table[hash].get(i).getBookNode().getAuthor().equals(genre)){
					list2.add(table[hash].get(i).getBookNode());
				}
			}
		}
		return list2;
	} 

	public void put0(BookNode booknode){
		HashEntryGenre temp = new HashEntryGenre(booknode,0);
		int hash = (temp.getKey0(booknode) % TABLE_SIZE);
		table[hash].add(temp);
	} 
	
	public void put1(BookNode booknode){
		HashEntryGenre temp = new HashEntryGenre(booknode,1);
		int hash = (temp.getKey1(booknode) % TABLE_SIZE);
		table[hash].add(temp);
	} 
	
	public void put2(BookNode booknode){
		HashEntryGenre temp = new HashEntryGenre(booknode ,2);
		int hash = (temp.getKey2(booknode) % TABLE_SIZE);
		table[hash].add(temp);
	}
}
