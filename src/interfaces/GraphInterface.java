/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * GraphInterface.java
 * March 30, 2016
 * Version 1.0
 */

package interfaces;

import graphDatabase.BookEdge;
import graphDatabase.BookNode;

public interface GraphInterface {
	
	/**
	 * Adds a node to the graph
	 * @param node the node to be added
	 */
	void addNode(BookNode node);
	
	/**
	 * Adds and edge between two nodes in the graph
	 * @param nodeOne the first node of the edge
	 * @param nodeTwo the second node of the edge
	 * @return true if an edge is added
	 */
	boolean addEdge(BookNode nodeOne, BookNode nodeTwo);
	
	/**
	 * Check if there's an edge that connects two nodes
	 * @param edge the edge to check 
	 * @return true if there's a connection
	 */
	boolean containsEdge(BookEdge edge);
}
