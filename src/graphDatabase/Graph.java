/**
 * @author María Fernanda López Cárdenas	A01229740
 * @author Samuel Osuna Zatarain			A01630427
 * Graph.java
 * March 30, 2016
 * Version 1.0
 */


package graphDatabase;

import java.util.ArrayList;
import java.util.List;

import interfaces.GraphInterface;

public class Graph implements GraphInterface {
	
	private List<BookNode> nodeList;
	
	public Graph() {
		this.nodeList = new ArrayList<BookNode>();
	}
	
	@Override
	public void addNode(BookNode node) {
		if (this.nodeList.contains(node)) {
			System.out.println("The book " + node.getBookTitle() + " is already added.");
		}
		else {
			for (BookNode bookNode : nodeList) {
				bookNode.addSimilarNode(node);
			}
			nodeList.add(node);
		}
	}
	
	public List<BookNode> getNodeArr() {
		return this.nodeList;
	}
	
	public void setNodeArr(ArrayList<BookNode> nodeArr) {
		this.nodeList = nodeArr;
	}
	
	
	public String displayNodes() {
		String nodeString = "";
		for (BookNode node: nodeList) {
			nodeString = nodeString + node + "\n";
		}
		return nodeString;
	}
}
