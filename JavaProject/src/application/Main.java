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
		Map StarterMap = new Map(2560,1440);
		StarterMap.addBackground("file:image/Map/Eclipse_Sky.jpg",1920,1080);
		StarterMap.addBackground("file:image/Map/Kiln_of_the_First_Flame.png",2560,1440);
		StarterMap.addPlatform(new  SinglePlatform("file:image/Platform/Platform.png",1000,1150,290,125));
		worldMap.addMap("Starter Map", StarterMap);
		return worldMap;
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