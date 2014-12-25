package moving;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Head {
	
	private EV3MediumRegulatedMotor motor;
	private double rotFormm;
	private int position;
	
	public Head() {
		motor = new EV3MediumRegulatedMotor(MotorPort.C);
		rotFormm = 7;
		position = 0;
	}
	
	public void prolongToDefault() {
		prolongHead(30 - position);
		position = 30;
	}
	
	public void contractFully() {
		prolongHead(0 - position);
		position = 0;
	}
	
	private void prolongHead(int mm) {
		motor.rotate((int)(rotFormm * mm));
	}

}
