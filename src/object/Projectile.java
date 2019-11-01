package object;

import application.Main;

public class Projectile extends MoveableObject implements Actionable {
	
	private double attackDamage = 20;
	
	public Projectile(String imagePath, double x, double y, double width, double height, double dx, double dy) {
		super(imagePath, x, y, width, height);
		this.dx = dx;
		this.dy = dy;
		gravity = 0;
	}
	
	public Projectile(String imagePath, double x, double y, double width, double height, double dx, double dy, double gravity) {
		this(imagePath, x, y, width, height, dx, dy);
		this.gravity = gravity;
	}
	
	public void action() {
		dy += gravity;
		move();
		if(Main.hero.hitCheck(x, y, size[0], size[1])) {
			Main.hero.attacked(attackDamage, Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2 ? -25 : 25, 15);
		}
	}
	
	protected void moveX() {
		if(dx < 0 && leftWallCheck()) {
			remove();
		}else if(dx > 0 && rightWallCheck()) {
			remove();
		}
		x +=dx;
	}
	
	protected void moveY() {
		if(dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if(dy < 0 && topCheck()) {
			remove();
		}else if(dy > 0 && landingCheck()) {
			remove();
		}
		y += dy;
	}
	
	private void remove() {
		Main.world.getActionableList().remove(this);
		Main.game.getChildren().remove(body);
	}

}
