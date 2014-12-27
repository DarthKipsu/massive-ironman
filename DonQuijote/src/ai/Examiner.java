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
		while (objFind.measureDistance() > 8 && distance < 50) {
			while (objFind.measureDistance() > 50) move.rotateLeft(2);
			move.moveForward(1);
			distance++;
		}
		int colorCode = color.measureColor();
		while ((colorCode == -1 || colorCode > 7) && objFind.measureDistance() < 20) {
			move.rotateLeft(1);
			colorCode = color.measureColor();
		}
		if (colorCode == -1 || colorCode > 7) move.rotateRight(4);
		while ((colorCode == -1 || colorCode > 7) && objFind.measureDistance() < 20) {
			move.rotateRight(1);
			colorCode = color.measureColor();
		}
		reactToColor(colorCode);
		color.turnOffFloodlight();
		head.contractFully();
		move.moveBackward(distance);
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

}
