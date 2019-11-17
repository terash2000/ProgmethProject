package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import object.Updateable;

public class Controller {
	
	private static AnimationTimer gameLoop;
	private static boolean left, right, up, down, jump, attack, dash, pause;
	
	public static void startGameLoop() {
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Main.setSceneWidth(Main.stage.getWidth());
				if (Main.stage.isFullScreen()) {
					Main.setSceneHeight(Main.stage.getHeight());
				} else {
					Main.setSceneHeight(Main.stage.getHeight() - 30);
				}
				updateHero();
				updateObject();
			}
		};
		gameLoop.start();
	}
	
	public static void setGameKey() {
		Main.gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = true;
					break;
				case RIGHT: 
					right = true;
					break;
				case UP:
					up = true;
					break;
				case DOWN:  
					down = true;
					break;
				case A:    
					jump = true;
					break;
				case S:
					attack = true;
					break;
				case D:
					dash = true;
					break;
				default:	   
				}
			}
		});
		Main.gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = false;
					break;
				case RIGHT: 
					right = false; 
					break;
				case UP:
					up = false;
					break;
				case DOWN:  
					down = false; 
					break;
				case A:    
					jump = false;
					break;
				case S:
					attack = false;
					break;
				case D:
					dash = false;
					break;
				case I:
					if (pause) {
						pause = false;
						Main.root.getChildren().remove(Main.inventory);
						Main.inventory.getChildren().clear();
						Main.world.reloadBackground();
					} else {
						pause = true;
						Main.root.getChildren().add(Main.inventory);
						Main.inventory.update();
					}
					break;
				default:
				}
			}
		});
	}
	
	private static void updateHero(){
		if (left && !right && !pause) {
			Main.hero.walk(-1);
			Main.hero.turn(true);
		} else if (right && !left && !pause) {
			Main.hero.walk(1);
			Main.hero.turn(false);
		} else {
			Main.hero.walk(0);
		}
		if (jump && !pause) {
			Main.hero.jumping();
		} else {
			Main.hero.stopJump();
		}
		if (dash && !pause) {
			Main.hero.dash();
		}
		if (attack && !pause) {
			if (down && !up) {
				Main.hero.downwardSlash();
			} else if (up && !down){
				Main.hero.upperSlash();
			} else {
				Main.hero.frontSlash();
			}
		}
		Main.hero.update();
	}
	
	private static void updateObject(){
		for (Updateable updateable: new ArrayList<Updateable>(Main.world.getObjectList())) {
			updateable.update();
		}
	}
	
}
