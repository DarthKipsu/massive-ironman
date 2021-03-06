package motors;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * Moves robot motors to make it move straight or rotate.
 *
 */
public class Motors implements Movable {
	
	private RegulatedMotor rightMotor;
	private RegulatedMotor leftMotor;
	private double tireSpacing;
	private double travelPerDegree;
	
	public Motors() {
		rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		tireSpacing = 20; // Increase if robot tires are further away from each other
		travelPerDegree = 14.6; // Increase if robot tire diameter is bigger
	}
	
	@Override
	public void moveForward(int centimeters) {
		int movement = (int)Math.round(centimeters / travelPerDegree * 360.0);
		move(movement);
	}

	@Override
	public void moveBackward(int centimeters) {
		moveForward(-centimeters);
	}
	
	@Override
	public void rotateLeft(double degrees) {
		int rotation = (int)Math.round(tireSpacing * Math.PI / (360 / degrees) / travelPerDegree * 360.0);
		rotate(rotation);
	}
	
	@Override
	public void rotateRight(int degrees) {
		rotateLeft(-degrees);
	}
	
	@Override
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
