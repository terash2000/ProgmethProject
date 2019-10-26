package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.MoveableEnemy;

public class Glimback extends MoveableEnemy {
	
	public Glimback(double x, double y) {
		super(200, 180, x, y);
		body.getChildren().add(new ImageView(new Image("file:image/Character/Glimback.png",200,200,false,true)));
		body.getChildren().get(0).setLayoutY(-20);
		speed = 5;
		friction = 0.02;
		attackDamage = 20;
	}
	
	public void die() {
		
	}

}
