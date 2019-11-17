package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Main;
import item.GoldenSword;
import object.MoveableEnemy;

public class Vengefly extends MoveableEnemy {
	
	private static final double vision = 200000;
	
	public Vengefly(double x, double y) {
		super(x, y, 130, 80);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Vengefly.png").toString(), 130, 80, false, true)));
		artList.add("normal");
		friction = 0.05;
		speed = 5;
		maxHp = 40;
		attackDamage = 20;
	}
	
	public void setMovement() {
		switch(cerrentStage) {
		case "idle":
			dx -= (dx)*friction;
			dy -= (dy)*friction;
			if (Math.pow((Main.hero.getX() + Main.hero.getSize()[0]/2) - (x + size[0]/2), 2) + 
					Math.pow((Main.hero.getY() + Main.hero.getSize()[1]/2) - (y + size[1]/2), 2)
					< vision) {
				changeArt("normal");
			}
			break;
		case "normal":
			dx += ((turnLeft ? -speed : speed) - dx)*friction;
			dy += (((Main.hero.getY()+Main.hero.getSize()[1]/2 < y+size[1]/2) ? -speed : speed) - dy)*friction;
			turn((Main.hero.getX() + Main.hero.getSize()[0]/2) < (x + size[0]/2));
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