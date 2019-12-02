package object;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveableCharacter extends MoveableObject implements Destroyable {
	
	protected double attackDamage, maxHp, hp;
	protected boolean turnLeft, inAir;
	protected List<String> artList = new ArrayList<String>();
	
	public MoveableCharacter(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	public boolean intersectCheck(double x, double y, double width, double height) {
		if ((this.x <= (x + width)) && ((this.x + size[0]) >= x) 
				&& (this.y <= (y + height)) && ((this.y + size[1]) >= y)) {
			return true;
		}
		return false;
	}
	
	public void attacked(double damage, double knockBackX, double knockBackY) {
		if (knockBackX != 0) {
			dx = knockBackX;
		}
		if (knockBackY != 0) {
			dy = -knockBackY;
		}
		hp = ((damage > hp) ? 0 : (hp - damage));
		if (hp == 0) {
			die();
		}
	}
	
	protected void changeSprite(String art) {
		getChildren().forEach((image)->{
			image.setVisible(false);
		});
		getChildren().get(artList.indexOf(art)).setVisible(true);
	}
	
	public void turn(boolean turnLeft) {
		this.turnLeft = turnLeft;
		setScaleX(turnLeft ? -1 : 1);
	}
	
	public double getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(double attackDamage) {
		this.attackDamage = attackDamage;
	}

	public double getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = (hp < maxHp) ? hp : maxHp;
	}
}
