package ai;

import sensors.ColorSensor;
import sensors.ColorSensorImpl;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import motors.Head;
import motors.HeadImpl;
import motors.Movable;

/**
 * Used to examine targets and react to them based on color of the
 * target.
 * 
 * The robot needs to be facing towards the target, before calling
 * exaineTarget() method. It will then move close enough to detect
 * the target color, act accordingly and then move back to the location
 * it started from.
 *
 */
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
		adjustLocationBeforeExamining();
		advanceTowardsTarget();
		repositionToDetectColor();
		reactToColor();
		deactivateHead();
		moveBackToWhereYouStartedFrom();
	}
	
	private void activateHead() {
		head.prolongToDefault();
		color.turnOnFloodlight();
	}
	
	private void adjustLocationBeforeExamining() {
		move.rotateLeft(4);
		distance = 0;
	}
	
	private void advanceTowardsTarget() {
		while (objFind.measureDistance() >= 12 && distance < 50) {
			int movement = (int)Math.sqrt(objFind.measureDistance());
			move.moveForward(movement);
			distance += movement;
		}
	}
	
	private void repositionToDetectColor() {
		colorCode = color.measureColor();
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
	
	private void reactToColor() {
		switch (colorCode) {
		case 0:
			System.out.println("No color: full circle");
			attackWithFulCircle();
			break;
		case 1:
			System.out.println("Black: attack from left");
			attackFromLeftSide();
			break;
		case 2:
			System.out.println("Blue: attack from right");
			attackFromRightSide();
			break;
		case 3:
			System.out.println("Green: ram");
			ram();
			break;
		case 4:
			System.out.println("Yellow: shiver");
			shiver();
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
			System.out.println("Color " + colorCode + " not known");
			Sound.buzz();
			break;
		}
	}
	
	private void attackWithFulCircle() {
		beginAttackFrom(20);
		move.rotateRight(360);
		move.rotateRight(30);
	}
	
	private void beginAttackFrom(int rotation) {
		head.contractFully();
		move.rotateLeft(rotation);
		move.moveForward(5);
		distance += 5;
		head.prolongToSideAttack();
	}
	
	private void attackFromLeftSide() {
		beginAttackFrom(20);
		move.rotateRight(40);
		move.rotateLeft(20);
	}
	
	private void attackFromRightSide() {
		beginAttackFrom(-20);
		move.rotateLeft(40);
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
	
	private void ram() {
		move.moveForward(20);
		distance += 20;
	}
	
	private void attackWithHead() {
		Button.LEDPattern(2);
		head.contractFully();
		move.moveForward(3);
		head.attackForward();
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
	
	private void moveBackToWhereYouStartedFrom() {
		move.moveBackward(distance);
	}

}
