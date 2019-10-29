package monster;

import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Boss;;

public class False_Knight extends Boss {
	
	public False_Knight(double x, double y) {
		super(x, y, 200, 250);
		body.getChildren().add(new ImageView(new Image("file:image/Character/False_Knight.png",500,300,false,true)));
		body.getChildren().get(0).setLayoutY(-50);
		friction = 0.2;
		speed = 2;
		maxHp = 150;
		attackDamage = 20;
	}
	
	public void setMovement() {
		dx += ((Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2 ? -speed : speed) - dx)*friction;
		dy += gravity;
		turnLeft = Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2;
	}
	
	public void turn() {
		body.getChildren().get(0).setLayoutX(turnLeft ? -100 : -200);
		super.turn();
	}

}
