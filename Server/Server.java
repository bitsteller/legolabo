import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Server {
	public static ArrayList<Transporter> transporters = new ArrayList<Transporter>();;

	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(new Connections());
		thread.start();
	
		String command = "";
		while(!command.equals("exit")) {			
			for (Transporter t: Server.transporters) {
				System.out.print(t.name + " ");
			}
			System.out.println();
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Name: ");
			String name = sc.next();
			System.out.println("Command: ");
			command = sc.next();
			
			for (Transporter t: Server.transporters) {
				if (t.name.equals(name)) {
					//t.sendCommand(command.toCharArray()[0]);
					t.cmdq.add(0,command.toCharArray()[0]);
				}
			}
		}
	}
}
