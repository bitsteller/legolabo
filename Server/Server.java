import lejos.pc.comm.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Server {
	public static ArrayList<Transporter> transporters = new ArrayList<Transporter>();

	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(new Connections());
		thread.start(); //Start connection management
	
		while(true) { //Command input mode
			String robotlist = "";
			for (Transporter t: Server.transporters) {
				robotlist += t.name + " ";
			}
			
			String name = JOptionPane.showInputDialog("Name (" + robotlist + "):");
			if (name.equals("exit")) {
				for (Transporter t: Server.transporters) {
					t.disconnect();
				}
				
				thread.stop();
				return;
			}
			else {
				Transporter t = Transporter.getByName(name);
				
				if (t != null) {
					String command = JOptionPane.showInputDialog("Commands:");
			
					for (char c : command.toCharArray()) {
							t.enqueueCommand(c);
					}
				}
				else {
					System.out.println(name + ": No such transporter available. Please check connection!");
				}
			}
		}
	}
}
