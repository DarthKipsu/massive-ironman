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
		while (iR.measureDistance() < 50) {
			move.rotateLeft(5);
			currentDeg += 5;
		}
		while (currentDeg < 360) {
			move.rotateLeft(5);
			currentDeg += 5;
			if (iR.measureDistance() < 50) {
				move.rotateLeft(5);
				currentDeg += 5;
				return iR.measureDistance();
			}
		}
		return -1;
	}
}
