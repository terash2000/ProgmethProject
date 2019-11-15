package object;

import application.Main;

public abstract class MoveableObject extends GameObject {
	
	protected double friction = 0.3;
	protected double gravity = 1;
	protected double maxFallSpeed = 25;
	protected double speed, dx, dy;
	protected boolean fallSpeedLimit = true;
	
	public MoveableObject(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public MoveableObject(String imagePath, double x, double y, double width, double height) {
		super(imagePath, x, y, width, height);
	}
	
	public void move() {
		moveX();
		moveY();
		changeView();
	}
	
	protected void moveX() {
		if (dx < 0) {
			try {
				leftWallCheck();
				x +=dx;
			} catch(HitWallException exception) {
				x += exception.distance;
				dx = 0;
			}
		}else if (dx > 0) {
			try {
				rightWallCheck();
				x +=dx;
			} catch(HitWallException exception) {
				x += exception.distance;
				dx = 0;
			}
		}
	}
	
	protected void moveY() {
		if (dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if (dy < 0) {
			try {
				topCheck();
				y += dy;
			} catch(HitWallException exception) {
				y += exception.distance;
				dy = 0;
			}
		}else if (dy >= 0) {
			try {
				landingCheck();
				y += dy;
			} catch(HitWallException exception) {
				y += exception.distance;
				dy = 0;
			}
		}
	}
	
	protected void leftWallCheck() throws HitWallException {
		for (GamePlatform platform:Main.world.getCerrentMap().getPlatformList()) {
			try {
				platform.checkRight(this);
			} catch(HitWallException exception) {
				throw exception;
			}
		}
		if (x + dx < 0) {
			throw new HitWallException(-x);
		}
	}
	
	protected void rightWallCheck() throws HitWallException {
		for (GamePlatform platform:Main.world.getCerrentMap().getPlatformList()) {
			try {
				platform.checkLeft(this);
			} catch(HitWallException exception) {
				throw exception;
			}
		}
		if (x + dx > Main.world.getCerrentMap().getWidth() - size[0]) {
			throw new HitWallException(Main.world.getCerrentMap().getWidth() - size[0] - x);
		}
	}
	
	protected void topCheck() throws HitWallException {
		for (GamePlatform platform:Main.world.getCerrentMap().getPlatformList()) {
			try {
				platform.checkBottom(this);
			} catch(HitWallException exception) {
				throw exception;
			}
		}
		if (y + dy < 0) {
			throw new HitWallException(-y);
		}
	}
	
	
	protected void landingCheck() throws HitWallException {
		for (GamePlatform platform:Main.world.getCerrentMap().getPlatformList()) {
			try {
				platform.checkTop(this);
			} catch(HitWallException exception) {
				throw exception;
			}
		}
		if (y + dy > Main.world.getCerrentMap().getHeight() - size[1]) {
			throw new HitWallException(Main.world.getCerrentMap().getHeight() - size[1] - y);
		}
	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed < 0 ? 0 : speed;
	}
	
	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}
	
	public void setFallSpeedLimit(boolean fallSpeedLimit) {
		this.fallSpeedLimit = fallSpeedLimit;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}
}
