package moving;

import ai.Memory;
import sensors.IRSample;
import sensors.IRSensor;

public class ObjectFinder {
	
	private Moving move;
	private Memory memory;
	private int currentDeg;
	private IRSensor iR;
	private IRSample nearestTarget;
	
	public ObjectFinder(Moving move, Memory memory) {
		this.move = move;
		this.memory = memory;
		currentDeg = 0;
		iR = new IRSensor();
	}
	
	public int findNearestObject() {
		if (nearestTarget == null) findTargets();
		if (rotateTowardsNearest()) return nearestTarget.getNearestDist();
		else return -1;
	}
	
	private void findTargets() {
		resetNearest();
		rotateToFindNearest();
		memory.analyzeData();
	}
	
	private void resetNearest() {
		memory.resetMemory();
	}
	
	private void rotateToFindNearest() {
		for	(int i = 0; i < 360 / 5; i++) {
			move.rotateLeft(5);
			addSampleToMemory(iR.measureDistance(), i);
		}
	}
	
	private void addSampleToMemory(int distance, int i) {
		memory.addValues(distance, i);
	}
	
	private boolean rotateTowardsNearest() {
		updateNearestTarget();
		if (nearestTarget == null) return false;
		rotate();
		updateCurrentDeg();
		updateTargetDistance();
		return true;
	}
	
	private void updateNearestTarget() {
		nearestTarget = memory.getDirection();
	}
	
	private int setNearestDegrees() {
		int nearestDeg = nearestTarget.getNearestDegree();
		if (nearestDeg < currentDeg - 180) nearestDeg += 360;
		return nearestDeg;
	}
	
	private void rotate() {
		int nearestDeg = setNearestDegrees();
		if (targetOnLeftSide(nearestDeg)) {
			move.rotateLeft(nearestDeg - currentDeg);
		} else {
			adjustCurrentDegIfNeeded(nearestDeg);
			move.rotateRight(Math.abs(currentDeg - nearestDeg));
		}
	}
	
	private boolean targetOnLeftSide(int nearestDeg) {
		return nearestDeg > currentDeg && nearestDeg < currentDeg + 180;
	}
	
	private void adjustCurrentDegIfNeeded(int nearestDeg) {
		if (nearestDeg > currentDeg + 180) currentDeg -= 360;
	}
	
	private void updateCurrentDeg() {
		currentDeg = nearestTarget.getNearestDegree();
	}
	
	private void updateTargetDistance() {
		nearestTarget.setNearestDist(iR.measureDistance());
	}

}
