package application;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import object.Enemy;

public class Controller {
	
	private static AnimationTimer gameLoop;
	private static boolean left, right, down, jump, dash;
	private static int direction;
	
	public static void startTimer() {
		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				Main.setSceneWidth(Main.cerrentStage.getWidth());
				if(Main.cerrentStage.isFullScreen()) {
					Main.setSceneHeight(Main.cerrentStage.getHeight());
				}else {
					Main.setSceneHeight(Main.cerrentStage.getHeight() - 30);
				}
				updateHero();
				updateEnemy();
			}
		};
		gameLoop.start();
	}
	
	public static void setKey() {
		Main.cerrentStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = true;
					break;
				case RIGHT: 
					right = true;
					break;
				case DOWN:  
					down = true;
					break;
				case A:    
					jump = true;
					break;
				case D:
					dash = true;
					break;
				default:	   
					break;
				}
			}
		});
		Main.cerrentStage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override 
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case LEFT:  
					left = false;
					break;
				case RIGHT: 
					right = false; 
					break;
				case DOWN:  
					down = false; 
					break;
				case A:    
					jump = false;
					break;
				case D:
					dash = false;
					break;
				default:	   
					break;
				}
			}
		});
	}
	
	private static void updateHero(){
		direction = 0;
		if (left) {
			direction += -1;
			if(!right) {
				Main.hero.setTurnLeft(true);
			}
		}
		if (right) {
			direction += 1;
			if(!left) {
				Main.hero.setTurnLeft(false);
			}
		}
		Main.hero.setMovement(direction);
		if (down) {
			Main.hero.diving();
		}else {
			Main.hero.setFallSpeedLimit(true);
		}
		if (jump) {
			Main.hero.jumping();
		}else {
			Main.hero.stopJump();
		}
		if (dash) {
			Main.hero.dash();
		}
		Main.hero.move();
	}
	
	private static void updateEnemy(){
		for(Enemy i:Main.worldMap.getCerrentMap().getEnemyList()) {
			i.action();
		}
	}

}
