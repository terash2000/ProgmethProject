package object;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Delay;
import application.Main;

public abstract class GameObject extends Group implements Updateable {
	
	protected double x, y;
	protected double[] size = new double[2];
	protected Delay stageHolder = new Delay(0);
	protected boolean alive;
	
	public GameObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		size[0] = width;
		size[1] = height;
	}
	
	public GameObject(String imagePath, double x, double y, double width, double height) {
		this(x, y, width, height);
		getChildren().add(new ImageView(new Image(imagePath,width,height,false,true)));
	}
	
	public void update() {
		changeView();
	}
	
	public void changeView() {
		setLayoutX(x - Main.world.getViewX());
		setLayoutY(y - Main.world.getViewY());
	}
	
	public void remove() {
		alive = false;
		Main.world.getObjectList().remove(this);
		Main.game.getChildren().remove(this);
	}
	
	protected void changeStage() {
		
	};
	
	protected void holdStage(long time) {
		stageHolder = new Delay(time, this) {
			@Override
			public void end() {
				Platform.runLater(new Runnable(){
					@Override
					public void run() {
						if (getObject().alive) {
							getObject().changeStage();
						}
					}
				});
			}
		};
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

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
