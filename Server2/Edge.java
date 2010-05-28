import java.util.*;

public class Edge {
	public Node from;
	public Node to;
	
	public Edge(Node from, Node to) {
		this.from = from;
		this.to = to;
	}
	
	public static Edge getByNodes(Node a, Node b) { //FIXME: Parallele Kanten!
		for (int i = 0; i<4; i++) {
			Edge e = a.edges[i];
			if (e != null) {
				if ((e.from == a && e.to == b) || (e.from == b && e.to == a)) return e;
			}
		}
		
		System.out.println("HELP!!!! NO EDGE!!!");
		return null;
	}

}
