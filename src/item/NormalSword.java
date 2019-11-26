package item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NormalSword extends Sword {
	
	private static int attackDamageBonus = 10;
	
	public NormalSword() {
		super(attackDamageBonus);
		ImageView normalSword = new ImageView(new Image(ClassLoader.getSystemResource("ItemImage/NormalSword.png").toString()));
//		normalSword.setFitHeight(100);
//		normalSword.setFitWidth(100);
//		this.setPrefHeight(100);
//		this.setPrefWidth(100);
		setGraphic(normalSword);
	}
	
}
