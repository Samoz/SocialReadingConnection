/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * Graph.java
 * March 30, 2016
 * Version 1.0
 */


package graphDatabase;

import java.util.ArrayList;

import interfaces.GraphInterface;

public class Graph implements GraphInterface {
	
	private ArrayList<BookNode> nodeArr;
	private ArrayList<BookEdge> edgeArr;
	
	public Graph() {
		this.nodeArr = new ArrayList<BookNode>();
		this.edgeArr = new ArrayList<BookEdge>();
	}
	
	@Override
	public void addNode(BookNode node) {
		if (this.nodeArr.contains(node)) {
			System.out.println("The book " + node.getBookTitle() + " is already added.");
		}
		else {
			nodeArr.add(node);
		}
	}

	@Override
	public boolean addEdge(BookNode bookOne, BookNode bookTwo) {
		if (bookOne.equals(bookTwo)) {
			System.out.println("Can't relate same book.");
			return false;
		}
		BookEdge tempEdge = new BookEdge(bookOne, bookTwo);
		if (edgeArr.contains(tempEdge)) {
			return false;
		}
		else if (bookOne.containsConnection(tempEdge) || bookTwo.containsConnection(tempEdge)) {
			return false;
		}
		this.edgeArr.add(tempEdge);
		bookOne.addConnection(tempEdge);
		bookTwo.addConnection(tempEdge);
		return true;
	}

	@Override
	public boolean containsEdge(BookEdge edge) {
		if (edge.getBookOne() == null || edge.getBookTwo() == null) {
			return false;
		}
		return this.edgeArr.contains(edge);
	}	
	
	public ArrayList<BookNode> getNodeArr() {
		return this.nodeArr;
	}
	
	public void setNodeArr(ArrayList<BookNode> nodeArr) {
		this.nodeArr = nodeArr;
	}
	
	public ArrayList<BookEdge> getEdgeArr() {
		return this.edgeArr;
	}
	
	public void setEdgeArr(ArrayList<BookEdge> edgeArr) {
		this.edgeArr = edgeArr;
	}
	
	public String displayNodes() {
		String nodeString = "";
		for (BookNode node: nodeArr) {
			nodeString = nodeString + node + "\n";
		}
		return nodeString;
	}
}
