package object;

import character.MoveableCharacter;
import javafx.scene.image.ImageView;

public abstract class Platform {
	
	protected double[][] position = new double[2][2];
	
	public Platform(double x, double y, double width,double height) {
		position[0][0] = x;
		position[0][1] = y;
		position[1][0] = x + width;
		position[1][1] = y + height - 10;
	}
	
	public abstract ImageView getBody();
	
	public boolean checkTop(MoveableCharacter character) {
		if(character.getX() + character.getSize()[0] > position[0][0] && 
				character.getX() < position[1][0] && 
				character.getY() + character.getSize()[1] <= position[0][1] && 
				character.getY() + character.getSize()[1] + character.getDy() > position[0][1]) {
			character.setDy(position[0][1] - character.getY() - character.getSize()[1]);
			return true;
		}
		return false;
	}
	
	public boolean checkBottom(MoveableCharacter character) {
		if(character.getX() + character.getSize()[0] > position[0][0] && 
				character.getX() < position[1][0] && 
				character.getY() >= position[1][1] && 
				character.getY() + character.getDy() < position[1][1]) {
			character.setDy(position[1][1] - character.getY());
			return true;
		}
		return false;
	}
	
	public boolean checkLeft(MoveableCharacter character) {
		if(character.getY() + character.getSize()[1] > position[0][1] && 
				character.getY() < position[1][1] && 
				character.getX() + character.getSize()[0] <= position[0][0] && 
				character.getX() + character.getSize()[0] + character.getDx() > position[0][0]) {
			character.setDx(position[0][0] - character.getX() - character.getSize()[0]);
			return true;
		}
		return false;
	}
	
	public boolean checkRight(MoveableCharacter character) {
		if(character.getY() + character.getSize()[1] > position[0][1] && 
				character.getY() < position[1][1] && 
				character.getX() >= position[0][0] && 
				character.getX() + character.getDx() < position[1][0]) {
			character.setDx(position[1][0] - character.getX());
			return true;
		}
		return false;
	}
	
	public double[][] getPosition() {
		return position;
	}

}
