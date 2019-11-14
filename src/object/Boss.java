package object;

import application.Main;
import application.Music;
import application.Sound;
import menu.BossHpBar;

public abstract class Boss extends MoveableEnemy {
	
	protected Music bossTheme;
	protected BossHpBar hpBar;
	
	public Boss(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public void action() {
		if (hpBar != null) {
			hpBar.update(hp);
		}
		super.action();
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
		Sound.changeBackgroundMusic(bossTheme);
		Main.world.startBossFight(this);
		hpBar = new BossHpBar(maxHp);
		Main.root.getChildren().add(hpBar);
	}

	public BossHpBar getHpBar() {
		return hpBar;
	}
	
}
