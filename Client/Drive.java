import lejos.nxt.*;
import lejos.nxt.addon.*;

public class Drive {

	private static final int SCHWELLE = 100;
	
	public static void moveToNextCrossing() {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		
		Motor.A.setSpeed(320);
		Motor.B.setSpeed(320);
		Motor.A.forward();
		Motor.B.forward();
		
		while(true) {
			int cn1 = c1.getColor()[0] + c1.getColor()[1] + c1.getColor()[2];
			int cn2 = c2.getColor()[0] + c2.getColor()[1] + c2.getColor()[2];

			if(cn1 > SCHWELLE) Motor.A.forward();
			if(cn2 > SCHWELLE) Motor.B.forward();
			if(cn1 < SCHWELLE) Motor.A.flt();
			if(cn2 < SCHWELLE) Motor.B.flt();

			if(cn1 < SCHWELLE && cn2 < SCHWELLE) {
				Motor.A.stop();
				Motor.B.stop();
				Motor.C.forward();
				Sound.setVolume(100);
				Sound.playNote(Sound.FLUTE,440,500);
				return;
			}
		}
	}
	
	public static void turn(int direction) {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		
		Motor.A.setSpeed(400);
		Motor.B.setSpeed(400);
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
		Motor.C.stop();
	}
	
	public static boolean isBlack (ColorSensor cs) {
		int cn = cs.getColor()[0] + cs.getColor()[1] + cs.getColor()[2];
		if (cn < SCHWELLE) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void reverse() {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		Motor.A.setSpeed(280);
		Motor.B.setSpeed(280);
		Motor.A.resetTachoCount();
		while(Motor.A.getTachoCount() < 150){
			Motor.A.forward();
			Motor.B.backward();
		}
		
		while(!isBlack(c2)){
			Motor.A.forward();
		}
		while(isBlack(c2)){
			Motor.A.forward();
		}
		Motor.A.stop();
		Motor.C.stop();
	}
	
	public static void kalibrieren(ColorSensor c1, ColorSensor c2) {

		Sound.setVolume(100);
		Sound.playNote(Sound.PIANO,440,500);

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
		LCD.clear();
		Sound.setVolume(100);
		Sound.playNote(Sound.PIANO,440,500);
	}

}
