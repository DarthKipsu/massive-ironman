package moving;

import sensors.IRSensor;

public class ObjectFinder {
	
	private Moving move;
	private int currentDeg;
	private IRSensor iR;
	
	public ObjectFinder(Moving move) {
		this.move = move;
		currentDeg = 0;
		iR = new IRSensor();
	}
	
	public int findNearestObject() {
		return rotateToFindNearest();
	}
	
	public int measureDistance() {
		return iR.measureDistance();
	}
	
	private int rotateToFindNearest() {
		if (currentDeg == 0) {
			if (iR.measureDistance() < 50) return iR.measureDistance();
		} else {
			moveAwayFromCurrentTarget();
		}
		return distanceToNextTarget();
	}
	
	private void moveAwayFromCurrentTarget() {
		while (iR.measureDistance() < 50) {
			move.rotateLeft(5);
			currentDeg += 5;
		}
	}
	
	private int distanceToNextTarget() {
		while (currentDeg < 360) {
			move5degrees();
			if (iR.measureDistance() < 50) {
				move5degrees();
				return iR.measureDistance();
			}
		}
		return -1;
	}
	
	private void move5degrees() {
		move.rotateLeft(5);
		currentDeg += 5;
	}
}
