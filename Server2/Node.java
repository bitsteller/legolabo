import java.util.*;

public class Node {
	public String name = "";
	public Edge[] edges = new Edge[4];;
	
	public Node(String name) {
		this.name = name;
	}
	
	public static Node getByName(String name) {
		for (Node n : Graph.nodes) {
			if (n.name.equals(name)) {
				return n;
			}
		}
		System.out.println("WARNING: Node " + name + " does not exist!");
		return null;
	}
	
	public Graph.Dir getEdgeDirection(Edge edge) {
		int dir = -1;
		
		for (int i = 0; i < 4; i++) {
			if (this.edges[i] == edge) {
				dir = i;
			}
		}
		if (dir == -1) throw new IllegalArgumentException("HELP!!!! NO WAY!!!");
		
		return Graph.Dir.values()[dir];
	}
}
