package menu;

import application.Main;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HpBar {
	
	private Group body;
	private double yellowHp;
	private Rectangle border;
	private Rectangle yellowBar;
	private Rectangle bar;
	private double maxHp, hp;
	
	public HpBar(double maxHp) {
		this.maxHp = maxHp;
		border = new Rectangle(maxHp*3, 20, Color.DARKGRAY);
		yellowBar = new Rectangle(maxHp*3, 20, Color.LIGHTYELLOW);
		bar = new Rectangle(maxHp*3, 20, Color.RED);
		body = new Group(border, yellowBar, bar);
		Main.HpBar = body;
	}
	
	public void update(double hp) {
		this.hp = hp;
		if(yellowHp > hp) {
			yellowHp -= 0.5;
		}else {
			yellowHp = hp;
		}
		bar.setWidth(hp*3);
		yellowBar.setWidth(yellowHp*3);
		
	}

	public Group getBody() {
		return body;
	}

	public double getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}
	
	
	
}
