package item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GoldenSword extends Sword{
	private static int attackDamageBonus = 20;
	
	public GoldenSword() {
		super(attackDamageBonus);
		setGraphic(new ImageView(new Image(ClassLoader.getSystemResource("ItemImage/GoldenSword.png").toString())));
		this.setPrefHeight(10);
		this.setPrefWidth(10);
	}
}
