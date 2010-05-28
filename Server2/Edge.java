import java.util.*;

public class Edge {
	public Node from;
	public Node to;
	
	public Edge(Node from, Node to) {
		this.from = from;
		this.to = to;
	}
	
	public static Edge getByNodes(Node a, Node b) { //FIXME: Parallele Kanten!
		for (Edge e:a.edges) {
			if ((e.to == a && e.from == b) || (e.from== a && e.to == b)) return e;
		}
		
		throw new IllegalArgumentException("HELP!!!! NO EDGE!!!");
	}

}
