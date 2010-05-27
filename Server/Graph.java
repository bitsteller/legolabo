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
}
