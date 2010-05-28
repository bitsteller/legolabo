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
		Graph.addNode("E");
		Graph.addNode("F");
		Graph.addNode("G");
		Graph.addNode("H");
		Graph.addNode("EISEN");
		Graph.addNode("STAHL");
		Graph.addNode("KOHLE");
		Graph.addNode("STADT");
		Graph.addNode("FABRIK");
		Graph.addNode("WALD");
		
		Graph.addEdge("A", Graph.Dir.E, "B", Graph.Dir.W);
		Graph.addEdge("A", Graph.Dir.W, "FABRIK", Graph.Dir.E);
		Graph.addEdge("A", Graph.Dir.S, "G", Graph.Dir.N);
		Graph.addEdge("G", Graph.Dir.E, "STADT", Graph.Dir.W);
		Graph.addEdge("G", Graph.Dir.S, "C", Graph.Dir.N);
		Graph.addEdge("C", Graph.Dir.W, "KOHLE", Graph.Dir.E);
		Graph.addEdge("C", Graph.Dir.S, "E", Graph.Dir.W);
		Graph.addEdge("C", Graph.Dir.E, "D", Graph.Dir.W);
		Graph.addEdge("D", Graph.Dir.N, "H", Graph.Dir.W);
		Graph.addEdge("D", Graph.Dir.S, "E", Graph.Dir.N);
		Graph.addEdge("E", Graph.Dir.S, "STAHL", Graph.Dir.N);
		Graph.addEdge("E", Graph.Dir.E, "F", Graph.Dir.W);
		Graph.addEdge("F", Graph.Dir.N, "H", Graph.Dir.S);
		Graph.addEdge("F", Graph.Dir.E, "HOLZ", Graph.Dir.W);
		Graph.addEdge("H", Graph.Dir.E, "EISEN", Graph.Dir.W);

		/*Graph.addEdge("A", Graph.Dir.E, "B", Graph.Dir.W);
		Graph.addEdge("A", Graph.Dir.S, "C", Graph.Dir.N);
		Graph.addEdge("B", Graph.Dir.S, "D", Graph.Dir.N);
		Graph.addEdge("C", Graph.Dir.E, "G", Graph.Dir.W);
		Graph.addEdge("G", Graph.Dir.E, "D", Graph.Dir.W);
		Graph.addEdge("G", Graph.Dir.S, "H", Graph.Dir.W);
		Graph.addEdge("D", Graph.Dir.S, "H", Graph.Dir.N);
		Graph.addEdge("H", Graph.Dir.S, "F", Graph.Dir.N);
		Graph.addEdge("E", Graph.Dir.E, "F", Graph.Dir.W);
		Graph.addEdge("C", Graph.Dir.S, "E", Graph.Dir.N);
		Graph.addEdge("EISEN", Graph.Dir.S, "A", Graph.Dir.N);
		Graph.addEdge("KOHLE", Graph.Dir.S, "B", Graph.Dir.N);
		Graph.addEdge("STAHL", Graph.Dir.E, "C", Graph.Dir.W);
		Graph.addEdge("STADT", Graph.Dir.W, "D", Graph.Dir.E);
		Graph.addEdge("FABRIK", Graph.Dir.W, "F", Graph.Dir.E);
		Graph.addEdge("WALD", Graph.Dir.N, "E", Graph.Dir.S);*/
	}
	
	public static char getTurnDirection(Graph.Dir fromdir, Graph.Dir todir) {
		int value = (todir.ordinal()-fromdir.ordinal())%4;
		
		while (value < 0) value +=4;
		
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
