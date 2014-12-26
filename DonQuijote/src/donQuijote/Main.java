package donQuijote;

import ai.Examiner;
import ai.Memory;
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
				break;
			} else {
				examiner.examineTargetAt(distance/2);
			}
		}
	}

}
