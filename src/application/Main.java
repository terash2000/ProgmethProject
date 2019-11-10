package application;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.*;
import monster.*;
import object.Hero;
import object.Platform;
import object.PlatformType;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;
	public static Stage stage;
	public static Scene gameScene;
	public static Group game = new Group();
	public static StackPane HpBar = new StackPane();
	public static Group inventory = new Group();
	public static World world;
	public static Hero hero;

	public static void main(String[] args) { 
		launch(args); 
	}
  
	@Override 
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("MyGame");
		stage.show();
		stage.setFullScreenExitHint("");
		newGame();
	}
	
	public static void newGame() {
		hero = new Hero();
		createWorld();
		world.setCerrentMap(MapName.Starter, 500, 1175);
		gameScene = new Scene(new Group(game, HpBar, inventory), 1000, 600);
		HpBar.setLayoutX(50);
		HpBar.setLayoutY(50);
		inventory.setVisible(false);
		
		stage.setScene(gameScene);
		stage.setFullScreen(true);
		Controller.setGameKey();
		Controller.startGameLoop();
	}
	
	public static void createWorld() {
		world = new World();
		createStarterMap();
		createFalseKnightRoom();
		createTownMap();
		createCaveMap();
	}
	
	public static void createStarterMap() {
		Map map = new Map(2560,1440);
		map.setRightMap(new Gate(MapName.False_Knight_room, 20, 1015));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Eclipse_Sky.jpg").toString(), 1920, 1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Kiln_of_the_First_Flame.png").toString(), 2080, 1240);
		map.addPlatform(new  Platform(PlatformType.Platform1, 700, 670));
		map.addPlatform(new  Platform(PlatformType.Platform1, 1000, 880));
		map.addPlatform(new  Platform(PlatformType.Platform1, 1500, 570));
		map.addPlatform(new  Platform(PlatformType.Platform2, 500, 420));
		map.addPlatform(new  Platform(PlatformType.FloorPlatform1, 0, 1260, 2560, 218));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, 0, 0, 200, 1370));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, 2410, 0, 200, 1020, true, true));
		map.addEnemy(new Vengefly(400, 200));
		map.addEnemy(new Vengefly(1900, 600));
		map.addEnemy(new Vengefly(1700, 900));
		map.setMusic(Music.Friend_shitai);
		world.addMap(MapName.Starter, map);
	}
	
	public static void createFalseKnightRoom() {
		Map map = new Map(2240,1260);
		map.setLeftMap(new Gate(MapName.Starter, 2460, 1175));
		map.setRightMap(new Gate(MapName.Town, 20, 1175));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Sky.jpg").toString(), 1920, 1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Castle.png").toString(), 2080, 1240);
		map.addPlatform(new  Platform(PlatformType.FloorPlatform2, 0, 1100, 2240, 218));
		map.addPlatform(new  Platform(PlatformType.SidePlatform2, 0, 0, 100, 900));
		map.addPlatform(new  Platform(PlatformType.SidePlatform2, 2140, 0, 100, 900, true, false));
		map.addEnemy(new False_Knight(800, -1000));
		map.setMusic(Music.Friend_shitai);
		world.addMap(MapName.False_Knight_room, map);
	}
	
	public static void createTownMap() {
		Map map = new Map(2560,1440);
		map.setLeftMap(new Gate(MapName.False_Knight_room, 2140, 1015));
		map.setLowerMap(new Gate(MapName.Cave, 250, 0));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Cloud.jpg").toString(), 1920, 1080);
		map.addBackground(
				ClassLoader.getSystemResource("Background/Archdragon_Peak.png").toString(), 2080, 1240);
		map.addPlatform(new  Platform(PlatformType.FloorPlatform1, 0, 1260, 2160, 218));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, -50, 0, 200, 1020, false, true));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, 2360, 0, 200, 1440, true, false));
		map.setMusic(Music.Friend_shitai);
		world.addMap(MapName.Town, map);
	}
	
	public static void createCaveMap() {
		Map map = new Map(1800,2400);
		map.setUpperMap(new Gate(MapName.Town, 2220, 1440));
		map.addBackground(
				ClassLoader.getSystemResource("Background/Cave.jpg").toString(), 1500, 2000);
		map.addPlatform(new  Platform(PlatformType.Platform1, 800, 1900));
		map.addPlatform(new  Platform(PlatformType.Platform1, 1300, 1600));
		map.addPlatform(new  Platform(PlatformType.Platform1, 1000, 880));
		map.addPlatform(new  Platform(PlatformType.Platform1, 600, 1100));
		map.addPlatform(new  Platform(PlatformType.Platform1, 700, 500));
		map.addPlatform(new  Platform(PlatformType.Platform2, 1000, 1400));
		map.addPlatform(new  Platform(PlatformType.Platform2, 1300, 700));
		map.addPlatform(new  Platform(PlatformType.Platform2, 400, 1800));
		map.addPlatform(new  Platform(PlatformType.FloorPlatform1, 0, 2200, 1800, 218));
		map.addPlatform(new  Platform(PlatformType.FloorPlatform1, 400, -100, 1800, 218, true, true));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, 0, 0, 200, 1400, false, true));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, 0, 1600, 200, 730));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, 180, 400, 250, 500));
		map.addPlatform(new  Platform(PlatformType.SidePlatform1, 1600, 0, 200, 2330, true, false));
		map.addEnemy(new Vengefly(1100, 1600));
		map.addEnemy(new Vengefly(1300, 1000));
		map.addEnemy(new Glimback(1300, 2020));
		map.setMusic(Music.Friend_shitai);
		map.setDarkArea(true);
		world.addMap(MapName.Cave, map);
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