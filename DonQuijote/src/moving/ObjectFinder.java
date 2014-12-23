package moving;

public class ObjectFinder {
	
	private Moving move;
	
	public ObjectFinder() {
		move = new Moving();
	}
	
	public boolean findNearestObject() {
		for	(int i = 0; i < 360 / 5; i++) {
			move.rotateLeft(5);
		}
		return true;
	}

}
