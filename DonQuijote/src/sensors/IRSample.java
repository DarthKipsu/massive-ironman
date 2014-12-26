package sensors;

public class IRSample implements Comparable<IRSample>{

	private int nearestDegree;
	private int nearestDist;
	
	public IRSample(int nearestDist, int nearestDegree) {
		this.nearestDist = nearestDist;
		this.nearestDegree = nearestDegree;
	}
	
	public int getNearestDegree() {
		return nearestDegree;
	}
	
	public int getNearestDist() {
		return nearestDist;
	}

	@Override
	public int compareTo(IRSample sample2) {
		return nearestDist - sample2.nearestDist;
	}
}
