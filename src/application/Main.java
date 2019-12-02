package application;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import item.ControlInventory;
import map.World;
import menu.MainMenu;
import object.Hero;

public class Main extends Application {
	private static double sceneWidth, sceneHeight;
	public static Stage stage;
	public static Scene gameScene;
	public static Group root;
	public static ControlInventory controlInventory = new ControlInventory();
	public static Hero hero;
	public static World world;
	
	public static final double defaultWidth = 1000;
	public static final double defaultHeight = 600;
	private static final double barHeigth = 30;

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
	
	public static void updateSceneSize() {
		sceneWidth = stage.getWidth();
		if (stage.isFullScreen()) {
			sceneHeight = stage.getHeight();
		} else {
			sceneHeight = stage.getHeight() - barHeigth;
		}
	}

	public static double getSceneWidth() {
		return sceneWidth;
	}

	public static double getSceneHeight() {
		return sceneHeight;
	}

}