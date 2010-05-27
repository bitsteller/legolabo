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
			out.writeChar('_');
			out.writeChars("test");
			out.writeChar('_');
			out.flush();
			out.close();
		}
		catch (Exception e) {
		
		}
	}
}
