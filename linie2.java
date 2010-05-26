import lejos.nxt.*;
import lejos.nxt.addon.*;

public class linie2 {

	private static int schwelle = 100;

	public static void run() {
		while(!Button.ESCAPE.isPressed()){
			System.out.println("Bitte Enter zum Starten!");
			if (Button.ENTER.isPressed()) {
				moveToNextCrossing();
				System.out.println("Kreuzung!");
				turn(-1);
				System.out.println("Links abgebogen!");
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
		
		 
		while(!Button.ENTER.isPressed()){
			int cn1 = c1.getColor()[0] + c1.getColor()[1] + c1.getColor()[2];
			int cn2 = c2.getColor()[0] + c2.getColor()[1] + c2.getColor()[2];

			LCD.drawString("Wert1: " + cn1 , 0, 0);
			LCD.drawString("Wert2: " + cn2 , 0, 2);
			
			if(cn1 > schwelle) Motor.A.forward();
			if(cn2 > schwelle) Motor.B.forward();
			if(cn1 < schwelle) Motor.A.stop();
			if(cn2 < schwelle) Motor.B.stop();		
			
			if(cn1 < schwelle && cn2 < schwelle) {
				Motor.A.stop();	
				Motor.B.stop();
				return;
			}
		}
	}
	
	public static void turn(int direction) {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		
		Motor.A.setSpeed(150);
		Motor.B.setSpeed(150);
		Motor.A.forward();
		Motor.B.forward();
		
		if(isBlack(c1) && isBlack(c2)) {
			Motor.A.stop();	
			Motor.B.stop();
			return;
		}
		
		if (direction == -1) {
			Motor.B.rotate(60);
			while(isBlack(c1)){
				Motor.A.forward();
			}
			Motor.A.stop();
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
