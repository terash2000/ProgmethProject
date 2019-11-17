package item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RareArmor extends Armor {
	private static int maxHpBonus = 20;
	
	
	public RareArmor() {
		super(maxHpBonus);
		setGraphic(new ImageView(new Image(ClassLoader.getSystemResource("ItemImage/RareArmor.png").toString())));
	}

}
