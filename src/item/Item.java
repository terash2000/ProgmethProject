package item;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
	private int attackDamageBonus;
	private int hpBonus;
	private int maxHpBonus;
	private int jumpPowerBonus;
	
	public Item(int attackDamageBonus, int jumpPowerBonus, int maxHpBonus, int hpBonus) {
		this.attackDamageBonus = attackDamageBonus;
		this.hpBonus = hpBonus;
		this.jumpPowerBonus = jumpPowerBonus;
		this.maxHpBonus = maxHpBonus;
		
		setPadding(new Insets(5));
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
	
	public void applyBonuses(Hero hero) {
	
	}
	
	public void unapplyBonuses(Hero hero) {

	}
	
	public void highlight() {
		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));

	}
	
	public void unhighlight() {
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));	
	}

	
	
	
}
