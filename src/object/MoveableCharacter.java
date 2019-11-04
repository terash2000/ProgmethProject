package object;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveableCharacter extends MoveableObject implements Destroyable {
	
	protected double maxHp, hp, attackDamage;
	protected boolean turnLeft, inAir;
	protected List<String> artList = new ArrayList<String>();
	
	public MoveableCharacter(double x, double y, double width, double height) {
		super(x, y, width, height);
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
	
	protected void changeArt(String art) {
		body.getChildren().forEach((i)->{
			i.setVisible(false);
		});
		body.getChildren().get(artList.indexOf(art)).setVisible(true);
	}
	
	public void reset() {
		dx = 0;
		dy = 0;
		turn(false);
	}
	
	public void turn(boolean turnLeft) {
		this.turnLeft = turnLeft;
		body.setScaleX(turnLeft ? -1 : 1);
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
