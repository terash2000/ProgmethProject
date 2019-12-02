package item;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import object.Hero;

public class Item extends Button{
	protected int attackDamageBonus;
	protected int hpBonus;
	protected int maxHpBonus;
	protected int jumpPowerBonus;
	
	public Item(int attackDamageBonus, int jumpPowerBonus, int maxHpBonus, int hpBonus) {
		this.attackDamageBonus = attackDamageBonus;
		this.hpBonus = hpBonus;
		this.jumpPowerBonus = jumpPowerBonus;
		this.maxHpBonus = maxHpBonus;
		
		setPadding(new Insets(5));
		this.setPrefHeight(1);
		this.setPrefWidth(1);
		setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	public int getAttackDamageBonus() {
		return attackDamageBonus;
	}

	public int getHpBonus() {
		return hpBonus;
	}

	public int getMaxHpBonus() {
		return maxHpBonus;
	}

	public int getJumpPowerBonus() {
		return jumpPowerBonus;
	}
	
	public String getTypeOfItem() {
		return "NULL" ;
	}
	
	public void applyBonuses() {
	
	}
	
	public void unapplyBonuses() {

	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));	
	}

	
	
	
}
