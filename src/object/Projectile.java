package object;

import application.Main;

public class Projectile extends MoveableObject {
	
	private double damage;
	private static final double heroKnockBackX = 25;
	private static final double heroKnockBackY = 15;
	
	public Projectile(String imagePath, double x, double y, double width, double height,
			double dx, double dy, double damage) {
		super(imagePath, x, y, width, height);
		this.dx = dx;
		this.dy = dy;
		this.damage = damage;
	}
	
	public void update() {
		if (Main.hero.intersectCheck(x, y, size[0], size[1])) {
			Main.hero.attacked(damage, ((Main.hero.getCenterX() < getCenterX()) ? -heroKnockBackX : heroKnockBackX), heroKnockBackY);
		}
		super.update();
	}
	
	protected void moveX() {
		if (dx < 0) {
			try {
				leftWallCheck();
				x +=dx;
			} catch(HitWallException e) {
				remove();
			}
		}else if (dx > 0) {
			try {
				rightWallCheck();
				x +=dx;
			} catch(HitWallException e) {
				remove();
			}
		}
	}
	
	protected void moveY() {
		if ((dy > maxFallSpeed) && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if (dy < 0) {
			try {
				topCheck();
			} catch(HitWallException e) {
				remove();
			}
		}else if (dy > 0) {
			try {
				landingCheck();
			} catch(HitWallException e) {
				remove();
			}
		}
		y += dy;
	}

}
