package ai;

import moving.Head;
import moving.Moving;

public class Examiner {
	
	private Moving move;
	private Head head;
	
	public Examiner(Moving move) {
		this.move = move;
		head = new Head();
	}
	
	public void examineTargetAt(int distance) {
		head.prolongToDefault();
		move.moveForward(distance);
		head.contractFully();
	}

}
