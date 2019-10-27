package object;

import menu.HpBar;
import application.Delay;
import application.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import map.MapName;;

public class Hero extends MoveableCharacter {
	
	private double divePower = 1.5;
	private double jumpPower = 16;
	private double doubleJumpPower = 14;
	private double dashPower = 25;
	private double unstableFriction = 0.1;
	private long jumpTime = 180;
	private long dashTime = 150;
	private long dashCooldownTime = 450;
	private long recoverTime = 1000;
	private Delay jump = new Delay(0);
	private Delay dash = new Delay(0);
	private Delay dashCooldown = new Delay(0);
	private Delay unstable = new Delay(0);
	private boolean doubleJumped = true;
	private boolean doubleJumpable, dashable;
	private HpBar hpBar;
	
	public Hero() {
		super(0, 0, 80, 85);
		body.getChildren().add(new ImageView(new Image("file:image/Character/hero.png",80,100,false,true)));
		body.getChildren().get(0).setLayoutY(-15);
		body.getChildren().add(new ImageView(new Image("file:image/Character/dash.png",200,100,false,true)));
		body.getChildren().get(1).setVisible(false);
		body.getChildren().get(1).setLayoutY(-15);
		speed = 8;
		maxHp = 100;
		hp = 100;
		hpBar = new HpBar(maxHp);
	}
	
	protected void artCheck() {
		if(dashCheck()) {
			changeArt("dash");
		}else {
			jumpCheck();
			changeArt("normal");
		}
		turn();
		hpBar.update(hp);
	}
	
	protected void changeArt(String art) {
		switch(art) {
		case "normal":
			body.getChildren().get(cerrentArt).setVisible(false);
			body.getChildren().get(0).setVisible(true);
			cerrentArt = 0;
			break;
		case "dash":
			body.getChildren().get(cerrentArt).setVisible(false);
			body.getChildren().get(1).setLayoutX(turnLeft ? 0 : -120);
			body.getChildren().get(1).setVisible(true);
			cerrentArt = 1;
			break;
		}
	}
	
	public void die() {
		Main.worldMap.setCerrentMap(MapName.Starter, 500, 100);
		hp = maxHp;
	}
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		makeImmune(recoverTime);
		makeUnstable(recoverTime/2);
		dash.interrupt();
		super.attacked(damage, knockbackX, knockbackY);
	}
	
	public void changeView() {
		Main.worldMap.changeView();
		body.setLayoutX(x - Main.worldMap.getViewX());
		body.setLayoutY(y - Main.worldMap.getViewY());
	}
	
	public void landing() {
		inAir = false;
		doubleJumped = false;
		doubleJumpable = false;
		if(!dashCooldown.isAlive()) {
			dashable = true;
		}
	}
	
	public void reset() {
		super.reset();
		unstable.interrupt();
		jump.interrupt();
		dash.interrupt();
		dashCooldown.interrupt();
		dashCooldown = new Delay(100);
		doubleJumped = true;
		doubleJumpable = false;
		dashable = false;
	}
	
	protected boolean leftWallCheck() {
		if(x + dx < 0 && Main.worldMap.getCerrentMap().getLeftMap() != null) {
			Main.worldMap.getCerrentMap().getLeftMap().travel();
			return false;
		}
		return super.leftWallCheck();
	}
	
	protected boolean rightWallCheck() {
		if(x + dx > Main.worldMap.getCerrentMap().getWidth() - size[0]
			&& Main.worldMap.getCerrentMap().getRightMap() != null) {
			Main.worldMap.getCerrentMap().getRightMap().travel();
			return false;
		}
		return super.rightWallCheck();
	}
	
	protected boolean topCheck() {
		if(y + dy < 0 && Main.worldMap.getCerrentMap().getUpperMap() != null) {
			Main.worldMap.getCerrentMap().getUpperMap().travel();
			return false;
		}
		return super.topCheck();
	}
	
	
	protected boolean landingCheck() {
		if(y + dy > Main.worldMap.getCerrentMap().getHeight() - size[1] &&
			Main.worldMap.getCerrentMap().getLowerMap() != null) {
			Main.worldMap.getCerrentMap().getLowerMap().travel();
			return false;
		}
		return super.landingCheck();
	}
	
	public void setMovement(int direction) {
		if(unstable.isAlive()) {
			ax = (speed*direction - dx)*unstableFriction;
		}else if(inAir && dx <= speed) {
			ax = (speed*direction - dx)*unstableFriction;
		}else {
			ax = (speed*direction - dx)*friction;
		}
		ay = gravity;
	}
	
	public void diving() {
		ay += divePower;
		fallSpeedLimit = false;
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
	
	public boolean jumpCheck() {
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
			ay = 0;
			dash = new Delay(dashTime, turnLeft ? -dashPower : dashPower);
			dashCooldown = new Delay(dashCooldownTime);
			dashable = false;
			makeUnstable(dashCooldownTime);
		}
	}
	
	private boolean dashCheck() {
		if(dash.isAlive()) {
			dx = dash.getData();
			turnLeft = dash.getData() < 0 ? true : false;
			dy = 0;
			ay = 0;
			return true;
		}
		return false;
	}
	
	public void makeImmune(long time) {
		immune.interrupt();
		immune = new Delay(time);
	}
	
	public void makeUnstable(long time) {
		unstable.interrupt();
		unstable = new Delay(time);
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

}
