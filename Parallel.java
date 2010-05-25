import lejos.nxt.*;
import lejos.nxt.addon.*;
public class Parallel implements Runnable {

	private ColorSensor cs;
	private int tNo;
	

	public void run() {
		while(!Button.ENTER.isPressed()){
			LCD.clearDisplay();
		 	if(tNo == 1) {
				LCD.drawString("Wert1: " + cs.getColorNumber() , 0, 0);
			} else if (tNo == 2) {
				LCD.drawString("Wert2: " + cs.getColorNumber() , 0, 2);
			}
			try {
				Thread.sleep(200);
			}
			catch(InterruptedException e) {
			}
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
		
		System.out.println("auf Schwarz stellen und Enter");
		
		Button.ENTER.waitForPress();
		c1.initBlackLevel();
		c2.initBlackLevel();
		
		LCD.clear();
		System.out.println("Kalibriert bitte Enter");
		Button.ENTER.waitForPress();
		
		
		t1.start();
		t2.start();
		
		System.out.println("Wollen sie beenden?");
		Button.ENTER.waitForPress();
	}
}
