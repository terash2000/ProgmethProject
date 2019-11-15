package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Main;
import application.Music;
import object.Boss;
import object.Projectile;

public class False_Knight extends Boss {
	
	public False_Knight(double x, double y) {
		super(x, y, 200, 250);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight.png").toString(), 550, 350, false, true)));
		getChildren().get(0).setLayoutY(-100);
		artList.add("normal");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight_Charge.png").toString(), 500, 300, false, true)));
		getChildren().get(1).setLayoutY(-50);
		artList.add("charge");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight_Slam.png").toString(), 600, 250, false, true)));
		artList.add("slam");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight_Jumping.png").toString(), 550, 330, false, true)));
		getChildren().get(3).setLayoutY(-80);
		artList.add("jump");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/False_Knight_Leaping.png").toString(), 460, 525, false, true)));
		getChildren().get(4).setLayoutY(-275);
		artList.add("leap");
		friction = 0.5;
		maxHp = 800;
		attackDamage = 25;
		bossTheme = Music.Guren_no_Yumiya;
	}
	
	public void setMovement() {	
		dx -= dx*friction;
		dy += gravity;
		switch(cerrentStage) {
		case "idle":
			if ( Main.hero.getX() > x+size[0]) {
				startBossFight();
				changeStage();
			}else {
				dy = 0;
			}
			break;
		case "normal":
			turn(Main.hero.getX() + Main.hero.getSize()[0]/2 < x + size[0]/2);
			break;
		case "jump":
			dx += speed;
			break;
		case "leap":
			if (inAir) {
				dx += speed;
			}else {
				changeStage();
			}
			break;
		default:
			break;
		}
	}
	
	public void changeStage() {
		switch (cerrentStage) {
		case "idle":
			inAir = true;
			changeArt("jump");
			holdStage(1200);
			break;
		case "normal":
			double nextMove = Math.random();
			if (nextMove < 0.4) {
				changeArt("charge");
				holdStage(500);
				break;
			}else {
				dy = -25;
				changeArt("jump");
				double jumpRange = Math.random()*4 - 1;
				speed = ((Main.hero.getX() + Main.hero.getSize()[0]/2) 
						- (x + size[0]/2))*0.01 + (turnLeft ? jumpRange : -jumpRange);
				holdStage(700);
				break;
			}
		case "charge":
			Projectile shockwave = new Projectile(ClassLoader.getSystemResource("Effect/shockwave.png").toString(), 
					x + (turnLeft ? -100 : 200), y + 50, 100, 200, turnLeft ? -15 : 15, 0, attackDamage);
			shockwave.setScaleX(turnLeft ? -1 : 1);
			Main.world.addObject(shockwave);
			if(Main.hero.hitCheck(x + (turnLeft ? -300 : 200), y - 100, 250, 350)) {
				Main.hero.attacked(attackDamage, turnLeft ? -25 : 25, 15);
			}
			changeArt("slam");
			holdStage(200);
			break;
		case "slam":
			changeArt("normal");
			holdStage(800);
			break;
		case "jump":
			changeArt("leap");
			break;
		case "leap":
			if(Main.hero.hitCheck(x + (turnLeft ? -300 : 200), y - 150, 250, 400)) {
				Main.hero.attacked(attackDamage, turnLeft ? -25 : 25, 15);
			}
			changeArt("slam");
			holdStage(200);
			break;
		}
	}
	
	public void turn(boolean turnLeft) {
		super.turn(turnLeft);
		getChildren().get(0).setLayoutX(turnLeft ? -125 : -225);
		getChildren().get(1).setLayoutX(turnLeft ? -30 : -270);
		getChildren().get(2).setLayoutX(turnLeft ? -400 : 0);
		getChildren().get(3).setLayoutX(turnLeft ? -70 : -280);
		getChildren().get(4).setLayoutX(turnLeft ? -100 : -160);
	}
	
	public void reset() {
		speed = 0;
		cerrentStage = "idle";
		super.reset();
	}
}
