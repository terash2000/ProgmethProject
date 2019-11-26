package monster;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Main;
import application.Music;
import object.Boss;
import object.Projectile;

public class False_Knight extends Boss {
	
	private static final double shockwaveSpeed = 15;
	private static final double shockwaveWidth = 100;
	private static final double shockwaveHeight = 200;
	private static final double jumpSpeed = 25;
	private static final long recoverTime = 800;
	private static final long jumpTime = 700;
	private static final long chargeTime = 500;
	private static final long slamTime = 200;
	private static final long fallTime = 1200;
	
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
		maxHp = 600;
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
			turn(Main.hero.getCenterX() < getCenterX());
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
		}
	}
	
	protected void changeStage() {
		switch (cerrentStage) {
		case "idle":
			inAir = true;
			changeArt("jump");
			holdStage(fallTime);
			break;
		case "normal":
			double nextMove = Math.random();
			if (nextMove < 0.4) {
				changeArt("charge");
				holdStage(chargeTime);
				break;
			}else {
				dy = -jumpSpeed;
				changeArt("jump");
				double jumpRange = Math.random()*4 - 1;
				speed = ((Main.hero.getX() + Main.hero.getSize()[0]/2) 
						- (x + size[0]/2))*0.01 + (turnLeft ? jumpRange : -jumpRange);
				holdStage(jumpTime);
				break;
			}
		case "charge":
			Projectile shockwave = new Projectile(ClassLoader.getSystemResource("Effect/shockwave.png").toString(), 
					x + (turnLeft ? -100 : 200), y + 50, shockwaveWidth, shockwaveHeight, 
					(turnLeft ? -shockwaveSpeed : shockwaveSpeed), 0, attackDamage);
			shockwave.setScaleX(turnLeft ? -1 : 1);
			Main.world.addObject(shockwave);
			if(Main.hero.hitCheck(x + (turnLeft ? -300 : 200), y - 100, 250, 350)) {
				Main.hero.attacked(attackDamage, (turnLeft ? -heroKnockBackX : heroKnockBackX), heroKnockBackY);
			}
			changeArt("slam");
			holdStage(slamTime);
			break;
		case "slam":
			changeArt("normal");
			holdStage(recoverTime);
			break;
		case "jump":
			changeArt("leap");
			break;
		case "leap":
			if(Main.hero.hitCheck(x + (turnLeft ? -300 : 200), y - 150, 250, 400)) {
				Main.hero.attacked(attackDamage, (turnLeft ? -heroKnockBackX : heroKnockBackX), heroKnockBackY);
			}
			changeArt("slam");
			holdStage(slamTime);
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
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		if (knockbackX != 0) {
			dx += knockbackX;
		}
		if (knockbackY != 0) {
			dy += -knockbackY;
		}
		hp = damage > hp ? 0 : hp - damage;
		if(hp == 0) {
			die();
		}
	}
	
	protected void reset() {
		super.reset();
		speed = 0;
		cerrentStage = "idle";
	}
}
