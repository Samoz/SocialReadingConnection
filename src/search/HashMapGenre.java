package search;

import java.util.ArrayList;
import java.util.List;

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
	public int getGenreKey(String genre){
		return (int)genre.charAt(0);
	
	}
}
