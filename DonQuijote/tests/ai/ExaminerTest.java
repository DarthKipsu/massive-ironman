package ai;

import static org.junit.Assert.*;
import mocks.ColorSensorMock;
import mocks.HeadMock;
import mocks.IRSensorMock;
import mocks.MovableMock;

import org.junit.Before;
import org.junit.Test;

public class ExaminerTest {
	
	private MovableMock move;
	private IRSensorMock irs;
	private ColorSensorMock cs;
	private Examiner ex;

	@Before
	public void setUp() {
		move = new MovableMock();
		irs = new IRSensorMock();
		cs = new ColorSensorMock();
		ex = new Examiner(move, new ObjectFinder(move, irs), cs, new HeadMock());
	}

	@Test
	public void doeNotMoveCloserToTargetWhenAlreadyCloseEnough() {
		int[] measurements = createEmptyIRArray();
		addSamples(measurements, 9);
		irs.setMeasurements(measurements);
		ex.examineTarget();
		// + 5 comes from reaction to target, testing only movement before it
		assertEquals(0 + 5, move.getMovementForward());
	}
	
	@Test
	public void movesCloserOnceIfNearbyObject() {
		int[] measurements = createEmptyIRArray();
		addSamples(measurements, 12, 12, 9);
		irs.setMeasurements(measurements);
		ex.examineTarget();
		// + 5 comes from reaction to target, testing only movement before it
		assertEquals(3 + 5, move.getMovementForward());
	}
	
	@Test
	public void movesCloserTwiceIfFurtherFromObject() {
		int[] measurements = createEmptyIRArray();
		addSamples(measurements, 16, 16, 12, 12, 9);
		irs.setMeasurements(measurements);
		ex.examineTarget();
		// + 5 comes from reaction to target, testing only movement before it
		assertEquals(7 + 5, move.getMovementForward());
	}
	
	@Test
	public void movesSameAmountBackwardsAsForward() {
		int[] measurements = createEmptyIRArray();
		addSamples(measurements, 16, 16, 12, 12, 9);
		irs.setMeasurements(measurements);
		ex.examineTarget();
		// + 5 comes from reaction to target, testing only movement before it
		assertEquals(7 + 5, move.getMovementBackward());
	}
	
	@Test
	public void rotatesOnceToRelocateTargetIfNoColorCanBeRead() {
		int[] measurements = createEmptyIRArray();
		addSamples(measurements, 9, 9);
		irs.setMeasurements(measurements);
		measurements = createEmptyColorArray();
		addSamples(measurements, -1, 3);
		cs.setMeasurements(measurements);
		ex.examineTarget();
		// + 4 comes from initial rotation closer to the center of the target
		assertEquals(1 + 4, move.getRotatedLeft(), 0.1);
	}
	
	@Test
	public void rotatesSeveralTimesToRelocateTargetIfNoColorCanBeRead() {
		int[] measurements = createEmptyIRArray();
		addSamples(measurements, 9, 9, 9, 9);
		irs.setMeasurements(measurements);
		measurements = createEmptyColorArray();
		addSamples(measurements, -1, -1, -1, 3);
		cs.setMeasurements(measurements);
		ex.examineTarget();
		// + 4 comes from initial rotation closer to the center of the target
		assertEquals(3 + 4, move.getRotatedLeft(), 0.1);
	}
	
	@Test
	public void rotatesRightIfObjectNotFoundFromLeft() {
		int[] measurements = createEmptyIRArray();
		addSamples(measurements, 9, 9, 9, 100, 9);
		irs.setMeasurements(measurements);
		measurements = createEmptyColorArray();
		addSamples(measurements, -1, -1, -1, 3, 3);
		cs.setMeasurements(measurements);
		ex.examineTarget();
		// + 4 comes from initial rotation closer to the center of the target:w
		assertEquals(1 + 4, move.getRotatedLeft(), 0.1);
		assertEquals(4, move.getRotatedRight(), 0.1);
	}
	
	private int[] createEmptyIRArray() {
		int[] measurements = new int[100];
		for (int i = 0; i < 100; i++) {
			measurements[i] = 100;
		}
		return measurements;
	}
	
	private int[] createEmptyColorArray() {
		int[] measurements = new int[100];
		for (int i = 0; i < 100; i++) {
			measurements[i] = -1;
		}
		return measurements;
	}
	
	private void addSamples(int[] measurements, int... samples) {
		for (int i = 0; i < samples.length; i++) {
			measurements[i] = samples[i];
		}
	}

}
