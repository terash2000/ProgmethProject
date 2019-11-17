package item;

import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import application.Main;

public class Inventory extends GridPane {
	
	private ObservableList<Item> myInventory = FXCollections.observableArrayList();
	private ObservableList<Item> myActivateItem = FXCollections.observableArrayList();
	
	public Inventory () {
		// TODO Implements CataloguePane's constructor
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.setHgap(10);
		
		setLayoutX(100);
		setLayoutY(100);
	} 
	
	public void addItem(Item item) {
		myInventory.add(item);
	}
	
	public void activateItem(Item item) {
		int index = isItemtypeActivate(item);
		if (index > 0) 	deactivateItem(index);
		myActivateItem.add(item);
		item.applyBonuses(Main.hero);
		item.highlight();
	}
	
	public void deactivateItem(int index) {
		if (index > 0) {
			myActivateItem.get(index).unhighlight();
			myActivateItem.get(index).unapplyBonuses(Main.hero);
			myActivateItem.remove(index);
		}
	}
	
	public int isItemtypeActivate(Item item) {
		for	(int i=0; i < myActivateItem.size(); i++) {
			if(item instanceof Sword && myActivateItem.get(i) instanceof Sword) {
				return i;
			}
			else if(item instanceof Armor && myActivateItem.get(i) instanceof Armor) {
				return i;
			}
		}
		return -1;
	}
	
	public void update() {
		int column = 0;
		for (Item x : myInventory) {
				this.add(x, column, 0);
				column++;
		}
	}
	
}
