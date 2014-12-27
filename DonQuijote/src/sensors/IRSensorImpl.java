package sensors;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

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
