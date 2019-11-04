package object;

import application.Delay;
import application.Main;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
	private double[] spawnLocation = new double[2];
	protected String cerrentStage;
	protected Delay hold;
	
	public MoveableEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		spawnLocation[0] = x;
		spawnLocation[1] = y;
	}
	
	public abstract void setMovement();
	
	protected void changeArt(String art) {
		cerrentStage = art;
		super.changeArt(art);
	}
	
	protected void moveY() {
		if(dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if(dy < 0) {
			topCheck();
		}else if(dy >= 0) {
			if(landingCheck()) {
				inAir = false;
			}else {
				inAir = true;
			}
		}
		y += dy;
	}
	
	public void die() {
		Main.world.getActionableList().remove(this);
		Main.world.getDestroyableList().remove(this);
		Main.game.getChildren().remove(body);
	}
	
	public void spawn() {
		reset();
		hp = maxHp;
		Main.game.getChildren().add(body);
		x = spawnLocation[0];
		y = spawnLocation[1];
		fallSpeedLimit = true;
	}
	
	public void action() {
		setMovement();
		move();
		if(Main.hero.hitCheck(x, y, size[0], size[1])) {
			Main.hero.attacked(attackDamage, Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2 ? -25 : 25, 15);
		}
	}
	
}
