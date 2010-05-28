import lejos.nxt.*;
import lejos.nxt.addon.*;

public class Linie {

	public static void run() {
		while(!Button.ESCAPE.isPressed()){
				Drive.moveToNextCrossing();
				System.out.println("Kreuzung!");
				
				while(!Button.ESCAPE.isPressed()){
					if (Button.RIGHT.isPressed()){
						System.out.println("Links abbiegen...");
						Drive.turn(-1);
						System.out.println("Links abgebogen!");
						break;
					}
					if (Button.LEFT.isPressed()){
						System.out.println("Rechts abbiegen...");
						Drive.turn(1);
						System.out.println("Rechts abgebogen!");
						break;
					}
					if (Button.ENTER.isPressed()){
						System.out.println("Geradeaus fahren...");
						Drive.turn(0);
						System.out.println("Geradeaus gefahren!");
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
