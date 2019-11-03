package object;

import application.Main;
import application.Music;
import application.Sound;
import application.Delay;
import java.util.ArrayList;
import java.util.List;

public abstract class Boss extends MoveableEnemy {
	
	protected Delay hold;
	protected String cerrentStage;
	protected boolean inAir;
	protected List<String> artList = new ArrayList<String>();
	protected Music bossTheme;
	
	protected abstract void changeStage();
	protected abstract void changeArt(String art);
	
	public Boss(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public void die() {
		Main.world.exitBossFight();
		Main.world.getCerrentMap().getEnemyList().remove(this);
		super.die();
	}
	
	public void reset() {
		cerrentStage = "idle";
		super.reset();
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
			}
		}
		y += dy;
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
