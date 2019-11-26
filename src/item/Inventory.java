package item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

import application.Main;

public class Inventory extends GridPane {
	
	private ObservableList<Item> myInventory = FXCollections.observableArrayList();
	private Map<String, Item> myActivateItem = new HashMap<>();
	// myActivateItem index0 = Sword , index1 = Armor ;
	private GridPane activateItemPane;
	
	public Inventory () {
		// set property of this GridPane
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.setHgap(10);	
		this.myInventoryPaneClear();
		
		// create and set property of myActivateItem GridPane.
		this.activateItemPane = new GridPane();
		this.activateItemPane.setAlignment(Pos.CENTER);
		this.activateItemPane.setVgap(10);
		this.activateItemPane.setHgap(10);
		this.myActivePaneClear();
		//12
		
		// Add Item For test only. don't forget to delete after test.
		addItem(new GoldenSword());
		addItem(new NormalSword());
		addItem(new NormalArmor());
		
		// SetOnAction for all Button in myInventory.
		for(Item x : myInventory) {
			x.setOnAction( e -> activateItem ( x ));
		}
	} 
	
	public void addItem(Item newItem) {
		myInventory.add(newItem);
		this.myInventoryPaneAdd(newItem);
	}
	
	public void removeItem(Item item) {
		myInventory.remove(item);
		this.myInventoryPaneRemove(item);
	}
	
	public void activateItem(Item newItem) {
		if (isItemtypeActivate(newItem)) {
			// 1 remove Old item in myActivateItem (map)
			Item removedItem = deactivateItem(newItem.getTypeOfItem());
			// 2. remove newItem from myInventory (List)
			removeItem(newItem);
			// 3. add newItem to myActivateItem (map) and applyBonuses(Main.hero);
			myActivateItem.put(newItem.getTypeOfItem(), newItem);
			newItem.applyBonuses(Main.hero);
			// 4. add removedItem to myInventory (List)
			addItem(removedItem);
		}else {
			// add newItem to myActivateItem (map) and applyBonuses(Main.hero);
			myActivateItem.put(newItem.getTypeOfItem(), newItem);
			newItem.applyBonuses(Main.hero);
		}
		
		// (final) update MyActivatePane
		activatePaneBlock(newItem); // Update myActivatePane
	}
	
	public Item deactivateItem(String typeOfItem) { // item is any item that same type of item we need to deactivate
		// Guarantee that there is items in the myActivateItem (map) before call this method.
		if(typeOfItem != "Sword" || typeOfItem != "Armor") return null;
		Item removedItem = myActivateItem.remove(typeOfItem);
		removedItem.unapplyBonuses(Main.hero);
		deActivatePaneBlock(removedItem);
		return removedItem;
	}
	
	public void activatePaneBlock(Item newItem) {	
		// Update only block of new item.
		if (newItem.getTypeOfItem() == "Sword") {activateItemPane.add(newItem, 0, 0);}
		if (newItem.getTypeOfItem() == "Armor") {activateItemPane.add(newItem, 1, 0);}
	}
	
	public void deActivatePaneBlock(Item removedItem) {
		// call clearActivateBlock when replace old item image with emptyblock.png
		ImageView emptyBlock = new ImageView(new Image(ClassLoader.getSystemResource
				("ItemImage/InventoryBlock.png").toString()));
		 if (removedItem.getTypeOfItem() == "Sword") this.activateItemPane.add(emptyBlock, 0, 0);
		 if (removedItem.getTypeOfItem() == "Armor") this.activateItemPane.add(emptyBlock, 1, 0);
	}

	public boolean isItemtypeActivate(Item item) {
		return myActivateItem.containsKey(item.getTypeOfItem());
	}
	
	
	
	public GridPane getActivateItemPane() {
		return this.activateItemPane;
	}
	
	public Map<String, Item> getMyActivateItem() {
		return myActivateItem;
	}


	public void MyInventoryUpdate(Item newItem) {
		// MyInventory update only when we add new item in myInventory List.
		
		int count = 0;
		for	(int row = 0 ; row < 3 ; row++) {
			for	(int column=0; column < 6 ; column++) {
				// add item in myInventory to Inventory empty block.
				if (count < myInventory.size()) {
					this.add(myInventory.get(count), column, row);
					count++;
				}
				// add empty image to Inventory another empty block.
				else {
					ImageView emptyBlock = new ImageView(new Image(ClassLoader.getSystemResource
							("ItemImage/InventoryBlock.png").toString()));
					emptyBlock.setFitHeight(63);
					emptyBlock.setFitWidth(63);
					this.add(emptyBlock, column, row);
				}
			}
		}
	}
	
	public void myInventoryPaneAdd(Item newItem) {
		int column = myInventory.indexOf(newItem);
		this.add(newItem, column, 0);
	}
	
	public void myInventoryPaneRemove(Item removedItem) {
		ImageView activateBlock = new ImageView(new Image(ClassLoader.getSystemResource
				("ItemImage/ActivateBlock.png").toString()));
		int column = myInventory.indexOf(removedItem);
		this.add(activateBlock, column, 0);
	}
	
	public void myInventoryPaneClear() {
		for	(int row = 0 ; row < 3 ; row++) {
			for	(int column=0; column < 6 ; column++) {
				ImageView emptyBlock = new ImageView(new Image(ClassLoader.getSystemResource
						("ItemImage/InventoryBlock.png").toString()));
				emptyBlock.setFitHeight(63);
				emptyBlock.setFitWidth(63);
				this.add(emptyBlock, column, row);
			}
		}
	}
	
	public void myActivePaneClear() {
		// myActivePaneClear mean replace all activate item block with empty block image.
		
		for	(int column=0; column < 3 ; column++) {
			ImageView emptyBlock = new ImageView(new Image(ClassLoader.getSystemResource
					("ItemImage/InventoryBlock.png").toString()));
			emptyBlock.setFitHeight(63);
			emptyBlock.setFitWidth(63);
			this.activateItemPane.add(emptyBlock, column, 0);
		}
		Label tSword = new Label("Sword"); tSword.setTextFill(Color.WHITE); tSword.setAlignment(Pos.CENTER);
		Label tArmor = new Label("Armor"); tArmor.setTextFill(Color.WHITE); tArmor.setAlignment(Pos.CENTER);
		Label tOther = new Label("Other"); tOther.setTextFill(Color.WHITE); tOther.setAlignment(Pos.CENTER);
		this.activateItemPane.add(tSword, 0, 1);this.activateItemPane.add(tArmor, 1, 1);this.activateItemPane.add(tOther, 2, 1);
	}
	
}
