package object;

import application.Main;
import application.Delay;
import java.util.ArrayList;
import java.util.List;

public abstract class Boss extends MoveableEnemy {
	
	protected Delay hold;
	protected String cerrentStage = "normal";
	protected boolean inAir;
	protected List<String> artList = new ArrayList<String>();
	protected abstract void changeStage();
	protected abstract void changeArt(String art);
	
	public Boss(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public void die() {
		Main.world.getCerrentMap().getEnemyList().remove(this);
		super.die();
	}
	
	public void reset() {
		changeArt("normal");
		hold = new Delay(1000);
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
	
}
