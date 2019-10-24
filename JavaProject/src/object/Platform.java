package object;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import character.Moveable;

public class Platform extends gameObject {
	
	public Platform(String ImagePath, double x, double y, double width, double height) {
		super(ImagePath, x, y, width, height);
		body = new Group(new ImageView(new Image(ImagePath, width, height, false, true)));
		size[1] -= 10;
	}
	
	public Platform(String ImagePath, double x, double y, double width, double height,
			boolean multiX, boolean multiY) {
		super(ImagePath, x, y, width, height);
		body = new Group();
		Image image = new Image(ImagePath);
		if(!image.isError()) {
			int nX = multiX ? (int)(width/image.getWidth() + 0.5) : 1;
			int nY = multiY ? (int)(height/image.getHeight() + 0.5) : 1;
			image = new Image(ImagePath, width/nX + 1, height/nY + 1, false, true);
			for(int i = 0; i < nX; i++) {
				for(int j = 0; j < nY; j++) {
					body.getChildren().add(new ImageView(image));
					body.getChildren().get(body.getChildren().size() - 1).setLayoutX(width/nX*i);
					body.getChildren().get(body.getChildren().size() - 1).setLayoutY(height/nY*j);
				}
			}
		}
	}
	
	public Platform(String ImagePath, double x, double y, double width, double height,
			boolean multiX, boolean multiY ,boolean flipX, boolean flipY) {
		this(ImagePath, x, y, width, height, multiX, multiY);
		if(flipX) {
			body.setScaleX(-1);
		}
		if(flipY) {
			body.setScaleY(-1);
		}
	}
	
	public void changeView() {
		body.setLayoutX(x - map.getViewX());
		body.setLayoutY(y - map.getViewY());
	}
	
	public boolean checkTop(Moveable character) {
		if(character.getX() + character.getSize()[0] > x && 
				character.getX() < x+size[0] && 
				character.getY() + character.getSize()[1] <= y && 
				character.getY() + character.getSize()[1] + character.getDy() >= y) {
			character.setDy(y - character.getY() - character.getSize()[1]);
			return true;
		}
		return false;
	}
	
	public boolean checkBottom(Moveable character) {
		if(character.getX() + character.getSize()[0] > x && 
				character.getX() < x+size[0] && 
				character.getY() >= y+size[1] && 
				character.getY() + character.getDy() < y+size[1]) {
			character.setDy(y+size[1] - character.getY());
			return true;
		}
		return false;
	}
	
	public boolean checkLeft(Moveable character) {
		if(character.getY() + character.getSize()[1] > y && 
				character.getY() < y+size[1] && 
				character.getX() + character.getSize()[0] <= x && 
				character.getX() + character.getSize()[0] + character.getDx() > x) {
			character.setDx(x - character.getX() - character.getSize()[0]);
			return true;
		}
		return false;
	}
	
	public boolean checkRight(Moveable character) {
		if(character.getY() + character.getSize()[1] > y && 
				character.getY() < y+size[1] && 
				character.getX() >= x+size[0] && 
				character.getX() + character.getDx() < x+size[0]) {
			character.setDx(x+size[0] - character.getX());
			return true;
		}
		return false;
	}
	
}
