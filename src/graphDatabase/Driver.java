package graphDatabase;

public class Driver {
	
	static Graph graph = new Graph();
	static BookNode harryPotter = new BookNode("Harry Potter", "JK", "Rowling");
	static BookNode harryPotter2 = new BookNode("Harry Potter 2", "JK", "Rowling");
	
	public static void main(String[] args) {
		graph.addNode(harryPotter);
		graph.addNode(harryPotter2);
		graph.addEdge(harryPotter, harryPotter2);
		System.out.println("Graph Contains {1, 2}: " + graph.containsEdge(new BookEdge(harryPotter, harryPotter2)));
	}
	
}
