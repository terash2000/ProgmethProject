package item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LegendArmor extends Armor {
	private static int maxHpBonus = 50;
	
	
	public LegendArmor() {
		super(maxHpBonus);
		setGraphic(new ImageView(new Image(ClassLoader.getSystemResource("ItemImage/LegendArmor.png").toString())));
	}
}
