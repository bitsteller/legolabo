import lejos.nxt.*;
public class Parallel implements Runnable {

	private ColorSensor cs;
	private int tNo;
	

	public void run(){
		while(!Button.ENTER.isPressed()){
		 	if(tNo = 1) {
				LCD.drawString("Wert1: " + cs.getColorNumber() , 0, 0);
			} else if (tNo = 2) {
				LCD.drawString("Wert2: " + cs.getColorNumber() , 0, 5);
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
		
		t1.start();
		t2.start();
		
		System.out.Println("Wollen sie beenden?");
		Button.ENTER.waitForPress();
	}
}
