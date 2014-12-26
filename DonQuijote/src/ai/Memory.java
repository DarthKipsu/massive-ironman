package ai;

import java.util.PriorityQueue;

import sensors.IRSample;

public class Memory {

	private IRSample[] allSamples;
	private PriorityQueue<IRSample> targets;
	
	public Memory() {
		resetMemory();
	}
	
	public void addValues(int dist, int i) {
		allSamples[i] = new IRSample(dist, i * 5);
	}
	
	public IRSample getDirection() {
		if (targets.isEmpty()) return null;
		return targets.poll();
	}
	
	public void analyzeData() {
		int localSmallest = 100;
		int localDeg = 0;
		for (int i = 0; i < allSamples.length; i++) {
			if (allSamples[i].getNearestDist() < 50) {
				if (allSamples[i].getNearestDist() < localSmallest) {
					localSmallest = allSamples[i].getNearestDist();
					localDeg = allSamples[i].getNearestDegree();
				}
			} else if (localSmallest != 100) {
				targets.add(allSamples[localDeg/5]);
				localSmallest = 100;
			}
		}
	}
	
	public void resetMemory() {
		allSamples = new IRSample[72];
		targets = new PriorityQueue<>();
	}
}
