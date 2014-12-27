package moving;

public interface Movable {

	void moveForward(int cm);
	void moveBackward(int cm);
	void rotateLeft(double degrees);
	void rotateRight(int degrees);
	void closeMotors();
	
}
