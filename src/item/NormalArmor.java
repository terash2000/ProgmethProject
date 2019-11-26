package item;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NormalArmor extends Armor {
	private static int maxHpBonus = 10;
	
	
	public NormalArmor() {
		super(maxHpBonus);
		setGraphic(new ImageView(new Image(ClassLoader.getSystemResource("ItemImage/NormalArmor.png").toString())));
		
		// Information list
		
//		this.setOnMouseEntered(new EventHandler<MouseEvent>() { // create mouse action
//			@Override
//			public void handle(MouseEvent event) {
//				Alert alert = new Alert(AlertType.NONE);
//				alert.setTitle("Item information");
//				alert.setHeaderText("Sword (Normal)");
//				alert.setContentText("HP Bonus : 10");
//				alert.showAndWait();
//			}
//			
//			this.setOnMouseExited(new EventHandler<MouseEvent>() {
//				@Override
//				public void handle(MouseEvent event) {
//					signinBtn.setPrefWidth(65);
//				}
//			});
//		});
		
	}
	
}
