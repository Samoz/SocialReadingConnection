/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * BookEdgeInterface.java
 * March 30, 2016
 * Version 1.0
 */

package interfaces;

import graphDatabase.BookNode;

public interface BookEdgeInterface {	
	
	/**
	 * 
	 * @return the first node that is connected to the edge
	 */
	BookNode getBookOne();
	
	/**
	 * 
	 * @return the second node that is connected to the edge
	 */
	BookNode getBookTwo();
	
	/**
	 * 
	 * @return a well-formated string of the edge
	 */
	String toString();
}
