package mocks;

import motors.Movable;

public class MovableMock implements Movable {
	
	private int movementForward;
	private int movementBackward;
	private double rotatedLeft;
	private double rotatedRight;
	
	public MovableMock() {
		movementForward = 0;
		movementBackward = 0;
		rotatedLeft = 0;
		rotatedRight = 0;
	}
	
	public int getMovementForward() {
		return movementForward;
	}
	
	public int getMovementBackward() {
		return movementBackward;
	}
	
	public double getRotatedLeft() {
		return rotatedLeft;
	}
	
	public double getRotatedRight() {
		return rotatedRight;
	}

	@Override
	public void moveForward(int cm) {
		movementForward += cm;
	}

	@Override
	public void moveBackward(int cm) {
		movementBackward += cm;
	}

	@Override
	public void rotateLeft(double degrees) {
		rotatedLeft += degrees;
	}

	@Override
	public void rotateRight(int degrees) {
		rotatedRight += degrees;
	}

	@Override
	public void closeMotors() {
	}

}
