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
}
