package donQuijote;

import ai.Examiner;
import ai.Memory;
import lejos.hardware.Button;
import moving.Moving;
import moving.ObjectFinder;

public class Main {

	public static void main(String[] args) {
		Moving move = new Moving();
		Memory memory = new Memory();
		ObjectFinder objFinder = new ObjectFinder(move, memory);
		Examiner examiner = new Examiner(move);
		
		while (true) {
			int distance = objFinder.findNearestObject();
			if (distance == -1) {
				move.closeMotors();
				printNoTargetsLeft();
				break;
			} else {
				examiner.examineTargetAt(distance/2);
				move.moveBackward(distance/2);
			}
		}
	}
	
	private static void printNoTargetsLeft() {
		Button.LEDPattern(2);
		LcdPrinter.draw("No targets left!");
		while (!Button.DOWN.isDown()) continue;
		Button.LEDPattern(0);
	}

}
