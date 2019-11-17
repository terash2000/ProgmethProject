package application;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import item.Inventory;
import map.MapCreater;
import map.MapName;
import map.World;
import menu.HeroHpBar;
import object.Hero;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;
	public static Stage stage;
	public static Scene gameScene;
	public static Group root;
	public static Group game = new Group();
	public static HeroHpBar hpBar = new HeroHpBar();
	public static Inventory inventory = new Inventory();
	public static Hero hero = new Hero();
	public static World world;

	public static void main(String[] args) { 
		launch(args); 
	}
  
	@Override 
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Hollow Knight");
		stage.show();
		stage.setFullScreenExitHint("");
		newGame();
	}
	
	public static void newGame() {
		world = MapCreater.createWorld();
		world.setCerrentMap(MapName.Starter, 500, 1175);
		root = new Group(game, hpBar);
		gameScene = new Scene(root, 1000, 600);
		
		stage.setScene(gameScene);
		stage.setFullScreen(true);
		Controller.setGameKey();
		Controller.startGameLoop();
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