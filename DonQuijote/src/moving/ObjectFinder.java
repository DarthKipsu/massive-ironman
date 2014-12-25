package moving;

import sensors.IRSensor;

public class ObjectFinder {
	
	private Moving move;
	private IRSensor iR;
	private int nearestDegree;
	private int nearestDist;
	
	public ObjectFinder(Moving move) {
		this.move = move;
		iR = new IRSensor();
	}
	
	public int findNearestObject() {
		resetNearest();
		rotateToFindNearest();
		rotateTowardsNearest();
		return nearestDist;
	}
	
	private void resetNearest() {
		nearestDegree = 0;
		nearestDist = 100;
	}
	
	private void rotateToFindNearest() {
		for	(int i = 0; i < 360 / 5; i++) {
			move.rotateLeft(5);
			updateNEarestIfNeeded(iR.measureDistance(), i);
		}
	}
	
	private void updateNEarestIfNeeded(int distance, int i) {
		if (distance < nearestDist) {
			nearestDist = distance;
			nearestDegree = i * 5;
		}
	}
	
	private void rotateTowardsNearest() {
		move.rotateLeft(nearestDegree);
	}

}
