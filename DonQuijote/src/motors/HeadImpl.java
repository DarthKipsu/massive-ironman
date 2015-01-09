package motors;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * Moves the robot head and keeps track of it's current position.
 *
 */
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
		prolongHead(30);
	}
	
	@Override
	public void prolongToSideAttack() {
		prolongHead(45);
	}
	
	@Override
	public void contractFully() {
		prolongHead(0);
	}

	@Override
	public void attackForward() {
		motor.setSpeed(550);
		prolongHead(60);
		contractFully();
		motor.setSpeed(360);
	}
	
	private void prolongHead(int mm) {
		motor.rotate((int)(rotFormm * (mm - position)));
		position = mm;
	}

}
