package ai;

import motors.Movable;
import sensors.IRSensor;
import sensors.IRSensorImpl;

public class ObjectFinder {
	
	private Movable move;
	private int currentDeg;
	private IRSensor iR;
	
	public ObjectFinder(Movable move, IRSensor irs) {
		this.move = move;
		currentDeg = 0;
		iR = irs;
	}
	
	public ObjectFinder(Movable move) {
		this(move, new IRSensorImpl());
	}
	
	public int measureDistance() {
		return iR.measureDistance();
	}
	
	public int findNearestObject() {
		if (atStartingLocation()) {
			int distance = iR.measureDistance();
			if (distance < 50) return distance;
		} else {
			rotateUntilCurrentTargetNoLongerInSight();
		}
		return distanceToNextTarget();
	}
	
	private boolean atStartingLocation() {
		return currentDeg == 0;
	}
	
	private void rotateUntilCurrentTargetNoLongerInSight() {
		while (iR.measureDistance() < 50) {
			move5degrees();
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
