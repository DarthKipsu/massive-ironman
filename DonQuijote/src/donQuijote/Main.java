package donQuijote;

import ai.Examiner;
import moving.Moving;
import moving.ObjectFinder;

public class Main {

	public static void main(String[] args) {
		Moving move = new Moving();
		ObjectFinder objFinder = new ObjectFinder(move);
		Examiner examiner = new Examiner(move);
		
		int distance = objFinder.findNearestObject();
		examiner.examineTargetAt(distance);
	}

}
