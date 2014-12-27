package ai;

import sensors.ColorSensor;
import sensors.ColorSensorImpl;
import lejos.hardware.Sound;
import moving.Head;
import moving.HeadImpl;
import moving.Movable;
import moving.ObjectFinder;

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
	
	public void examineTargetAt() {
		activateHead();
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
			while (objFind.measureDistance() > 50) move.rotateLeft(2);
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
			System.out.println("No color detected");
			break;
		case 1:
			System.out.println("Black tower");
			break;
		case 2:
			System.out.println("Blue skies");
			break;
		case 3:
			System.out.println("The other side of the fence");
			break;
		case 4:
			System.out.println("Yellowish");
			break;
		case 5:
			System.out.println("Red alert!");
			break;
		case 6:
			System.out.println("White flag");
			break;
		case 7:
			System.out.println("Brown teddy");
			break;
		default:
			Sound.buzz();
			System.out.println(colorCode);
			break;
		}
	}
	
	private void deactivateHead() {
		color.turnOffFloodlight();
		head.contractFully();
	}

}
