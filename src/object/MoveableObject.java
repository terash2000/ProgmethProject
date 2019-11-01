package object;

import application.Main;

public abstract class MoveableObject extends GameObject {
	
	protected double dx, dy;
	protected double gravity = 1;
	protected double friction = 0.2;
	protected double speed;
	protected double maxFallSpeed = 25;
	protected boolean fallSpeedLimit = true;
	
	public MoveableObject(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public MoveableObject(String imagePath, double x, double y, double width, double height) {
		super(imagePath, x, y, width, height);
	}
	
	public void move() {
		moveY();
		moveX();
		changeView();
	}
	
	protected void moveX() {
		if(dx < 0) {
			leftWallCheck();
		}else if(dx > 0) {
			rightWallCheck();
		}
		x +=dx;
	}
	
	protected void moveY() {
		if(dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if(dy < 0) {
			topCheck();
		}else if(dy >= 0) {
			landingCheck();
		}
		y += dy;
	}
	
	protected boolean leftWallCheck() {
		for(Platform i:Main.world.getCerrentMap().getPlatformList()) {
			if(i.checkRight(this)) {
				return true;
			}
		}
		if(x + dx < 0) {
			dx = -x;
			return true;
		}
		return false;
	}
	
	protected boolean rightWallCheck() {
		for(Platform i:Main.world.getCerrentMap().getPlatformList()) {
			if(i.checkLeft(this)) {
				return true;
			}
		}
		if(x + dx > Main.world.getCerrentMap().getWidth() - size[0]) {
			dx = Main.world.getCerrentMap().getWidth() - size[0] - x;
			return true;
		}
		return false;
	}
	
	protected boolean topCheck() {
		for(Platform i:Main.world.getCerrentMap().getPlatformList()) {
			if(i.checkBottom(this)) {
				return true;
			}
		}
		if(y + dy < 0) {
			dy = -y;
			return true;
		}
		return false;
	}
	
	
	protected boolean landingCheck() {
		for(Platform i:Main.world.getCerrentMap().getPlatformList()) {
			if(i.checkTop(this)) {
				return true;
			}
		}
		if(y + dy > Main.world.getCerrentMap().getHeight() - size[1]) {
			dy = Main.world.getCerrentMap().getHeight() - size[1] - y;
			return true;
		}
		return false;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void setFallSpeedLimit(boolean fallSpeedLimit) {
		this.fallSpeedLimit = fallSpeedLimit;
	}

	public double getFriction() {
		return friction;
	}

	public void setFriction(double friction) {
		this.friction = friction > 1 ? 1 : (friction < 0 ? 0 : friction);
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed < 0 ? 0 : speed;
	}
	
}
