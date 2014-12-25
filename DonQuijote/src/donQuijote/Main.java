package donQuijote;

import lejos.utility.Delay;
import moving.Head;
import moving.ObjectFinder;

public class Main {

	public static void main(String[] args) {
		ObjectFinder of = new ObjectFinder();
		Head head = new Head();
		of.findNearestObject();
		head.prolongToDefault();
		Delay.msDelay(1000);
		head.contractFully();
	}

}
