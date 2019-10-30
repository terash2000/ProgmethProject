package object;

import application.Main;
import application.Delay;

public abstract class Boss extends MoveableEnemy {
	
	protected Delay hold = new Delay(0);
	protected String cerrentStage = "normal";
	
	protected abstract void changeStage(String stage);
	protected abstract void changeArt(String art);
	
	public Boss(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public void die() {
		Main.worldMap.getCerrentMap().getEnemyList().remove(this);
		super.die();
	}
	
	public void reset() {
		changeArt("normal");
		super.reset();
	}
	
}
