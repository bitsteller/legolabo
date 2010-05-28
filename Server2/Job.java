import java.util.*;

public class Job {
	private static int jobcount = 0;
	
	public enum State  {ABORTED, PENDING, WORKING, FINISHED}
	
	public Node from;
	public Node to;
	public ArrayList<Edge> pendingEdges;
	public State state;
	public int id;
	public Good good;
	
	public Job(Node from, ArrayList<Edge> way, Node to, Good load) {
		this.from = from;
		this.to = to;
		this.pendingEdges = way;
		this.good = load;
		
		this.id = jobcount;
		this.state = Job.State.PENDING;
		jobcount++;
	}
	
	public void printState() {
		System.out.println("Job-ID " + this.id + " ("  + this.from.name + " to " + this.to.name + "): " + this.state.toString());
	}
}
