package ai;

import static org.junit.Assert.*;
import mocks.IRSensorMock;
import mocks.MovableMock;

import org.junit.Before;
import org.junit.Test;

public class ObjectFinderTest {
	
	private MovableMock move;
	private IRSensorMock irs;
	private ObjectFinder objFind;

	@Before
	public void setUp() {
		move = new MovableMock();
		irs = new IRSensorMock();
		objFind = new ObjectFinder(move, irs);
	}
	
	@Test
	public void doesNptFindObjectsWhereThereIsNone() {
		irs.setMeasurements(createEmptyArray());
		assertEquals(-1, objFind.findNearestObject());
	}

	@Test
	public void findsObjectDirectlyInFrontOfRobot() {
		int[] measurements = createEmptyArray();
		addSamples(measurements, 0, 5);
		irs.setMeasurements(measurements);
		assertEquals(5, objFind.findNearestObject());
	}
	
	@Test
	public void findsObjectTest1() {
		int[] measurements = createEmptyArray();
		addSamples(measurements, 30, 5, 31, 5);
		irs.setMeasurements(measurements);
		assertEquals(5, objFind.findNearestObject());
	}
	
	@Test
	public void findsObjectTest2() {
		int[] measurements = createEmptyArray();
		addSamples(measurements, 41, 49, 42, 49);
		irs.setMeasurements(measurements);
		assertEquals(49, objFind.findNearestObject());
	}
	
	@Test
	public void doesNotFindObjectTooFarFromRobot() {
		int[] measurements = createEmptyArray();
		addSamples(measurements, 41, 50, 42, 50);
		irs.setMeasurements(measurements);
		assertEquals(-1, objFind.findNearestObject());
	}
	
	@Test
	public void findsTwoObjects() {
		int[] measurements = createEmptyArray();
		addSamples(measurements, 20, 30, 21, 30, 50, 40, 51, 40);
		irs.setMeasurements(measurements);
		assertEquals(30, objFind.findNearestObject());
		assertEquals(40, objFind.findNearestObject());
	}
	
	@Test
	public void findsOnlyTwoObjectsWhenTheresOnlyTwoOfThem() {
		int[] measurements = createEmptyArray();
		addSamples(measurements, 20, 30, 21, 30, 50, 40, 51, 40);
		irs.setMeasurements(measurements);
		assertEquals(30, objFind.findNearestObject());
		assertEquals(40, objFind.findNearestObject());
		assertEquals(-1, objFind.findNearestObject());
	}
	
	@Test
	public void findsTwoObjectsEvenIfOneIsVeryWide() {
		int[] measurements = createEmptyArray();
		addSamples(measurements, 20, 30, 21, 30, 22, 30, 23, 30, 24, 30, 25, 30, 50, 40, 51, 40);
		irs.setMeasurements(measurements);
		assertEquals(30, objFind.findNearestObject());
		assertEquals(40, objFind.findNearestObject());
	}
	
	private int[] createEmptyArray() {
		int[] measurements = new int[100];
		for (int i = 0; i < 100; i++) {
			measurements[i] = 100;
		}
		return measurements;
	}
	
	private void addSamples(int[] measurements, int... samples) {
		for (int i = 0; i < samples.length; i += 2) {
			measurements[samples[i]] = samples[i + 1];
		}
	}

}
