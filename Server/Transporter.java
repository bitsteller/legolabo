import lejos.pc.comm.*;
import java.io.*;
import java.util.*;

public class Transporter implements Runnable {

	private DataInputStream in;
	private DataOutputStream out;
	public String name;
	public Thread thread;
	
	private ArrayList<Character> cmdq = new ArrayList<Character>();

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
				Thread.yield();
				Thread.sleep(100);
				Thread.yield();
			}
			catch (Exception e) {
			
			}
		}
	}
	
	public void enqueueCommand(char cmd) throws Exception {
		this.cmdq.add(cmd);
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
	
	public void disconnect() throws Exception {
		System.out.println("Sending shutdown request to + " + this.name + "...");
		sendCommand('.');
		System.out.println("Waiting for shutdown of + " + this.name + "...");
		waitForMessage('k');
		Server.transporters.remove(this);
		System.out.println(this.name + " successfully disconnected.");
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
