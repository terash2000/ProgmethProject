package monster;

import application.Main;
import application.Music;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Boss;

public class Illya extends Boss {
	
	private static final double range = 500;
	
	public Illya(double x, double y) {
		super(x, y, 60, 200);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Illya.png").toString(), 225, 200, false, true)));
		artList.add("normal");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Illya_Attack.png").toString(), 180, 180, false, true)));
		getChildren().get(1).setLayoutY(10);
		artList.add("attack");
		friction = 0.05;
		speed = 5;
		maxHp = 500;
		attackDamage = 25;
		bossTheme = Music.Get_Goal;
	}
	
	public void setMovement() {
		double distanceX = Main.hero.getCenterX() - getCenterX();
		double distanceY = Main.hero.getCenterY() - getCenterY();
		double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		switch(cerrentStage) {
		case "idle":
			if (distance < range) {
				startBossFight();
				changeStage();
			}
			break;
		case "normal":
			turn(Main.hero.getCenterX() < getCenterX());
			if (distance > range) {
				dx += (speed*distanceX/distance - dx)*friction;
				dy += (speed*distanceY/distance - dy)*friction;
			} else {
				dx += ((speed*distanceY/distance)*((distanceX*distanceY) > 0 ? -1 : 1) - dx)*friction;
				dy += ((speed*distanceX/distance)*((distanceX*distanceY) > 0 ? -1 : 1) - dy)*friction;
			}
			break;
		case "attack":
			turn(Main.hero.getCenterX() < getCenterX());
			if (distance > range) {
				dx += (speed*distanceX/distance - dx)*friction;
				dy += (speed*distanceY/distance - dy)*friction;
			} else {
				dx += ((speed*distanceY/distance)*((distanceX*distanceY) > 0 ? -1 : 1) - dx)*friction;
				dy += ((speed*distanceX/distance)*((distanceX*distanceY) > 0 ? -1 : 1) - dy)*friction;
			}
			break;
		}
	}
	
	protected void changeStage() {
		switch (cerrentStage) {
		case "idle":
			changeArt("normal");
			turn((Main.hero.getX() + Main.hero.getSize()[0]/2) < (x + size[0]/2));
			setVisible(true);
			holdStage(3000);
			break;
		case "normal":
			changeArt("attack");
			holdStage(1000);
			break;
		case "attack":
			changeArt("normal");
			holdStage(3000);
			break;
		}
	}
	
	public void turn(boolean turnLeft) {
		super.turn(turnLeft);
		getChildren().get(0).setLayoutX(turnLeft ? -70 : -95);
		getChildren().get(1).setLayoutX(turnLeft ? -30 : -90);
	}
	
	protected void reset() {
		super.reset();
		setVisible(false);
		cerrentStage = "idle";
	}

}
