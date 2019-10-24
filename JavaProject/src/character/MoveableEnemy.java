package character;

import application.Main;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
	private Hero target;
	private double[] spawnLocation = new double[2];
	
	public MoveableEnemy(String imagePath,double width,double height,double x, double y) {
		super(imagePath, x, y, width, height);
		spawnLocation[0] = x;
		spawnLocation[1] = y;
	}
	
	public void spawn() {
		reset();
		Main.getGame().getChildren().add(body);
		x = spawnLocation[0];
		y = spawnLocation[1];
		fallSpeedLimit = true;
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
		hero.attacked(attackDamage, target.getX()+target.getSize()[0]/2 < x+size[0]/2 ? -30 : 30, 15);
	}
	
	public void setMovement() {
		ax = (speed*(target.getX()+target.getSize()[0]/2 < x+size[0]/2 ? -1 : 1) - dx)*friction;
		ay = gravity;
		turnLeft = target.getX()+target.getSize()[0]/2 < x+size[0]/2;
	}

	public Hero getTarget() {
		return target;
	}

	public void setTarget(Hero target) {
		this.target = target;
	}
	
}
