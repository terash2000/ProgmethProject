package menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class HpBar extends StackPane {
	
	protected Rectangle border;
	protected Rectangle yellowBar;
	protected Rectangle bar;
	protected double maxHp, hp, yellowHp, scale, hpDecreaseSpeed;
	
	public HpBar(double size) {
		border = new Rectangle(0, size, Color.DARKGRAY);
		yellowBar = new Rectangle(0, size, Color.LIGHTYELLOW);
		bar = new Rectangle(0, size, Color.RED);
		this.getChildren().addAll(border, yellowBar, bar);
		setAlignment(Pos.CENTER_LEFT);
	}
	
	public HpBar(double size, double maxHp, double scale) {
		this.maxHp = maxHp;
		this.scale = scale;
		border = new Rectangle(maxHp*scale, size, Color.DARKGRAY);
		yellowBar = new Rectangle(maxHp*scale, size, Color.LIGHTYELLOW);
		bar = new Rectangle(maxHp*scale, size, Color.RED);
		this.getChildren().addAll(border, yellowBar, bar);
		setAlignment(Pos.CENTER_LEFT);
	}
	
	public void update(double hp) {
		this.hp = hp;
		if (yellowHp > hp) {
			yellowHp -= hpDecreaseSpeed;
		}else {
			yellowHp = hp;
		}
		bar.setWidth(hp*scale);
		yellowBar.setWidth(yellowHp*scale);
	}
	
	public double getMaxHp() {
		return maxHp;
	}
	
	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
		border.setWidth(maxHp*scale);
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

}
