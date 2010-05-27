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
}
