package object;

import application.Main;

public abstract class Boss extends MoveableEnemy {
	
	public Boss(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public void die() {
		Main.worldMap.getCerrentMap().getEnemyList().remove(this);
		super.die();
	}
	
}
