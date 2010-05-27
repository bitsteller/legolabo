import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Transporter implements Runnable {

	private DataInputStream in;
	private DataOutputStream out;
	public String name;
	public Thread thread;

	public Transporter(String name, DataInputStream in, DataOutputStream out) {
		this.name = name;
		this.in = in;
		this.out = out;
		thread = new Thread(this);
		thread.start();
	}

	public void run(){
		/*try {
			while (true) {
				System.out.println("Befehl für " + name + " eingeben: ");
				Scanner sc = new Scanner(System.in);
				String s = sc.next();
			
				sendCommand(s.toCharArray()[0]);
				
				if (s.equals(".")) {
					break;
				}
			}
			out.close();
		}
		catch (Exception e) {
		
		}*/
	}
	
	public void sendCommand(char cmd) throws Exception {
		System.out.println(name + ": " + cmd);
		out.writeChar(cmd);
		out.flush();
	}
	
	public void waitForMessage(char msg) throws Exception {
		char ch = in.readChar();
	 	while(ch != msg) {
			ch = in.readChar();
		}
		return;
	}
}
