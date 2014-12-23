package moving;

import lejos.utility.Delay;
import donQuijote.LcdPrinter;
import sensors.IRSensor;

public class ObjectFinder {
	
	private Moving move;
	private IRSensor iR;
	
	public ObjectFinder() {
		move = new Moving();
		iR = new IRSensor();
	}
	
	public boolean findNearestObject() {
		int nearestDegree = 0;
		int nearestDist = 100;
		for	(int i = 0; i < 360 / 5; i++) {
			move.rotateLeft(5);
			int distance = iR.measureDistance();
			if (distance < nearestDist) {
				nearestDist = distance;
				nearestDegree = i;
			}
		}
		move.rotateLeft(nearestDegree * 5);
		LcdPrinter.draw(nearestDegree + " deg. (" + nearestDist + ")");
		Delay.msDelay(10000);
		return nearestDist < 100;
	}

}
