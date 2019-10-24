package character;

public class Glimback extends MoveableEnemy {
	
	public Glimback(double x, double y) {
		super("file:image/Character/Glimback.png", 200, 200, x,y);
		speed = 5;
		friction = 0.05;
		attackDamage = 20;
	}
	
	public void die() {
		
	}

}
