import lejos.nxt.*;
import lejos.nxt.addon.*;
public class Linie implements Runnable {

	private ColorSensor cs;
	private int tNo;
	

	public void run() {
		while(!Button.ENTER.isPressed()){
			pilot.forward();
			if(tNo == 1 && cs.getColorNumber() == 0){
			 pilot.rotate(
			}
			if(tNo == 2 && cs.getColorNumber() == 0){
			
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

	public Linie(ColorSensor cs, int tNo){
		this.cs = cs;
		this.tNo = tNo;
	}
	
	public static void main(String[] args){
		//c1 ist der Rechte Sensor
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		Pilot pilot = new TachoPilot(4.4f,4.4f,Motor.B,Motor.A);

		kalibrieren(c1,c2);

		Thread t1 = new Thread(new Linie(c1,1));
		Thread t2 = new Thread(new Linie(c2,2));

		t1.start();
		t2.start();
		
		System.out.println("Wollen sie beenden?");
		Button.ENTER.waitForPress();
	}
}
