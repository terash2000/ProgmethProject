package character;

import application.Delay;
import object.Map;
import object.Platform;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MoveableCharacter {
	
	protected Image turnRightImage;
	protected Image turnLeftImage;
	protected ImageView body;
	protected double ax, ay, dx, dy;
	protected double x = 0;
	protected double y = 0;
	protected double friction = 0.2;
	protected double unstableFriction = 0.1;
	protected double speed;
	protected double maxFallSpeed = 25;
	protected boolean fallSpeedLimit = true;
	protected boolean inAir = true;
	protected boolean turnLeft;
	protected Delay unstable = new Delay(0);
	protected double[] size = new double[2];
	protected Map map;
	
	public MoveableCharacter(String turnRightImagePath,String turnLeftImagePath,double width,double height, double speed) {
		turnRightImage = new Image(turnRightImagePath,width,height,false,true);
		turnLeftImage = new Image(turnLeftImagePath,width,height,false,true);
		size[0] = width;
		size[1] = height;
		body = new ImageView(turnRightImage);
		setSpeed(speed);
	}
	
	public abstract void setMovement(int direction);
	
	public void move() {
		moveY();
		moveX();
		body.setLayoutX(x - map.getViewX());
		body.setLayoutY(y - map.getViewY());
	}
	
	protected void moveX() {
		dx += ax;
		if(dx < 0) {
			leftWallCheck();
		}else if(dx > 0) {
			rightWallCheck();
		}
		x +=dx;
	}
	
	protected void moveY() {
		dy += ay;
		if(dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if(dy < 0 && !topCheck()) {
			inAir = true;
		}else if(dy > 0) {
			if(landingCheck()) {
				landing();
			}else {
				inAir = true;
			}
		}
		y += dy;
	}
	
	protected boolean leftWallCheck() {
		for(Platform i:map.getPlatformList()) {
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
		for(Platform i:map.getPlatformList()) {
			if(i.checkLeft(this)) {
				return true;
			}
		}
		if(x + dx > map.getWidth() - size[0]) {
			dx = map.getWidth() - size[0] - x;
			return true;
		}
		return false;
	}
	
	protected boolean topCheck() {
		for(Platform i:map.getPlatformList()) {
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
		for(Platform i:map.getPlatformList()) {
			if(i.checkTop(this)) {
				return true;
			}
		}
		if(y + dy > map.getHeight() - size[1]) {
			dy = map.getHeight() - size[1] - y;
			return true;
		}
		return false;
	}
	
	public void landing() {
		inAir = false;
	}
	
	public void turn(boolean turnLeft) {
		if(turnLeft && !this.turnLeft) {
			body.setImage(turnLeftImage);
			this.turnLeft = true;
		}else if(!turnLeft && this.turnLeft) {
			body.setImage(turnRightImage);
			this.turnLeft = false;
		}
	}
	
	public void makeUnstable(long time) {
		unstable.interrupt();
		unstable = new Delay(time);
	}

	public ImageView getBody() {
		return body;
	}

	public double getAx() {
		return ax;
	}

	public void setAx(double ax) {
		this.ax = ax;
	}

	public double getAy() {
		return ay;
	}

	public void setAy(double ay) {
		this.ay = ay;
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

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
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

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed < 0 ? 0 : speed;
	}

	public boolean isInAir() {
		return inAir;
	}

	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public double[] getSize() {
		return size;
	}

	public Delay getUnstable() {
		return unstable;
	}

	public void setUnstable(Delay unstable) {
		this.unstable = unstable;
	}
	
}
