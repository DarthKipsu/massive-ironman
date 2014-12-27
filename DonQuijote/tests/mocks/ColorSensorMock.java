package mocks;

import sensors.ColorSensor;

public class ColorSensorMock implements ColorSensor {

	private int[] measurements;
	private int i;
	
	public ColorSensorMock() {
		i = -1;
	}
	
	public void setMeasurements(int[] measurements) {
		this.measurements = measurements;
	}
	
	@Override
	public int measureColor() {
		i++;
		if (measurements == null) return 0;
		return measurements[i];
	}

	@Override
	public void turnOnFloodlight() {
	}

	@Override
	public void turnOffFloodlight() {
	}

}
