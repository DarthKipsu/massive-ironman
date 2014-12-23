package moving;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Moving {
	
	private RegulatedMotor rightMotor;
	private RegulatedMotor leftMotor;
	private double tireSpacing;
	private double travelPerDegree;
	
	public Moving() {
		rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		tireSpacing = 35;
		travelPerDegree = 12.4;
	}
	
	public void moveForward(int centimeters) {
		int movement = (int)Math.round(centimeters / travelPerDegree * 360.0);
		move(movement);
	}
	
	public void moveBackward(int centimeters) {
		int movement = (int)Math.round(centimeters / travelPerDegree * 360.0);
		move(-movement);
	}
	
	public void rotateLeft(int degrees) {
		int rotation = (int)Math.round(tireSpacing * Math.PI / (360 / degrees) / travelPerDegree * 360.0);
		rotate(-rotation);
	}
	
	public void rotateRight(int degrees) {
		int rotation = (int)Math.round(tireSpacing * Math.PI / (360 / degrees) / travelPerDegree * 360.0);
		rotate(rotation);
	}
	
	public void closeMotors() {
		leftMotor.close();
		rightMotor.close();
	}
	
	private void move(int movement) {
		leftMotor.rotate(movement, true);
		rightMotor.rotate(movement);
	}
	
	private void rotate(int rotation) {
		leftMotor.rotate(rotation, true);
		rightMotor.rotate(-rotation);
	}

}
