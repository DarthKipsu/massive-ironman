package ai;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class MemoryTest {
	
	private Memory memory;
	private Random random;

	@Before
	public void setUp() {
		memory = new Memory();
		random = new Random();
		fillMemoryWithEmptyValues();
	}

	@Test
	public void findClosestObject() {
		memory.addValues(20, 10);
		memory.analyzeData();
		assertEquals(50, memory.getDirection().getNearestDegree());
	}
	
	@Test
	public void findClosestFromSeveralValuesOfSameTarget() {
		addValuesAround(30, 10, 2);
		memory.analyzeData();
		assertEquals(50, memory.getDirection().getNearestDegree());
	}
	
	@Test
	public void findClosestFromEvenMoreValuesOfSameTarget() {
		addValuesAround(30, 10, 10);
		memory.analyzeData();
		assertEquals(50, memory.getDirection().getNearestDegree());
	}
	
	@Test
	public void returnMinusOneIfNoTargetsAreLeft() {
		memory.analyzeData();
		assertNull(memory.getDirection());
	}
	
	@Test
	public void returnClosestTargetFromSeveralPossibilities() {
		addValuesAround(30, 10, 4);
		addValuesAround(20, 20, 4);
		memory.analyzeData();
		assertEquals(100, memory.getDirection().getNearestDegree());
	}
	
	@Test
	public void returnClosestTargetFromSeveralPossibilities2() {
		addValuesAround(30, 10, 4);
		addValuesAround(20, 20, 4);
		addValuesAround(40, 30, 4);
		memory.analyzeData();
		assertEquals(100, memory.getDirection().getNearestDegree());
	}
	
	private void fillMemoryWithEmptyValues() {
		for (int i = 0; i < 360 / 5; i++) {
			int dist = random.nextInt(5) + 51;
			memory.addValues(dist, i);
		}
	}
	
	private void addValuesAround(int closest, int deg, int width) {
		for (int i = -(width / 2); i <= width / 2; i++) {
			memory.addValues(closest + Math.abs(i), deg + i);
		}
	}

}
