package object;

public class HitWallException extends Exception {
	public double distance;
	
	public HitWallException(double distance) {
		this.distance = distance;
	}
}