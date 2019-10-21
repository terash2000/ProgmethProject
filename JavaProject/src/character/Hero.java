package character;

import application.Delay;

public class Hero extends MoveableCharacter {
	
	private double divePower = 1.5;
	private double jumpPower = 16;
	private double doubleJumpPower = 14;
	private long jumpTime = 180;
	private double dashPower = 20;
	private long dashTime = 150;
	private long dashCooldownTime = 450;
	private Delay jump = new Delay(0);
	private Delay dashing = new Delay(0);
	private Delay dashCooldown = new Delay(0);
	private boolean doubleJumped = true;
	private boolean doubleJumpable, dashable;
	
	public Hero() {
		super("file:image/Character/Hero/heroTurnRight.png","file:image/Character/Hero/heroTurnLeft.png",80,100,8);
	}
	
	public void setMovement(int direction) {
		if(unstable.isAlive()) {
			ax = (speed*direction - dx)*unstableFriction;
		}else if(inAir && dx <= speed) {
			ax = (speed*direction - dx)*unstableFriction;
		}else {
			ax = (speed*direction - dx)*friction;
		}
		ay = map.getGravity();
	}
	
	public void diving() {
		ay += divePower;
		fallSpeedLimit = false;
	}
	
	public void jumping() {
		if(!inAir) {
			jump(jumpPower);
		}
		if(jump.isAlive()) {
			dy = jump.getData();
		}else if(doubleJumpable) {
			doubleJumpable = false;
			doubleJumped = true;
			jump(doubleJumpPower);
		}
	}
	
	public void jump(double power) {
		dashing.interrupt();
		jump = new Delay(jumpTime, -power);
		dy = jump.getData();
	}
	
	public void stopJump() {
		jump.interrupt();
		if(inAir && !doubleJumped) {
			doubleJumpable = true;
		}
	}
	
	public void dash() {
		if(dashable && !dashCooldown.isAlive()) {
			dx = turnLeft ? -dashPower : dashPower;
			dy = 0;
			ay = 0;
			dashing = new Delay(dashTime, turnLeft ? -dashPower : dashPower);
			dashCooldown = new Delay(dashCooldownTime);
			dashable = false;
			makeUnstable(dashCooldownTime);
		}
	}
	
	private void dashCheck() {
		if(dashing.isAlive()) {
			dx = dashing.getData();
			dy = 0;
			ay = 0;
		}
	}
	
	public void move() {
		dashCheck();
		moveY();
		moveX();
		map.changeView(this);
		body.setLayoutX(x - map.getViewX());
		body.setLayoutY(y - map.getViewY());
	}
	
	public void landing() {
		inAir = false;
		doubleJumped = false;
		doubleJumpable = false;
		dashable = true;
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
}
