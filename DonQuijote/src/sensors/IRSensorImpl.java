package sensors;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

/**
 * Measures and returns the distance to target as an integer value.
 * 
 * 0 = target right ahead
 * 0-50 = ratio of distance to target
 * 50+ = nothing detected
 *
 */
public class IRSensorImpl implements IRSensor {
	
	private EV3IRSensor sensor;
	private SampleProvider sampler;
	
	public IRSensorImpl() {
		sensor = new EV3IRSensor(LocalEV3.get().getPort("S3"));
	}
	
	@Override
	public int measureDistance() {
		sampler = sensor.getDistanceMode();
    	float[] sample = new float[sampler.sampleSize()];
    	sampler.fetchSample(sample, 0);
		return (int) sample[0];
	}

}
