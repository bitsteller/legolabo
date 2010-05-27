import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Transporter implements Runnable {

	private DataInputStream in;
	private DataOutputStream out;
	public String name;
	public Thread thread;
	
	public ArrayList<Character> cmdq = new ArrayList<Character>();

	public Transporter(String name, DataInputStream in, DataOutputStream out) {
		this.name = name;
		this.in = in;
		this.out = out;
		thread = new Thread(this);
		thread.start();
	}

	public void run(){
		while (true) {
			try {
				if (cmdq.get(0) != null) {
					sendCommand(cmdq.get(0));
					cmdq.remove(0);
				}
				System.out.println(this.name + " run methode");
				Thread.yield();
				Thread.sleep(1000);
				Thread.yield();
			}
			catch (Exception e) {
			
			}
		}
	}
	
	public void sendCommand(char cmd) throws Exception {
		System.out.println(name + ": " + cmd);
		out.writeChar(cmd);
		out.flush();
	}
	
	public void sendCommands(String cmd) throws Exception {
		System.out.println(name + ": " + cmd);
		out.writeChars(cmd);
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
