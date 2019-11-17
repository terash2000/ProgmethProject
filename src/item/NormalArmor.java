package item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NormalArmor extends Armor {
	private static int maxHpBonus = 10;
	
	
	public NormalArmor() {
		super(maxHpBonus);
		setGraphic(new ImageView(new Image(ClassLoader.getSystemResource("ItemImage/NormalArmor.png").toString())));
	}
}
