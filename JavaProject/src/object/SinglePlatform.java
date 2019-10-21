package object;

import character.MoveableCharacter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SinglePlatform implements Platform {
	
	protected ImageView body;
	protected double[][] position = new double[2][2];
	
	public SinglePlatform(String ImagePath, double x, double y, double width,double height) {
		body = new ImageView(new Image(ImagePath, width, height, false, true));
		position[0][0] = x;
		position[0][1] = y;
		position[1][0] = x + width;
		position[1][1] = y + height;
	}
	
	public void checkTop(MoveableCharacter character) {
		if(character.getX() + character.getSize()[0] > position[0][0] && character.getX() < position[1][0] && 
				character.getY() + character.getSize()[1] <= position[0][1] && 
				character.getY() + character.getSize()[1] + character.getDy() > position[0][1]) {
			character.setDy(position[0][1] - character.getY() - character.getSize()[1]);
			character.landing();
		}
	}

	public ImageView getBody() {
		return body;
	}

	public double[][] getPosition() {
		return position;
	}
	
}