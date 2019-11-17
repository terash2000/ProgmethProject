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
		gravity = 0;
	}
	
	public Projectile(String imagePath, double x, double y, double width, double height, 
			double dx, double dy, double damage, double gravity) {
		this(imagePath, x, y, width, height, dx, dy, damage);
		this.gravity = gravity;
	}
	
	public void update() {
		dy += gravity;
		if (Main.hero.hitCheck(x, y, size[0], size[1])) {
			Main.hero.attacked(damage, (((Main.hero.getX() + Main.hero.getSize()[0]/2) < (x + size[0]/2)) 
					? -heroKnockBackX : heroKnockBackX), heroKnockBackY);
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
