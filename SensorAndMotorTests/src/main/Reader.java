package main;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Reader {
	
	private EV3ColorSensor sensor;
	private SampleProvider sampler;
	GraphicsLCD lcd;
	
	public Reader() {
		sensor = new EV3ColorSensor(LocalEV3.get().getPort("S2"));
		lcd = BrickFinder.getDefault().getGraphicsLCD();
	}
	
	public void readColors() {
		sampler = sensor.getColorIDMode();
		while (!Button.DOWN.isDown()) {
	    	float[] sample = new float[sampler.sampleSize()];
			sampler.fetchSample(sample, 0);
			switch ((int)sample[0]) {
			case 0:
				draw("Not a color");
				break;
			case 1:
				draw("Black");
				break;
			case 2:
				draw("Blue");
				break;
			case 3:
				draw("Green");
				break;
			case 4:
				draw("Yellow");
				break;
			case 5:
				draw("Red");
				break;
			case 6:
				draw("White");
				break;
			case 7:
				draw("Brown");
				break;

			default:
				draw("Nothing detected");
				break;
			}
		}
		sensor.setFloodlight(false);
	}
	
	public void readLuminance() {
		sampler = sensor.getAmbientMode();
		while (!Button.DOWN.isDown()) {
	    	float[] sample = new float[sampler.sampleSize()];
			sampler.fetchSample(sample, 0);
			draw("Light: " + sample[0]);
		}
		sensor.setFloodlight(false);
	}
	
	private void draw(String string) {
        final int SW = lcd.getWidth();
        final int SH = lcd.getHeight();
    	lcd.drawString(string, SW/2, SH/2, GraphicsLCD.BASELINE|GraphicsLCD.HCENTER);
        Delay.msDelay(500);
        lcd.clear();
	}

}
