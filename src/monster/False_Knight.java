package monster;

import application.Delay;
import application.Main;
import object.Boss;
import object.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class False_Knight extends Boss {
	
	public False_Knight(double x, double y) {
		super(x, y, 200, 250);
		body.getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight.png").toString(), 550, 350, false, true)));
		body.getChildren().get(0).setLayoutY(-100);
		artList.add("normal");
		body.getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight_Charge.png").toString(), 500, 300, false, true)));
		body.getChildren().get(1).setLayoutY(-50);
		artList.add("charge");
		body.getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight_Slam.png").toString(), 600, 250, false, true)));
		artList.add("slam");
		body.getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight_Jumping.png").toString(), 550, 330, false, true)));
		body.getChildren().get(3).setLayoutY(-80);
		artList.add("jump");
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
				hold = new Delay(500);
				break;
			}else {
				dy = -25;
				inAir = true;
				changeArt("jump");
				double rng2 = Math.random()*4 - 1;
				hold = new Delay(1000, ((Main.hero.getX()+Main.hero.getSize()[0]/2) - (x+size[0]/2))*0.01 + (turnLeft ? rng2 :-rng2));
				break;
			}
		case "charge":
			Projectile shockwave = new Projectile(ClassLoader.getSystemResource("Effect/shockwave.png").toString(), 
					x+(turnLeft ? -100 : 200), y+50, 100, 200, turnLeft ? -10 : 10, 0);
			shockwave.getBody().setScaleX(turnLeft ? -1 : 1);
			Main.world.addObject(shockwave);
			if(Main.hero.hitCheck(turnLeft ? x-300 : x+200, y-100, 250, 350)) {
				Main.hero.attacked(attackDamage, turnLeft ? -25 : 25, 15);
			}
			changeArt("slam");
			hold = new Delay(200);
			break;
		case "slam":
			changeArt("normal");
			hold = new Delay(800);
			break;
		case "jump":
			if(Main.hero.hitCheck(turnLeft ? x-300 : x+200, y-100, 250, 350)) {
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
		body.getChildren().get(artList.indexOf(art)).setVisible(true);
	}
	
	public void turn() {
		body.getChildren().get(0).setLayoutX(turnLeft ? -125 : -225);
		body.getChildren().get(1).setLayoutX(turnLeft ? -30 : -270);
		body.getChildren().get(2).setLayoutX(turnLeft ? -400 : 0);
		body.getChildren().get(3).setLayoutX(turnLeft ? -70 : -280);
		super.turn();
	}

}
