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
	
	public void update() {
		if (hpBar != null) {
			hpBar.update(hp);
		}
		super.update();
	}
	
	protected void startBossFight() {
		Sound.changeBackgroundMusic(bossTheme);
		Main.world.setBossFight(true);
		hpBar = new BossHpBar(maxHp);
		Main.root.getChildren().add(hpBar);
	}
	
	public void die() {
		Main.world.getCerrentMap().getEnemyList().remove(this);
		Sound.changeBackgroundMusic(Main.world.getCerrentMap().getMusic());
		super.die();
	}
	
	public void remove() {
		Main.root.getChildren().remove(hpBar);
		Main.world.setBossFight(false);
		super.remove();
	}

	public BossHpBar getHpBar() {
		return hpBar;
	}
	
}
