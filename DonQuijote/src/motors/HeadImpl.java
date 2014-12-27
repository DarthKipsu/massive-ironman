package motors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class HeadImpl implements Head {
	
	private EV3MediumRegulatedMotor motor;
	private double rotFormm;
	private int position;
	
	public HeadImpl() {
		motor = new EV3MediumRegulatedMotor(MotorPort.C);
		rotFormm = 7;
		position = 0;
	}
	
	@Override
	public void prolongToDefault() {
		prolongHead(30 - position);
		position = 30;
	}
	
	@Override
	public void contractFully() {
		prolongHead(-5 - position);
		position = 0;
	}
	
	private void prolongHead(int mm) {
		motor.rotate((int)(rotFormm * mm));
	}

}