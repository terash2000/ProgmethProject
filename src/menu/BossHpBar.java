package menu;

import application.Main;

public class BossHpBar extends HpBar {
	
	public BossHpBar(double maxHp) {
		super(30, maxHp,  (Main.getSceneWidth() - 100)/maxHp);
		setLayoutX(50);
		setLayoutY(Main.getSceneHeight() - 100);
	}
	
	public void update(double hp) {
		setLayoutY(Main.getSceneHeight() - 100);
		scale = (Main.getSceneWidth() - 100)/maxHp;
		border.setWidth(maxHp*scale);
		super.update(hp);
	}

}
