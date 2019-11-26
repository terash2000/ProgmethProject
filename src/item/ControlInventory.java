package item;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ControlInventory extends VBox{
	private Inventory inventory;
	
	public ControlInventory() {
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(450);
		this.setSpacing(15);
		setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setLayoutX(50);
		setLayoutY(100);
		
		this.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));
		
		Label title = new Label("My Inventory"); 
		title.setTextFill(Color.WHITE);
		title.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		title.setAlignment(Pos.CENTER);
		title.setFont(Font.font(15)); 
		title.setPrefWidth(600);
		this.getChildren().add(title);
		
		this.inventory = new Inventory(); this.getChildren().add(this.inventory);
		
		Label title2 = new Label("My Avtivate Items."); 
		title2.setTextFill(Color.WHITE);
		title2.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		title2.setAlignment(Pos.CENTER);
		title2.setFont(Font.font(15)); 
		title2.setPrefWidth(600);
		this.getChildren().add(title2);
		
		this.getChildren().add(this.inventory.getActivateItemPane());
		
	}

	public Inventory getInventory() {
		return inventory;
	}
}
