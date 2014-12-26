package sensors;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class IRSensor {
	
	private EV3IRSensor sensor;
	private SampleProvider sampler;
	
	public IRSensor() {
		sensor = new EV3IRSensor(LocalEV3.get().getPort("S3"));
	}
	
	public int measureDistance() {
		sampler = sensor.getDistanceMode();
    	float[] sample = new float[sampler.sampleSize()];
    	sampler.fetchSample(sample, 0);
		return (int) sample[0];
	}

}
