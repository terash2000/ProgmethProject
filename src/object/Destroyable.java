package object;

public interface Destroyable extends Updateable {
	
	public void attacked(double damage, double knockbackX, double knockbackY);
	
	public void die();
	
	public boolean hitCheck(double x0, double x1, double y0, double y1);

}
