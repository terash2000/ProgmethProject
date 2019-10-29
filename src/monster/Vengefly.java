package monster;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.MoveableEnemy;

public class Vengefly extends MoveableEnemy {
	
	public Vengefly(double x, double y) {
		super(x, y, 130, 80);
		body.getChildren().add(new ImageView(new Image("file:image/Character/Vengefly.png",130,80,false,true)));
		friction = 0.05;
		speed = 5;
		maxHp = 50;
		attackDamage = 20;
	}
	
	public void setMovement() {
		dx += ((turnLeft ? -speed : speed) - dx)*friction;
		dy += ((Main.hero.getY()+Main.hero.getSize()[1]/2 < y+size[1]/2 ? -speed : speed) - dy)*friction;
		turnLeft = Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2;
	}

}