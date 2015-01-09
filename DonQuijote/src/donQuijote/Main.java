package donQuijote;

import ai.Examiner;
import ai.ObjectFinder;
import lejos.hardware.Button;
import motors.Motors;
import motors.Movable;

/**
 * Main class for the robot. Initiates the program.
 *
 */
public class Main {
	
	private static Movable motors;
	private static ObjectFinder objFinder;
	private static Examiner examiner;

	public static void main(String[] args) {
		initiateClasses();
		findAndExamineTargets();
		motors.closeMotors();
		printNoTargetsLeft();
	}
	
	private static void initiateClasses() {
		motors = new Motors();
		objFinder = new ObjectFinder(motors);
		examiner = new Examiner(motors, objFinder);
	}
	
	private static void findAndExamineTargets() {
		while (objFinder.findNearestObject() != -1) {
			examiner.examineTarget();
		}
	}
	
	private static void printNoTargetsLeft() {
		Button.LEDPattern(2);
		LcdPrinter.draw("No targets left!");
		while (!Button.DOWN.isDown()) continue;
		Button.LEDPattern(0);
	}

}
