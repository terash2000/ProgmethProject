package object;

import application.Main;
import character.Hero;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {
	
	protected WorldMap worldMap;
	protected List<ImageView> background = new ArrayList<ImageView>();
	protected List<Platform> platformList = new ArrayList<Platform>();
	protected double width, height, viewX, viewY;
	
	private double gravity = 1;
	
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
	
	public Group setCerrentMap(Hero hero, double x, double y) {
		Group group = new Group();
		group.getChildren().addAll(background);
		group.getChildren().add(hero.getBody());
		setHeroLocation(hero, x, y);
		setPlatform(group);
		hero.setMap(this);
		return group;
	}
	
	public void setHeroLocation(Hero hero, double x, double y) {
		hero.setX(x);
		hero.setY(y);
		changeView(hero);
		hero.getBody().setLayoutX(x - viewX);
		hero.getBody().setLayoutX(y - viewY);
	}
	
	public void setPlatform(Group group) {
		for(Platform i:platformList) {
			group.getChildren().add(i.getBody());
		}
	}
	
	public void changeView(Hero hero) {
		double x = hero.getX() + hero.getSize()[0]/2;
		double y = hero.getY() + hero.getSize()[1]/2;
		viewX = x < Main.getSceneWidth()/2 ? 0 : 
			(x > width-Main.getSceneWidth()/2 ? width-Main.getSceneWidth() : x-Main.getSceneWidth()/2);
		viewY = y < Main.getSceneHeight()/2 ? 0 : 
			(y > height-Main.getSceneHeight()/2 ? height-Main.getSceneHeight() : y-Main.getSceneHeight()/2);
		for(ImageView i:background) {
			i.setLayoutX(-viewX*(i.getImage().getWidth()-Main.getSceneWidth())/(width-Main.getSceneWidth()));
			i.setLayoutY(-viewY*(i.getImage().getHeight()-Main.getSceneHeight())/(height-Main.getSceneHeight()));
		}
		for(Platform i:platformList) {
			i.getBody().setLayoutX(i.getPosition()[0][0] - viewX);
			i.getBody().setLayoutY(i.getPosition()[0][1] - viewY);
		}
	}

	public double getGravity() {
		return gravity;
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
