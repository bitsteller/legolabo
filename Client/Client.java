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
		
		while(!Button.ESCAPE.isPressed()) {
			ch = in.readChar();
			if(ch == '#') {
				while(ch != '#') {
					System.out.print("" + ch);
				}
				System.out.println();
			}
			if(ch == 'c') {
				LCD.clear();
			}
			if(ch == 'r') {
				drive.turn(1);
				drive.moveToNextCrossing();
			}
			if(ch == 'l') {
				drive.turn(-1);
				drive.moveToNextCrossing();
			}
			if(ch == 's') {
				drive.turn(0);
				drive.moveToNextCrossing();
			}
			if(ch == 't') {
				drive.reverse();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);

		//drive.kalibrieren(c1,c2);
		run();
	}

}
