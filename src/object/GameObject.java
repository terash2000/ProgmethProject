package object;

import application.Main;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {
	
	protected Group body = new Group();
	protected double x, y;
	protected double[] size = new double[2];
	
	public GameObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		size[0] = width;
		size[1] = height;
	}
	
	public GameObject(String imagePath, double x, double y, double width, double height) {
		this(x, y, width, height);
		body.getChildren().add(new ImageView(new Image(imagePath,width,height,false,true)));
	}
	
	public void changeView() {
		body.setLayoutX(x - Main.world.getViewX());
		body.setLayoutY(y - Main.world.getViewY());
	}

	public Group getBody() {
		return body;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double[] getSize() {
		return size;
	}

}
