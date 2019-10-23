package object;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import character.Moveable;

public class Platform {
	
	protected Group body;
	protected double[][] position = new double[2][2];
	protected Map map;
	
	public Platform(String ImagePath, double x, double y, double width, double height) {
		position[0][0] = x;
		position[0][1] = y;
		position[1][0] = x + width;
		position[1][1] = y + height - 10;
		body = new Group(new ImageView(new Image(ImagePath, width, height, false, true)));
	}
	
	public Platform(String ImagePath, double x, double y, double width, double height,
			boolean multiX, boolean multiY) {
		position[0][0] = x;
		position[0][1] = y;
		position[1][0] = x + width;
		position[1][1] = y + height - 10;
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
		body.setLayoutX(position[0][0] - map.getViewX());
		body.setLayoutY(position[0][1] - map.getViewY());
	}
	
	public boolean checkTop(Moveable character) {
		if(character.getX() + character.getSize()[0] > position[0][0] && 
				character.getX() < position[1][0] && 
				character.getY() + character.getSize()[1] <= position[0][1] && 
				character.getY() + character.getSize()[1] + character.getDy() >= position[0][1]) {
			character.setDy(position[0][1] - character.getY() - character.getSize()[1]);
			return true;
		}
		return false;
	}
	
	public boolean checkBottom(Moveable character) {
		if(character.getX() + character.getSize()[0] > position[0][0] && 
				character.getX() < position[1][0] && 
				character.getY() >= position[1][1] && 
				character.getY() + character.getDy() < position[1][1]) {
			character.setDy(position[1][1] - character.getY());
			return true;
		}
		return false;
	}
	
	public boolean checkLeft(Moveable character) {
		if(character.getY() + character.getSize()[1] > position[0][1] && 
				character.getY() < position[1][1] && 
				character.getX() + character.getSize()[0] <= position[0][0] && 
				character.getX() + character.getSize()[0] + character.getDx() > position[0][0]) {
			character.setDx(position[0][0] - character.getX() - character.getSize()[0]);
			return true;
		}
		return false;
	}
	
	public boolean checkRight(Moveable character) {
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

	public Group getBody() {
		return body;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
