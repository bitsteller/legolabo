import lejos.nxt.*;
import lejos.nxt.addon.*;
import lejos.nxt.remote.*;
import lejos.nxt.comm.*;
import java.io.*;

public class Client {

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
				Drive.turn(1);
				Drive.moveToNextCrossing();
			}
			if(ch == 'l') {
				Drive.turn(-1);
				Drive.moveToNextCrossing();
			}
			if(ch == 's') {
				Drive.turn(0);
				Drive.moveToNextCrossing();
			}
			if(ch == 't') {
				Drive.reverse();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);

		//Drive.kalibrieren(c1,c2);
		run();
	}

}
