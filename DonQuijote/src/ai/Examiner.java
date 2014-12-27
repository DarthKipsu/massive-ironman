package ai;

import sensors.ColorSensor;
import lejos.hardware.Sound;
import moving.Head;
import moving.Moving;
import moving.ObjectFinder;

public class Examiner {
	
	private Moving move;
	private ObjectFinder objFind;
	private ColorSensor color;
	private Head head;
	
	public Examiner(Moving move, ObjectFinder objFind) {
		this.move = move;
		this.objFind = objFind;
		color = new ColorSensor();
		head = new Head();
	}
	
	public void examineTargetAt() {
		head.prolongToDefault();
		color.turnOnFloodlight();
		int distance = 0;
		while (objFind.measureDistance() > 8) {
			move.moveForward(1);
			distance++;
		}
		System.out.println("Color: " + color.measureColor());
		reactToColor(color.measureColor());
		color.turnOffFloodlight();
		head.contractFully();
		move.moveBackward(distance);
	}
	
	private void reactToColor(int color) {
		switch (color) {
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
			break;
		}
	}

}
