import lejos.pc.comm.*;
import java.io.*;

public class Transporter implements Runnable {

	private DataInputStream in;
	private DataOutputStream out;
	private String name;

	public Transporter(String name, DataInputStream in, DataOutputStream out) {
		this.name = name;
		this.in = in;
		this.out = out;
	}

	public void run(){
		try {
			out.writeChars("test.");
			out.flush();
			out.close();
		}
		catch (Exception e) {
		
		}
	}
}
