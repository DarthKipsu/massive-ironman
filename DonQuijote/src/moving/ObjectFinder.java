package moving;

import lejos.hardware.Sound;
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
		nearestTarget = memory.getDirection();
		if (nearestTarget == null) return -1;
		rotateTowardsNearest();
		return nearestTarget.getNearestDist();
	}
	
	private void findTargets() {
		memory.resetMemory();
		rotateToFindNearest();
		memory.analyzeData();
		for (int i=0; i<memory.getTargetSize(); i++) Sound.beep();
	}
	
	private void rotateToFindNearest() {
		for	(int i = 0; i < 360 / 5; i++) {
			move.rotateLeft(5);
			memory.addValues(iR.measureDistance(), i);
		}
	}
	
	private boolean rotateTowardsNearest() {
		rotate();
		adjustPosition();
		updateCurrentDeg();
		return true;
	}
	
	private void rotate() {
		int nearestDeg = setNearestDegree();
		if (targetOnLeftSide(nearestDeg)) {
			move.rotateLeft(nearestDeg - currentDeg);
		} else {
			adjustCurrentDegIfNeeded(nearestDeg);
			move.rotateRight(Math.abs(currentDeg - nearestDeg));
		}
	}
	
	private int setNearestDegree() {
		int nearestDeg = nearestTarget.getNearestDegree();
		if (nearestDeg < currentDeg - 180) nearestDeg += 360;
		return nearestDeg;
	}
	
	private boolean targetOnLeftSide(int nearestDeg) {
		return nearestDeg > currentDeg && nearestDeg < currentDeg + 180;
	}
	
	private void adjustCurrentDegIfNeeded(int nearestDeg) {
		if (nearestDeg > currentDeg + 180) currentDeg -= 360;
	}
	
	private void adjustPosition() {
		int distance = iR.measureDistance();
		int fix = 3;
		while (distance >= 50) {
			rotateFor(fix);
			distance = iR.measureDistance();
			fix += 3;
		}
		nearestTarget.setNearestDist(iR.measureDistance());
	}
	
	private void rotateFor(int fix) {
		if (fix % 2 == 0) move.rotateRight(fix);
		else move.rotateLeft(fix);
	}
	
	private void updateCurrentDeg() {
		currentDeg = nearestTarget.getNearestDegree();
	}
}
