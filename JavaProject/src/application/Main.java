package application;

import character.*;
import object.*;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;
	private static Group game = new Group();
	private static Group HpBar = new Group();

	public static void main(String[] args) { 
		launch(args); 
	}
  
	@Override 
	public void start(Stage stage) throws Exception {
		Hero hero = new Hero();
		WorldMap worldMap = createWorld();
		worldMap.getMapList().get("Starter Map").setCerrentMap(hero,500,100);
		Scene scene = new Scene(new Group(game, HpBar), 1000, 600);
		HpBar.setLayoutX(50);
		HpBar.setLayoutY(50);
		stage.setScene(scene);
		Controller.setKey(stage);
		Controller.addTimer(hero);
		stage.setTitle("MyGame");
		stage.show();
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
	}
	
	public static WorldMap createWorld() {
		WorldMap worldMap = new WorldMap();
		createStarterMap(worldMap);
		return worldMap;
	}
	
	public static void createStarterMap(WorldMap worldMap) {
		Map map = new Map(2560,1440);
		map.addBackground("file:image/Map/Eclipse_Sky.jpg",1920,1080);
		map.addBackground("file:image/Map/Kiln_of_the_First_Flame.png",2080,1240);
		map.addPlatform(new  Platform("file:image/Platform/Platform1.png",700,670,174,75));
		map.addPlatform(new  Platform("file:image/Platform/Platform1.png",1000,880,174,75));
		map.addPlatform(new  Platform("file:image/Platform/Platform1.png",1200,570,174,75));
		map.addPlatform(new  Platform("file:image/Platform/Platform2.png",600,420,79,47));
		map.addPlatform(new  Platform("file:image/Platform/PlatformFloor2.jpg",0,1261,2560,218,true,false));
		map.addPlatform(new  Platform("file:image/Platform/PlatformLeft1.jpg",0,0,200,1370,false,true));
		map.addPlatform(new  Platform("file:image/Platform/PlatformLeft1.jpg",2360,0,200,1370,false,true,true,false));
		map.addEnemy(new Glimback(1500,1000));
		worldMap.addMap("Starter Map", map);
	}

	public static Group getGame() {
		return game;
	}

	public static void setHpBar(Group hpBar) {
		HpBar = hpBar;
	}

	public static double getSceneWidth() {
		return sceneWidth;
	}

	public static void setSceneWidth(double sceneWidth) {
		Main.sceneWidth = sceneWidth;
	}

	public static double getSceneHeight() {
		return sceneHeight;
	}

	public static void setSceneHeight(double sceneHeight) {
		Main.sceneHeight = sceneHeight;
	}

}