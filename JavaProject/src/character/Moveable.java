package character;

import object.Map;
import object.Platform;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;

public abstract class Moveable {
	
	protected Image image;
	protected Group body;
	protected double ax, ay, dx, dy;
	protected double x = 0;
	protected double y = 0;
	protected double gravity = 1;
	protected double friction = 0.2;
	protected double speed = 1;
	protected double maxFallSpeed = 25;
	protected boolean fallSpeedLimit = true;
	protected double[] size = new double[2];
	protected int cerrentArt;
	protected Map map;
	
	protected abstract void artCheck();
	
	protected abstract void changeArt(String art);
	
	public Moveable(String imagePath, double width, double height) {
		image = new Image(imagePath,width,height,false,true);
		size[0] = width;
		size[1] = height;
		body = new Group(new ImageView(image));
		setSpeed(speed);
	}
	
	public void move() {
		artCheck();
		moveY();
		moveX();
		changeView();
	}
	
	public void changeView() {
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
		if(dy < 0) {
			topCheck();
		}else if(dy >= 0) {
			landingCheck();
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

	public Group getBody() {
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

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public double[] getSize() {
		return size;
	}
	
}
