import lejos.nxt.*;
import lejos.nxt.addon.*;
import lejos.nxt.remote.*;
import lejos.nxt.comm.*;

public class client {

	public static void run() {
		NXTConnection connection = Bluetooth.waitForConnection();
	}

	public static void main(String[] args){
		ColorSensor c1 = new ColorSensor(SensorPort.S2);
		ColorSensor c2 = new ColorSensor(SensorPort.S3);

		drive.kalibrieren(c1,c2);
		run();
	}

}
