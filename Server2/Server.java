import lejos.pc.comm.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Server {
	public static ArrayList<Transporter> transporters = new ArrayList<Transporter>();

	public static void main(String[] args) throws Exception {
		Thread conMgmt = new Thread(new Connections());
		conMgmt.start(); //Start connection management
		
		Graph.init();
	
		while(true) { //Command input mode
			String robotlist = "";
			for (Transporter t: Server.transporters) {
				robotlist += t.name + " ";
			}
			
			String name = JOptionPane.showInputDialog("Name (" + robotlist + "):");
			if (name.equals("exit")) {
				System.out.println("Disconnecting all...");
				for (Transporter t: Server.transporters) {
					t.disconnect();
				}
				conMgmt.stop(); //Stop connection management
				
				System.out.println("Everything disconnected.");	
				return;
			}
			else {
				Transporter t = Transporter.getByName(name);
				
				if (t != null) {
					String command = JOptionPane.showInputDialog("Command:");
			
					if (command.equals("disconnect")) {
						t.disconnect();
					}
					else if (command.equals("add")) {
						Node from = t.position;
						ArrayList<Edge> way = new ArrayList<Edge>();
						Node to = null;
						
						try {
							String nodename = JOptionPane.showInputDialog("Enter node (return when complete):");
							Node l = from;
							Node n = null;
							while (!nodename.equals("return")) {
								n = Node.getByName(nodename);
								System.out.println("Searching way:" + l.name + " to " + n.name);
								Edge e = Edge.getByNodes(l,n);
								way.add(e);
								l = n;
								nodename = JOptionPane.showInputDialog("Enter node (return when complete):");
							}
							to = n;
							
							Good good = null;
							int goodnumber = Integer.parseInt(JOptionPane.showInputDialog("Enter good type number:"));
							if (goodnumber != -1) {
								Good.Goods g = Good.Goods.values()[goodnumber];
								good = t.position.pullGood(g,Transporter.getMaxLoad(g));
							}
							else {
								good = null;
							}
						
							Job job = new Job(from, way, to, good);
							t.enqueueJob(job);
						}
						catch (Exception e)
						{
							System.out.println("ERROR: Job not added: " + e.getMessage());
						}
					}
					else if (command.equals("set")) {
						String nodename = JOptionPane.showInputDialog("Enter node:");
						t.position = Node.getByName(nodename);
						String direction = JOptionPane.showInputDialog("Enter direction (0=North,...):");
						t.direction = Graph.Dir.values()[Integer.parseInt(direction)];
					}
						
				}
				else {
					System.out.println(name + ": No such transporter available. Please check connection!");
				}
			}
		}
	}
}
