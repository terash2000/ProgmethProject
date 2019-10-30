package object;

import application.Main;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
	private double[] spawnLocation = new double[2];
	
	public MoveableEnemy(double x, double y, double width, double height) {
		super(x, y, width, height);
		spawnLocation[0] = x;
		spawnLocation[1] = y;
	}
	
	public abstract void setMovement();
	
	public void die() {
		Main.worldMap.getActionableList().remove(this);
		Main.worldMap.getDestroyableList().remove(this);
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
