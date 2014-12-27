package sensors;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensorImpl implements ColorSensor {

	private EV3ColorSensor sensor;
	private SampleProvider sampler;
	
	public ColorSensorImpl() {
		sensor = new EV3ColorSensor(LocalEV3.get().getPort("S2"));
	}
	
	@Override
	public void turnOnFloodlight() {
		sensor.setFloodlight(true);
	}
	
	@Override
	public void turnOffFloodlight() {
		sensor.setFloodlight(false);
	}
	
	@Override
	public int measureColor() {
		sampler = sensor.getColorIDMode();
    	float[] sample = new float[sampler.sampleSize()];
    	sampler.fetchSample(sample, 0);
		return (int) sample[0];
	}
}