package object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Platform extends GameObject {
	
	public Platform(PlatformType platformType, double x, double y) {
		super(platformType.getPath(), x, y, platformType.getWidth(), platformType.getHeight());
	}
	
	public Platform(PlatformType platformType, double x, double y, double width, double height) {
		super(x, y, width, height);
		Image image = new Image(platformType.getPath());
		int nX = platformType.multiX() ? (int)(width/image.getWidth() + 0.5) : 1;
		int nY = platformType.multiY() ? (int)(height/image.getHeight() + 0.5) : 1;
		image = new Image(platformType.getPath(), width/nX + 1, height/nY + 1, false, true);
		for(int i = 0; i < nX; i++) {
			for(int j = 0; j < nY; j++) {
				getChildren().add(new ImageView(image));
				getChildren().get(getChildren().size() - 1).setLayoutX(width/nX*i);
				getChildren().get(getChildren().size() - 1).setLayoutY(height/nY*j);
			}
		}
	}
	
	public Platform(PlatformType platformType, double x, double y, double width, double height, 
			boolean flipX, boolean flipY) {
		this(platformType, x, y, width, height);
		if(flipX) {
			setScaleX(-1);
		}
		if(flipY) {
			setScaleY(-1);
		}
	}
	
	public Platform(String ImagePath, double x, double y, double width, double height,
			boolean multiX, boolean multiY) {
		super(x, y, width, height);
		Image image = new Image(ImagePath);
		int nX = multiX ? (int)(width/image.getWidth() + 0.5) : 1;
		int nY = multiY ? (int)(height/image.getHeight() + 0.5) : 1;
		image = new Image(ImagePath, width/nX + 1, height/nY + 1, false, true);
		for(int i = 0; i < nX; i++) {
			for(int j = 0; j < nY; j++) {
				getChildren().add(new ImageView(image));
				getChildren().get(getChildren().size() - 1).setLayoutX(width/nX*i);
				getChildren().get(getChildren().size() - 1).setLayoutY(height/nY*j);
			}
		}
	}
	
	public Platform(String ImagePath, double x, double y, double width, double height,
			boolean multiX, boolean multiY ,boolean flipX, boolean flipY) {
		this(ImagePath, x, y, width, height, multiX, multiY);
		if(flipX) {
			setScaleX(-1);
		}
		if(flipY) {
			setScaleY(-1);
		}
	}
	
	public void checkTop(MoveableObject character) throws HitWallException {
		if(character.getX() + character.getSize()[0] > x 
				&& character.getX() < x+size[0] 
				&& character.getY() + character.getSize()[1] <= y 
				&& character.getY() + character.getSize()[1] + character.getDy() >= y) {
			throw new HitWallException(y - character.getY() - character.getSize()[1]);
		}
	}
	
	public void checkBottom(MoveableObject character) throws HitWallException {
		if(character.getX() + character.getSize()[0] > x 
				&& character.getX() < x+size[0] 
				&& character.getY() >= y+size[1] 
				&& character.getY() + character.getDy() < y+size[1]) {
			throw new HitWallException(y+size[1] - character.getY());
		}
	}
	
	public void checkLeft(MoveableObject character) throws HitWallException {
		if(character.getY() + character.getSize()[1] > y 
				&& character.getY() < y+size[1] 
				&& character.getX() + character.getSize()[0] <= x 
				&& character.getX() + character.getSize()[0] + character.getDx() > x) {
			throw new HitWallException(x - character.getX() - character.getSize()[0]);
		}
	}
	
	public void checkRight(MoveableObject character) throws HitWallException {
		if(character.getY() + character.getSize()[1] > y 
				&& character.getY() < y+size[1] 
				&& character.getX() >= x+size[0] 
				&& character.getX() + character.getDx() < x+size[0]) {
			throw new HitWallException(x+size[0] - character.getX());
		}
	}
	
}
