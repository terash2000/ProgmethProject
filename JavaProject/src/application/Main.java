package application;

import character.Hero;
import object.*;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;

	public static void main(String[] args) { 
		launch(args); 
	}
  
	@Override 
	public void start(Stage stage) throws Exception {
		Hero hero = new Hero();
		WorldMap worldMap = createWorld();
		Group group = worldMap.getMapList().get("Starter Map").setCerrentMap(hero,500,100);;
		Scene scene = new Scene(group, 1000, 600);
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
		map.addPlatform(new  SinglePlatform("file:image/Platform/Platform.png",700,670,174,75));
		map.addPlatform(new  SinglePlatform("file:image/Platform/Platform.png",1000,1130,174,75));
		map.addPlatform(new  SinglePlatform("file:image/Platform/Platform.png",1200,770,174,75));
		worldMap.addMap("Starter Map", map);
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