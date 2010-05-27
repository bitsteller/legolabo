import lejos.pc.comm.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Server {
	public static ArrayList<Transporter> transporters = new ArrayList<Transporter>();

	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(new Connections());
		thread.start(); //Start connection management
	
		String command = "";
		while(!command.equals("exit")) {
			for (Transporter t: Server.transporters) {
				System.out.print(t.name + " ");
			}
			System.out.println();
			
			String name = JOptionPane.showInputDialog("Name:");
			if (name.equals("exit")) {
				for (Transporter t: Server.transporters) {
					t.cmdq.add('.');
				}
				Thread.sleep(10000);
				return;
			}
			command = JOptionPane.showInputDialog("Commands:");
			
			for (Transporter t: Server.transporters) {
				if (t.name.equals(name)) {
				
					for (char c : command.toCharArray()) {
						t.cmdq.add(c);
					}
				}
			}
		}
	}
}
