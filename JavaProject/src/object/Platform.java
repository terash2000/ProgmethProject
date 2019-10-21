package object;

import character.MoveableCharacter;
import javafx.scene.image.ImageView;;

public interface Platform {
	
	public void checkTop(MoveableCharacter character);
	
	public ImageView getBody();
	public double[][] getPosition();

}
