package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.HitWallException;
import object.MoveableEnemy;

public class Glimback extends MoveableEnemy {
	
	public Glimback(double x, double y) {
		super(x, y, 200, 180);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Glimback.png").toString(), 200, 200, false, true)));
		getChildren().get(0).setLayoutY(-20);
		speed = 3;
		maxHp = 100;
		attackDamage = 20;
	}
	
	public void setMovement() {
		dx += ((turnLeft ? -speed : speed) - dx)*friction;
		dy += gravity;
	}
	
	protected void moveX() {
		if (dx < 0) {
			try {
				leftWallCheck();
				x +=dx;
			} catch(HitWallException e) {
				turn(false);
			}
		}else if (dx > 0) {
			try {
				rightWallCheck();
				x +=dx;
			} catch(HitWallException e) {
				turn(true);
			}
		}
	}

}
