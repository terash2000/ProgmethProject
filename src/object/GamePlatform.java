package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GamePlatform extends GameObject {
	
	public GamePlatform(PlatformType platformType, double x, double y) {
		super(platformType.getPath(), x, y, platformType.getWidth(), platformType.getHeight());
	}
	
	public GamePlatform(PlatformType platformType, double x, double y, double width, double height) {
		super(x, y, width, height);
		Image image = new Image(platformType.getPath());
		int duplicateX = (platformType.repeatX() ? (int)(width/image.getWidth() + 0.5) : 1);
		int duplicateY = (platformType.repeatY() ? (int)(height/image.getHeight() + 0.5) : 1);
		image = new Image(platformType.getPath(), (width/duplicateX + 1), (height/duplicateY + 1), false, true);
		for (int i = 0; i < duplicateX; i++) {
			for (int j = 0; j < duplicateY; j++) {
				getChildren().add(new ImageView(image));
				getChildren().get(getChildren().size() - 1).setLayoutX(width/duplicateX*i);
				getChildren().get(getChildren().size() - 1).setLayoutY(height/duplicateY*j);
			}
		}
	}
	
	public GamePlatform(PlatformType platformType, double x, double y, double width, double height, 
			boolean flipX, boolean flipY) {
		this(platformType, x, y, width, height);
		if (flipX) {
			setScaleX(-1);
		}
		if (flipY) {
			setScaleY(-1);
		}
	}
	
	public void checkLeft(MoveableObject character) throws HitWallException {
		if (((character.getY() + character.getSize()[1]) > y)
				&& (character.getY() < (y + size[1]))
				&& ((character.getX() + character.getSize()[0]) <= x)
				&& ((character.getX() + character.getSize()[0] + character.getDx()) > x)) {
			throw new HitWallException(x - character.getX() - character.getSize()[0]);
		}
	}
	
	public void checkRight(MoveableObject character) throws HitWallException {
		if (((character.getY() + character.getSize()[1]) > y)
				&& (character.getY() < (y + size[1]))
				&& (character.getX() >= (x + size[0]))
				&& ((character.getX() + character.getDx()) < (x + size[0]))) {
			throw new HitWallException(x + size[0] - character.getX());
		}
	}
	
	public void checkTop(MoveableObject character) throws HitWallException {
		if (((character.getX() + character.getSize()[0]) > x)
				&& (character.getX() < (x + size[0]))
				&& ((character.getY() + character.getSize()[1]) <= y)
				&& ((character.getY() + character.getSize()[1] + character.getDy()) >= y)) {
			throw new HitWallException(y - character.getY() - character.getSize()[1]);
		}
	}
	
	public void checkBottom(MoveableObject character) throws HitWallException {
		if (((character.getX() + character.getSize()[0]) > x)
				&& (character.getX() < (x + size[0]))
				&& (character.getY() >= (y + size[1]))
				&& ((character.getY() + character.getDy()) < (y + size[1]))) {
			throw new HitWallException(y + size[1] - character.getY());
		}
	}
	
	public void remove() {
		super.remove();
	}
}
