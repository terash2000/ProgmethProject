package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.MoveableEnemy;

public class Glimback extends MoveableEnemy {
	
	public Glimback(double x, double y) {
		super(x, y, 200, 180);
		body.getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/Glimback.png").toString(), 200, 200, false, true)));
		body.getChildren().get(0).setLayoutY(-20);
		speed = 3;
		maxHp = 100;
		attackDamage = 20;
	}
	
	public void setMovement() {
		dx += ((turnLeft ? -speed : speed) - dx)*friction;
		dy += gravity;
	}
	
	protected void moveX() {
		if(dx < 0 && leftWallCheck()) {
			turn(false);
		}else if(rightWallCheck()) {
			turn(true);
		}
		x +=dx;
	}

}
