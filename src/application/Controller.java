package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import object.Updateable;

public class Controller {
	
	private static AnimationTimer gameLoop;
	private static boolean left, right, up, down, jump, attack, dash, pause;
	
	private static final double barHeigth = 30;
	private static final int LEFT_DIRECTION = -1;
	private static final int RIGHT_DIRECTION = 1;
	private static final int NO_MOVE = 0;
	
	public static void startGameLoop() {
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Main.setSceneWidth(Main.stage.getWidth());
				if (Main.stage.isFullScreen()) {
					Main.setSceneHeight(Main.stage.getHeight());
				} else {
					Main.setSceneHeight(Main.stage.getHeight() - barHeigth);
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
				case SPACE:
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
				case SPACE:
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
//						Main.controlInventory.getInventory().getChildren().clear();
						Main.root.getChildren().remove(Main.controlInventory);
						Main.world.reloadBackground();
					} else {
						pause = true;
						Main.root.getChildren().add(Main.controlInventory);
//						Main.controlInventory.getInventory().MyInventoryUpdate();
					}
					break;
				default:
				}
			}
		});
	}
	
	private static void updateHero(){
		if (!pause) {
			if (left && !right) {
				Main.hero.walk(LEFT_DIRECTION);
				Main.hero.turn(true);
			} else if (right && !left) {
				Main.hero.walk(RIGHT_DIRECTION);
				Main.hero.turn(false);
			} else {
				Main.hero.walk(NO_MOVE);
			}
			if (jump) {
				Main.hero.jumping();
			} else {
				Main.hero.stopJump();
			}
			if (dash) {
				Main.hero.dash();
			}
			if (attack) {
				if (down && !up) {
					Main.hero.downwardSlash();
				} else if (up && !down){
					Main.hero.upperSlash();
				} else {
					Main.hero.frontSlash();
				}
			}
		} else {
			Main.hero.walk(NO_MOVE);
			Main.hero.stopJump();
		}
		Main.hero.update();
	}
	
	private static void updateObject(){
		for (Updateable updateable: new ArrayList<Updateable>(Main.world.getObjectList())) {
			updateable.update();
		}
	}
	
}
