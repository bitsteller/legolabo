import lejos.nxt.*;
import lejos.nxt.addon.*;

public class linie2 {

	public static void run() {
		while(!Button.ESCAPE.isPressed()){
			moveToNextCrossing();
			System.out.println("Kreuzung erreicht.");
			Button.ENTER.waitForPress();
		}


	}
	
	public static void moveToNextCrossing() {
		ColorSensor c1 = new ColorSensor(SensorPort.S3);
		ColorSensor c2 = new ColorSensor(SensorPort.S2);
		
		Motor.A.setSpeed(100);
		Motor.B.setSpeed(100);
		Motor.A.forward();
		Motor.B.forward();
		
		 
		while(!Button.ENTER.isPressed()){
			LCD.drawString("Wert1: " + c1.getColorNumber() , 0, 0);
			LCD.drawString("Wert2: " + c2.getColorNumber() , 0, 2);
			
			if(c1.getColorNumber()!=0) Motor.A.forward();
			else if(c2.getColorNumber()!=0) Motor.B.forward();
			else if(c1.getColorNumber()==0) Motor.A.stop();
			else if(c2.getColorNumber()==0) Motor.B.stop();		
			
			if(c1.getColorNumber()==0 && c1.getColorNumber()==0) {
				Motor.A.stop();	
				Motor.B.stop();
				return;
			}
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
