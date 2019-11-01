package application;

import monster.*;
import object.Hero;
import object.Platform;
import map.*;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;
	public static Stage stage;
	public static Scene gameScene;
	public static Group game = new Group();
	public static Group HpBar = new Group();
	public static Group inventory = new Group();
	public static World worldMap;
	public static Hero hero;

	public static void main(String[] args) { 
		launch(args); 
	}
  
	@Override 
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		hero = new Hero();
		createWorld();
		worldMap.setCerrentMap(MapName.Starter, 500, 100);
		gameScene = new Scene(new Group(game, HpBar, inventory), 1000, 600);
		HpBar.setLayoutX(50);
		HpBar.setLayoutY(50);
		inventory.setVisible(false);
		
		primaryStage.setScene(gameScene);
		Controller.setKey();
		primaryStage.setTitle("MyGame");
		primaryStage.show();
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(true);
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
		map.addBackground(
				ClassLoader.getSystemResource("Background/Eclipse_Sky.jpg").toString(), 1920, 1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Kiln_of_the_First_Flame.png").toString(), 2080, 1240);
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/Platform1.png").toString(), 700, 670, 174, 75));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/Platform1.png").toString(), 1000, 880, 174, 75));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/Platform1.png").toString(), 1200, 570, 174, 75));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/Platform2.png").toString(), 600, 420, 79, 47));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/PlatformFloor1.jpg").toString(), 0, 1260, 2560, 218, true, false));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/PlatformLeft1.jpg").toString(), 0, 0, 200, 1370, false, true));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/PlatformLeft1.jpg").toString(), 2360, 0, 200, 1020, false, true, true, true));
		map.addEnemy(new Glimback(1000, 1080));
		map.addEnemy(new Vengefly(1500, 500));
		map.addEnemy(new Vengefly(1700, 1000));
		map.addEnemy(new Vengefly(1400, 1200));
		worldMap.addMap(MapName.Starter, map);
	}
	
	public static void createTownMap() {
		Map map = new Map(2560,1440);
		map.setLeftMap(new Gate(MapName.Starter, 2460, 1175));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Sky.jpg").toString(),1920,1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Castle.png").toString(), 2080, 1240);
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/PlatformFloor2.jpg").toString(), 0, 1260, 2560, 218, true, false));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/PlatformLeft1.jpg").toString(), 0, 0, 200, 1100, false, true, false, true));
		map.addPlatform(new  Platform(
				ClassLoader.getSystemResource("Platform/PlatformLeft1.jpg").toString(), 2360, 0, 200, 1370, false, true, true, false));
		map.addEnemy(new False_Knight(1000, 1010));
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