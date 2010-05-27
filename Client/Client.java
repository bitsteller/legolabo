import lejos.nxt.*;
import lejos.nxt.addon.*;
import lejos.nxt.remote.*;
import lejos.nxt.comm.*;
import java.io.*;

public class Client {
	private static DataInputStream in;
	private static DataOutputStream out;
	
	public static void run() throws Exception {
		NXTConnection connection = Bluetooth.waitForConnection();
		in = connection.openDataInputStream();
		out = connection.openDataOutputStream();
		
		System.out.println("Starten...");
		Drive.moveToNextCrossing();
		System.out.println("Bereit");
		sendMessage('k');
		Drive.playTune("HHCDEDCHAACEDC",200);
		
		char ch = ' ';
		
		while(!Button.ESCAPE.isPressed()) {
			ch = in.readChar();
			if(ch == '#') { //String ausgeben
				sendMessage('k');
				ch = in.readChar();
			 	while(ch != '#') {
			 		System.out.print("" + ch);
			 		sendMessage('k');
					ch = in.readChar();
				}
				System.out.println();
				sendMessage('k');
			}
			if(ch == 'c') { //Display löscchen
				LCD.clear();
				sendMessage('k');
			}
			if(ch == 'r') { //Rechts abbiegen
				System.out.println("Befehl r");
				Drive.turn(1);
				Drive.moveToNextCrossing();
				sendMessage('k');
			}
			if(ch == 'l') { //Links abbiegen
				System.out.println("Befehl l");
				Drive.turn(-1);
				Drive.moveToNextCrossing();
				sendMessage('k');
			}
			if(ch == 's') { //Geradaus fahren
				System.out.println("Befehl s");
				Drive.turn(0);
				Drive.moveToNextCrossing();
				sendMessage('k');
			}
			if(ch == 't') { //Umdrehen
				System.out.println("Befehl t");
				Drive.reverse();
				Drive.playTune("HHCDEDCHAACEDC",200);
				Drive.moveToNextCrossing();
				sendMessage('k');
			}
			if (ch == '.') { //Programm beenden
				Drive.playTune("CDECAAHCDEDCHH",200);
				sendMessage('k');
				in.close();
				out.close();
				return;
			}
		}
	}
	
	public static void sendMessage(char msg) throws IOException{
		out.writeChar(msg);
		out.flush();
	}

	public static void main(String[] args) throws Exception {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);

		Drive.kalibrieren(c1,c2);
		run();
	}

}
