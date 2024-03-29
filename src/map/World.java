package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import application.Main;
import application.Sound;
import object.CheckPoint;
import object.Destroyable;
import object.Enemy;
import object.GameObject;
import object.GamePlatform;
import object.Updateable;

public class World extends Group {
	
	private HashMap<MapName, Map> mapList = new HashMap<MapName, Map>();
	private Map cerrentMap;
	private List<Updateable> objectList = new ArrayList<Updateable>();
	private List<Destroyable> destroyableList = new ArrayList<Destroyable>();
	private double width, height, viewX, viewY;
	private boolean bossFight;
	
	public World() {
		addMap(MapName.Starter, MapCreater.createStarterMap());
		addMap(MapName.False_Knight_room, MapCreater.createFalseKnightRoom());
		addMap(MapName.Town, MapCreater.createTown());
		addMap(MapName.Cave, MapCreater.createCave());
		addMap(MapName.Dark_Cave, MapCreater.createDarkCave());
		addMap(MapName.Crystal_Cave, MapCreater.createCrystalCave());
	}
	
	public void setCerrentMap(MapName name, double x, double y) {
		getChildren().clear();
		for (Updateable object: new ArrayList<Updateable>(Main.world.getObjectList())) {
			object.remove();
		}
		cerrentMap = mapList.get(name);
		width = cerrentMap.getWidth();
		height = cerrentMap.getHeight();
		getChildren().addAll(cerrentMap.getBackground());
		for (GamePlatform platform: cerrentMap.getPlatformList()) {
			objectList.add(platform);
			getChildren().add(platform);
		}
		for (CheckPoint checkPoint: cerrentMap.getCheckPointList()) {
			objectList.add(checkPoint);
			getChildren().add(checkPoint);
		}
		for (Enemy enemy: cerrentMap.getEnemyList()) {
			objectList.add(enemy);
			destroyableList.add(enemy);
			enemy.spawn();
		}
		if (cerrentMap.isDarkArea()) {
			getChildren().add(Main.hero.getLight());
		}
		getChildren().add(Main.hero);
		Main.hero.setAlive(true);
		Main.hero.setLocation(x, y);
		Sound.changeBackgroundMusic(cerrentMap.getMusic());
	}
	
	public void drawBackground() {
		double x = Main.hero.getX() + Main.hero.getSize()[0]/2;
		double y = Main.hero.getY() + Main.hero.getSize()[1]/2;
		viewX = (x < Main.getSceneWidth()/2) 
				? 0 : ((x > (width - Main.getSceneWidth()/2)) 
						? (width - Main.getSceneWidth()) : (x - Main.getSceneWidth()/2));
		viewY = (y < Main.getSceneHeight()/2)
				? 0 : ((y > (height - Main.getSceneHeight()/2)) 
						? (height - Main.getSceneHeight()) : (y - Main.getSceneHeight()/2));
		for (ImageView i:cerrentMap.getBackground()) {
			i.setLayoutX(-viewX*(i.getImage().getWidth() - Main.getSceneWidth())/(width-Main.getSceneWidth()));
			i.setLayoutY(-viewY*(i.getImage().getHeight() - Main.getSceneHeight())/(height-Main.getSceneHeight()));
		}
	}
	
	public void addMap(MapName name, Map map) {
		mapList.put(name, map);
	}
	
	public void addObject(GameObject object) {
		objectList.add(object);
		if (object instanceof Destroyable) {
			Destroyable destroyable = (Destroyable) object;
			destroyableList.add(destroyable);
		}
		getChildren().add(object);
		object.draw();
		object.setAlive(true);
	}
	
	public void reloadBackground() {
		getChildren().removeAll(cerrentMap.getBackground());
		getChildren().addAll(0, cerrentMap.getBackground());
	}

	public boolean isBossFight() {
		return bossFight;
	}
	
	public void setBossFight(Boolean bossFight) {
		this.bossFight = bossFight;
	}
	
	public HashMap<MapName, Map> getMapList() {
		return mapList;
	}

	public Map getCerrentMap() {
		return cerrentMap;
	}
	
	public List<Updateable> getObjectList() {
		return objectList;
	}

	public List<Destroyable> getDestroyableList() {
		return destroyableList;
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
