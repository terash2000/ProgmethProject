package object;

public class HitWallException extends Exception {

	private static final long serialVersionUID = 4455528836319535409L;
	public double distance;
	
	public HitWallException(double distance) {
		this.distance = distance;
	}
}