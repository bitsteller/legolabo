import lejos.nxt.*;
import lejos.nxt.addon.*;
public class Parallel ruimplements Runnable {

	private ColorSensor cs;
	

	public void run() {
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		
		while(!Button.ENTER.isPressed()){
			if(c1.getColor
		}
	}

	public Parallel(ColorSensor cs, int tNo){
		this.cs = cs;
		this.tNo = tNo;
	}
	
	public static void main(String[] args){
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);
		
		Thread t1 = new Thread(new Parallel(c1,1));
		Thread t2 = new Thread(new Parallel(c2,2));
		
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
		
		t1.start();
		t2.start();
		
		System.out.println("Wollen sie beenden?");
		Button.ENTER.waitForPress();
	}
}
