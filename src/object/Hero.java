package object;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import application.Delay;
import application.Main;
import map.MapName;

public class Hero extends MoveableCharacter {
	
	private double divePower = 1.5;
	private double jumpPower = 16;
	private double doubleJumpPower = 10;
	private double dashPower = 25;
	private double unstableFriction = 0.1;
	private long jumpTime = 180;
	private long attackTime = 400;
	private long dashTime = 120;
	private long dashCooldownTime = 450;
	private long recoverTime = 1000;
	private Delay jump = new Delay(0);
	private Delay attackCooldown = new Delay(0);
	private Delay dash = new Delay(0);
	private Delay dashCooldown = new Delay(0);
	private Delay unstable = new Delay(0);
	protected Delay immune = new Delay(0);
	private boolean doubleJumped = true;
	private boolean doubleJumpable, dashable;
	private String attackEffect = ClassLoader.getSystemResource("Effect/attacking.png").toString();
	private Circle light = new Circle(0, 0, 400, new RadialGradient(0, 0, 0, 0, 400, false, 
			CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.TRANSPARENT)));;
	
	public Hero() {
		super(0, 0, 80, 85);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/hero.png").toString(), 80, 100, false, true)));
		getChildren().get(0).setLayoutY(-15);
		artList.add("normal");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/dash.png").toString(), 200, 100, false, true)));
		getChildren().get(0).setVisible(false);
		getChildren().get(1).setLayoutY(-15);
		artList.add("dash");
		speed = 8;
		maxHp = 100;
		hp = 100;
		attackDamage = 20;
		Main.HpBar.setMaxHp(maxHp);
		light.setOpacity(0.5);
	}
	
	private void artCheck() {
		if(dashCheck()) {
			changeArt("dash");
		}else {
			jumpCheck();
			changeArt("normal");
		}
	}
	
	public void move() {
		artCheck();
		Main.HpBar.update(hp);
		super.move();
	}
	
	public void turn(boolean turnLeft) {
		super.turn(turnLeft);
		getChildren().get(1).setLayoutX(turnLeft ? 0 : -120);
	}
	
	public void die() {
		if(Main.world.isBossFight()) {
			Main.world.exitBossFight();
		}
		Main.world.setCerrentMap(MapName.Starter, 500, 1175);
		hp = maxHp;
	}
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		immune.interrupt();
		immune = new Delay(recoverTime);
		unstable.interrupt();
		unstable = new Delay(recoverTime/2);
		dash.interrupt();
		dashable = true;
		super.attacked(damage, knockbackX, knockbackY);
	}
	
	public void changeView() {
		Main.world.changeView();
		setLayoutX(x - Main.world.getViewX());
		setLayoutY(y - Main.world.getViewY());
		light.setLayoutX(x + 40 - Main.world.getViewX());
		light.setLayoutY(y + 42 - Main.world.getViewY());
	}
	
	protected void moveY() {
		if(dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if(dy < 0) {
			try {
				topCheck();
				y += dy;
				inAir = true;
			} catch(HitWallException exception) {
				y += exception.distance;
				dy = 0;
			}
		}else if(dy >= 0) {		
			try {
				landingCheck();
				y += dy;
				inAir = true;
			} catch(HitWallException exception) {
				y += exception.distance;
				dy = 0;
				inAir = false;
				doubleJumped = false;
				doubleJumpable = false;
				if(!dashCooldown.isAlive()) {
					dashable = true;
				}
			}
		}
	}
	
	public void reset() {
		dx = 0;
		dy = 0;
		unstable.interrupt();
		jump.interrupt();
		dash.interrupt();
		immune.interrupt();
		dashCooldown.interrupt();
		dashCooldown = new Delay(100);
		doubleJumped = true;
		doubleJumpable = false;
		dashable = false;
	}
	
	protected void leftWallCheck() throws HitWallException {
		if(x + dx < 0 && Main.world.getCerrentMap().getLeftMap() != null
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getLeftMap().travel();
		}else {
			super.leftWallCheck();
		}
	}
	
	protected void rightWallCheck() throws HitWallException {
		if(x + dx > Main.world.getCerrentMap().getWidth() - size[0]
				&& Main.world.getCerrentMap().getRightMap() != null
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getRightMap().travel();
		}else {
			super.rightWallCheck();
		}
	}
	
	protected void topCheck() throws HitWallException {
		if(y + dy < 0 && Main.world.getCerrentMap().getUpperMap() != null
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getUpperMap().travel();
			dy = -28;
		}else {
			super.topCheck();
		}
	}
	
	
	protected void landingCheck() throws HitWallException {
		if(y + dy > Main.world.getCerrentMap().getHeight() - size[1] 
				&& Main.world.getCerrentMap().getLowerMap() != null
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getLowerMap().travel();
		}else {
			super.landingCheck();
		}
	}
	
	public boolean hitCheck(double x, double y, double width, double height) {
		if(immune.isAlive()) {
			return false;
		}
		return super.hitCheck(x, y, width, height);
	}
	
	public void frontAttack() {
		if(!attackCooldown.isAlive()) {
			dash.interrupt();
			attackCooldown = new Delay(attackTime);
			Main.world.addObject(
					new Effect(attackEffect, 30, x+dx+(turnLeft?-120:0), y+dy-30, 200, 100, turnLeft, false));
			boolean hit = false;
			for(Destroyable destroyable: new ArrayList<Destroyable>(Main.world.getDestroyableList())) {
				if(destroyable.hitCheck(x+dx+(turnLeft?-120:0), y+dy-30, 200, 100)) {
					destroyable.attacked(attackDamage, turnLeft?-15:15, 0);
					hit = true;
				}
			}
			if(hit) {
				dx += turnLeft?10:-10;
			}
		}
	}
	
	public void upperSlash() {
		if(!attackCooldown.isAlive()) {
			dash.interrupt();
			attackCooldown = new Delay(attackTime);
			Effect effect = new Effect(attackEffect, 20, x+dx+(turnLeft ? -50 : -70), y+dy-75, 200, 100, turnLeft, false);
			effect.setRotate(turnLeft ? 90 : 270);
			Main.world.addObject(effect);
			boolean hit = false;
			for(Destroyable destroyable: new ArrayList<Destroyable>(Main.world.getDestroyableList())) {
				if(destroyable.hitCheck(x+dx+(turnLeft?0:-20), y+dy-125, 100, 200)) {
					destroyable.attacked(attackDamage, 0, 12);
					hit = true;
				}
			}
			if(hit) {
				dy += 8;
			}
		}
	}
	
	public void downwardSlash() {
		if(!attackCooldown.isAlive()) {
			if(inAir) {
				dash.interrupt();
				attackCooldown = new Delay(attackTime);
				Effect effect = new Effect(attackEffect, 20, x+dx+(turnLeft ? -70 : -50), y+dy+60, 200, 100, turnLeft, false);
				effect.setRotate(turnLeft ? 270 : 90);
				Main.world.addObject(effect);
				for(Destroyable destroyable: new ArrayList<Destroyable>(Main.world.getDestroyableList())) {
					if(destroyable.hitCheck(x+dx+(turnLeft?-20:0), y+dy+10, 100, 200)) {
						destroyable.attacked(attackDamage, 0, -5);
						dy = -15;
					}
				}
			}else {
				frontAttack();
			}
		}
	}
	
	public void walk(int direction) {
		if(unstable.isAlive()) {
			dx += (speed*direction - dx)*unstableFriction;
		}else if(inAir && dx <= speed) {
			dx += (speed*direction - dx)*unstableFriction;
		}else {
			dx += (speed*direction - dx)*friction;
		}
		dy += gravity;
	}
	
	public void jumping() {
		if(!inAir) {
			dashable = true;
			jump(jumpPower);
		}else if(doubleJumpable) {
			doubleJumpable = false;
			doubleJumped = true;
			jump(doubleJumpPower);
		}
	}
	
	public void jump(double power) {
		dash.interrupt();
		jump = new Delay(jumpTime, -power);
		dy = jump.getData();
	}
	
	public void stopJump() {
		jump.interrupt();
		if(inAir && !doubleJumped) {
			doubleJumpable = true;
		}
	}
	
	private boolean jumpCheck() {
		if(jump.isAlive()) {
			dy = jump.getData();
			return true;
		}
		return false;
	}
	
	public void dash() {
		if(dashable && !dashCooldown.isAlive()) {
			dx = turnLeft ? -dashPower : dashPower;
			dy = 0;
			dash = new Delay(dashTime, turnLeft ? -dashPower : dashPower);
			dashCooldown = new Delay(dashCooldownTime);
			dashable = false;
			unstable.interrupt();
			unstable = new Delay(dashCooldownTime*2/3);
		}
	}
	
	private boolean dashCheck() {
		if(dash.isAlive()) {
			dx = dash.getData();
			turn(dash.getData() < 0);
			dy = 0;
			return true;
		}
		return false;
	}

	public double getDivePower() {
		return divePower;
	}

	public void setDivePower(double divePower) {
		this.divePower = divePower < 0 ? 0 : divePower;
	}

	public double getJumpPower() {
		return jumpPower;
	}

	public void setJumpPower(double jumpPower) {
		this.jumpPower = jumpPower < 0 ? 0 : jumpPower;
	}

	public Delay getJump() {
		return jump;
	}

	public double getDoubleJumpPower() {
		return doubleJumpPower;
	}

	public void setDoubleJumpPower(double doubleJumpPower) {
		this.doubleJumpPower = doubleJumpPower < 0 ? 0 : doubleJumpPower;
	}

	public double getDashPower() {
		return dashPower;
	}

	public void setDashPower(double dashPower) {
		this.dashPower = dashPower < 0 ? 0 : dashPower;
	}

	public long getDashCooldownTime() {
		return dashCooldownTime;
	}

	public void setDashCooldownTime(long dashCooldownTime) {
		this.dashCooldownTime = dashCooldownTime < 0 ? 0 : dashCooldownTime;
	}

	public Delay getDash() {
		return dash;
	}

	public Delay getUnstable() {
		return unstable;
	}

	public Circle getLight() {
		return light;
	}

}
