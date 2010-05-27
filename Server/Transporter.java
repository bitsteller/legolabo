import lejos.pc.comm.*;
import java.io.*;

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
		try {
			sendCommand('#');
			out.writeChars("Hallo");
			sendCommand('#');
			sendCommand('r');
			waitForMessage('k');
			sendCommand('l');
			waitForMessage('k');
			sendCommand('s');
			waitForMessage('k');
			sendCommand('#');
			out.writeChars("Goodbye");
			sendCommand('#');
			sendCommand('.');

			out.close();
		}
		catch (Exception e) {
		
		}
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
