package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Main;
import item.GoldenSword;
import object.MoveableEnemy;

public class Vengefly extends MoveableEnemy {
	
	private static final double vision = 500;
	
	public Vengefly(double x, double y) {
		super(x, y, 130, 80);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Vengefly.png").toString(), 130, 80, false, true)));
		artList.add("normal");
		friction = 0.05;
		speed = 7;
		maxHp = 40;
		attackDamage = 20;
	}
	
	public void setMovement() {
		double distanceX = Main.hero.getCenterX() - getCenterX();
		double distanceY = Main.hero.getCenterY() - getCenterY();
		double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		switch(cerrentStage) {
		case "idle":
			dx -= dx*friction;
			dy -= dy*friction;
			if (distance < vision) {
				changeArt("normal");
			}
			break;
		case "normal":
			dx += (speed*distanceX/distance - dx)*friction;
			dy += (speed*distanceY/distance - dy)*friction;
			turn(Main.hero.getCenterX() < getCenterX());
			break;
		}
	}

	protected void reset() {
		super.reset();
		cerrentStage = "idle";
	}
	
	public void die() {
		super.die();
		Main.inventory.addItem(new GoldenSword());
	}
}