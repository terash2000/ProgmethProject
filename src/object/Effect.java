package object;

import application.Delay;
import application.Main;;

public class Effect extends GameObject implements Actionable {
	
	private Delay delay;
	
	public Effect(String imagePath, long time, double x, double y, double width, double height) {
		super(imagePath, x, y, width, height);
		delay = new Delay(time);
	}
	
	public Effect(String imagePath, long time, double x, double y, double width, double height, boolean flipX, boolean flipY) {
		this(imagePath, time, x, y, width, height);
		if(flipX) {
			body.setScaleX(-1);
		}
		if(flipY) {
			body.setScaleY(-1);
		}
	}
	
	public void action() {
		if(!delay.isAlive()) {
			Main.worldMap.getObjectList().remove(this);
			Main.game.getChildren().remove(body);
		}
	}

}
