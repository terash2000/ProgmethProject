package map;

import application.Main;
import object.Actionable;
import object.Enemy;
import object.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.image.ImageView;
public class World {
	
	private HashMap<MapName, Map> mapList = new HashMap<MapName, Map>();
	private Map cerrentMap;
	private List<Platform> platformList = new ArrayList<Platform>();
	private List<Actionable> objectList = new ArrayList<Actionable>();
	private double width, height, viewX, viewY;
	
	public void addMap(MapName name, Map map) {
		mapList.put(name, map);
	}
	
	public void setCerrentMap(MapName name, double x, double y) {
		cerrentMap = mapList.get(name);
		width = cerrentMap.getWidth();
		height = cerrentMap.getHeight();
		Main.game.getChildren().clear();
		Main.game.getChildren().addAll(cerrentMap.getBackground());
		platformList.clear();
		for(Platform i:cerrentMap.getPlatformList()) {
			platformList.add(i);
			Main.game.getChildren().add(i.getBody());
		}
		objectList.clear();
		for(Enemy i:cerrentMap.getEnemyList()) {
			objectList.add(i);
			i.spawn();
		}
		Main.game.getChildren().add(Main.hero.getBody());
		setHeroLocation(x, y);
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
		for(Actionable i:objectList) {
			i.changeView();
		}
	}
	
	public HashMap<MapName, Map> getMapList() {
		return mapList;
	}

	public Map getCerrentMap() {
		return cerrentMap;
	}
	
	public List<Actionable> getObjectList() {
		return objectList;
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
