package monster;

import application.Delay;
import application.Main;
import object.Boss;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class False_Knight extends Boss {
	
	public False_Knight(double x, double y) {
		super(x, y, 200, 250);
		body.getChildren().add(new ImageView(new Image("file:image/Character/False_Knight.png",550,350,false,true)));
		body.getChildren().get(0).setLayoutY(-100);
		body.getChildren().add(new ImageView(new Image("file:image/Character/False_Knight_Charge.png",500,300,false,true)));
		body.getChildren().get(1).setLayoutY(-50);
		body.getChildren().add(new ImageView(new Image("file:image/Character/False_Knight_Slam.png",600,250,false,true)));
		body.getChildren().add(new ImageView(new Image("file:image/Character/False_Knight_Jumping.png",550,330,false,true)));
		body.getChildren().get(3).setLayoutY(-80);
		friction = 0.5;
		maxHp = 800;
		attackDamage = 25;
	}
	
	public void setMovement() {
		if(hold.isAlive()) {
			dx -= dx*friction;
			dy += gravity;
			switch(cerrentStage) {
			case "normal":
				turnLeft = Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2;
				break;
			case "jump":
				if(inAir) {
					dx += hold.getData();
				}else {
					hold.interrupt();
					changeStage();
				}
				break;
			}
				
		}else {
			changeStage();
		}
	}
	
	protected void changeStage() {
		switch(cerrentStage) {
		case "normal":
			double rng = Math.random();
			if(rng < 0.4) {
				changeArt("charge");
				hold = new Delay(600);
				break;
			}else {
				dy = -25;
				inAir = true;
				changeArt("jump");
				double rng2 = Math.random()*2+1;
				hold = new Delay(2000, ((Main.hero.getX()+Main.hero.getSize()[0]/2) - (x+size[0]/2))*0.01 + (turnLeft ? rng2 :-rng2));
				break;
			}
		case "charge":
			if(Main.hero.hitCheck(turnLeft ? x-300 : x+200, y, 250, 300)) {
				Main.hero.attacked(attackDamage, turnLeft ? -25 : 25, 15);
			}
			changeArt("slam");
			hold = new Delay(200);
			break;
		case "slam":
			changeArt("normal");
			hold = new Delay(1500);
			break;
		case "jump":
			if(Main.hero.hitCheck(turnLeft ? x-300 : x+200, y, 250, 300)) {
				Main.hero.attacked(attackDamage, turnLeft ? -25 : 25, 15);
			}
			changeArt("slam");
			hold = new Delay(200);
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
		case "slam":
			body.getChildren().get(2).setVisible(true);
			break;
		case "jump":
			body.getChildren().get(3).setVisible(true);
			break;
		}
	}
	
	public void turn() {
		body.getChildren().get(0).setLayoutX(turnLeft ? -125 : -225);
		body.getChildren().get(1).setLayoutX(turnLeft ? -30 : -270);
		body.getChildren().get(2).setLayoutX(turnLeft ? -400 : 0);
		body.getChildren().get(3).setLayoutX(turnLeft ? -70 : -280);
		super.turn();
	}

}
