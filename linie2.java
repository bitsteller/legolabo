import lejos.nxt.*;
import lejos.nxt.addon.*;

public class linie2 {

	private static int schwelle = 100;

	public static void run() {
		while(!Button.ESCAPE.isPressed()){
				moveToNextCrossing();
				System.out.println("Kreuzung!");
				Motor.C.forward();
				Sound.setVolume(75);		//für Wettbewerb 100 setzen
				Sound.playNote(Sound.FLUTE,440,500);
				
				while(!Button.ESCAPE.isPressed()){
					if (Button.RIGHT.isPressed()){
						System.out.println("Links abbiegen...");
						turn(-1);
						System.out.println("Links abgebogen!");
						Motor.C.stop();
						break;
					}
					if (Button.LEFT.isPressed()){
						System.out.println("Rechts abbiegen...");
						turn(1);
						System.out.println("Rechts abgebogen!");
						Motor.C.stop();
						break;
					}
					if (Button.ENTER.isPressed()){
						System.out.println("Geradeaus fahren...");
						turn(0);
						System.out.println("Geradeaus gefahren!");
						Motor.C.stop();
						break;
					}
				}
		}
	}
	
	public static void moveToNextCrossing() {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		
		Motor.A.setSpeed(300);
		Motor.B.setSpeed(300);
		Motor.A.forward();
		Motor.B.forward();
		
		while(!(isBlack(c1) && isBlack(c2))){
			if(!isBlack(c1)) Motor.A.forward();
			if(!isBlack(c2)) Motor.B.forward();
			if(isBlack(c1)) Motor.A.flt();
			if(isBlack(c2)) Motor.B.flt();		
		}
		
		Motor.A.stop();
		Motor.B.stop();
	}
	
	public static void turn(int direction) {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		
		Motor.A.setSpeed(200);
		Motor.B.setSpeed(200);
		Motor.A.forward();
		Motor.B.forward();
		
		while(isBlack(c1) && isBlack(c2)) {
			Motor.A.forward();	
			Motor.B.forward();
		}
		Motor.A.stop();
		Motor.B.stop();
		
		if (direction == -1) {
			Motor.A.resetTachoCount();
			while(Motor.A.getTachoCount() < 200){
				Motor.A.forward();
			}
			
			while(!isBlack(c2)){
				Motor.A.forward();
			}
			while(isBlack(c2)){
				Motor.A.forward();
			}
			Motor.A.stop();
		}
		
		if (direction == 1) {
			Motor.B.resetTachoCount();
			while(Motor.B.getTachoCount() < 200){
				Motor.B.forward();
			}
			
			while(!isBlack(c1)){
				Motor.B.forward();
			}
			while(isBlack(c1)){
				Motor.B.forward();
			}
			Motor.B.stop();
		}
	}
	
	public static boolean isBlack (ColorSensor cs) {
		int cn = cs.getColor()[0] + cs.getColor()[1] + cs.getColor()[2];
		if (cn < schwelle) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void kalibrieren(ColorSensor c1, ColorSensor c2) {

		// BlackBalance

		System.out.println("auf Schwarz stellen und Enter");
		
		Button.ENTER.waitForPress();
		c1.initBlackLevel();
		c2.initBlackLevel();
		
		LCD.clear();
		System.out.println("Kalibriert bitte Enter");
		Button.ENTER.waitForPress();

		// Clearen

		LCD.clear();

		// WhiteBalance

		System.out.println("auf Weiss stellen und Enter");
		
		Button.ENTER.waitForPress();
		c1.initWhiteBalance();
		c2.initWhiteBalance();
		
		LCD.clear();
		System.out.println("Kalibriert bitte Enter");
		Button.ENTER.waitForPress();

	}
	
	public static void main(String[] args){
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);

		kalibrieren(c1,c2);
		run();
	}
}
