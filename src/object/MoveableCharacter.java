package object;

import application.Delay;

public abstract class MoveableCharacter extends MoveableObject implements Destroyable {
	
	protected double maxHp, hp, attackDamage;
	protected boolean inAir;
	protected boolean turnLeft;
	protected Delay immune = new Delay(0);
	
	public MoveableCharacter(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	protected void changeArt(String art) {
	}
	
	public void move() {
		turn();
		super.move();
	}
	
	protected void moveY() {
		if(dy > maxFallSpeed && fallSpeedLimit) {
			dy = maxFallSpeed;
		}
		if(dy < 0) {
			topCheck();
			inAir = true;
		}else if(dy >= 0) {
			if(landingCheck()) {
				landing();
			}else {
				inAir = true;
			}
		}
		y += dy;
	}
	
	public boolean hitCheck(double x, double y, double width, double height) {
		if(this.x <= x+width && 
			this.x + size[0] >= x &&
			this.y <= y+height && 
			this.y + size[1] >= y ) {
			return true;
		}
		return false;
	}
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		if(knockbackX != 0) {
			dx = knockbackX;
		}
		if(knockbackY != 0) {
			dy = -knockbackY;
		}
		hp = damage > hp ? 0 : hp - damage;
		if(hp == 0) {
			die();
		}
	}
	
	public void reset() {
		dx = 0;
		dy = 0;
		turnLeft = false;
		immune.interrupt();
	}
	
	public void turn() {
		body.setScaleX(turnLeft ? -1 : 1);
	}
	
	public void landing() {
		inAir = false;
	}
	
	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public boolean isTurnLeft() {
		return turnLeft;
	}

	public void setTurnLeft(boolean turnLeft) {
		this.turnLeft = turnLeft;
	}
	
}
