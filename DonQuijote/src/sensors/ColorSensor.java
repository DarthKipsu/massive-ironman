package sensors;

/**
 * Interface for collecting data from color sensor.
 *
 */
public interface ColorSensor {

	int measureColor();
	void turnOnFloodlight();
	void turnOffFloodlight();
	
}
