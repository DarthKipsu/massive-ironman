package moving;

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
