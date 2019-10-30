package monster;

import application.Delay;
import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Boss;;

public class False_Knight extends Boss {
	
	public False_Knight(double x, double y) {
		super(x, y, 200, 250);
		body.getChildren().add(new ImageView(new Image("file:image/Character/False_Knight.png",550,350,false,true)));
		body.getChildren().get(0).setLayoutY(-80);
		body.getChildren().add(new ImageView(new Image("file:image/Character/False_Knight_Charge.png",500,300,false,true)));
		body.getChildren().get(1).setLayoutY(-50);
		friction = 0.2;
		speed = 2;
		maxHp = 150;
		attackDamage = 20;
	}
	
	public void setMovement() {
		if(hold.isAlive()) {
			dx -= dx*friction;
			dy += gravity;
			switch(cerrentStage) {
			case "normal":
				turnLeft = Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2;
				break;
			}
				
		}else {
			changeStage(cerrentStage);
		}
	}
	
	protected void changeStage(String stage) {
		switch(stage) {
		case "normal":
			changeArt("charge");
			hold = new Delay(1000);
			break;
		case "charge":
			if(Main.hero.hitCheck(turnLeft ? x-250 : x+200, y, 250, size[1])) {
				Main.hero.attacked(attackDamage, turnLeft ? -25 : 25, 15);
			}
			changeArt("normal");
			hold = new Delay(2000);
			break;
		}
	}
	
	protected void changeArt(String art) {
		cerrentStage = art;
		body.getChildren().forEach((i)->{
			i.setVisible(false);
		});
		switch(art) {
		case "normal":
			body.getChildren().get(0).setVisible(true);
			break;
		case "charge":
			body.getChildren().get(1).setVisible(true);
			break;
		}
	}
	
	public void turn() {
		body.getChildren().get(0).setLayoutX(turnLeft ? -125 : -225);
		body.getChildren().get(1).setLayoutX(turnLeft ? -50 : -250);
		super.turn();
	}

}
