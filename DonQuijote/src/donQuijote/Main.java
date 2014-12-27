package donQuijote;

import ai.Examiner;
import lejos.hardware.Button;
import moving.Moving;
import moving.ObjectFinder;

public class Main {
	
	private static Moving move;
	private static ObjectFinder objFinder;
	private static Examiner examiner;

	public static void main(String[] args) {
		initiateClasses();
		findAndExamineTargets();
		move.closeMotors();
		printNoTargetsLeft();
	}
	
	private static void initiateClasses() {
		move = new Moving();
		objFinder = new ObjectFinder(move);
		examiner = new Examiner(move, objFinder);
	}
	
	private static void findAndExamineTargets() {
		while (objFinder.findNearestObject() != -1) {
			examiner.examineTargetAt();
		}
	}
	
	private static void printNoTargetsLeft() {
		Button.LEDPattern(2);
		LcdPrinter.draw("No targets left!");
		while (!Button.DOWN.isDown()) continue;
		Button.LEDPattern(0);
	}

}
