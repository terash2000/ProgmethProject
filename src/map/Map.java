package map;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import application.Music;
import object.Enemy;
import object.GamePlatform;

public class Map {
	
	private List<ImageView> background = new ArrayList<ImageView>();
	private List<GamePlatform> platformList = new ArrayList<GamePlatform>();
	private List<Enemy> enemyList = new ArrayList<Enemy>();
	private Gate leftMap, rightMap, upperMap, lowerMap;
	private Music music;
	private double width, height;
	private boolean darkArea;
	
	public Map(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public void addBackground(String backgroundImagePath, double width, double height) {
		Image backgroundImage = new Image(backgroundImagePath, width, height, false, true);
		background.add(new ImageView(backgroundImage));
	}
	
	public List<ImageView> getBackground() {
		return background;
	}
	
	public void addPlatform(GamePlatform platform) {
		platformList.add(platform);
	}
	
	public List<GamePlatform> getPlatformList() {
		return platformList;
	}
	
	public void addEnemy(Enemy enemy) {
		enemyList.add(enemy);
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public Gate getLeftMap() {
		return leftMap;
	}

	public void setLeftMap(Gate leftMap) {
		this.leftMap = leftMap;
	}

	public Gate getRightMap() {
		return rightMap;
	}

	public void setRightMap(Gate rightMap) {
		this.rightMap = rightMap;
	}

	public Gate getUpperMap() {
		return upperMap;
	}

	public void setUpperMap(Gate upperMap) {
		this.upperMap = upperMap;
	}

	public Gate getLowerMap() {
		return lowerMap;
	}

	public void setLowerMap(Gate lowerMap) {
		this.lowerMap = lowerMap;
	}
	
	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public boolean isDarkArea() {
		return darkArea;
	}

	public void setDarkArea(boolean darkArea) {
		this.darkArea = darkArea;
	}
}
