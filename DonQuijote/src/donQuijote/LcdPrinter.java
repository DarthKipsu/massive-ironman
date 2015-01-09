package donQuijote;

import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;

/**
 * Prints messages in the middle of brick LCD display.
 *
 */

public class LcdPrinter {
	
	private static final GraphicsLCD lcd = BrickFinder.getDefault().getGraphicsLCD();
	
	public static void draw(String string) {
        final int SW = lcd.getWidth();
        final int SH = lcd.getHeight();
        lcd.clear();
    	lcd.drawString(string, SW/2, SH/2, GraphicsLCD.BASELINE|GraphicsLCD.HCENTER);
	}

}
