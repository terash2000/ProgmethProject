package map;

import application.Main;
import application.Sound;
import object.Actionable;
import object.Destroyable;
import object.Enemy;
import object.GameObject;
import object.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.image.ImageView;
public class World {
	
	private HashMap<MapName, Map> mapList = new HashMap<MapName, Map>();
	private Map cerrentMap;
	private List<Platform> platformList = new ArrayList<Platform>();
	private List<Actionable> actionableList = new ArrayList<Actionable>();
	private List<Destroyable> destroyableList = new ArrayList<Destroyable>();
	private boolean bossFight;
	private double width, height, viewX, viewY;
	
	public void addMap(MapName name, Map map) {
		mapList.put(name, map);
	}
	
	public void addObject(GameObject object) {
		if(object instanceof Actionable) {
			Actionable actionable = (Actionable) object;
			actionableList.add(actionable);
		}
		if(object instanceof Destroyable) {
			Destroyable destroyable = (Destroyable) object;
			destroyableList.add(destroyable);
		}
		Main.game.getChildren().add(object.getBody());
		object.changeView();
	}
	
	public void setCerrentMap(MapName name, double x, double y) {
		cerrentMap = mapList.get(name);
		width = cerrentMap.getWidth();
		height = cerrentMap.getHeight();
		Main.game.getChildren().clear();
		Main.game.getChildren().addAll(cerrentMap.getBackground());
		platformList.clear();
		actionableList.clear();
		destroyableList.clear();
		for(Platform i:cerrentMap.getPlatformList()) {
			platformList.add(i);
			Main.game.getChildren().add(i.getBody());
		}
		for(Enemy i:cerrentMap.getEnemyList()) {
			actionableList.add(i);
			destroyableList.add(i);
			i.spawn();
		}
		Main.game.getChildren().add(Main.hero.getBody());
		setHeroLocation(x, y);
		Sound.changeBackgroundMusic(cerrentMap.getMusic());
	}
	
	public void setHeroLocation(double x, double y) {
		Main.hero.reset();
		Main.hero.setX(x);
		Main.hero.setY(y);
		changeView();
	}
	
	public void changeView() {
		double x = Main.hero.getX() + Main.hero.getSize()[0]/2;
		double y = Main.hero.getY() + Main.hero.getSize()[1]/2;
		viewX = x < Main.getSceneWidth()/2 ? 0 : 
			(x > width-Main.getSceneWidth()/2 ? width-Main.getSceneWidth() : x-Main.getSceneWidth()/2);
		viewY = y < Main.getSceneHeight()/2 ? 0 : 
			(y > height-Main.getSceneHeight()/2 ? height-Main.getSceneHeight() : y-Main.getSceneHeight()/2);
		for(ImageView i:cerrentMap.getBackground()) {
			i.setLayoutX(-viewX*(i.getImage().getWidth()-Main.getSceneWidth())/(width-Main.getSceneWidth()));
			i.setLayoutY(-viewY*(i.getImage().getHeight()-Main.getSceneHeight())/(height-Main.getSceneHeight()));
		}
		for(Platform i:platformList) {
			i.changeView();
		}
		for(Actionable i:actionableList) {
			i.changeView();
		}
	}
	
	public boolean isBossFight() {
		return bossFight;
	}

	public void setBossFight(boolean bossFight) {
		this.bossFight = bossFight;
	}

	public void exitBossFight() {
		setBossFight(false);
		Sound.changeBackgroundMusic(cerrentMap.getMusic());
	}
	
	public HashMap<MapName, Map> getMapList() {
		return mapList;
	}

	public Map getCerrentMap() {
		return cerrentMap;
	}
	
	public List<Actionable> getActionableList() {
		return actionableList;
	}

	public List<Destroyable> getDestroyableList() {
		return destroyableList;
	}

	public List<Platform> getPlatformList() {
		return platformList;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}


	public double getViewX() {
		return viewX;
	}


	public double getViewY() {
		return viewY;
	}

}
