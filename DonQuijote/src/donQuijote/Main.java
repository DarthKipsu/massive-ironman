package donQuijote;

import ai.Examiner;
import lejos.hardware.Button;
import moving.Moving;
import moving.ObjectFinder;

public class Main {

	public static void main(String[] args) {
		Moving move = new Moving();
		ObjectFinder objFinder = new ObjectFinder(move);
		Examiner examiner = new Examiner(move, objFinder);
		
		while (objFinder.findNearestObject() != -1) {
			examiner.examineTargetAt();
		}
		move.closeMotors();
		printNoTargetsLeft();
	}
	
	private static void printNoTargetsLeft() {
		Button.LEDPattern(2);
		LcdPrinter.draw("No targets left!");
		while (!Button.DOWN.isDown()) continue;
		Button.LEDPattern(0);
	}

}
