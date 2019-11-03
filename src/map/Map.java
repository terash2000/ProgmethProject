package map;

import application.Music;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import object.Enemy;
import object.Platform;

public class Map {
	
	private List<ImageView> background = new ArrayList<ImageView>();
	private List<Platform> platformList = new ArrayList<Platform>();
	private List<Enemy> enemyList = new ArrayList<Enemy>();
	private double width, height;
	private Gate leftMap, rightMap, upperMap, lowerMap;
	private Music music;
	
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
		enemyList.add(enemy);
	}

	public List<ImageView> getBackground() {
		return background;
	}

	public List<Platform> getPlatformList() {
		return platformList;
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

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
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

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

}
