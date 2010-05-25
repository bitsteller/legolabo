import lejos.nxt.*;
import lejos.nxt.addon.*;
public class test {

	public static void main(String[] args) {
		/*Motor.B.forward();
		LCD.drawString("forward", 0, 0);
		Button.waitForPress();
		Motor.B.setSpeed(700);
		LCD.drawString("speed", 0, 1);
		Button.waitForPress();*/
    ColorSensor color = new ColorSensor(SensorPort.S1);
		LCD.drawInt(color.initBlackLevel(), 4, 0, 0);
		Button.ENTER.waitForPress();

		while (true) {
      LCD.drawInt(color.getColorNumber(), 4, 0, 0);
		}
	}

}
