package main;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class SensonrAndMotorTest {

	public static void main(String[] args) {
		testMotors();
		testIRSensor();
		Button.LEDPattern(2);
	}
	
	private static void testMotors() {
		Cruiser cruiser = new Cruiser();
		cruiser.moveForward(20);
		Delay.msDelay(100);
		cruiser.rotateLeft(90);
		Delay.msDelay(1000);
		cruiser.rotateRight(180);
		Delay.msDelay(1000);
		cruiser.rotateLeft(90);
		Delay.msDelay(100);
		cruiser.moveBackward(20);
		cruiser.closeMotors();
	}
	
	private static void testIRSensor() {
		Watcher watcher = new Watcher();
		watcher.printDistances();
		Button.LEDPattern(2);
		while (Button.DOWN.isDown()) continue;
		Button.LEDPattern(0);
		watcher.printSeek();
	}

}
