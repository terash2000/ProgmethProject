package object;

import application.Main;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
	private double[] spawnLocation = new double[2];
	protected String cerrentStage;
	
	protected static final double heroKnockBackX = 25;
	protected static final double heroKnockBackY = 15;
	
	public MoveableEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		spawnLocation[0] = x;
		spawnLocation[1] = y;
	}
	
	public abstract void setMovement();
	
	public void update() {
		setMovement();
		if (Main.hero.intersectCheck(x, y, size[0], size[1])) {
			Main.hero.attacked(attackDamage, ((Main.hero.getCenterX() < getCenterX()) ? -heroKnockBackX : heroKnockBackX), heroKnockBackY);
		}
		super.update();
	}
	
	protected void moveY() {
		if (dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if (dy < 0) {
			try {
				topCheck();
				inAir = true;
			} catch(HitWallException exception) {
				dy = exception.distance;
			}
		}else if (dy >= 0) {
			try {
				landingCheck();
				inAir = true;
			} catch(HitWallException exception) {
				dy = exception.distance;
				inAir = false;
			}
		}
		y += dy;
	}
	
	public void spawn() {
		reset();
		alive = true;
		hp = maxHp;
		Main.world.getChildren().add(this);
		x = spawnLocation[0];
		y = spawnLocation[1];
		turn(false);
	}
	
	public void die() {
		remove();
	}
	
	protected void reset() {
		turn(false);
		super.reset();
	}
	
	public void remove() {
		Main.world.getDestroyableList().remove(this);
		super.remove();
	}
	
	protected void changeSprite(String art) {
		cerrentStage = art;
		super.changeSprite(art);
	}
	
}
