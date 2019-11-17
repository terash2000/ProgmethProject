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
	
	private double jumpPower = 16;
	private double doubleJumpPower = 12;
	private double dashPower = 25;
	private long attackSpeed = 400;
	private long dashCooldownTime = 450;
	private boolean doubleJumped, doubleJumpable, dashable;
	private Delay jump = new Delay(0);
	private Delay attackCooldown = new Delay(0);
	private Delay dashCooldown = new Delay(0);
	private Delay unstable = new Delay(0);
	private Delay immune = new Delay(0);
	
	private static final String attackEffect = ClassLoader.getSystemResource("Effect/attacking.png").toString();
	private static final Circle light = new Circle(0, 0, 1500, new RadialGradient(0, 0, 0, 0, 600, false, 
			CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(250, 250, 250, 0.3)), 
			new Stop(0.5, Color.TRANSPARENT), new Stop(1, Color.rgb(0, 0, 0, 0.9))));
	private static final double unstableFriction = 0.1;
	private static final double attackRange = 200;
	private static final double attackHeight = 100;
	private static final double frontSlashKnockPower = 15;
	private static final double frontSlashKnockBack = 10;
	private static final double upperSlashKnockPower = 12;
	private static final double upperSlashKnockBack = 8;
	private static final double downwardSlashKnockPower = 5;
	private static final double downwardSlashKnockBack = 15;
	private static final long jumpTime = 180;
	private static final long dashTime = 120;
	private static final long recoverTime = 1000;
	private static final long attackEffectTime = 30;
	
	public Hero() {
		super(0, 0, 80, 85);
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/hero.png").toString(), 80, 100, false, true)));
		getChildren().get(0).setLayoutY(-15);
		artList.add("normal");
		getChildren().add(new ImageView(new Image(
				ClassLoader.getSystemResource("Character/dash.png").toString(), 200, 100, false, true)));
		getChildren().get(1).setVisible(false);
		getChildren().get(1).setLayoutY(-15);
		artList.add("dash");
		speed = 8;
		maxHp = 100;
		hp = 100;
		attackDamage = 20;
		Main.hpBar.setMaxHp(maxHp);
	}
	
	public void update() {
		dashCheck();
		Main.hpBar.update(hp);
		super.update();
	}
	
	protected void moveY() {
		if ((dy > maxFallSpeed) && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if (dy < 0) {
			try {
				topCheck();
				y += dy;
				inAir = true;
			} catch(HitWallException exception) {
				y += exception.distance;
				dy = 0;
			}
		}else if (dy >= 0) {		
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
	
	protected void leftWallCheck() throws HitWallException {
		if (((x + dx) < 0) && (Main.world.getCerrentMap().getLeftMap() != null)
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getLeftMap().travel();
		}else {
			super.leftWallCheck();
		}
	}
	
	protected void rightWallCheck() throws HitWallException {
		if (((x + dx) > (Main.world.getCerrentMap().getWidth() - size[0]))
				&& (Main.world.getCerrentMap().getRightMap() != null)
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getRightMap().travel();
		}else {
			super.rightWallCheck();
		}
	}
	
	protected void topCheck() throws HitWallException {
		if (((y + dy) < 0) && (Main.world.getCerrentMap().getUpperMap() != null)
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getUpperMap().travel();
			dy = -28;
		}else {
			super.topCheck();
		}
	}
	
	
	protected void landingCheck() throws HitWallException {
		if (((y + dy) > (Main.world.getCerrentMap().getHeight() - size[1]))
				&& (Main.world.getCerrentMap().getLowerMap() != null)
				&& !Main.world.isBossFight()) {
			Main.world.getCerrentMap().getLowerMap().travel();
		}else {
			super.landingCheck();
		}
	}
	
	public void walk(int direction) {
		if (unstable.isAlive()) {
			dx += (speed*direction - dx)*unstableFriction;
		}else if (inAir && dx <= speed) {
			dx += (speed*direction - dx)*unstableFriction;
		}else {
			dx += (speed*direction - dx)*friction;
		}
		dy += gravity;
	}
	
	public void jumping() {
		if (!inAir) {
			dashable = true;
			jump(jumpPower);
		}else if (doubleJumpable) {
			doubleJumpable = false;
			doubleJumped = true;
			jump(doubleJumpPower);
		}
		if (jump.isAlive()) {
			dy = jump.getData();
		}
	}
	
	public void jump(double power) {
		stageHolder.interrupt();
		jump = new Delay(jumpTime, -power);
	}
	
	public void stopJump() {
		jump.interrupt();
		if (inAir && !doubleJumped) {
			doubleJumpable = true;
		}
	}
	
	public void dash() {
		if (dashable && !dashCooldown.isAlive()) {
			dx = turnLeft ? -dashPower : dashPower;
			dy = 0;
			holdStage(dashTime);
			changeArt("dash");
			dashCooldown = new Delay(dashCooldownTime);
			dashable = false;
			unstable.interrupt();
			unstable = new Delay(dashCooldownTime*2/3);
		}
	}
	
	private boolean dashCheck() {
		if (stageHolder.isAlive()) {
			dx = turnLeft ? -dashPower : dashPower;
			dy = 0;
			return true;
		}
		return false;
	}
	
	public void frontSlash() {
		if (!attackCooldown.isAlive()) {
			stageHolder.interrupt();
			attackCooldown = new Delay(attackSpeed);
			Main.world.addObject(
					new Effect(attackEffect, attackEffectTime, x + dx + (turnLeft?-120:0), y + dy - 30, 
							attackRange, attackHeight, turnLeft, false));
			for (Destroyable destroyable: new ArrayList<Destroyable>(Main.world.getDestroyableList())) {
				if (destroyable.hitCheck(x + dx + (turnLeft ? -120 : 0), y + dy - 30, attackRange, attackHeight)) {
					destroyable.attacked(attackDamage, (turnLeft ? -frontSlashKnockPower : frontSlashKnockPower), 0);
					dx += (turnLeft ? frontSlashKnockBack : -frontSlashKnockBack);
				}
			}
		}
	}
	
	public void upperSlash() {
		if (!attackCooldown.isAlive()) {
			stageHolder.interrupt();
			attackCooldown = new Delay(attackSpeed);
			Effect effect = new Effect(attackEffect, attackEffectTime, x + dx + (turnLeft ? -50 : -70), y + dy - 75, 
					attackRange, attackHeight, turnLeft, false);
			effect.setRotate(turnLeft ? 90 : 270);
			Main.world.addObject(effect);
			for (Destroyable destroyable: new ArrayList<Destroyable>(Main.world.getDestroyableList())) {
				if (destroyable.hitCheck(x + dx + (turnLeft ? 0 : -20), y + dy - 125, attackHeight, attackRange)) {
					destroyable.attacked(attackDamage, 0, upperSlashKnockPower);
					dy += upperSlashKnockBack;
				}
			}
		}
	}
	
	public void downwardSlash() {
		if (!attackCooldown.isAlive()) {
			if (inAir) {
				stageHolder.interrupt();
				attackCooldown = new Delay(attackSpeed);
				Effect effect = new Effect(attackEffect, attackEffectTime, x + dx + (turnLeft ? -70 : -50), y + dy + 60, 
						attackRange, attackHeight, turnLeft, false);
				effect.setRotate(turnLeft ? 270 : 90);
				Main.world.addObject(effect);
				for (Destroyable destroyable: new ArrayList<Destroyable>(Main.world.getDestroyableList())) {
					if (destroyable.hitCheck(x + dx + (turnLeft ? -20 : 0), y + dy + 10, attackHeight, attackRange)) {
						destroyable.attacked(attackDamage, 0, -downwardSlashKnockPower);
						dy = -downwardSlashKnockBack;
					}
				}
			}else {
				frontSlash();
			}
		}
	}
	
	public boolean hitCheck(double x, double y, double width, double height) {
		if (immune.isAlive()) {
			return false;
		}
		return super.hitCheck(x, y, width, height);
	}
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		immune.interrupt();
		immune = new Delay(recoverTime);
		unstable.interrupt();
		unstable = new Delay(recoverTime/2);
		stageHolder.interrupt();
		dashable = true;
		super.attacked(damage, knockbackX, knockbackY);
	}
	
	public void die() {
		Main.world.setBossFight(false);
		Main.world.setCerrentMap(MapName.Starter, 500, 1175);
		hp = maxHp;
	}
	
	protected void reset() {
		unstable.interrupt();
		jump.interrupt();
		immune.interrupt();
		dashCooldown.interrupt();
		stageHolder.interrupt();
		dashCooldown = new Delay(100);
		doubleJumped = true;
		doubleJumpable = false;
		dashable = false;
		super.reset();
	}
	
	protected void changeStage() {
		changeArt("normal");
	}
	
	public void turn(boolean turnLeft) {
		if (!stageHolder.isAlive()) {
			super.turn(turnLeft);
			getChildren().get(1).setLayoutX(turnLeft ? 0 : -120);
		}
	}
	
	public void changeView() {
		Main.world.changeBackgroundView();
		light.setLayoutX(x + 40 - Main.world.getViewX());
		light.setLayoutY(y + 42 - Main.world.getViewY());
		super.changeView();
	}
	
	public void setLocation(double x, double y) {
		reset();
		this.x = x;
		this.y = y;
		changeView();
		for (Updateable updateable: Main.world.getObjectList()) {
			updateable.changeView();
		}
	}

	public double getJumpPower() {
		return jumpPower;
	}

	public void setJumpPower(double jumpPower) {
		this.jumpPower = jumpPower < 0 ? 0 : jumpPower;
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

	public long getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(long attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public long getDashCooldownTime() {
		return dashCooldownTime;
	}

	public void setDashCooldownTime(long dashCooldownTime) {
		this.dashCooldownTime = dashCooldownTime < 0 ? 0 : dashCooldownTime;
	}

	public Circle getLight() {
		return light;
	}

}
