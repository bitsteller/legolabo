import java.util.*;

public class Node {
	public String name = "";
	public Edge[] edges = new Edge[4];
	public ArrayList<Good> goods = new ArrayList<Good>();
	
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
	
	public Good pullGood(Good.Goods type, int amount) {
		if (type == Good.Goods.EISEN || type == Good.Goods.KOHLE) {
			return new Good(type,amount);
		}
		else {
			//Aus Lager nehmen
			return null;
		}
	}
	
	public void pushGood(Good g) {
		Good ges = null;
		
		for (Good go : this.goods) {
			if (go.type == g.type) {
				ges = go;
				break;
			}
		}
		
		if (ges == null) {
			ges = new Good(g.type, 0);
			this.goods.add(ges);
		}
		
		ges.amount += g.amount;
		
		System.out.println("Knoten " + name + ": " + ges.amount + " Einheiten " + ges.type.toString() + " gelagert.");
	}
}
