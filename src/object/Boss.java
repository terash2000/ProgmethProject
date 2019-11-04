package object;

import application.Main;
import application.Music;
import application.Sound;

public abstract class Boss extends MoveableEnemy {
	
	protected Music bossTheme;
	
	public Boss(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public void die() {
		Main.world.exitBossFight();
		Main.world.getCerrentMap().getEnemyList().remove(this);
		super.die();
	}
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		if(knockbackX != 0) {
			dx += knockbackX;
		}
		if(knockbackY != 0) {
			dy += -knockbackY;
		}
		hp = damage > hp ? 0 : hp - damage;
		if(hp == 0) {
			die();
		}
	}
	
	protected void startBossFight() {
		Main.world.setBossFight(true);
		Sound.changeBackgroundMusic(bossTheme);
	}
	
}
