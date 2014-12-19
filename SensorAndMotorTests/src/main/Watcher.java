package main;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Watcher {
	
	private EV3IRSensor sensor;
	private SampleProvider sampler;
	GraphicsLCD lcd;
	
	public Watcher() {
		sensor = new EV3IRSensor(LocalEV3.get().getPort("S3"));
		lcd = BrickFinder.getDefault().getGraphicsLCD();
	}
	
	public void printDistances() {
		sampler = sensor.getDistanceMode();
        while (!Button.DOWN.isDown()) {
        	float[] sample = new float[sampler.sampleSize()];
        	sampler.fetchSample(sample, 0);
        	draw("distance: " + sample[0]);
        }
	}
	
	public void printSeek() {
		sampler = sensor.getSeekMode();
		while (!Button.DOWN.isDown()) {
	    	float[] sample = new float[sampler.sampleSize()];
			sampler.fetchSample(sample, 0);
			if (sample[0] == 0.0) {
				draw("No beacon detected");
			} else {
				// direction + distance
				draw((sample[0] > 0 ? "right: " : "left: ") + Math.abs(sample[0]) + ", " + sample[1]);
			}
        }
	}
	
	private void draw(String string) {
        final int SW = lcd.getWidth();
        final int SH = lcd.getHeight();
    	lcd.drawString(string, SW/2, SH/2, GraphicsLCD.BASELINE|GraphicsLCD.HCENTER);
        Delay.msDelay(500);
        lcd.clear();
	}

}
