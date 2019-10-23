package character;

import javafx.scene.Group;
import object.Map;

public interface Enemy {
	
	public void action();
	
	public boolean hitCheck(Hero hero);
	
	public void hit(Hero hero);
	
	public void changeView();
	
	public Group getBody();
	
	public void setTarget(Hero target);
	
	public void setMap(Map map);

}
