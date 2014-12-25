package donQuijote;

import lejos.utility.Delay;
import moving.Head;
import moving.ObjectFinder;

public class Main {

	public static void main(String[] args) {
		ObjectFinder of = new ObjectFinder();
		Head head = new Head();
		head.prolongToDefault();
		of.findNearestObject();
		Delay.msDelay(1000);
		head.contractFully();
	}

}
