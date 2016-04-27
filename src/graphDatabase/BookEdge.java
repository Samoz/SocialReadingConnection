/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * BookEdge.java
 * March 30, 2016
 * Version 1.0
 */

package graphDatabase;

import interfaces.BookEdgeInterface;

public class BookEdge implements BookEdgeInterface, Comparable<BookEdge> {
	
	private BookNode bookOne, bookTwo;
	
	public BookEdge(BookNode bookOne, BookNode bookTwo) {
		
	}

	@Override
	public BookNode getBookOne() {
		return this.bookOne;
	}

	@Override
	public BookNode getBookTwo() {
		return this.bookTwo;
	}

	@Override
	public int compareTo(BookEdge o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		return "[" + this.bookOne.getBookTitle() + " – " + this.bookTwo.getBookTitle() +  "]";
	}
	
	public int hashCode() {
		return (this.bookOne.getBookTitle() + this.bookTwo.getBookTitle()).hashCode();
	}
	

}
