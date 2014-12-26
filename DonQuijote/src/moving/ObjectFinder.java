package moving;

import donQuijote.LcdPrinter;
import lejos.hardware.Button;
import ai.Memory;
import sensors.IRSample;
import sensors.IRSensor;

public class ObjectFinder {
	
	private Moving move;
	private Memory memory;
	private int currentRot;
	private IRSensor iR;
	private IRSample nearestTarget;
	
	public ObjectFinder(Moving move, Memory memory) {
		this.move = move;
		this.memory = memory;
		currentRot = 0;
		iR = new IRSensor();
	}
	
	public int findNearestObject() {
		if (nearestTarget == null) {
			resetNearest();
			rotateToFindNearest();
			memory.analyzeData();
		}
		if (rotateTowardsNearest()) {
			return nearestTarget.getNearestDist();
		} else return -1;
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
		nearestTarget = memory.getDirection();
		if (nearestTarget == null) {
			printNoTargetsLeft();
			return false;
		}
		int nearestDeg = nearestTarget.getNearestDegree();
		
		if (nearestDeg < currentRot - 180) nearestDeg += 360;
		if (nearestDeg > currentRot && nearestDeg < currentRot + 180) {
			move.rotateLeft(nearestDeg - currentRot);
		} else {
			if (nearestDeg > currentRot + 180) currentRot -= 360;
			move.rotateRight(Math.abs(currentRot - nearestDeg));
		}
		currentRot = nearestTarget.getNearestDegree();
		return true;
	}
	
	private static void printNoTargetsLeft() {
		Button.LEDPattern(2);
		LcdPrinter.draw("No targets left!");
		while (!Button.DOWN.isDown()) continue;
		Button.LEDPattern(0);
	}

}
