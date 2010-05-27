import lejos.nxt.*;
import lejos.nxt.addon.*;
import lejos.nxt.remote.*;
import lejos.nxt.comm.*;
import java.io.*;

public class client {

	public static void run() throws Exception {
		NXTConnection connection = Bluetooth.waitForConnection();
		DataInputStream in = connection.openDataInputStream();
		
		char ch = ' ';
		
		while(ch != '.') {
			ch = in.readChar();
			System.out.print("" + ch);
			Button.ENTER.waitForPress();
		}
	}

	public static void main(String[] args) throws Exception {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);

		//drive.kalibrieren(c1,c2);
		run();
	}

}
