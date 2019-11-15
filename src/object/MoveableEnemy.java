package object;

import application.Main;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
	private double[] spawnLocation = new double[2];
	protected String cerrentStage;
	
	public MoveableEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		spawnLocation[0] = x;
		spawnLocation[1] = y;
	}
	
	public abstract void setMovement();
	
	public void update() {
		setMovement();
		move();
		if (Main.hero.hitCheck(x, y, size[0], size[1])) {
			Main.hero.attacked(attackDamage, Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2 ? -25 : 25, 15);
		}
		changeView();
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
	
	protected void changeArt(String art) {
		cerrentStage = art;
		super.changeArt(art);
	}
	
	public void spawn() {
		reset();
		hp = maxHp;
		Main.game.getChildren().add(this);
		x = spawnLocation[0];
		y = spawnLocation[1];
		fallSpeedLimit = true;
	}
	
	public void die() {
		remove();
	}
	
	public void remove() {
		Main.world.getDestroyableList().remove(this);
		super.remove();
	}
	
}
