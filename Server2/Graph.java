import java.util.*;

public class Graph {
	public static enum Dir {N, E, S, W}
		
	public static ArrayList<Node> nodes = new ArrayList<Node>();
	public static ArrayList<Edge> edges = new ArrayList<Edge>();

	
	public static void addNode(String name) {
		Node n = new Node(name);
		Graph.nodes.add(n);
	}
	
	public static void addEdge(Node from, Graph.Dir fromdir, Node to, Graph.Dir todir) {
		Edge e = new Edge(from, to);
		
		from.edges[fromdir.ordinal()] = e;
		to.edges[todir.ordinal()] = e;
		
		Graph.edges.add(e);
	}
	
	public static void addEdge(String froms, Graph.Dir fromdir, String tos, Graph.Dir todir) {
		Node from = Node.getByName(froms);
		Node to = Node.getByName(tos);
		
		Graph.addEdge(from, fromdir, to, todir);
	}
	
	public static void init() {
		Graph.addNode("A");
		Graph.addNode("B");
		Graph.addNode("C");
		Graph.addNode("D");
		Graph.addNode("G");
		
		Graph.addEdge("A", Graph.Dir.E, "B", Graph.Dir.W);
		Graph.addEdge("A", Graph.Dir.S, "C", Graph.Dir.N);
		Graph.addEdge("B", Graph.Dir.S, "D", Graph.Dir.N);
		Graph.addEdge("C", Graph.Dir.E, "G", Graph.Dir.W);
		Graph.addEdge("G", Graph.Dir.E, "D", Graph.Dir.W);
	}
	
	public static char getTurnDirection(Graph.Dir fromdir, Graph.Dir todir) {
		int value = Math.abs((todir.ordinal()-fromdir.ordinal())%4);
		
		System.out.println("DEBUG turndir: " + fromdir.ordinal() +  "," + todir.ordinal() + "," + value);
		switch (value) {
			case 0: return 't';
			case 1: return 'l';
			case 2: return 's';
			case 3: return 'r';
		}
		

		return 42; //DEBUG
	}
}
