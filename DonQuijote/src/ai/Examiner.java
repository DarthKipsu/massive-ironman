package ai;

import sensors.ColorSensor;
import sensors.ColorSensorImpl;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import motors.Head;
import motors.HeadImpl;
import motors.Movable;

public class Examiner {
	
	private Movable move;
	private ObjectFinder objFind;
	private ColorSensor color;
	private Head head;
	private int distance;
	private int colorCode;
	
	public Examiner(Movable move, ObjectFinder of, ColorSensor cs, Head head) {
		this.move = move;
		this.objFind = of;
		this.head = head;
		color = cs;
	}
	
	public Examiner(Movable move, ObjectFinder of) {
		this(move, of, new ColorSensorImpl(), new HeadImpl());
	}
	
	public void examineTarget() {
		activateHead();
		move.rotateLeft(4);
		distance = 0;
		advanceTowardsTarget();
		colorCode = color.measureColor();
		repositionToDetectColor();
		reactToColor(colorCode);
		deactivateHead();
		move.moveBackward(distance);
	}
	
	private void activateHead() {
		head.prolongToDefault();
		color.turnOnFloodlight();
	}
	
	private void advanceTowardsTarget() {
		while (objFind.measureDistance() >= 12 && distance < 50) {
			int movement = (int)Math.sqrt(objFind.measureDistance());
			move.moveForward(movement);
			distance += movement;
		}
	}
	
	private void repositionToDetectColor() {
		rotateToDirection(1);
		if (colorNotDetected()) move.rotateRight(4);
		rotateToDirection(-1);
	}
	
	private void rotateToDirection(int direction) {
		while (colorNotDetected() && objectStillInSight()) {
			move.rotateLeft(direction);
			colorCode = color.measureColor();
		}
	}
	
	private boolean colorNotDetected() {
		return (colorCode == -1 || colorCode > 7);
	}
	
	private boolean objectStillInSight() {
		return objFind.measureDistance() < 20;
	}
	
	private void reactToColor(int colorCode) {
		switch (colorCode) {
		case 0:
			System.out.println("No color");
			break;
		case 1:
			System.out.println("Black");
			break;
		case 2:
			System.out.println("Blue");
			attackFromRightSide();
			break;
		case 3:
			System.out.println("Green");
			shiver();
			break;
		case 4:
			System.out.println("Yellow");
			break;
		case 5:
			System.out.println("Red: attack");
			attackWithHead();
			break;
		case 6:
			System.out.println("White: ignore");
			ignoreTarget();
			break;
		case 7:
			System.out.println("Brown: attack");
			attackWithHead();
			break;
		default:
			Sound.buzz();
			System.out.println("Color " + colorCode + " not known");
			break;
		}
	}
	
	private void attackFromRightSide() {
		head.contractFully();
		move.rotateRight(20);
		move.moveForward(5);
		head.prolongToDefault();
		move.rotateLeft(40);
		move.moveBackward(5);
		move.rotateRight(20);
	}
	
	private void shiver() {
		head.contractFully();
		move.moveBackward(distance / 2);
		distance /= 2;
		move.rotateLeft(4);
		move.rotateRight(8);
		move.rotateLeft(8);
		move.rotateRight(4);
	}
	
	private void attackWithHead() {
		Button.LEDPattern(2);
		head.contractFully();
		move.moveForward(3);
		head.attack();
		move.moveBackward(3);
		Button.LEDPattern(0);
	}
	
	private void ignoreTarget() {
		head.contractFully();
	}
	
	private void deactivateHead() {
		color.turnOffFloodlight();
		head.contractFully();
	}

}
