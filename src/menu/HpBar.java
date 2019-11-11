package menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class HpBar extends StackPane {
	
	protected Rectangle border;
	protected Rectangle yellowBar;
	protected Rectangle bar;
	protected double maxHp, hp, yellowHp, scale;
	
	public HpBar(double size) {
		border = new Rectangle(0, size, Color.DARKGRAY);
		yellowBar = new Rectangle(0, size, Color.LIGHTYELLOW);
		bar = new Rectangle(0, size, Color.RED);
		this.getChildren().addAll(border, yellowBar, bar);
		setAlignment(Pos.CENTER_LEFT);
	}
	
	public void update(double hp) {
		this.hp = hp;
		if(yellowHp > hp) {
			yellowHp -= 0.5;
		}else {
			yellowHp = hp;
		}
		bar.setWidth(hp*scale);
		yellowBar.setWidth(yellowHp*scale);
	}
	
	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
		border.setWidth(maxHp*scale);
	}
	
	public double getMaxHp() {
		return maxHp;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

}
