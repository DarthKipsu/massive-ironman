package mocks;

import sensors.IRSensor;

public class IRSensorMock implements IRSensor {
	
	private int[] measurements;
	private int i;
	
	public IRSensorMock() {
		i = -1;
	}

	@Override
	public int measureDistance() {
		i++;
		return measurements[i];
	}
	
	public void setMeasurements(int[] measurements) {
		this.measurements = measurements;
	}

}
