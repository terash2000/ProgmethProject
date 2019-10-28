package application;

import monster.Glimback;
import object.Hero;
import object.Platform;
import map.*;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;
	public static Stage cerrentStage;
	public static Group game = new Group();
	public static Group HpBar = new Group();
	public static World worldMap;
	public static Hero hero;

	public static void main(String[] args) { 
		launch(args); 
	}
  
	@Override 
	public void start(Stage stage) throws Exception {
		cerrentStage = stage;
		hero = new Hero();
		createWorld();
		worldMap.setCerrentMap(MapName.Starter, 500, 100);
		Scene scene = new Scene(new Group(game, HpBar), 1000, 600);
		HpBar.setLayoutX(50);
		HpBar.setLayoutY(50);
		stage.setScene(scene);
		Controller.setKey();
		stage.setTitle("MyGame");
		stage.show();
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		Controller.startTimer();
	}
	
	public static void createWorld() {
		worldMap = new World();
		createStarterMap();
		createTownMap();
	}
	
	public static void createStarterMap() {
		Map map = new Map(2560,1440);
		map.setRightMap(new Gate(MapName.Town, 20, 1175));
		map.addBackground("file:image/Map/Eclipse_Sky.jpg",1920,1080);
		map.addBackground("file:image/Map/Kiln_of_the_First_Flame.png",2080,1240);
		map.addPlatform(new  Platform("file:image/Platform/Platform1.png",700,670,174,75));
		map.addPlatform(new  Platform("file:image/Platform/Platform1.png",1000,880,174,75));
		map.addPlatform(new  Platform("file:image/Platform/Platform1.png",1200,570,174,75));
		map.addPlatform(new  Platform("file:image/Platform/Platform2.png",600,420,79,47));
		map.addPlatform(new  Platform("file:image/Platform/PlatformFloor1.jpg",0,1260,2560,218,true,false));
		map.addPlatform(new  Platform("file:image/Platform/PlatformLeft1.jpg",0,0,200,1370,false,true));
		map.addPlatform(new  Platform("file:image/Platform/PlatformLeft1.jpg",2360,0,200,1120,false,true,true,false));
		worldMap.addMap(MapName.Starter, map);
	}
	
	public static void createTownMap() {
		Map map = new Map(2560,1440);
		map.setLeftMap(new Gate(MapName.Starter, 2460, 1175));
		map.addBackground("file:image/Map/Sky.jpg",1920,1080);
		map.addBackground("file:image/Map/Castle.png",2080,1240);
		map.addPlatform(new  Platform("file:image/Platform/Platform1.png",1000,880,174,75));
		map.addPlatform(new  Platform("file:image/Platform/Platform2.png",1200,620,79,47));
		map.addPlatform(new  Platform("file:image/Platform/PlatformFloor2.jpg",0,1260,2560,218,true,false));
		map.addPlatform(new  Platform("file:image/Platform/PlatformLeft1.jpg",0,0,200,1120,false,true));
		map.addPlatform(new  Platform("file:image/Platform/PlatformLeft1.jpg",2360,0,200,1370,false,true,true,false));
		map.addEnemy(new Glimback(1000,1080));
		worldMap.addMap(MapName.Town, map);
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