package donQuijote;

import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;

public class LcdPrinter {
	
	private static final GraphicsLCD lcd = BrickFinder.getDefault().getGraphicsLCD();
	
	public static void draw(String string) {
        final int SW = lcd.getWidth();
        final int SH = lcd.getHeight();
    	lcd.drawString(string, SW/2, SH/2, GraphicsLCD.BASELINE|GraphicsLCD.HCENTER);
        Delay.msDelay(500);
        lcd.clear();
	}

}
