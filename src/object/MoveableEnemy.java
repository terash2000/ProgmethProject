package object;

import application.Main;

public abstract class MoveableEnemy extends MoveableCharacter implements Enemy {
	
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
		if(!Main.hero.immune.isAlive()) {
			hitCheck();
		}
	}
	
	public boolean hitCheck() {
		if(Main.hero.getX() <= x + size[0] && 
			Main.hero.getX() + Main.hero.getSize()[0] >= x &&
			Main.hero.getY() <= y + size[1] && 
			Main.hero.getY() + Main.hero.getSize()[1] >= y ) {
			hit();
			return true;
		}
		return false;
	}
	
	public void hit() {
		Main.hero.attacked(attackDamage, Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2 ? -30 : 30, 15);
	}
	
	public void setMovement() {
		ax = (speed*(Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2 ? -1 : 1) - dx)*friction;
		ay = gravity;
		turnLeft = Main.hero.getX()+Main.hero.getSize()[0]/2 < x+size[0]/2;
	}
	
}
