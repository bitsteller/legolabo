import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Transporter implements Runnable {

	private DataInputStream in;
	private DataOutputStream out;
	public String name;
	public Thread thread;
	
	private ArrayList<Job> jobq = new ArrayList<Job>();
	
	public Node position;
	public Graph.Dir direction;

	public Transporter(String name, DataInputStream in, DataOutputStream out) {
		this.name = name;
		this.in = in;
		this.out = out;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		while (true) {
			try {
				while (jobq.size()>0) {
					Job job = jobq.get(0);
					
					System.out.println(name + ": starting with job " + job.id + "...");
					job.state = Job.State.WORKING;
					job.printState();
					
					while (job.pendingEdges.size()>0) {		
						Edge way = job.pendingEdges.get(0);
						
						Graph.Dir todir = position.getEdgeDirection(way);
						char cmd = Graph.getTurnDirection(direction, todir);
							
						sendCommand(cmd);
						waitForMessage('k');
						System.out.println(name + ": command finished (" + cmd + ")...");
						
						this.position = way.to;
						this.direction = way.to.getEdgeDirection(way);
						
						this.printPosition();
						
						job.pendingEdges.remove(way);
					}

					System.out.println(name + ": finished job ID" + job.id + ".");
					job.state = Job.State.FINISHED;
					job.printState();
					
					jobq.remove(job);
					
					Thread.yield();
				}
				
				Thread.yield();
				Thread.sleep(1000);
				Thread.yield();
			}
			catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
	}
	
	public void enqueueJob(Job job) throws Exception {
		System.out.println(name + ": endquened job " + job.id + "...");
		this.jobq.add(job);
	}
	
	private void sendCommand(char cmd) throws Exception {
		System.out.println(name + ": sending command(" + cmd + ")...");
		out.writeChar(cmd);
		out.flush();
	}
	
	public void waitForMessage(char msg) throws Exception {
		char ch = in.readChar();
		System.out.println(name + ": received (" + ch + ")...");
	 	while(ch != msg) {
			ch = in.readChar();
			System.out.println(name + ": received (" + ch + ")...");
		}
		return;
	}
	
	public void disconnect() throws Exception {
		System.out.println(this.name + ": sending shutdown request...");
		sendCommand('.');
		Server.transporters.remove(this);
		System.out.println(this.name + ": successfully disconnected.");
	}
	
	public void setPosition(Node node, Graph.Dir dir) {
		this.position = node;
		this.direction = dir;
	}
	
	public void printPosition() {
		System.out.println(this.name + ": is at " + this.position.name + this.direction.toString());
	}
	
	public static Transporter getByName(String name) {
		for (Transporter t: Server.transporters) {
			if (t.name.equals(name)) {
				return t;
			}
		}
		return null;
	}
	

}
