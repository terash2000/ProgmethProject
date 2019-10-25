package object;

import javafx.scene.Group;
import menu.Map;

public interface Enemy {
	
	public void spawn();
	
	public void action();
	
	public boolean hitCheck(Hero hero);
	
	public void hit(Hero hero);
	
	public void changeView();
	
	public Group getBody();
	
	public void setTarget(Hero target);
	
	public void setMap(Map map);

}
