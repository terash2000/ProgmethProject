package character;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
	private Hero target;
	
	public MoveableEnemy(String imagePath,double width,double height,double x, double y) {
		super(imagePath, x, y, width, height);
	}
	
	public void action() {
		setMovement();
		move();
		if(!target.immune.isAlive()) {
			hitCheck(target);
		}
	}
	
	public boolean hitCheck(Hero hero) {
		if(hero.getX() <= x + size[0] && 
				hero.getX() + hero.getSize()[0] >= x &&
				hero.getY() <= y + size[1] && 
				hero.getY() + hero.getSize()[1] >= y ) {
			hit(hero);
			return true;
		}
		return false;
	}
	
	public void hit(Hero hero) {
		hero.attacked(attackDamage, target.getX()+target.getSize()[0]/2 - x-size[0]/2 < 0 ? -30 : 30, 15);
	}
	
	public void setMovement() {
		ax = (speed*(target.getX()+target.getSize()[0]/2 - x-size[0]/2 < 0 ? -1 : 1) - dx)*friction;
		ay = gravity;
		turnLeft = target.getX()+target.getSize()[0]/2 - x-size[0]/2 < 0;
	}

	public Hero getTarget() {
		return target;
	}

	public void setTarget(Hero target) {
		this.target = target;
	}
	
}
