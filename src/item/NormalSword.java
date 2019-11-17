package item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NormalSword extends Sword {
	
	private static int attackDamageBonus = 10;
	
	public NormalSword() {
		super(attackDamageBonus);
		setGraphic(new ImageView(new Image(ClassLoader.getSystemResource("ItemImage/NormalSword.ico").toString())));
	}
	
}
