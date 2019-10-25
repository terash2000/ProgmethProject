package object;

import javafx.scene.Group;

public interface Enemy {
	
	public void spawn();
	
	public void action();
	
	public boolean hitCheck();
	
	public void hit();
	
	public void changeView();
	
	public Group getBody();

}
