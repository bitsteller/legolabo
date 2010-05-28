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
		
		Graph.addEdge("A", Graph.Dir.E, "B", Graph.Dir.W);
		Graph.addEdge("C", Graph.Dir.E, "D", Graph.Dir.W);
		Graph.addEdge("B", Graph.Dir.S, "D", Graph.Dir.N);
		Graph.addEdge("A", Graph.Dir.S, "C", Graph.Dir.N);
		
	}
}
