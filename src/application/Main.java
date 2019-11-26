package application;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import item.Inventory;
import map.World;
import menu.HeroHpBar;
import menu.MainMenu;
import object.Hero;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;
	public static Stage stage;
	public static Scene gameScene;
	public static Group root;
	public static HeroHpBar hpBar = new HeroHpBar();
	public static Inventory inventory = new Inventory();
	public static Hero hero;
	public static World world;
	
	public static final double defaultWidth = 1000;
	public static final double defaultHeight = 600;

	public static void main(String[] args) { 
		launch(args); 
	}
  
	@Override 
	public void start(Stage primaryStage) throws Exception {
		Scene mainMenuScene = new Scene(new MainMenu(), defaultWidth, defaultHeight);
		stage = primaryStage;
		stage.setScene(mainMenuScene);
		stage.setTitle("Hollow Knight");
		stage.show();
		stage.setFullScreenExitHint("");
		Sound.changeBackgroundMusic(Music.Friend_shitai);
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