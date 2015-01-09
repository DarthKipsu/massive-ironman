package sensors;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

/**
 * Returns color sensor data as integer result and sets flood light on and off.
 * 
 * Color codes:
 * 0 = Not known color 
 * 1 = Black 
 * 2 = Blue 
 * 3 = Green 
 * 4 = Yellow 
 * 5 = Red 
 * 6 = White 
 * 7 = Brown
 * -1 or > 7 = nothing detected
 *
 */
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
