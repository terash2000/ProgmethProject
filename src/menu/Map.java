package menu;

import application.Main;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Enemy;
import object.Platform;

public class Map {
	
	protected WorldMap worldMap;
	protected List<ImageView> background = new ArrayList<ImageView>();
	protected List<Platform> platformList = new ArrayList<Platform>();
	protected List<Enemy> defaultEnemyList = new ArrayList<Enemy>(); 
	protected List<Enemy> enemyList = new ArrayList<Enemy>(); 
	protected double width, height, viewX, viewY;
	
	public Map(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public void addBackground(String backgroundImagePath, double width, double height) {
		Image backgroundImage = new Image(backgroundImagePath, width, height, false, true);
		background.add(new ImageView(backgroundImage));
	}
	
	public void addPlatform(Platform platform) {
		platformList.add(platform);
	}
	
	public void addEnemy(Enemy enemy) {
		defaultEnemyList.add(enemy);
	}
	
	public void setAsCerrentMap(double x, double y) {
		Main.worldMap.setCerrentMap(this);
		Main.game.getChildren().clear();
		Main.game.getChildren().addAll(background);
		
		for(Platform i:platformList) {
			Main.game.getChildren().add(i.getBody());
		}
		
		enemyList.clear();
		for(Enemy i:defaultEnemyList) {
			enemyList.add(i);
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
		for(ImageView i:background) {
			i.setLayoutX(-viewX*(i.getImage().getWidth()-Main.getSceneWidth())/(width-Main.getSceneWidth()));
			i.setLayoutY(-viewY*(i.getImage().getHeight()-Main.getSceneHeight())/(height-Main.getSceneHeight()));
		}
		for(Platform i:platformList) {
			i.changeView();
		}
		for(Enemy i:enemyList) {
			i.changeView();
		}
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public List<Platform> getPlatformList() {
		return platformList;
	}

	public void setWorldMap(WorldMap worldMap) {
		this.worldMap = worldMap;
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
