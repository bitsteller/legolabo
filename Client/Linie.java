import lejos.nxt.*;
import lejos.nxt.addon.*;

public class Linie {

	public static void run() {
		while(!Button.ESCAPE.isPressed()){
				Drive.moveToNextCrossing();
				System.out.println("Kreuzung!");
				Motor.C.forward();
				Sound.setVolume(75);		//für Wettbewerb 100 setzen
				Sound.playNote(Sound.FLUTE,440,500);
				
				while(!Button.ESCAPE.isPressed()){
					if (Button.RIGHT.isPressed()){
						System.out.println("Links abbiegen...");
						Drive.turn(-1);
						System.out.println("Links abgebogen!");
						Motor.C.stop();
						break;
					}
					if (Button.LEFT.isPressed()){
						System.out.println("Rechts abbiegen...");
						Drive.turn(1);
						System.out.println("Rechts abgebogen!");
						Motor.C.stop();
						break;
					}
					if (Button.ENTER.isPressed()){
						System.out.println("Geradeaus fahren...");
						Drive.turn(0);
						System.out.println("Geradeaus gefahren!");
						Motor.C.stop();
						break;
					}
				}
		}
	}
	
	public static void main(String[] args){
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);

		Drive.kalibrieren(c1,c2);
		run();
	}
}
