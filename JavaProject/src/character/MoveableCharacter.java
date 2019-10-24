package character;

import application.Delay;

public abstract class MoveableCharacter extends Moveable {
	
	protected double maxHp, hp, attackDamage;
	protected boolean inAir;
	protected boolean turnLeft;
	protected Delay immune = new Delay(0);
	
	public MoveableCharacter(String imagePath, double x, double y, double width, double height) {
		super(imagePath, x, y, width, height);
	}
	
	public abstract void die();
	
	protected void artCheck() {
		turn();
	}
	
	protected void moveY() {
		dy += ay;
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
	
	public void reset() {
		dx = 0;
		dy = 0;
	}
	
	public void attacked(double damage, double knockbackX, double knockbackY) {
		dx = knockbackX;
		dy = -knockbackY;
		hp = damage > hp ? 0 : hp - damage;
		if(hp == 0) {
			die();
		}
	}
	
	public void turn() {
		if(turnLeft) {
			body.setScaleX(-1);
		}else {
			body.setScaleX(1);
		}
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
