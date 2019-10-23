package character;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
	private Hero target;
	
	public MoveableEnemy(String imagePath,double width,double height,double x, double y) {
		super(imagePath, width, height);
		this.x = x;
		this.y = y;
	}
	
	public void action() {
		setMovement();
		move();
		if(!target.immune.isAlive()) {
			hitCheck(target);
		}
	}
	
	public boolean hitCheck(Hero hero) {
		if(hero.x <= x + size[0] && 
				hero.x + hero.size[0] >= x &&
				hero.y <= y + size[1] && 
				hero.y + hero.size[1] >= y ) {
			hit(hero);
			return true;
		}
		return false;
	}
	
	public void hit(Hero hero) {
		hero.attacked(attackDamage, target.x+target.size[0]/2 - x-size[0]/2 < 0 ? -30 : 30, 15);
	}
	
	public void setMovement() {
		ax = (speed*(target.x+target.size[0]/2 - x-size[0]/2 < 0 ? -1 : 1) - dx)*friction;
		ay = gravity;
		turn(target.x+target.size[0]/2 - x-size[0]/2 < 0);
	}

	public Hero getTarget() {
		return target;
	}

	public void setTarget(Hero target) {
		this.target = target;
	}
	
}
